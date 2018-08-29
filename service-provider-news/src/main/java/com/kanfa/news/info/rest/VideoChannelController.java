package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.ChannelBiz;
import com.kanfa.news.info.entity.Channel;
import com.kanfa.news.info.vo.admin.video.VideoChannelAddInfo;
import com.kanfa.news.info.vo.admin.video.VideoChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("videoChannel")
public class VideoChannelController extends BaseController<ChannelBiz,Channel> {
    @Autowired
    private ChannelBiz channelBiz;

    /**
     * 查询视频管理的 频道列表
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<VideoChannelInfo> getPage(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return channelBiz.getPage(query);
    }

    /**
     * 添加视频频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addVideoChannel",method = RequestMethod.POST)
    public ObjectRestResponse<VideoChannelAddInfo> addVideoChannel(@RequestBody Channel entity){
        entity.setCategory(2);
        entity.setCreateTime(new Date());
        Integer integer = channelBiz.selectMaxOrderNumber(2);
        if (integer==null){
            entity.setOrderNumber(1);
        }else {
            entity.setOrderNumber(integer+1);
        }
        VideoChannelAddInfo channel = channelBiz.insertVideoChannel(entity);
        ObjectRestResponse<VideoChannelAddInfo> channelObjectRestResponse = new ObjectRestResponse<>();
        channelObjectRestResponse.setData(channel);
        return channelObjectRestResponse;
    }

    /**
     * 修改视频频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateVideoChannel",method = RequestMethod.PUT)
    public ObjectRestResponse<VideoChannelAddInfo> updateVideoChannel(@RequestBody Channel entity){
        VideoChannelAddInfo videoChannel = channelBiz.updateVideoChannel(entity);
        ObjectRestResponse<VideoChannelAddInfo> channelObjectRestResponse = new ObjectRestResponse<>();
        channelObjectRestResponse.setData(videoChannel);
        return channelObjectRestResponse;
    }
    /**
     * 得到视频频道
     * @param  id
     * @return
     */
    @RequestMapping(value = "/getVideoChannel",method = RequestMethod.GET)
    public ObjectRestResponse<VideoChannelAddInfo> selectVideoChannelByid(@RequestParam Integer id){
        VideoChannelAddInfo videoChannelAddInfo = channelBiz.selectVideoChannelByid(id);
        ObjectRestResponse<VideoChannelAddInfo> channelObjectRestResponse = new ObjectRestResponse<>();
        channelObjectRestResponse.setData(videoChannelAddInfo);
        return  channelObjectRestResponse;
    }




}