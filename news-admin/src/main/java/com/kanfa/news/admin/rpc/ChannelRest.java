package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.feign.IChannelServiceFeign;
import com.kanfa.news.admin.vo.adv.ChannelOfAdvInfo;
import com.kanfa.news.admin.vo.channel.ChannelAssociateContent;
import com.kanfa.news.admin.vo.channel.ChannelInfo;
import com.kanfa.news.admin.vo.channel.ObjectInfo;
import com.kanfa.news.admin.vo.pc.ChannelShow;
import com.kanfa.news.admin.vo.vr.VRChannelAddInfo;
import com.kanfa.news.admin.vo.vr.VRChannelInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/2/27 13:46
 */
@RestController
@RequestMapping("channel")
public class ChannelRest {
    @Autowired
    private IChannelServiceFeign channelServiceFeign;
    /**
     * 添加频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "addChannel", method = RequestMethod.POST)
    public ObjectRestResponse<Channel> addChannel(@RequestBody Channel entity) {
        if (StringUtils.isEmpty(entity.getName())){
            return getObjectRestResponse("频道名称不能为空");
        }
        if((entity.getCommentThreshold()!=null && entity.getCommentThreshold()<0) || (entity.getHotThreshold()!=null && entity.getHotThreshold()<0)
                || (entity.getViewInitNum() != null && entity.getViewInitNum()<0) || (entity.getViewThreshold()!=null && entity.getViewThreshold()<0) || (entity.getTopNum()!=null && entity.getTopNum()<0)){
            return getObjectRestResponse("数值不能小于0");
        }
        //初始化频道属性值
        entity.setCreateTime(new Date());
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setChannelStatus(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        entity.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.NOTPUBLISH.key());
        entity.setPublishTime(null);
        entity.setUrl("");
        //entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        //初始化排序号
        Long timeStamp=System.currentTimeMillis() / 1000;
        entity.setSortTime(timeStamp.intValue());
        ObjectRestResponse<Channel> add = channelServiceFeign.add(entity);
        return add;
    }

    /**
     * 根据id查询频道
     * @param id
     * @return
     */
    @RequestMapping(value = "/getChannel/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ObjectInfo> getChannel(@PathVariable("id") Integer id) {
        ObjectRestResponse<ObjectInfo> resp=new ObjectRestResponse<>();
        ObjectInfo info=new ObjectInfo();
        //查询关联的内容
        ObjectRestResponse<List<ChannelAssociateContent>> list = channelServiceFeign.selectAssociateContentList(id);
        ObjectRestResponse<Channel> channelObjectRestResponse = channelServiceFeign.get(id);
        info.setEntity(channelObjectRestResponse.getData());
        info.setList(list.getData());
        resp.setData(info);
        return resp;
    }

