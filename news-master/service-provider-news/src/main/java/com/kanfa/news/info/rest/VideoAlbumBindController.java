package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.VideoAlbumBindBiz;
import com.kanfa.news.info.entity.VideoAlbumBind;
import com.kanfa.news.info.vo.admin.video.VideoAlbumBindInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("videoAlbumBind")
public class VideoAlbumBindController extends BaseController<VideoAlbumBindBiz,VideoAlbumBind> {

    @Autowired
    private VideoAlbumBindBiz videoAlbumBindBiz;

    @RequestMapping(value = "/getPage",method = RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<VideoAlbumBindInfo> getPage(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam Integer id){
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return videoAlbumBindBiz.getPage(query,id);
    }

    @RequestMapping(value = "/getContentPage",method = RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<VideoAlbumBindInfo> getContentPage(@RequestParam Integer page, @RequestParam Integer limit,@RequestParam(name = "videoAlbumId") Integer videoAlbumId, @RequestParam String text){
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return videoAlbumBindBiz.getContentPage(query,videoAlbumId,text);
    }

    @RequestMapping(value = "/deleteBind",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<String> delete(@RequestParam("videoAlbumId")Integer videoAlbumId,
                                             @RequestParam("videoId")Integer videoId){
        VideoAlbumBind videoAlbumBind = new VideoAlbumBind();
        videoAlbumBind.setVideoAlbumId(videoAlbumId);
        videoAlbumBind.setVideoId(videoId);
        videoAlbumBindBiz.delete(videoAlbumBind);
        String msg = "已删除";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }
}