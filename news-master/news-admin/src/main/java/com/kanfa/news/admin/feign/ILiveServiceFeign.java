package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.KpiTypeConfig;
import com.kanfa.news.admin.entity.Live;
import com.kanfa.news.admin.vo.content.ContentResponseInfo;
import com.kanfa.news.admin.vo.live.LiveAddInfo;
import com.kanfa.news.admin.vo.live.LiveInfo;
import com.kanfa.news.admin.vo.live.LivePageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/16 10:27
 */
@FeignClient(name = "service-provider-news")
public interface ILiveServiceFeign {

    /**
     * 直播分页查询
     * @param live
     * @return
     */
    @RequestMapping(value = "/live/getLivePage", method = RequestMethod.POST)
    TableResultResponse<LiveInfo> getLivePage(@RequestBody LiveInfo live);

    @RequestMapping(value = "/live/{id}", method = RequestMethod.GET)
    ObjectRestResponse<Live> get(@PathVariable("id") Integer id);

    /**
     * 新增直播
     * @param
     * @return
     */
    @RequestMapping(value = "/live/addOneLive", method = RequestMethod.POST)
    ObjectRestResponse<LiveAddInfo> addOneLive(@RequestBody LiveAddInfo entity);

    /**
     * 得到直播的详尽信息
     * @param liveId
     * @return
     */
    @RequestMapping(value = "/live/getOneLive",method = RequestMethod.GET)
    LiveAddInfo getOneLive(@RequestParam("liveId") Integer liveId);

    /**
     * 编辑直播
     * @param
     * @return
     */
    @RequestMapping(value = "/live/updateLiveInfo", method = RequestMethod.POST)
    ObjectRestResponse<LiveAddInfo> updateLiveAddInfo(@RequestBody LiveAddInfo entity);


    /**
     * 直播列表分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/live/getLiveSearchPage",method = RequestMethod.POST)
    TableResultResponse<LivePageInfo> getLiveSearchPage(@RequestBody LivePageInfo entity);


    //保存直播 单表(慎用)
    @RequestMapping(value = "/live/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<Live> update(@PathVariable("id") Integer id, @RequestBody Live entity);


    /**
     * 得到一个直播
     * @param
     * @return
     */
    @RequestMapping(value = "/live/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<Live> get(@PathVariable("id") int id);

    /**
     * Live里的工作类型
     * @param
     * @return
     */
    @RequestMapping(value = "/kpiTypeConfig/selectWorkTypeForLive", method = RequestMethod.GET)
    List<KpiTypeConfig> selectWorkTypeForLive();

    /**
     * 查询直播表分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/live/getLiveForMessage", method = RequestMethod.GET)
    TableResultResponse<ContentResponseInfo> getLiveForMessage(@RequestParam Map<String, Object> params);


}
