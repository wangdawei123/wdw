package com.kanfa.news.admin.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-common")
public interface IFileServiceFeign {
    /**
     * 图片上传
     *
     * @param file
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,
            value = "/file/upload",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ObjectRestResponse upload(@RequestPart(value = "file") MultipartFile file);

    /**
     * 图片地址的上传
     *
     * @param url
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,
            value = "/file/imgDownload",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ObjectRestResponse imgDownload(@RequestParam(value = "url") String url);

}
