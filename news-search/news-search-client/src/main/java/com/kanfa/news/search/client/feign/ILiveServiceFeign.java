package com.kanfa.news.search.client.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.search.client.entity.Live;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Jiqc
 * @date 2018/3/16 10:27
 */
@FeignClient(name = "service-provider-news")
public interface ILiveServiceFeign {

    @RequestMapping(value = "/live/{id}", method = RequestMethod.GET)
    ObjectRestResponse<Live> get(@PathVariable("id") Integer id);


    @GetMapping("/live/listIds")
    List<Integer> listIds(@RequestParam("start") Integer start, @RequestParam("size") Integer size);

}
