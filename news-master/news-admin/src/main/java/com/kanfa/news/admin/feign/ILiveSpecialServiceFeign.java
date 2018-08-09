package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.LiveSpecial;
import com.kanfa.news.admin.vo.live.LiveSpecialInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/3/16 16:22
 */

@FeignClient(name = "service-provider-news")
public interface ILiveSpecialServiceFeign {

    /**
     * 分页查询直播专题
     * @param page limit
     * @return
     */
    @RequestMapping(value = "/liveSpecial/getPage")
    TableResultResponse<LiveSpecialInfo> getPage(@RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "limit") Integer limit);



    /**
     * 搜索直播专题
     * @param page,limit,title
     * @return
     */
    @RequestMapping(value = "/liveSpecial/getSearchPage",method = RequestMethod.GET)
    TableResultResponse<LiveSpecialInfo> getSearchPage(@RequestParam(name = "page") Integer page,
                                                       @RequestParam(name = "limit") Integer limit,
                                                       @RequestParam(name = "title") String title);


    /**
     * 得到一个直播专题
     * @param id
     * @return
     */
    @RequestMapping(value = "/liveSpecial/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<LiveSpecial> get(@PathVariable("id") int id);

    /**
     * 编辑一个直播专题
     * @param id
     * @return
     */
    @RequestMapping(value = "/liveSpecial/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<LiveSpecial> update(@PathVariable("id") Integer id,
                                           @RequestBody LiveSpecial entity);

    /**
     * 新增直播专题
     * @param entity
     * @return
     */
    @RequestMapping(value = "/liveSpecial", method = RequestMethod.POST)
    ObjectRestResponse<LiveSpecial> add(@RequestBody LiveSpecial entity);
}
