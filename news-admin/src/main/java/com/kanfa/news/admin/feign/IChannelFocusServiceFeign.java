package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ChannelFocus;
import com.kanfa.news.admin.vo.channel.ChannelFocusInfo;
import com.kanfa.news.admin.vo.video.PcChannelFocusInfo;
import com.kanfa.news.admin.vo.video.VideoChannelFocusInfo;
import com.kanfa.news.admin.vo.video.VideoChannelFocusListInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/7 18:29
 */
@FeignClient(name = "service-provider-news")
public interface IChannelFocusServiceFeign {

    @RequestMapping(value = "/channelFocus",method = RequestMethod.POST)
    ObjectRestResponse<ChannelFocus> add(@RequestBody ChannelFocus entity);

    @RequestMapping(value = "/channelFocus/{id}",method = RequestMethod.PUT)
    ObjectRestResponse<ChannelFocus> update(@PathVariable("id") Integer id,@RequestBody ChannelFocus entity);

    @RequestMapping(value = "/channelFocus/getPage",method = RequestMethod.GET)
    TableResultResponse<ChannelFocusInfo> list(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/channelFocus/all",method = RequestMethod.GET)
    List<ChannelFocusInfo> all();

    @RequestMapping(value = "/channelFocus/{id}",method = RequestMethod.GET)
    ObjectRestResponse<ChannelFocus> get(@PathVariable("id") Integer id);

    @RequestMapping(value = "/channelFocus/batchDelete",method = RequestMethod.POST)
    ObjectRestResponse<Integer> batchDelete(@RequestBody List<Integer> ids);

    @RequestMapping(value = "/channelFocus/getVideoChannelFocusPage",method = RequestMethod.GET)
    TableResultResponse<VideoChannelFocusInfo> getVideoChannelFocusPage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/channelFocus/getVideoFocusList",method = RequestMethod.GET)
    TableResultResponse<VideoChannelFocusListInfo> getVideoFocusList(@RequestParam(name = "page") Integer page,
                                                                     @RequestParam(name = "limit") Integer limit,
                                                                     @RequestParam(name = "channelId") Integer channelId);


    //VR管理-->焦点图
    @RequestMapping(value = "/channelFocus/getVRChannelFocusPage",method = RequestMethod.GET)
    TableResultResponse<VideoChannelFocusInfo> getVRChannelFocusPage(@RequestParam Map<String, Object> params);


    //PC管理-->焦点图
    @RequestMapping(value = "/channelFocus/getPcChannelFocusPage",method = RequestMethod.GET)
    ObjectRestResponse<List<PcChannelFocusInfo>> getPcChannelFocusPage();

    /**
     * 查询焦点图
     * @param newIds
     * @return
     */
    @RequestMapping(value = "/channelFocus/getChannelFocusByIdsAndChannelId",method = RequestMethod.POST)
    List<ChannelFocus> getChannelFocusByIdsAndChannelId(@RequestParam("ids") List<Integer> newIds);

    /**
     * 批量更新
     * @param channelFocusList
     * @return
     */
    @RequestMapping(value = "/channelFocus/batchUpdate",method = RequestMethod.POST)
    Integer batchUpdate(@RequestBody List<ChannelFocus> channelFocusList);

    @RequestMapping(value = "/channelFocus/addChannelFocus",method = RequestMethod.POST)
    ObjectRestResponse<ChannelFocusInfo> addChannelFocus(@RequestBody ChannelFocusInfo channelFocusInfo);

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/channelFocus/sortChannelFocus",method = RequestMethod.POST)
    ObjectRestResponse<Boolean> sortChannelFocus(@RequestBody Map<String, Object> params);

}
