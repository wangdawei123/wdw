package com.kanfa.news.app.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.config.RequestConfiguration;
import com.kanfa.news.app.entity.Channel;
import com.kanfa.news.app.entity.ChannelCustom;
import com.kanfa.news.app.entity.ChannelFocus;
import com.kanfa.news.app.mapper.ChannelCustomMapper;
import com.kanfa.news.app.mapper.ChannelFocusMapper;
import com.kanfa.news.app.mapper.ChannelMapper;
import com.kanfa.news.app.vo.admin.app.AppChannel;
import com.kanfa.news.app.vo.admin.info.ChannelAssociateContent;
import com.kanfa.news.app.vo.admin.info.ChannelInfo;
import com.kanfa.news.app.vo.admin.info.ContentInfo;
import com.kanfa.news.app.vo.admin.live.LiveInfo;
import com.kanfa.news.app.vo.admin.video.VideoChannelAddInfo;
import com.kanfa.news.app.vo.admin.video.VideoChannelInfo;
import com.kanfa.news.app.vo.admin.vr.VRChannelAddInfo;
import com.kanfa.news.app.vo.admin.vr.VRChannelInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.AppCommonType;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 频道表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-11 16:52:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChannelBiz extends BaseBiz<ChannelMapper, Channel> {
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private ChannelCustomMapper channelCustomMapper;
    @Autowired
    private ChannelFocusMapper channelFocusMapper;
    @Resource
    private RequestConfiguration requestConfiguration;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private  ContentBiz contentBiz;
    @Autowired
    private  ContentSpecialBiz contentSpecialBiz;

    public List<ChannelInfo> selectChannlList(Channel entity) {
        List<ChannelInfo> list = channelMapper.selectChannlList(entity);
        return list;
    }

    /**
     * 添加频道并返回频道实体
     *
     * @return
     */
    public Channel insertChannelSelective(Channel entity) {
        channelMapper.insertChannelSelective(entity);
        return entity;
    }

    /**
     * getKeywordData 根据关键词获取内容]
     */
    public List<Map<String ,Object>> getKeywordData(Integer chanid ,Integer page , Integer pcount ,Integer cate ,String keyword ,Integer pub , Integer type){
        Integer offset = (page == 1)? 0 : (page - 1)* pcount;
        List<Map<String ,Object>> list = new ArrayList<>();
        List<Integer> ids = getChannelByCate(cate);
        if(ids.size() == 0){
            return null;
        }
        if(type.equals(LiveCommonEnum.SSPECIAL_TYPE_LIVE)){
            //当type ==2时
            List<LiveInfo> listType2 = channelMapper.getKeywordDataType2(chanid ,offset ,pcount ,keyword ,type ,pub);
            for(LiveInfo live :listType2){
                Map<String ,Object> map = new HashMap<>(14);
                if(live.getLiveTypeId().equals(LiveCommonEnum.LIVE_STATUS)){
                    String flashObj = live.getFlashObj();
                    if(flashObj == null){
                        map.put("type",LiveCommonEnum.CONTENT_LIVE_COURT);
                    }else{
                        map.put("type",LiveCommonEnum.CONTENT_LIVE_COURT_H5);
                    }
                }else{
                    map.put("type",LiveCommonEnum.CONTENT_LIVE);
                }
                if(live.getLiveStatus().equals(LiveCommonEnum.PRIVIEW_STATUS)){
                    //预告
                    map.put("video_source",live.getPreviewUrl());
                }else if(live.getLiveStatus().equals(LiveCommonEnum.LIVE_STATUS)){
                    //直播
                    map.put("video_source",live.getLiveUrl());
                }else{
                    //回顾
                    map.put("video_source",live.getReviewUrl());
                    String liveDuration = live.getLiveDuration();
                    String s = StringToDuration.changeToFormat(liveDuration);
                    if(s == null){
                        map.put("duration","");
                    }else{
                        map.put("duration",s);
                    }
                }
                map.put("id",live.getId());
                map.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_LIVE);
                map.put("live_stat",live.getLiveStatus()+ LiveCommonEnum.CONSTANT_ONE);
                map.put("video_num", CovertLiveNum.change(live.getViewCount()));
                map.put("preview_time", DateUtil.between(new Date(), live.getStartTime())+"后");
                map.put("pub_time", DateUtil.fromToday(live.getUpdateTime()));
                map.put("title", live.getTitle());
                map.put("image", live.getCoverImg());
                map.put("live_type_id", live.getLiveTypeId());
                map.put("flash_obj", live.getFlashObj());
                map.put("video_num", live.getViewCount());
                map.put("live_class", live.getName());
                list.add(map);
            }
        }else{
            //当type!=2时
            List<ContentInfo> listType = channelMapper.getKeywordDataType(chanid ,offset ,pcount ,keyword ,type ,pub);
            String tag_color = AppCommonType.tag_color;
            JSONObject object = JSON.parseObject(tag_color);
            String value = object.getString("value");
            JSONObject valueObject = JSON.parseObject(value);
            String contentJson = valueObject.getString("original");
            JSONObject contentObject = JSON.parseObject(contentJson);
            Map<String, Object> tags = new HashMap<>(14);
            tags.put("name","原创");
            tags.put("font_color",contentObject.getString("font_color"));
            tags.put("border_color",contentObject.getString("border_color"));

            for(ContentInfo content :listType) {
                Map<String, Object> map = new HashMap<>(14);
                List<Integer> l = new ArrayList<>();
                l.add(2);
                l.add(3);
                l.add(4);
                List<Map<String, Object>> tagslist = new ArrayList<>();
                if(content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL) && l.contains(content.getType())){
                    //文章下发原创标签
                    tags.put("name","原创");
                    tagslist.add(tags);
                    map.put("tags",tagslist);
                }
                if(content.getType().equals(LiveCommonEnum.CONTENT_VR)){
                    //活动下发活动标签
                    tags.put("name","活动");
                    tagslist.add(tags);
                    map.put("tags",tagslist);
                }
                map.put("id",content.getId());
                map.put("card_type",content.getCardType());
                map.put("type",content.getType());
                map.put("is_adv",LiveCommonEnum.CONSTANT_ZERO);
                map.put("splited",LiveCommonEnum.CONSTANT_ZERO);
                map.put("title",content.getTitle());
                map.put("image",content.getImage());
                map.put("chan_id",content.getChannelId());
                map.put("video_source",content.getCustomUrl());
                map.put("duration",content.getDuration());
                map = contentBiz.addContentsListData(map, content);

                if(content.getType().equals(LiveCommonEnum.CONTENT_SPECIAL)){
                    //专题
                    List<Map<String ,Object>> specialList = contentSpecialBiz.getIndexSpecialData(content.getId(), tags);
                    map.put("splited",LiveCommonEnum.CONSTANT_ONE);
                    map.put("content",specialList);
                }
                list.add(map);
            }
        }
        return list;
    }



    public List<Integer> getChannelByCate(Integer cate){
        List<Integer> ids = new ArrayList<>();
        List<Channel> list = channelMapper.selectChannelIds(cate);
        if(list.size() > 0){
            for(Channel channel :list){
                ids.add(channel.getId());
            }
        }
        return ids;
    }


    /**
     * 查询频道绑定的内容集合5条
     *
     * @param id
     * @return
     */
    public List<ChannelAssociateContent> selectAssociateContentList(int id) {
        List<ChannelAssociateContent> list = channelMapper.selectAssociateContentList(id);
        return list;
    }

    /**
     * 查询视频管理的 频道列表
     *
     * @param query
     * @return
     */
    public TableResultResponse<VideoChannelInfo> getPage(Query query) {
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoChannelInfo> list = channelMapper.getPage();
        return new TableResultResponse<VideoChannelInfo>(result.getTotal(), list);
    }


    /**
     * 添加视频频道并返回视频频道实体
     *
     * @param entity
     * @return
     */
    public VideoChannelAddInfo insertVideoChannel(Channel entity) {
        channelMapper.insertVideoChannel(entity);
        VideoChannelAddInfo videoChannelAddInfo = new VideoChannelAddInfo();
        BeanUtils.copyProperties(entity, videoChannelAddInfo);
        return videoChannelAddInfo;
    }

    /**
     * 修改视频频道并返回视频频道实体
     *
     * @return
     */
    public VideoChannelAddInfo updateVideoChannel(Channel entity) {
        channelMapper.updateVideoChannel(entity);
        VideoChannelAddInfo videoChannelAddInfo = new VideoChannelAddInfo();
        BeanUtils.copyProperties(entity, videoChannelAddInfo);
        return videoChannelAddInfo;
    }

    /**
     * 查询视频频道并返回视频频道实体
     *
     * @return
     */
    public VideoChannelAddInfo selectVideoChannelByid(Integer id) {
        VideoChannelAddInfo entity = channelMapper.selectVideoChannelByid(id);
        return entity;
    }

    /**
     * 根据Ids批量查询
     *
     * @param oldIds
     * @return
     */
    public List<Channel> getChannelByIds(List<Integer> oldIds) {
        return channelMapper.getChannelByIds(oldIds);
    }

    public int batchUpdate(List<Channel> channelList) {
        int num = 0;
        for (Channel channel : channelList) {
            int flag = channelMapper.updateByPrimaryKeySelective(channel);
            if (flag > 0) {
                num++;
            }
        }
        return num;
    }

    public List<ChannelInfo> getChannelForContent(Integer contentId) {
        return channelMapper.getChannelForContent(contentId);
    }

    /**
     * APP频道-获取频道列表
     */
    public ObjectRestResponse<Map<String, Object>> getList(String cateId, String devId,Integer uid) {
        ObjectRestResponse<Map<String, Object>> channelObjectRestResponse = new ObjectRestResponse<Map<String, Object>>();
        int cate = Integer.parseInt(cateId);
        String devID = devId == null ? null : devId;
        //从headers获取参数
        String app_version = request.getHeader(requestConfiguration.getVersion());
        String headerUid=request.getHeader("uid");
        if(headerUid!=null){
            uid=Integer.valueOf(headerUid);
        }
        System.out.println("channel getList param:"+ cateId+"=="+devId+"=="+uid);
        ChannelCustom customs = new ChannelCustom();
        //用户显示的频道
        List<String> ids = new ArrayList<String>();
        //用户隐藏不显示的频道
        List<String> hideIds = new ArrayList<String>();
        //用户所有频道，用于鉴别是否有新增频道
        List<String> customIds = new ArrayList<>();
        //所有频道
        List<Channel> allch = new ArrayList<>();
        List<AppChannel> allch2 = new ArrayList<>();
        ChannelCustom channelCustom = new ChannelCustom();
        channelCustom.setUid(uid);
        channelCustom.setDevId(devID);
        customs = channelCustomMapper.selectOne(channelCustom);
        if(customs!=null){
            ids = Arrays.asList(customs.getChanIds().split(","));
            hideIds = Arrays.asList(customs.getHideChanIds().split(","));
            Set<String> set = new HashSet<String>();
            set.addAll(ids);
            set.addAll(hideIds);
            customIds = new ArrayList<>(set);

        }
        //查询所有频道
        allch = channelMapper.appChannelCheck(cate);
        if(allch!=null){
            if (app_version != null) {
                allch = noShowChannel(allch, app_version);
            }
            for (Channel data : allch) {
                AppChannel appch = new AppChannel();
                BeanUtils.copyProperties(data, appch);
                allch2.add(appch);
            }
            channelObjectRestResponse.setData(managerChannel(allch2, ids, customIds, hideIds));
        }
        return channelObjectRestResponse;
    }

    //过滤不展示渠道  app_version版本号
    public List<Channel> noShowChannel(List<Channel> allch, String app_version) {
        Iterator<Channel> iterator = allch.iterator();
        while (iterator.hasNext()) {
            Channel item = iterator.next();
            //比较版本号
            try {
                int ver = CompareVersionUtil.compareVersion(app_version, "2.1.1");
                if (("113".equals(item.getId())) && ver < 0) {
                    iterator.remove();
                } else if ("113".equals(item.getId())) {
                    item.setId(41);
                }
                if (ver < 0) {
                    if ("160".equals(item.getId())) {
                        iterator.remove();
                    }
                } else if ("3".equals(item.getId()) || "95".equals(item.getId())) {
                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return allch;
    }

    //整理展示渠道
    public Map<String, Object> managerChannel(List<AppChannel> allch, List<String> ids, List<String> customIds, List<String> hideIds) {
        Map<String, Object> result = new HashMap<>();
        List<String> topIds = new ArrayList<String>();
        List<String> newIds = new ArrayList<String>();
        List<String> customNotTopIds = new ArrayList<String>();
        List<String> showIds = new ArrayList<String>();
        List<AppChannel> show = new ArrayList<AppChannel>();
        List<AppChannel> hide = new ArrayList<AppChannel>();
        if (null != ids && ids.size()>0) {
            Iterator<AppChannel> iterator = allch.iterator();
            while (iterator.hasNext()) {
                AppChannel item = iterator.next();
                filter(item);
                ChannelFocus par = new ChannelFocus();
                par.setChannelId(item.getId());
                par.setIsDelete(1);
                item.setPicNum(channelFocusMapper.select(par).size());
                //置顶
                if (item.getIsTop() == 1) {
                    topIds.add(item.getId().toString());

                }
                if (!customIds.contains(item.getId().toString()) && item.getIsTop() != 1) {
                    newIds.add(item.getId().toString());
                }
            }
            Iterator<String> iterator2 = ids.iterator();
            while (iterator2.hasNext()) {
                String item = iterator2.next();
                if (!topIds.contains(item)) {
                    customNotTopIds.add(item);
                }
            }
            Set<String> set = new HashSet<String>();
            set.addAll(topIds);
            set.addAll(customNotTopIds);
            set.addAll(newIds);
            showIds = new ArrayList<>(set);
            for (String id : showIds) {
                for (AppChannel data : allch) {
                    if (id.equals(data.getId().toString())) {
                        show.add(data);
                    }
                }
            }
            for (String id : hideIds) {
                for (AppChannel data : allch) {
                    if (id.equals(data.getId().toString()) && !showIds.contains(id)) {
                        hide.add(data);
                    }
                }
            }

        } else {
            for (AppChannel item : allch) {
                filter(item);
                ChannelFocus par = new ChannelFocus();
                par.setChannelId(item.getId());
                par.setIsDelete(1);
                item.setPicNum(channelFocusMapper.select(par).size());
            }
            show.addAll(allch);
        }
        result.put("show", show);
        result.put("hide", hide);
        return result;
    }

    //屏蔽一下数值
    public void filter(AppChannel item) {
        item.setIsPublish(null);
        item.setPublishTime(null);
        item.setChannelStatus(null);
        item.setCreateUid(null);
        item.setCreateTime(null);
    }

    /**
     * APP频道-获取频道列表
     */
    public ObjectRestResponse<Map<String, Object>> getChannelList(Integer cate, String app_version, String pushId, String app_platform) {
        ObjectRestResponse<Map<String, Object>> channelObjectRestResponse = new ObjectRestResponse<Map<String, Object>>();
        //从session中获取用户ID
        Integer uid = null;
        //查询所有频道
        List<Channel> allch = new ArrayList<>();
        List<Channel> mine = new ArrayList<>();
        List<Channel> recommend = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        allch = channelMapper.appChannelCheck(cate);
        if (cate == 1) {
            List<String> ids = new ArrayList<>();
            if (null != uid) {
                ChannelCustom channelCustom = new ChannelCustom();
                channelCustom.setUid(uid);
                ChannelCustom customs = channelCustomMapper.selectOne(channelCustom);
                if (null != customs) {
                    ids = Arrays.asList(customs.getChanIds().split(","));
                }
            }
            if (ids.size() > 0) {
                for (String id : ids) {
                    for (Channel data : allch) {
                        if (data.getId() == Integer.parseInt(id)) {
                            mine.add(data);
                            recommend.add(data);
                        }
                    }
                }
            } else {
                mine = allch;
                recommend = allch;
            }
            result.put("mine", filterValue(mine));
            result.put("recommend", filterValue(recommend));
        } else if (cate == NewsEnumsConsts.ChannelOfCategory.VIDEO_APP.key() || cate == NewsEnumsConsts.ChannelOfCategory.VR_APP.key()) {
            result.put("all", filterValue(allch));
        }
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * APP频道-保存用户自定义频道
     *
     * @param chan_ids
     * @param devID
     * @param hide_chan_ids
     * @return
     */
    public ObjectRestResponse updateCustom(String chan_ids, String devID, String hide_chan_ids, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        ChannelCustom cus = new ChannelCustom();
        ChannelCustom getcus = null;
        if (null != uid) {
            cus.setUid(uid);
            getcus = channelCustomMapper.selectOne(cus);
        } else {
            cus.setDevId(devID);
            getcus = channelCustomMapper.selectOne(cus);
        }
        if (null != getcus) {
            getcus.setChanIds(chan_ids);
            getcus.setHideChanIds(hide_chan_ids);
            channelCustomMapper.updateByPrimaryKey(getcus);
        } else {
            ChannelCustom cusData = new ChannelCustom();
            cusData.setUid(uid != null ? uid : 0);
            cusData.setChanIds(chan_ids);
            cusData.setHideChanIds(hide_chan_ids);
            cusData.setDevId(devID);
            channelCustomMapper.insert(cusData);
        }
        return channelObjectRestResponse;
    }

    /**
     * 过滤不需要返回的属性
     *
     * @param data
     * @return
     */
    public List<Channel> filterValue(List<Channel> data) {
        List<Channel> result = new ArrayList<>();
        for (Channel ch : data) {
            Channel thisch = new Channel();
            thisch.setId(ch.getId());
            thisch.setCategory(ch.getCategory());
            thisch.setIsFixed(ch.getIsFixed());
            thisch.setName(ch.getName());
            result.add(thisch);
        }
        return result;
    }

    /**
     * 查询频道
     *
     * @param entity
     * @return
     */
    public List<ChannelInfo> getChannelCheck(ChannelInfo entity) {
        return channelMapper.getChannelCheck(entity);
    }

    /**
     * 视频频道查询 所有频道
     *
     * @param
     * @return
     */
    public List<Channel> getVideoChannel() {
        List<Channel> videoChannel = channelMapper.getVideoChannel();
        return videoChannel;
    }

    /**
     * 查询VR频道
     *
     * @param entity
     * @return
     */
    public TableResultResponse<VRChannelInfo> getAllVrChannel(VRChannelInfo entity) {
        //获得分页信息
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //设置晒出条件
        //设置信息来源为VR
        entity.setCategory(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
        //'状态，1：正常，0：删除'
        entity.setChannelStatus(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        List<VRChannelInfo> list = channelMapper.getAllVrChannel(entity);
        return new TableResultResponse<>(result.getTotal(), list);
    }

    /**
     * 新增VR频道
     *
     * @param entity
     * @return
     */
    public VRChannelAddInfo addVRChannel(VRChannelAddInfo entity) {
        //设置初步属性 VR 3
        entity.setCategory(NewsEnumsConsts.ChannelOfCategory.VR_APP.key());
        //设置order_number
        Integer category = entity.getCategory();
        Integer integer = channelMapper.selectMaxOrderNumber(category);
        entity.setOrderNumber(integer + 1);
        channelMapper.addVRChannel(entity);
        return entity;
    }

    /**
     * pc分页展示
     *
     * @param
     * @return
     */
    public TableResultResponse<VRChannelInfo> getPCChannel(VRChannelInfo entity) {
        //获得分页信息
        Page<Map> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        List<VRChannelInfo> list = channelMapper.getPCChannel(entity);
        return new TableResultResponse<>(result.getTotal(), list);
    }

    /**
     * 新增PC频道
     *
     * @param
     * @return
     */
    public VRChannelAddInfo addPCChannel(VRChannelAddInfo entity) {
        //设置初步属性
        entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_PC.key());
        //设置order_number
        Integer category = entity.getCategory();
        Integer integer = channelMapper.selectMaxOrderNumber(category);
        if (integer.equals(null)){
            integer = 0;
        }
        entity.setOrderNumber(integer + 1);
        channelMapper.addPCChannel(entity);
        return entity;
    }


    /**
     * VR列表左边的频道栏目 所有频道
     *
     * @param
     * @return
     */
    public List<Channel> getVRLeftChannel() {
        List<Channel> vrLeftChannel = channelMapper.getVRLeftChannel();
        return vrLeftChannel;
    }

    /**
     * PC列表左边的频道栏目 所有频道
     *
     * @param
     * @return
     */
    public List<Channel> getPCLeftChannel() {
        List<Channel> pcLeftChannel = channelMapper.getPCLeftChannel();
        return pcLeftChannel;
    }

    /**
     * 咨询列表左边的频道栏目 所有频道
     *
     * @param
     * @return
     */
    public List<Channel> getConsultLeftChannel() {
        List<Channel> consultLeftChannel = channelMapper.getConsultLeftChannel();
        return consultLeftChannel;
    }


}