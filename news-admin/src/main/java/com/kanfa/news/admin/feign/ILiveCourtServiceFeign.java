package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.LiveCourt;
import com.kanfa.news.admin.vo.live.LiveCourtInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/3/18 16:01
 */

@FeignClient(name = "service-provider-news")
public interface ILiveCourtServiceFeign {

    //分页查询
    @RequestMapping(value = "/liveCourt/getPage")
    TableResultResponse<LiveCourtInfo> list(@RequestParam(name = "page") Integer page,
                                            @RequestParam(name = "limit") Integer limit);

    //按标题搜索
    @RequestMapping(value = "/liveCourt/getSearchPage",method = RequestMethod.GET)
    TableResultResponse<LiveCourtInfo> searchList(@RequestParam(name = "page") Integer page,
                                                   @RequestParam(name = "limit") Integer limit,
                                                   @RequestParam(name = "name") String name);


    //保存
    @RequestMapping(value = "/liveCourt/{id}",method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<LiveCourt> update(@PathVariable("id") Integer id,
                                         @RequestBody LiveCourt entity);


    //增加
    @RequestMapping(value = "/liveCourt/addLiveCourt", method = RequestMethod.POST)
    ObjectRestResponse<LiveCourt> addLiveCourt(@RequestBody LiveCourt entity);

    //查找一个
    @RequestMapping(value = "/liveCourt/selectOne")
    LiveCourtInfo selectOne(@RequestParam(name = "id") Integer  id);
}
