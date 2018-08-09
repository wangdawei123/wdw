package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.LiveSpecialBind;
import com.kanfa.news.admin.vo.live.LiveSpecialBindInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/18 14:39
 */
@FeignClient(name = "service-provider-news")
public interface ILiveSpecialBindServiceFeign {
    /**
     * 分页显示关联视频
     *
     * @param page,limit,id
     * @return
     */
    @RequestMapping(value = "/liveSpecialBind/getPage", method = RequestMethod.GET)
    TableResultResponse<LiveSpecialBindInfo> getPage(@RequestParam(name = "page") Integer page,
                                                     @RequestParam(name = "limit") Integer limit,
                                                     @RequestParam(name = "liveSpecialId") Integer liveSpecialId);

    /**
     * 分页显示按标题搜索关联视频
     *
     * @param page,limit,text
     * @return
     */
    @RequestMapping(value = "/liveSpecialBind/getSearchPage", method = RequestMethod.GET)
    TableResultResponse<LiveSpecialBindInfo> getPage(@RequestParam(name = "page") Integer page,
                                                    @RequestParam(name = "limit") Integer limit,
                                                    @RequestParam(name = "liveSpecialId") Integer liveSpecialId,
                                                    @RequestParam(name = "title") String title);



    /**
     * 新增直播专题关联内容
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/liveSpecialBind", method = RequestMethod.POST)
    ObjectRestResponse<LiveSpecialBind> add(@RequestBody LiveSpecialBind entity);


    /**
     * 删除直播专题关联内容
     *
     * @param liveId
     * @return
     */
    @RequestMapping(value = "/liveSpecialBind/deleteLiveSpecialBind", method = RequestMethod.GET)
    void delete(@RequestParam("liveId") Integer liveId);


    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/liveSpecialBind/getLiveSpecialBind",method = RequestMethod.GET)
    List<LiveSpecialBind> getLiveSpecialBind(@RequestParam("ids") List<Integer> ids, @RequestParam("contentId") Integer contentId);

    /**
     * 排序
     * @param
     * @return
     */
    @RequestMapping(value = "/liveSpecialBind/batchSort",method = RequestMethod.POST)
    ObjectRestResponse<Boolean> batchSort(@RequestBody Map<String, Object> params);

    /**
     * 通过liveSpecialId查找sort最大值
     * @param
     * @return
     */
    @RequestMapping(value = "/liveSpecialBind/getMaxSort",method = RequestMethod.GET)
    Integer getMaxSort(@RequestParam("liveSpecialId") Integer liveSpecialId);


}
