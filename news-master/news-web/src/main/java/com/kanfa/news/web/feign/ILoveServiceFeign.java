package com.kanfa.news.web.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.vo.news.LoveInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiqc
 * @email jiqingchao@kanfanews.com
 * @date 2018/5/31 14:02
 */
@FeignClient(name = "service-provider-news")
public interface ILoveServiceFeign {


    @RequestMapping(value = "/love" ,method = RequestMethod.POST)
    ObjectRestResponse addLove(@RequestBody LoveInfo loveInfo);
}
