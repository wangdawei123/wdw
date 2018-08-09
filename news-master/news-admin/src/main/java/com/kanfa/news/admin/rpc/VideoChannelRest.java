package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.feign.IVideoChannelServiceFeign;
import com.kanfa.news.admin.vo.video.VideoChannelAddInfo;
import com.kanfa.news.admin.vo.video.VideoChannelInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/6 10:21
 */
@RestController
@RequestMapping("videoChannel")
public class VideoChannelRest {

    @Autowired
    private IVideoChannelServiceFeign videoChannelServiceFeign;
    /**
     * 添加频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "addVideoChannel", method = RequestMethod.POST)
    public ObjectRestResponse addVideoChannel(@RequestBody Channel entity) {
        //初始化频道属性值
        entity.setCategory(2);
        entity.setIsTop(0);
        entity.setIsHeadline(0);
        entity.setChannelStatus(1);
        entity.setIsPublish(0);
        entity.setOrderNumber(0);
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
        if (entity.getCommentThreshold()==null){
            entity.setCommentThreshold(0);

        }
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));

        return videoChannelServiceFeign.add(entity);
    }

    /**
     * 修改视频频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateVideoChannel", method = RequestMethod.PUT)
    public ObjectRestResponse<VideoChannelAddInfo> updateVideoChannel( @RequestBody Channel entity) {
        entity.setEditUid(BaseContextHandler.getUserID());
        //初始化排序号
        Long timeStamp=System.currentTimeMillis() / 1000;
        entity.setSortTime(timeStamp.intValue());
        ObjectRestResponse<VideoChannelAddInfo> videoChannel = videoChannelServiceFeign.updateVideoChannel(entity);
        return videoChannel;
    }


    //得到一个频道列表
    @RequestMapping(value = "/getVideoChannel", method = RequestMethod.GET)
    public ObjectRestResponse<VideoChannelAddInfo> getVideoChannel(@RequestParam Integer id) {
        ObjectRestResponse<VideoChannelAddInfo> videoChannel = videoChannelServiceFeign.getVideoChannel(id);
        return videoChannel;
    }

    //频道列表的分页显示
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<VideoChannelInfo> getPage(@RequestParam Map<String, Object> params) {
        return videoChannelServiceFeign.list(params);
    }

    //取消发布
    @RequestMapping(value = "/cancelPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse cancelPublish(@PathVariable("id") Integer id) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setIsPublish(0);
        Long timeStamp=System.currentTimeMillis() / 1000;
        channel.setSortTime(timeStamp.intValue());
        channel.setEditUid(BaseContextHandler.getUserID());
        return videoChannelServiceFeign.update(id, channel);
    }

    //发布
    @RequestMapping(value = "/onPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse onPublish(@PathVariable("id") Integer id) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setIsPublish(1);
        channel.setPublishTime(new Date());
      Long timeStamp=System.currentTimeMillis() / 1000;
        channel.setSortTime(timeStamp.intValue());
        channel.setEditUid(BaseContextHandler.getUserID());
        return videoChannelServiceFeign.update(id, channel);
    }

    //置顶
    @RequestMapping(value = "/isTop/{id}" , method = RequestMethod.PUT)
    public ObjectRestResponse isTop(@PathVariable("id") Integer id){
        Channel channel = new Channel();
        channel.setId(id);
        channel.setIsTop(1);
        Long timeStamp=System.currentTimeMillis() / 1000;
        channel.setSortTime(timeStamp.intValue());
        channel.setEditUid(BaseContextHandler.getUserID());
        return videoChannelServiceFeign.update(id, channel);
    }

    //取消置顶
    @RequestMapping(value = "/noTop/{id}" , method = RequestMethod.PUT)
    public ObjectRestResponse noTop(@PathVariable("id") Integer id){
        Channel channel = new Channel();
        channel.setId(id);
        channel.setIsTop(0);
        Long timeStamp=System.currentTimeMillis() / 1000;
        channel.setSortTime(timeStamp.intValue());
        channel.setEditUid(BaseContextHandler.getUserID());
        return videoChannelServiceFeign.update(id, channel);
    }

    //删除  状态，1：正常，0：删除
    @RequestMapping(value = "/delete/{id}" , method = RequestMethod.PUT)
    public  ObjectRestResponse delete(@PathVariable("id") Integer id){
        Channel channel = new Channel();
        channel.setId(id);
        channel.setChannelStatus(0);
        Long timeStamp=System.currentTimeMillis() / 1000;
        channel.setSortTime(timeStamp.intValue());
        channel.setEditUid(BaseContextHandler.getUserID());
        return videoChannelServiceFeign.update(id, channel);
    }

}
