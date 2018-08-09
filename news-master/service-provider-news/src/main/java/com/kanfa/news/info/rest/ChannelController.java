package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ChannelBiz;
import com.kanfa.news.info.biz.ChannelContentBiz;
import com.kanfa.news.info.biz.ContentBiz;
import com.kanfa.news.info.entity.Channel;
import com.kanfa.news.info.vo.admin.info.ChannelAssociateContent;
import com.kanfa.news.info.vo.admin.info.ChannelInfo;
import com.kanfa.news.info.vo.admin.vr.VRChannelAddInfo;
import com.kanfa.news.info.vo.admin.vr.VRChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("channel")
public class ChannelController extends BaseController<ChannelBiz,Channel> {

    @Autowired
    private ChannelBiz channelBiz;
    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private ChannelContentBiz channelContentBiz;


    /**
     * 添加频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addChannel",method = RequestMethod.POST)
    public ObjectRestResponse<Channel> addChannel(@RequestBody Channel entity){
        Channel channel1 = new Channel();
        channel1.setName(entity.getName());
        channel1.setCategory(entity.getCategory());
        List<Channel> channels = channelBiz.selectList(channel1);
        ObjectRestResponse<Channel> channelObjectRestResponse = new ObjectRestResponse<>();
        if(null!=channels && channels.size()>0){
            return getObjectRestResponse("频道名称不能重复");
        }
        Channel channel = channelBiz.insertChannelSelective(entity);
        channelObjectRestResponse.setData(channel);
        return channelObjectRestResponse;
    }

    /**
     * 查询全部频道集合
     * @return
     */
    @RequestMapping(value = "/getListChannelAll/{channelStatus}",method = RequestMethod.GET)
    public ObjectRestResponse getListChannelAll(@PathVariable(name = "channelStatus") Integer channelStatus){
        ObjectRestResponse<List<ChannelInfo>> entityObjectRestResponse = new ObjectRestResponse<List<ChannelInfo>>();
        Channel entity=new Channel();
        entity.setChannelStatus(channelStatus);
        entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        entityObjectRestResponse.setData(channelBiz.selectChannlList2(entity));
        return entityObjectRestResponse;
    }

    /**
     * 查询全部频道集合PC
     * @return
     */
    @RequestMapping(value = "/getListChannelAllPC",method = RequestMethod.POST)
    public ObjectRestResponse getListChannelAllPC(@RequestBody Channel entity){
        ObjectRestResponse<List<ChannelInfo>> entityObjectRestResponse = new ObjectRestResponse<List<ChannelInfo>>();
        entityObjectRestResponse.setData(channelBiz.selectChannlList2(entity));
        return entityObjectRestResponse;
    }


    /**
     * 条件查询频道集合
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getChannelList",method = RequestMethod.POST)
    public ObjectRestResponse<List<Channel>> getChannelList(@RequestBody ChannelInfo entity){
        List<Channel> channels = channelBiz.getChannelListForContentPage(entity);
        ObjectRestResponse restResponse=new ObjectRestResponse();
        restResponse.setData(channels);
        return restResponse;
    }

    /**
     * 查询五条资讯中绑定的内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/selectAssociateContentList/{id}",method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelAssociateContent>> selectAssociateContentList(@PathVariable(name = "id") Integer id){
        ObjectRestResponse<List<ChannelAssociateContent>> listResponse = new ObjectRestResponse<List<ChannelAssociateContent>>();
        listResponse.setData(channelBiz.selectAssociateContentList(id));
        return listResponse;
    }

    /**
     * 根据ids查询
     * @param oldIds
     * @return
     */
    @RequestMapping(value = "/getChannelByIds")
    public List<Channel> getChannelByIds(@RequestParam List<Integer> oldIds){
        return channelBiz.getChannelByIds(oldIds);
    }

    /**
     * 批量跟新频道
     * @param channelList
     * @return
     */
    @RequestMapping(value = "/batchUpdate",method = RequestMethod.POST)
    public int batchUpdate(@RequestBody List<Channel> channelList){
        return channelBiz.batchUpdate(channelList);
    }

