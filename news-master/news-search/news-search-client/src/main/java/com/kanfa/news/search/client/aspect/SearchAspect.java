package com.kanfa.news.search.client.aspect;

import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.util.CovertLiveNum;
import com.kanfa.news.common.util.DateUtil;
import com.kanfa.news.search.client.entity.*;
import com.kanfa.news.search.client.feign.*;
import com.kanfa.news.search.client.vo.ChannelContentCardInfo;
import com.kanfa.news.search.client.vo.ContentInfo;
import com.kanfa.news.search.client.vo.LiveAddInfo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/6/7 18:49
 */
@Aspect
@Configuration
@Component
public class SearchAspect {
    @Autowired
    private ISearchServiceFeign iSearchServiceFeign;

    @Autowired
    private IContentServiceFeign iContentServiceFeign;

    @Autowired
    private ILiveServiceFeign iLiveServiceFeign;

    @Autowired
    private ILiveTypeServiceFeign iLiveTypeServiceFeign;

    @Autowired
    private IChannelContentFeign iChannelContentFeign;

    private String PASS_CHECK_STATUS = "1";
    private String CONTENT_INDEX = "kf_news_dev_content";
    private String LIVE_INDEX = "kf_news_dev_live";
    private String CONTENT_TYPE = "kf_content";
    private String LIVE_TYPE = "kf_live";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 返回通知：目标方法正常执行完毕时执行以下代码
     * @param jp
     * @param result
     */
    @AfterReturning(value="execution(* com.kanfa.news.admin.rpc.CheckContentRest.*(..)) " +
                        "|| execution(* com.kanfa.news.admin.rpc.ContentRest.*(..))" +
                        "|| execution(* com.kanfa.news.admin.rpc.LiveRest.*(..))" +
                        "|| execution(* com.kanfa.news.admin.rpc.LiveTypeRest.*(..))" +
            "",returning="result")
    public void afterReturningMethod(JoinPoint jp, Object result){
        String methodName = jp.getSignature().getName();
        //访问目标方法的参数：
        Object[] args = jp.getArgs();
        //审核
        if (methodName.equals("checkPass")){
            Integer id = (Integer)args[1];
            Integer checkStatus = (Integer)args[2];
            Integer fromType = (Integer)args[3];
            if (NewsEnumsConsts.ChannelContentOfFromType.Content.key().intValue() == fromType &&  PASS_CHECK_STATUS.equals(checkStatus.toString())) {
                contentToEs(id);
            } else if( PASS_CHECK_STATUS.equals(checkStatus)){
                liveToEs(id);
            }
        }
        //非直播修改
/*       else if (methodName.equals("update")){
            ContentInfo contentInfo = new ContentInfo();
            BeanUtils.copyProperties(args[0],contentInfo);
            contentToEs(contentInfo.getId());
        }*/
        //非直播删除、我的内容，删除时，删除相应的
        else if(methodName.equals("batchDelete")){
            Map<String, List<Integer>> params = (Map<String, List<Integer>>)args[0];
            List<Integer> ids = params.get("ids");
            if(null != ids){
                deleteContentFromEsByIds(ids);
            }
        }
        //非直播发布或取消发布
        else if(methodName.equals("publish")){
            Integer channelId = (Integer)args[0];
            Integer contentId = (Integer)args[1];
            Integer isPublish = (Integer)args[2];
            if(1 == isPublish){
                //发布
                contentToEs(contentId);
            }else{
                //全部标签中  取消发布
                if(null  == channelId){
                    //删除对应频道的ES中相应的数据
                    iSearchServiceFeign.deleteDataById(CONTENT_INDEX, CONTENT_TYPE, contentId);
                }else{

                    //某一具体频道中  取消发布
                    //删除对应频道的ES中相应的数据
                    iSearchServiceFeign.deleteDataByChannelIdAndId(CONTENT_INDEX, CONTENT_TYPE, channelId,contentId);
                }
            }
        }
        //非直播 频道样式修改
        else if(methodName.equals("updateCardType")){
            ChannelContentCardInfo channelContentCardInfo = new ChannelContentCardInfo();
            BeanUtils.copyProperties(args[0],channelContentCardInfo);

            Map<String, Object> sourceMap = new HashMap<String, Object>(1);
            sourceMap.put("id",channelContentCardInfo.getContentId());
            sourceMap.put("chan_id",channelContentCardInfo.getChannelId());
            sourceMap.put("card_type",channelContentCardInfo.getCardType());
            sourceMap.put("image",channelContentCardInfo.getId());
            sourceMap.put("images",channelContentCardInfo.getImageList());

            Map<String, Object> conditionMap = new HashMap<String, Object>(1);
            conditionMap.put("id",channelContentCardInfo.getContentId());
            conditionMap.put("chan_id",channelContentCardInfo.getChannelId());

            Map<String, Object> conditionSourceMap = new HashMap<String, Object>(2);
            conditionSourceMap.put("conditionMap", map2String(conditionMap));
            conditionSourceMap.put("sourceMap", map2String(sourceMap));
            iSearchServiceFeign.updateDataByCondition(CONTENT_INDEX, CONTENT_TYPE, conditionSourceMap);
        }

        //直播修改，修改相应的ES数据
        else if(methodName.equals("updateLiveInfo")){
            LiveAddInfo liveAddInfo = new LiveAddInfo();
            BeanUtils.copyProperties(args[0],liveAddInfo);
            liveToEs(liveAddInfo.getId());
        }
        //直播类型删除时、直播类型下线时，删除相应的ES数据
        else if(methodName.equals("deleteLive") || methodName.equals("cancelPublishLive")){
            Integer liveId = (Integer)args[0];
            //删除ES中相应的数据
            iSearchServiceFeign.deleteDataById(LIVE_INDEX, LIVE_TYPE, liveId);
        }
        //直播上线
        else if(methodName.equals("onPublicLive") ){
            Integer liveId = (Integer)args[0];
            //添加相应的数据到ES
            liveToEs(liveId);
        }
/*
        boolean ifLiveTypeUpdate = request.getRequestURI().contains(END_WITH_LIVETYPE_UPDATE);

        boolean ifLiveTypePublish = request.getRequestURI().contains(END_WITH_LIVETYPE_PUBLISH);

        boolean ifLiveTypeCancelPublish = request.getRequestURI().contains(END_WITH_LIVETYPE_CANCELPUBLISH);
        */
        //直播类型 标签数据修改时，修改相应的ES数据
        else if(methodName.equals("updateLiveType")){
            Integer liveTypeId = (Integer)args[0];
            LiveType liveType = new LiveType();
            BeanUtils.copyProperties(args[1],liveType);
            Map<String, Object> sourceMap = new HashMap<String, Object>(1);
            sourceMap.put("liveTypeName", liveType.getName());

            Map<String, Object> conditionMap = new HashMap<String, Object>(1);
            conditionMap.put("liveTypeId", liveTypeId);
            Map<String, Object> conditionSourceMap = new HashMap<String, Object>(2);
            conditionSourceMap.put("conditionMap", map2String(conditionMap));
            conditionSourceMap.put("sourceMap", map2String(sourceMap));
            iSearchServiceFeign.updateDataByCondition(LIVE_INDEX, LIVE_TYPE, conditionSourceMap);
        }
        //直播类型 标签上、下线时，修改相应的ES数据
        else if(methodName.equals("cancelPublishLiveType") || methodName.equals("publishLiveType")){
            Integer liveTypeId = (Integer)args[0];
            LiveType liveType = new LiveType();
            BeanUtils.copyProperties(iLiveTypeServiceFeign.get(liveTypeId).getData(),liveType);
            Map<String, Object> sourceMap = new HashMap<String, Object>(1);
            sourceMap.put("liveTypeStatus", liveType.getIsPublish());

            Map<String, Object> conditionMap = new HashMap<String, Object>(1);
            conditionMap.put("liveTypeId", liveTypeId);
            Map<String, Object> conditionSourceMap = new HashMap<String, Object>(2);
            conditionSourceMap.put("conditionMap", map2String(conditionMap));
            conditionSourceMap.put("sourceMap", map2String(sourceMap));
            iSearchServiceFeign.updateDataByCondition(LIVE_INDEX, LIVE_TYPE, conditionSourceMap);
        }
    }


