package com.kanfa.news.search.databatchimport.rest;


import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.search.databatchimport.entity.Content;
import com.kanfa.news.search.databatchimport.entity.Live;
import com.kanfa.news.search.databatchimport.entity.LiveType;
import com.kanfa.news.search.databatchimport.feign.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("search-databatchimport")
public class SearchDataBatchImportController  {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchDataBatchImportController.class);

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
     * 直播无论是否关联到频道，只要是已发布的，都可以查询出来
     * 所以不需要关联查询 channelContent
     * @param id
     * @return
     */
    public String liveToEs(Integer id)   {
        String tid = null;
        //如果审核通过，则添加标题信息到ES
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String,Object>();

        //只选取1条记录，并获取相应的channelId进行channelContentCard数据匹配cardType,image
//        List<Map<String,Object>> listMap = iChannelContentFeign.selectByContentIdFromType(id,2);
//        for (int i = 0; i < listMap.size(); i++) {
//            Map<String, Object> resultMap = listMap.get(i);
            //直播类型
            Live live = iLiveServiceFeign.get(id).getData();
            LiveType liveType = live.getLiveTypeId() == null ? null : iLiveTypeServiceFeign.get(live.getLiveTypeId()).getData();
            if (null != live && live.getIsPublish() == 1 && live.getIsDelete() == 0 && live.getIsLock() == 0) {
                map.put("id", live.getId());
//                map.put("chan_id", resultMap.get("channelId"));
//                map.put("pub_time", resultMap.get("publishTime"));
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
//                    map.put("sort_create_time", sdf.parse(resultMap.get("publishTime").toString()).getTime());
                    map.put("sort_create_time",  live.getUpdateTime().getTime());
                } catch (Exception e) {
//                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mapList.add(map);
            }
//        }
        //判断ES中是否存已存在，如果存在修改相应的数据，如果不存在 ，添加相应的数据到ES中
        Object esObject = iSearchServiceFeign.searchDataById(LIVE_INDEX, LIVE_TYPE, id);
        if (null == esObject) {
            //如果不存在 ，添加相应的数据到ES中
            tid = iSearchServiceFeign.addData(LIVE_INDEX, LIVE_TYPE, mapList);
        } else {
            //如果已存在，修改ES中相应的数据
            tid = iSearchServiceFeign.updateDataById(LIVE_INDEX, LIVE_TYPE, mapList);
        }
        return tid;

    }


    public String contentToEs(Integer id) {
        String tid = null;

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
            tid =  iSearchServiceFeign.addData(CONTENT_INDEX, CONTENT_TYPE, mapList);
        } else {
            //如果已存在，修改ES中相应的数据
            tid = iSearchServiceFeign.updateDataById(CONTENT_INDEX, CONTENT_TYPE, mapList);
        }
        return tid;
    }

    @GetMapping("/{typeName}/{start}/{size}")
    public String searchDataPage(@PathVariable("typeName") String typeName, @PathVariable("start") Integer start,@PathVariable("size") Integer size) {
        List<Integer> lids = null;
        int y = 1;
        int n = 1;
        StringBuilder sb = new StringBuilder();
       if(CONTENT_TYPE.equals(typeName)){
           //文章
            lids = iContentServiceFeign.listIds(start,size);
            int lidsSize = lids.size();
           for (int i = 0 ; i < lidsSize; i++ ) {
               String tid = contentToEs(lids.get(i));
               if(null != tid){

                   LOGGER.info(tid+":===================成功导入:"+y+++"条");
               }else{
                   LOGGER.info(lids.get(i)+":===================失败: 第"+n+++"条");
               }
           }
        }else{
           //直播
           lids = iLiveServiceFeign.listIds(start,size);
           for (Integer id:lids) {
               String tid = liveToEs(id);
               if(null != tid){

                   LOGGER.info(tid+":===================成功导入:"+y+++"条");
               }else{
                   LOGGER.info(id+":===================失败: 第"+n+++"条");
               }
           }
       }

       return sb.append("==========成功========").append(y-1).append("条,\n==========失败========").append(n-1).append("条").toString();
    }

}