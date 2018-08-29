package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.VideoAlbumBind;
import com.kanfa.news.admin.feign.IVideoAlbumBindServiceFeign;
import com.kanfa.news.admin.vo.video.VideoAlbumBindInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/3/5 11:52
 */

@RestController
@RequestMapping("videoAlbumBind")
public class VideoAlbumBindRest {
    @Autowired
    private IVideoAlbumBindServiceFeign videoAlbumBindServiceFeign;

    //增加视频关联
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<VideoAlbumBind> add(@RequestBody VideoAlbumBind videoAlbumBind) {
        return videoAlbumBindServiceFeign.add(videoAlbumBind);
    }

    //解除视频关联
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ObjectRestResponse<String> remove(@RequestParam("videoAlbumId")Integer videoAlbumId,
                                                     @RequestParam("videoId")Integer videoId) {
        return videoAlbumBindServiceFeign.remove(videoAlbumId,videoId);
    }

    //显示关联内容
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<VideoAlbumBindInfo> getPage(@RequestParam Integer page,
                                                           @RequestParam Integer limit,
                                                           @RequestParam Integer id) {
        return videoAlbumBindServiceFeign.getPage(page, limit, id);
    }

    //显示搜索标题
    @RequestMapping(value = "/getContentPage", method = RequestMethod.GET)
    public TableResultResponse<VideoAlbumBindInfo> getPage(@RequestParam Integer page,
                                                           @RequestParam Integer limit,
                                                           @RequestParam Integer videoAlbumId,
                                                           @RequestParam String text) {
        return videoAlbumBindServiceFeign.getPage(page, limit,videoAlbumId, text);
    }

}
