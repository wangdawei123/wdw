package com.kanfa.news.web.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.feign.callBack.ContentServiceFallBack;
import com.kanfa.news.web.vo.image.ImageDetailInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiqc
 * @date 2018/3/5 17:28
 */
@FeignClient(name = "service-provider-news", fallback = ContentServiceFallBack.class)
public interface IImageServiceFeign {

    /**
     * 查询图集
     * @param cid
     * @param cate
     * @return
     */
    @RequestMapping(value = "/content/getContentImage",method = RequestMethod.GET)
    ObjectRestResponse<ImageDetailInfo> getContentImage(@RequestParam("cid") Integer cid, @RequestParam("cate") Integer cate);
}
