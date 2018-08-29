package com.kanfa.news.app.feign;

import com.kanfa.news.app.vo.video.VideoAlbumAppInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-news")
public interface IVideoServiceFeign {
    @RequestMapping(value = "/login/updatePlayViews", method = RequestMethod.POST)
    ObjectRestResponse updateVideoViews(@RequestParam("id") Integer id,
                                        @RequestParam("fromApp") Integer fromApp);

    @RequestMapping(value = "/video/portrait", method = RequestMethod.POST)
    ObjectRestResponse getContentList(@RequestParam(value = "chanId") Integer chanId,
                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "pcount", defaultValue = "10") Integer pcout);

    @RequestMapping(value = "/video/getVideoDetail", method = RequestMethod.POST)
    ObjectRestResponse getVideoDetail(@RequestParam(value = "cid") Integer cid,
                                      @RequestParam(value = "cate") Integer cate);

    @RequestMapping(value = "/videoAlbum/getalbum", method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse<VideoAlbumAppInfo> getAlbum(@RequestParam(value = "id") Integer id,
                                                   @RequestParam(value = "user_id", defaultValue = "") Integer userId,
                                                   @RequestParam(value = "album_type", defaultValue = "") Integer albumType);
}
