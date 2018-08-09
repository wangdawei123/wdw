package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.VideoAlbumBiz;
import com.kanfa.news.info.entity.VideoAlbum;
import com.kanfa.news.info.vo.admin.video.VideoAlbumInfo;
import com.kanfa.news.info.vo.app.video.VideoAlbumAppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("videoAlbum")
public class VideoAlbumController extends BaseController<VideoAlbumBiz, VideoAlbum> {
    @Autowired
    private VideoAlbumBiz videoAlbumBiz;


    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<VideoAlbumInfo> getPage(@RequestParam Map<String, Object> params) {
        //查询视频专辑列表数据
        Query query = new Query(params);
        return videoAlbumBiz.page(query);
    }

    @RequestMapping(value = "/getSearchPage", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<VideoAlbumInfo> getSearchPage(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam String text) {
        //根据标题搜索
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return videoAlbumBiz.searchPage(query, text);
    }

    /**
     * 视频专辑
     *
     * @param id     专辑ID
     * @param userId 用户ID
     * @return
     */
    @RequestMapping(value = "/getalbum", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<VideoAlbumAppInfo> getAlbum(@RequestParam(value = "id") Integer id,
                                                          @RequestParam(value = "user_id", defaultValue = "") Integer userId,
                                                          @RequestParam(value = "album_type", defaultValue = "") Integer albumType) {
        return videoAlbumBiz.getAlbum(id, userId, albumType);
    }

    /**
     * 视频专辑 判断是否已有推荐 album_type = 2
     *  存在为1 不存在为0
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/recommendedExist",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Integer> recommendedExist(){
        return videoAlbumBiz.recommendedExist();
    }


}