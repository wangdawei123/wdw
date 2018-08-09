package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.vo.video.VideoChannelAddInfo;
import com.kanfa.news.admin.vo.video.VideoChannelInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/6 10:13
 * 视频来源作者列表
 */

@FeignClient(name = "service-provider-news")
public interface IVideoChannelServiceFeign {
    /**
     * 添加视频频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/videoChannel/addVideoChannel",method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse<VideoChannelAddInfo> add(@RequestBody Channel entity);
    /**
     * 修改视频频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/videoChannel/updateVideoChannel",method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<VideoChannelAddInfo> updateVideoChannel(@RequestBody Channel entity);

    //得到一个频道列表
    @RequestMapping(value = "/videoChannel/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<Channel> get(@PathVariable("id") int id);

    //编辑频道列表
    @RequestMapping(value = "/videoChannel/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<Channel> update(@PathVariable("id") Integer id, @RequestBody Channel entity);

    //删除频道列表
    @RequestMapping(value = "/videoChannel/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<Channel> remove(@PathVariable("id") int id);

    //分页查询频道列表
    @RequestMapping(value = "/videoChannel/getPage")
    TableResultResponse<VideoChannelInfo> list(@RequestParam Map<String, Object> params);

    /**
     * 得到视频频道
     * @param id
     * @return
     */
    @RequestMapping(value = "/videoChannel/getVideoChannel", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<VideoChannelAddInfo> getVideoChannel(@RequestParam("id") Integer id);



}
