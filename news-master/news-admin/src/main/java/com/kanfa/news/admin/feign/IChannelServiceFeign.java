package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.vo.channel.ChannelAssociateContent;
import com.kanfa.news.admin.vo.channel.ChannelInfo;
import com.kanfa.news.admin.vo.vr.VRChannelAddInfo;
import com.kanfa.news.admin.vo.vr.VRChannelInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/2/24 15:35
 */
@FeignClient(name = "service-provider-news")
public interface IChannelServiceFeign {

    /**
     * 根据id查询频道
     * @param id
     * @return
     */
    @RequestMapping(value = "/channel/{id}",method = RequestMethod.GET)
    ObjectRestResponse<Channel> get(@PathVariable("id") int id);

    /**
     * 添加频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/channel/addChannel",method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse<Channel> add(@RequestBody Channel entity);

    /**
     * 查询频道集合
     * @return
     */
    @RequestMapping(value = "/channel/getListChannelAll/{channelStatus}",method = RequestMethod.GET)
    ObjectRestResponse<List<ChannelInfo>> getListChannelAll(@PathVariable(value = "channelStatus") Integer channelStatus);

    /**
     * 更新频道信息
     * @param entity
     * @return
     */
    @RequestMapping(value = "/channel/{id}",method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<Channel> update(@PathVariable("id") Integer id,
                                       @RequestBody Channel entity);

    /**
     * 删除频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/channel/{id}",method = RequestMethod.DELETE)
    ObjectRestResponse<Channel> remove(@RequestBody Channel entity);

    @RequestMapping(value = "/channel/selectAssociateContentList/{id}",method = RequestMethod.GET)
    ObjectRestResponse<List<ChannelAssociateContent>> selectAssociateContentList(@PathVariable(name = "id") Integer id);

    /**
     * 频道列表
     * @param entity
     * @return
     */
    @RequestMapping(value = "/channel/getChannelList",method = RequestMethod.POST)
    ObjectRestResponse<List<Channel>> getChannelList(@RequestBody ChannelInfo entity);

    @RequestMapping(value = "/channel",method = RequestMethod.GET)
    ObjectRestResponse<Channel> getChannelById(Long id);

    @RequestMapping(value = "/channel/getChannelByIds")
    List<Channel> getChannelByIds(@RequestParam("oldIds") List <Integer> oldIds);

    /**
     * 批量跟新频道
     * @param channelList
     * @return
     */
    @RequestMapping(value = "/channel/batchUpdate",method = RequestMethod.POST)
    int batchUpdate(@RequestBody List<Channel> channelList);


    @RequestMapping(value = "/channel/getChannelForContent",method = RequestMethod.GET)
    ObjectRestResponse<List<ChannelInfo>> getChannelForContent(@RequestParam(value = "contentId") Integer contentId);

    @RequestMapping(value = "/channel/getChannelAllForContent",method = RequestMethod.GET)
    public ObjectRestResponse<List<ChannelInfo>> getChannelAllForContent();

    /**
     * 查询频道绑定数量
     * @return
     */
    @RequestMapping(value = "/channel/getChannelCheck",method = RequestMethod.GET)
    ObjectRestResponse<List<ChannelInfo>> getChannelCheck(@RequestParam("checkStatus") Integer checkStatus);

    /**
     * 视频频道查询 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/channel/getVideoChannel",method = RequestMethod.GET)
    List<Channel> getVideoChannel();

    /**
     * VR频道列表分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/channel/getVRChannels",method = RequestMethod.POST)
    TableResultResponse<VRChannelInfo> getVRChannels(@RequestBody VRChannelInfo entity);

    /**
     * 新增VR频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/channel/addVRChannel", method = RequestMethod.POST)
    ObjectRestResponse<VRChannelAddInfo> addVRChannel(@RequestBody VRChannelAddInfo entity);


    /**
     * PC频道列表分页查询
     * @param
     * @return
     */
    @RequestMapping(value = "/channel/getPCChannel",method = RequestMethod.POST)
    TableResultResponse<VRChannelInfo> getPcChannel(@RequestBody VRChannelInfo entity);

    /**
     * 新增pc频道
     * @param entity
     * @return
     */
    @RequestMapping(value = "/channel/addPCChannel", method = RequestMethod.POST)
    ObjectRestResponse<VRChannelAddInfo> addPCChannel(@RequestBody VRChannelAddInfo entity);

    /**
     * VR视频列表左边的频道栏目 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/channel/getVRLeftChannel",method = RequestMethod.GET)
    List<Channel> getVRLeftChannel();

    /**
     * PC视频列表左边的频道栏目 所有频道
     * @param
     * @return
     */
    @RequestMapping(value = "/channel/getPCLeftChannel",method = RequestMethod.GET)
    List<Channel> getPCLeftChannel();

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/channel/sortChannel",method = RequestMethod.POST)
    ObjectRestResponse<Boolean> sortChannel(@RequestBody Map<String, List <Integer>> params);

    /**
     * 视频列表-->app pv (v1.0)
     * @param
     * @return
     */
    @RequestMapping(value = "/channel/newVideoChannels",method = RequestMethod.GET)
    ObjectRestResponse newVideoChannels();


}