    @RequestMapping(value = "/getChannelForContent",method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelInfo>> getChannelForContent(@RequestParam Integer contentId){
        ObjectRestResponse<List<ChannelInfo>> listResponse=new ObjectRestResponse<>();
        List<ChannelInfo> list=channelBiz.getChannelForContent(contentId);
        listResponse.setData(list);
        return listResponse;
    }
    @RequestMapping(value = "/getChannelAllForContent",method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelInfo>> getChannelAllForContent(){
        ObjectRestResponse<List<ChannelInfo>> listResponse=new ObjectRestResponse<>();
        List<ChannelInfo> list=channelBiz.getChannelForContent(null);
        listResponse.setData(list);
        return listResponse;
    }

    /**
     * APP频道-获取频道列表
     * @param cate
     * @return
     */
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getList(@RequestParam("cate") String cate,
                                                          @RequestParam(value = "devID" ,required = false) String devID,
                                                          @RequestParam(value = "uid" ,required = false) Integer uid){
        return channelBiz.getList(cate,devID,uid);
    }

    /**
     * APP频道-获取频道列表
     * @param cate
     * @return
     */
    @RequestMapping(value = "/index/getChannelList",method = RequestMethod.POST)
    public ObjectRestResponse<Map<String,Object>> getChannelList(@RequestParam("cate") Integer cate){
        //从headers获取参数
        String app_version = request.getHeader("VERSION");
        String pushId = request.getHeader("PUSHID");
        String app_platform = request.getHeader("PLATFORM");
        return channelBiz.getChannelList(cate,app_version,pushId,app_platform);
    }

    /**
     * APP频道-保存用户自定义频道
     * @param chan_ids
     * @param devID
     * @param hide_chan_ids
     * @return
     */
    @RequestMapping(value = "/updateCustom",method = RequestMethod.POST)
    public ObjectRestResponse updateCustom(@RequestParam("chan_ids") String chan_ids,
                                           @RequestParam(value = "devID",required = false) String devID,
                                           @RequestParam("hide_chan_ids") String hide_chan_ids,
                                           @RequestParam(value = "uid",required = false) Integer uid){
        return channelBiz.updateCustom(chan_ids,devID,hide_chan_ids,uid);
    }

    /**
     * 查询频道（审核时的内数量）
     * @return
     */
    @RequestMapping(value = "/getChannelCheck",method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelInfo>> getChannelCheck(@RequestParam("checkStatus") Integer checkStatus) {
        ChannelInfo entity = new ChannelInfo();
        entity.setChannelStatus(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        List<ChannelInfo> list = channelBiz.getChannelCheck(entity);
        for (ChannelInfo channelInfo : list) {
            Integer aLong = channelContentBiz.selectCountByChannelId(channelInfo.getId(),checkStatus);
            channelInfo.setCount(aLong);
        }
        ObjectRestResponse<List<ChannelInfo>> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(list);
        return objectRestResponse;
    }

     /**
      * 视频频道查询 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/getVideoChannel",method = RequestMethod.GET)
    public List<Channel> getVideoChannel(){
        List<Channel> videoChannel = channelBiz.getVideoChannel();
        return videoChannel;
    }

    /**
     * 查询VR频道集合
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getVRChannels", method = RequestMethod.POST)
    public TableResultResponse<VRChannelInfo> getVRChannels(@RequestBody VRChannelInfo entity) {
        return channelBiz.getAllVrChannel(entity);
    }

    /**
     * 新增VR频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addVRChannel",method = RequestMethod.POST)
    public ObjectRestResponse<VRChannelAddInfo> addVRChannel(@RequestBody VRChannelAddInfo entity){
        VRChannelAddInfo info = channelBiz.addVRChannel(entity);
        ObjectRestResponse<VRChannelAddInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }


    /**
     * Pc频道的分页显示
     * @param
     * @return
     */
    @RequestMapping(value = "/getPCChannel", method = RequestMethod.POST)
    @ResponseBody
    public TableResultResponse<VRChannelInfo> getPCChannel(@RequestBody VRChannelInfo entity) {
        return channelBiz.getPCChannel(entity);
    }

    /**
     * 新增pc频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addPCChannel",method = RequestMethod.POST)
    public ObjectRestResponse<VRChannelAddInfo> addPCChannel(@RequestBody VRChannelAddInfo entity){
        VRChannelAddInfo info = channelBiz.addPCChannel(entity);
        ObjectRestResponse<VRChannelAddInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }


    /**
     * VR视频列表左边的频道栏目 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/getVRLeftChannel",method = RequestMethod.GET)
    public List<Channel> getVRLeftChannel(){
        List<Channel> vrLeftChannel = channelBiz.getVRLeftChannel();
        return vrLeftChannel;
    }


    /**
     * PC视频列表左边的频道栏目 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/getPCLeftChannel",method = RequestMethod.GET)
    public List<Channel> getPCLeftChannel(){
        List<Channel> pcLeftChannel = channelBiz.getPCLeftChannel();
        return pcLeftChannel;
    }

    /**
     * 视频频道-->app pc(1.0)
     * @param
     * @return
     */
    @RequestMapping(value = "/newVideoChannels",method = RequestMethod.GET)
    public ObjectRestResponse newVideoListChannels(){
    return channelBiz.newVideoListChannels();
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortChannel",method = RequestMethod.POST)
    public ObjectRestResponse<Boolean> sortChannel(@RequestBody Map<String, List <Integer>> params){
        //查询频道原有的集合
        List<Integer> newIds = params.get("newIds");
        List<Integer> oldIds = params.get("oldIds");
        int count=newIds.size();
        for (int i = 0; i < oldIds.size(); i++) {
            if(!(oldIds.get(i).equals(newIds.get(i)))){
                count=i;
                break;
            }
        }
        List<Channel> oldList=channelBiz.getChannelByIds(oldIds);
        //新的
        List<Integer> sort=new ArrayList<>(oldList.size());
        if(oldList!=null && oldList.size()>0){
            for (Channel channel : oldList) {
                sort.add(channel.getOrderNumber());
            }
        }
        List<Channel> newChannelList=new ArrayList<>(oldList.size());
        for (int i = 0; i < newIds.size(); i++) {
            Channel channel=new Channel();
            channel.setId(newIds.get(i));
            channel.setOrderNumber(sort.get(i));
            channel.setEditUid(BaseContextHandler.getUserID());
            newChannelList.add(channel);
        }
        List<Channel> channelList = newChannelList.subList(count,newChannelList.size());
        ObjectRestResponse<Boolean> restResponse=new ObjectRestResponse<>();
        if(channelList!=null && channelList.size()>0){
            int succNum=channelBiz.batchUpdate(channelList);
            if(succNum<=0){
                restResponse.setData(true);
                restResponse.setStatus(506);
                restResponse.setMessage("排序失败");
            }
        }
        restResponse.setRel(false);
        return restResponse;
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setStatus(506);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }

}