    public void deleteContentFromEsByIds(List<Integer> ids) {
        int idsSize = ids.size();
        for (int i = 0; i < idsSize; i++) {
            Integer contentId = ids.get(i);
            //删除ES中相应的数据
            iSearchServiceFeign.deleteDataById(CONTENT_INDEX, CONTENT_TYPE, contentId);
        }
    }

    public void liveToEs(Integer id)   {
        //如果审核通过，则添加标题信息到ES
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        //只选取1条记录，并获取相应的channelId进行channelContentCard数据匹配cardType,image
        List<Map<String,Object>> listMap = iChannelContentFeign.selectByContentIdFromType(id,2);
        for (int i = 0; i < listMap.size(); i++) {
            Map<String, Object> resultMap = listMap.get(i);
            //直播类型
            Live live = iLiveServiceFeign.get(id).getData();
            LiveType liveType = live.getLiveTypeId() == null ? null : iLiveTypeServiceFeign.get(live.getLiveTypeId()).getData();
            if (null != live) {
                map.put("id", live.getId());
                map.put("chan_id", resultMap.get("channelId"));
                map.put("pub_time", resultMap.get("publishTime"));
                map.put("title", live.getTitle());


                //直播状态 0: 预告 1:直播中 2:回顾
                map.put("live_stat", live.getLiveStatus());
                //封面图片
                map.put("image", live.getCoverImg());
                //标签
                map.put("live_type_id", null == liveType ? null : liveType.getId());
                //标签名称
                map.put("live_type_name", null == liveType ? null : liveType.getName());
                //标签状态
                map.put("live_type_status", null == liveType ? null : liveType.getIsPublish());

                map.put("type",LiveCommonEnum.CONTENT_LIVE);
                map.put("create_time", live.getCreateTime());
                try {
                    map.put("sort_create_time", sdf.parse(resultMap.get("publishTime").toString()).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mapList.add(map);
            }
        }
        //判断ES中是否存已存在，如果存在修改相应的数据，如果不存在 ，添加相应的数据到ES中
        Object esObject = iSearchServiceFeign.searchDataById(LIVE_INDEX, LIVE_TYPE, id);
        if (null == esObject) {
            //如果不存在 ，添加相应的数据到ES中
            iSearchServiceFeign.addData(LIVE_INDEX, LIVE_TYPE, mapList);
        } else {
            //如果已存在，修改ES中相应的数据
            iSearchServiceFeign.updateDataById(LIVE_INDEX, LIVE_TYPE, mapList);
        }


    }


    public void contentToEs(Integer id) {

        //如果审核通过，则添加标题信息到ES
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        List<Map<String,Object>> listMap = iChannelContentFeign.selectByContentIdFromType(id,1);


        for (int i = 0; i < listMap.size(); i++) {
            Map<String,Object> resultMap = listMap.get(i);

            //非直播类型
            Content content = iContentServiceFeign.get(id).getData();
            if (null != content) {

                map.put("id", content.getId());
                map.put("chan_id", resultMap.get("channelId"));
                map.put("card_type", resultMap.get("cardType"));
                map.put("pub_time", resultMap.get("publishTime"));
                map.put("title", content.getTitle());

                map.put("cate", content.getCategory());
                map.put("content_type", content.getContentType());
                map.put("content_style", content.getContentStyle());
                map.put("is_adv",LiveCommonEnum.CONSTANT_ZERO);

                map.put("custom_url", content.getCustomUrl());
                //标签  1 原创 2 转载 3 抓取
                map.put("source_type", content.getSourceType());

                map.put("image", content.getImage());
                map.put("cover_img", content.getCoverImg());
                map = iContentServiceFeign.addContentsListDataForEs(map);
                // 将展现样式作为type类型来展示
                if(content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_BOOK)){
                    map.put("type",LiveCommonEnum.CONTENT_JUDGMENT);
                }else if(content.getContentStyle().equals(LiveCommonEnum.CONTENT_STYLE_ADV)) {
                    map.put("type", LiveCommonEnum.CONTENT_POST);
                }else{
                    map.put("type",content.getContentType() );
                }
                if ("1".equals(content.getSourceType().toString())) {
                    //只有原创类型有才有标签
                    Map<String, Object> tagMap = new HashMap<String, Object>(3);
                    tagMap.put("name", "原创");
                    tagMap.put("font_color", "#CD2525");
                    tagMap.put("border_color", "#CD2525");
                    List<Map<String, Object>> tagsList = new ArrayList<>();
                    tagsList.add(tagMap);
                    map.put("tags", tagsList);
                }/* else if ("2".equals(content.getSourceType().toString())) {
                    map.put("tagName", "转载");
                } else if ("3".equals(content.getSourceType().toString())) {
                    map.put("tagName", "抓取");
                }*/
                map.put("create_time", content.getCreateTime());
                try {
                    map.put("sort_create_time", sdf.parse(resultMap.get("publishTime").toString()).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mapList.add(map);

            }

        }
        //判断ES中是否存已存在，如果存在修改相应的数据，如果不存在 ，添加相应的数据到ES中
        Object esObject = iSearchServiceFeign.searchDataById(CONTENT_INDEX, CONTENT_TYPE, id);
        if (null == esObject) {
            //如果不存在 ，添加相应的数据到ES中
            iSearchServiceFeign.addData(CONTENT_INDEX, CONTENT_TYPE, mapList);
        } else {
            //如果已存在，修改ES中相应的数据
            iSearchServiceFeign.updateDataById(CONTENT_INDEX, CONTENT_TYPE, mapList);
        }

    }

    public String map2String(Map map){
        Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (Map.Entry)iterator.next();
            if(entry.getValue() instanceof Integer ){
                sb.append(entry.getKey().toString()).append( "'" ).append(null==entry.getValue()?"":entry.getValue()).append (iterator.hasNext() ? "^" : "");
            }else{
                sb.append(entry.getKey().toString()).append( "'" ).append(null==entry.getValue()?"":
                        entry.getValue().toString().substring(1, entry.getValue().toString().length() - 1)).append (iterator.hasNext() ? "^" : "");
            }

        }
        return sb.toString();
    }

}
