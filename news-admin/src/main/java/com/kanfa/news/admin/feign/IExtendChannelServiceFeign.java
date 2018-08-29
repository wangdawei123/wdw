package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ExtendChannel;
import com.kanfa.news.admin.vo.activity.ExtendChannelPageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Chen
 * on 2018/4/16 11:49
 */
@FeignClient(name = "service-provider-news")
public interface IExtendChannelServiceFeign {


    /**
     * 推广渠道的分页及查询
     * @return
     */
    @RequestMapping(value = "/extendChannel/getExtendChannelPage",method = RequestMethod.POST)
    TableResultResponse<ExtendChannelPageInfo> getExtendChannelPage(@RequestBody ExtendChannelPageInfo entity);


    /**
     * 推广渠道 新增
     * @return
     */
    @RequestMapping(value = "/extendChannel", method = RequestMethod.POST)
    ObjectRestResponse<ExtendChannel> add(@RequestBody ExtendChannel entity);

    /**
     * 推广渠道 编辑
     * @return
     */
    @RequestMapping(value = "/extendChannel/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ExtendChannel> update(@PathVariable("id") Integer id, @RequestBody ExtendChannel entity);


    /**
     * 推广渠道 得到详情
     * @return
     */
    @RequestMapping(value = "/extendChannel/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<ExtendChannel> get(@PathVariable("id") int id);


    @RequestMapping(value = "/extendChannel/extendChannelIdNames",method = RequestMethod.GET)
    List<ExtendChannel> extendChannelIdNames();

}
