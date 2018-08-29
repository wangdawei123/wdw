package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Province;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Chen
 * on 2018/3/18 16:31
 */

@FeignClient(name = "service-provider-news")
public interface IProvinceServiceFeign {

    //得到一个省名
    @RequestMapping(value = "/province/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<Province> get(@PathVariable("id") int id);

    //得到所有的省
    @RequestMapping(value = "/province/all", method = RequestMethod.GET)
    @ResponseBody
    List<Province> all();
}
