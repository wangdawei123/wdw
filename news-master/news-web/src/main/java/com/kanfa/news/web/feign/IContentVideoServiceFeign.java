package com.kanfa.news.web.feign;

import com.kanfa.news.web.feign.callBack.ContentVideoServiceFallBack;
import com.kanfa.news.web.vo.channel.ContentInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/5 17:28
 */
@FeignClient(name = "service-provider-news", fallback = ContentVideoServiceFallBack.class)
public interface IContentVideoServiceFeign {



    /**
     * 根据id查询内容+内容详情表
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getContentVideoById/{id}",method = RequestMethod.GET)
    ContentInfo getContentVideoById(@PathVariable("id") int id);

}
