package com.kanfa.news.common.client.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文件图片处理服务
 *
 * @author jezy
 */
@FeignClient(name = "service-provider-common")
public interface IFileServiceFeign {
    @PostMapping("/file/merger")
    ObjectRestResponse merger(@RequestParam(value = "file", required = false) String imagePath);
}