    /**
     * 查询所有频道集合
     * @return
     */
    @RequestMapping(value = "/allChannel", method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelInfo>> allChannel() {
        //去除删除的频道
        return channelServiceFeign.getListChannelAll(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
    }

    /**
     * 更新频道信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateChannel", method = RequestMethod.POST)
    public ObjectRestResponse<Channel> updateChannel(@RequestBody Channel entity) {
        ObjectRestResponse objectRestResponse = getChannelObjectRestResponse(entity);
        if (objectRestResponse != null) {
            return objectRestResponse;
        }
        entity.setEditUid(BaseContextHandler.getUserID());
        //添加更新时间
        if(entity.getIsPublish()!=null && entity.getIsPublish().equals(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key())){
            entity.setPublishTime(new Date());
        }
        entity.setEditUid(BaseContextHandler.getUserID());
        return channelServiceFeign.update(entity.getId(),entity);
    }

    /**
     * 查询频道集合区分app，pc，默认所有
     * @return
     */
    @RequestMapping(value = "/getChannelList", method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelInfo>> getChannelList() {
        ChannelInfo entity=new ChannelInfo();
        entity.setChannelStatus(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        entity.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
        ObjectRestResponse<List<Channel>> channelList = channelServiceFeign.getChannelList(entity);
        List<ChannelInfo> list=new ArrayList<>(channelList.getData().size());
        for (Channel channel : channelList.getData()) {
            ChannelInfo channelInfo=new ChannelInfo();
            channelInfo.setId(channel.getId());
            channelInfo.setName(channel.getName());
            list.add(channelInfo);
        }
        //
        ObjectRestResponse<List<ChannelInfo>> objectRestResponse=new ObjectRestResponse<>();
        objectRestResponse.setData(list);
        return objectRestResponse;
    }

    /**
     * 拖拽排序频道
     * @param params
     * @return
     */
    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Boolean> sort( @RequestBody Map<String, List <Integer>> params) {
        return channelServiceFeign.sortChannel(params);
    }

    private ObjectRestResponse<Channel> getChannelObjectRestResponse(Channel entity) {
        if (entity.getId()==null){
            ObjectRestResponse objectRestResponse = new ObjectRestResponse();
            objectRestResponse.setRel(true);
            objectRestResponse.setStatus(506);
            objectRestResponse.setMessage("频道无法找到(无Id)");
            return objectRestResponse;
        }
        return null;
    }

    /**
     * 视频频道查询 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/getVideoChannel", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Channel> getVideoChannel(){
        List<Channel> videoChannel = channelServiceFeign.getVideoChannel();
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(videoChannel);
        return  objectRestResponse;
    }

    /**
     * VR频道列表分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getVRChannels", method = RequestMethod.POST)
    public TableResultResponse<VRChannelInfo> getVRChannels(@RequestBody VRChannelInfo entity) {
        TableResultResponse<VRChannelInfo> list=channelServiceFeign.getVRChannels(entity);
        return list;
    }

    /**
     * 新增VR频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addVRChannel", method = RequestMethod.POST)
    public ObjectRestResponse<ChannelShow> addVRChannel(@RequestBody VRChannelAddInfo entity) {
        ChannelShow channelShow = new ChannelShow();
        if (StringUtils.isEmpty(entity.getName())){
            String msg= "名称不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreateTime(new Date());
        if (entity.getViewThreshold()==null){
            entity.setViewThreshold(0);
        }
        if (entity.getHotThreshold()==null){
            entity.setHotThreshold(0);
        }
        if (entity.getViewInitNum()==null){
            entity.setViewInitNum(0);
        }
        if (entity.getCommentShowRule()==null){
            entity.setCommentShowRule("");
        }
        if (entity.getTopNum()==null){
            entity.setTopNum(0);
        }
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        ObjectRestResponse<VRChannelAddInfo> vrChannelAddInfoObjectRestResponse = channelServiceFeign.addVRChannel(entity);
        VRChannelAddInfo data = vrChannelAddInfoObjectRestResponse.getData();
        BeanUtils.copyProperties(data,channelShow);
        ObjectRestResponse<ChannelShow> channelShowObjectRestResponse = new ObjectRestResponse<>();
        channelShowObjectRestResponse.setData(channelShow);
        return channelShowObjectRestResponse;
    }

    /**
     * 得到VR频道详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getVRChannel/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ChannelShow> getVRChannel(@PathVariable("id") Integer id) {
        ChannelShow channelShow = new ChannelShow();
        ObjectRestResponse<Channel> channelObjectRestResponse = channelServiceFeign.get(id);
        Channel data = channelObjectRestResponse.getData();
        BeanUtils.copyProperties(data, channelShow);
        ObjectRestResponse<ChannelShow> channelShowObjectRestResponse = new ObjectRestResponse<>();
        channelShowObjectRestResponse.setData(channelShow);
        return channelShowObjectRestResponse;
    }

    /**
     * 编辑VR频道
     * @param
     * @return
     */
    @RequestMapping(value = "/updateVRChannel", method = RequestMethod.POST)
    public ObjectRestResponse<VRChannelAddInfo> updateVRChannel(@RequestBody VRChannelAddInfo entity) {
        Channel channel = new Channel();
        BeanUtils.copyProperties(entity, channel);
        channel.setEditUid(BaseContextHandler.getUserID());
        //设置时间
        Long l = System.currentTimeMillis() / 1000;
        Integer time = l.intValue();
        channel.setSortTime(time);
        channelServiceFeign.update(entity.getId(),channel);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(entity);
        return objectRestResponse;
    }

    /**
     * Pc频道列表分页查询
     * @param
     * @return
     */
    @RequestMapping(value = "/getPCChannel", method = RequestMethod.POST)
    public TableResultResponse<VRChannelInfo> getPcChannel(@RequestBody VRChannelInfo entity) {
        return channelServiceFeign.getPcChannel(entity);
    }

    /**
     * 新增pc频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addPCChannel", method = RequestMethod.POST)
    public ObjectRestResponse<ChannelShow> addPCChannel(@RequestBody VRChannelAddInfo entity) {
        ChannelShow channelShow = new ChannelShow();
        if (StringUtils.isEmpty(entity.getName())){
            String msg= "名称不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreateTime(new Date());
        if (entity.getViewThreshold()==null){
            entity.setViewThreshold(0);
        }
        if (entity.getHotThreshold()==null){
            entity.setHotThreshold(0);
        }
        if (entity.getViewInitNum()==null){
            entity.setViewInitNum(0);
        }
        if (entity.getCommentShowRule()==null){
            entity.setCommentShowRule("");
        }
        if (entity.getTopNum()==null){
            entity.setTopNum(0);
        }
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        ObjectRestResponse<VRChannelAddInfo> vrChannelAddInfoObjectRestResponse = channelServiceFeign.addPCChannel(entity);
        VRChannelAddInfo data = vrChannelAddInfoObjectRestResponse.getData();
        BeanUtils.copyProperties(data,channelShow);
        ObjectRestResponse<ChannelShow> channelShowObjectRestResponse = new ObjectRestResponse<>();
        channelShowObjectRestResponse.setData(channelShow);
        return channelShowObjectRestResponse;
    }


    /**
     * VR视频列表左边的频道栏目 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/getVRLeftChannel", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Channel> getVRLeftChannel(){
        List<Channel> videoChannel = channelServiceFeign.getVRLeftChannel();
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(videoChannel);
        return  objectRestResponse;
    }


    /**
     * PC视频列表左边的频道栏目 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/getPCLeftChannel", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Channel> getPCLeftChannel(){
        List<Channel> pcLeftChannelChannel = channelServiceFeign.getPCLeftChannel();
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(pcLeftChannelChannel);
        return  objectRestResponse;
    }

    /**
     * 查询广告频道
     * @return
     */
    @RequestMapping(value = "/ChannelOfAdvInfo", method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelOfAdvInfo>> getChannelForAdv() {
        ChannelInfo entity=new ChannelInfo();
        entity.setChannelStatus(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        entity.setIsPublish(NewsEnumsConsts.ChannelOfIsPublish.PUBLISHED.key());
        ObjectRestResponse<List<Channel>> channelList = channelServiceFeign.getChannelList(entity);
        List<ChannelOfAdvInfo> list=new ArrayList<>(channelList.getData().size());
        for (Channel channel : channelList.getData()) {
            ChannelOfAdvInfo channelOfAdvInfo=new ChannelOfAdvInfo();
            channelOfAdvInfo.setId(channel.getId()+"-1");
            channelOfAdvInfo.setName(channel.getName());
            list.add(channelOfAdvInfo);
        }
        ObjectRestResponse<List<ChannelOfAdvInfo>> objectRestResponse=new ObjectRestResponse<>();
        objectRestResponse.setData(list);
        return objectRestResponse;
    }


    /**
     * 视频列表-->app pv (v1.0)
     * @return
     */
    @RequestMapping(value = "/newVideoChannels",method = RequestMethod.GET)
    public ObjectRestResponse newVideoChannels(){
        return  channelServiceFeign.newVideoChannels();
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setStatus(506);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }
}
