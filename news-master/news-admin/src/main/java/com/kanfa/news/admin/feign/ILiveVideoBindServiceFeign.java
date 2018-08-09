package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.LiveVideoBind;
import com.kanfa.news.admin.vo.live.LiveVideoBindInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/28 13:53
 */
@FeignClient(name = "service-provider-news")
public interface ILiveVideoBindServiceFeign {

    /**
     * 得到直播列表下的关联内容
     * @param liveId
     * @return
     */
    @RequestMapping(value = "/liveVideoBind/getAllBind", method = RequestMethod.GET)
    List<LiveVideoBindInfo> getBind(@RequestParam(name = "liveId") Integer liveId);

    /**
     * 直播列表下的关联内容的搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/liveVideoBind/getSearchPage", method = RequestMethod.GET)
    TableResultResponse<LiveVideoBindInfo> getSearchPage(@RequestParam(name = "page") Integer page,
                                                     @RequestParam(name = "limit") Integer limit,
                                                     @RequestParam(name = "fromType") Integer fromType,
                                                     @RequestParam(name = "liveId") Integer liveId,
                                                     @RequestParam(name = "title") String title);


    /**
     * 新增直播列表关联内容
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/liveVideoBind", method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse<LiveVideoBind> add(@RequestBody LiveVideoBind entity);

    @RequestMapping(value = "/liveVideoBind/addLiveVideoBind", method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse<String> addLiveVideoBind(@RequestBody LiveVideoBind entity);

    /**
     * 删除直播列表关联内容
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/liveVideoBind/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<LiveVideoBind> delete(@PathVariable("id") int id);


    /**
     * 通过bindId得到id
     * @param
     * @param cid,bindId
     * @return
     */
    @RequestMapping(value = "/liveVideoBind/getLiveVideoBindId", method = RequestMethod.GET)
    Integer getLiveVideoBindId(@RequestParam("cid")Integer cid,@RequestParam(name = "bindId")Integer bindId);

    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/liveVideoBind/getLiveVideoBind",method = RequestMethod.GET)
    List<LiveVideoBind> getLiveVideoBind(@RequestParam("ids") List<Integer> ids, @RequestParam("contentId") Integer contentId);

    /**
     * 排序
     * @param
     * @return
     */
    @RequestMapping(value = "/liveVideoBind/batchSort",method = RequestMethod.POST)
    ObjectRestResponse<Boolean> batchSort(@RequestBody Map<String, Object> params);

}
