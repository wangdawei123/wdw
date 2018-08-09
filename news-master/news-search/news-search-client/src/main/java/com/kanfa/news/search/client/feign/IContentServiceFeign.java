package com.kanfa.news.search.client.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.search.client.entity.Content;
import com.kanfa.news.search.client.vo.ContentInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/5 17:28
 */
@FeignClient(name = "service-provider-news")
public interface IContentServiceFeign {

    /**
     * 根据id查询内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/{id}",method = RequestMethod.GET)
    ObjectRestResponse<Content> get(@PathVariable("id") int id);

    @GetMapping("/content/addContentsListDataForEs")
    Map<String ,Object> addContentsListDataForEs(@RequestParam("map") Map<String, Object> map);

    @GetMapping("/content/addLivesListDataForEs")
    Map<String ,Object> addLivesListDataForEs(@RequestParam("map") Map<String, Object> map);

    @GetMapping("/content/listIds")
    List<Integer> listIds(@RequestParam("start") Integer start, @RequestParam("size") Integer size);

}
