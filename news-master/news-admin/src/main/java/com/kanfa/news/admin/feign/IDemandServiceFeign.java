package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Demand;
import com.kanfa.news.admin.vo.my.DemandInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Chen
 * on 2018/5/15 16:38
 */
@FeignClient(name = "service-provider-news")
public interface IDemandServiceFeign {


    @RequestMapping(value = "/demand/insertDemand",method = RequestMethod.POST)
    Integer insertDemand(@RequestBody DemandInfo demandInfo);


    @RequestMapping(value = "/demand/getAllVideoMD",method = RequestMethod.GET)
    List<String> getAllViedeoMD();

    @RequestMapping(value = "/demand/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<Demand> get(@PathVariable("id") int id);

    @RequestMapping(value = "/demand/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<Demand> update(@PathVariable("id") Integer id, @RequestBody Demand entity);

    @RequestMapping(value = "/demand/getDemandId",method = RequestMethod.GET)
    Demand getDemandId(@RequestParam("name")String name);

    @RequestMapping(value = "/demand/getDemandByMedid",method = RequestMethod.GET)
    Demand getDemandByMedid(@RequestParam("medid")String medid);


}
