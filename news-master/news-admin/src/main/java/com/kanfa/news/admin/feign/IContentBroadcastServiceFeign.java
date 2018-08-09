package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ContentBroadcast;
import com.kanfa.news.admin.vo.live.ContentBroadcastInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/3/28 17:42
 */
@FeignClient(name = "service-provider-news")
public interface IContentBroadcastServiceFeign {

    /**
     * aliyun列表分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/contentBroadcast/getPage",method = RequestMethod.POST)
    TableResultResponse<ContentBroadcastInfo> getPage(@RequestBody ContentBroadcastInfo entity);

    /**
     * 得到aliyun列表的详尽信息
     * @param
     * @return
     */
    @RequestMapping(value = "/contentBroadcast/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<ContentBroadcast> get(@PathVariable("id") int id);

    /**
     * 修改aliyun
     * @param
     * @return
     */
    @RequestMapping(value = "/contentBroadcast/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ContentBroadcast> update(@PathVariable("id") Integer id, @RequestBody ContentBroadcast entity);


    /**
     * 新增aliyun
     * @param
     * @return
     */
    @RequestMapping(value = "/contentBroadcast", method = RequestMethod.POST)
    ObjectRestResponse<ContentBroadcast> add(@RequestBody ContentBroadcast entity);


}
