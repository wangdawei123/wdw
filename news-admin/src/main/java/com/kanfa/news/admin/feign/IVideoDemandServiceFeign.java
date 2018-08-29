package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Demand;
import com.kanfa.news.admin.vo.my.MyDemandPageInfo;
import com.kanfa.news.admin.vo.video.VideoDemandInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/3/15 14:03
 */

@FeignClient(name = "service-provider-news")
public interface IVideoDemandServiceFeign {

    /**
     * 分页查询视频类型
     * @param page limit
     * @return
     */
    @RequestMapping(value = "/demand/getPage")
    TableResultResponse<VideoDemandInfo> getPage(@RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "limit") Integer limit);



    /**
     * 搜索
     * @param page,limit,title
     * @return
     */
    @RequestMapping(value = "/demand/getSearchPage",method = RequestMethod.GET)
    TableResultResponse<VideoDemandInfo> getSearchPage(@RequestParam(name = "page") Integer page,
                                                       @RequestParam(name = "limit") Integer limit,
                                                       @RequestParam(name = "title") String title);


    /**
     * 删除某条视频
     * @param id
     * @return
     */
    @RequestMapping(value = "/demand/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<Demand> remove(@PathVariable("id") int id);


    /**
     * 我的视频库 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/demand/getMyDemandPage",method = RequestMethod.POST)
    TableResultResponse<MyDemandPageInfo> getMyDemandPage(@RequestBody MyDemandPageInfo entity);


}
