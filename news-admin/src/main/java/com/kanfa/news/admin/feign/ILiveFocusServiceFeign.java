package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.LiveFocus;
import com.kanfa.news.admin.vo.live.LiveFocusInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/3/24 11:55
 */
@FeignClient(name = "service-provider-news")
public interface ILiveFocusServiceFeign {

    /**
     * 直播焦点图的分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/liveFocus/getPage",method = RequestMethod.POST)
    TableResultResponse<LiveFocusInfo> getPage(@RequestBody LiveFocusInfo entity);

    /**
     * 得到直播焦点图信息
     * @param
     * @return
     */
    @RequestMapping(value = "/liveFocus/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<LiveFocus> get(@PathVariable("id") int id);

    /**
     * 编辑直播焦点图
     * @param
     * @return
     */
    @RequestMapping(value = "/liveFocus/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<LiveFocus> update(@PathVariable("id") Integer id, @RequestBody LiveFocus entity);


    /**
     * 新增直播焦点图
     * @param
     * @return
     */
    @RequestMapping(value = "/liveFocus", method = RequestMethod.POST)
    ObjectRestResponse<LiveFocus> add(@RequestBody LiveFocus entity);

}
