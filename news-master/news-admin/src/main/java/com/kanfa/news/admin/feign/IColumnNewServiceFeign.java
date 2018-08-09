package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ColumnNew;
import com.kanfa.news.admin.vo.column.ColumnNewPageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/25 15:44
 */
@FeignClient(name = "service-provider-news")
public interface IColumnNewServiceFeign {


    @RequestMapping(value = "/columnNew/getColumnPage",method = RequestMethod.POST)
    TableResultResponse<ColumnNewPageInfo> getColumnPage(@RequestBody ColumnNewPageInfo entity);


    //得到
    @RequestMapping(value = "/columnNew/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<ColumnNew> get(@PathVariable("id") int id);

    //编辑
    @RequestMapping(value = "/columnNew/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<ColumnNew> update(@PathVariable("id") Integer id, @RequestBody ColumnNew entity);


}
