package com.kanfa.news.admin.rpc;

import com.alibaba.druid.util.StringUtils;
import com.kanfa.news.admin.entity.VideoAlbum;
import com.kanfa.news.admin.feign.IVideoAlbumServiceFeign;
import com.kanfa.news.admin.vo.video.VideoAlbumInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/2/28 18:12
 */
@RestController
@RequestMapping("videoAlbum")
public class VideoAlbumRest {
    @Autowired
    private IVideoAlbumServiceFeign videoAlbumServiceFeign;

    //增加视频专辑列表
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<VideoAlbum> add(@RequestBody VideoAlbum videoAlbum) {
        //初始化videoAlbum的属性
        if (StringUtils.isEmpty(videoAlbum.getTitle())) {
            ObjectRestResponse objectRestResponse = new ObjectRestResponse();
            objectRestResponse.setRel(true);
            objectRestResponse.setMessage("视频专辑标题不能为空");
            return objectRestResponse;
        }
        //未删除 默认1
        videoAlbum.setIsDelete(1);
        //已发布 默认1
        videoAlbum.setIsPublish(1);
        //专辑类型 1为普通 2为推荐 默认为1
        videoAlbum.setAlbumType(1);
        //设置videoAlbum的专辑排序  albumOrder
        //设置创建时间
        videoAlbum.setCreateTime(new Date());
        //获得创建的id
        videoAlbum.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoAlbumServiceFeign.add(videoAlbum);
    }

    //得到一个视频专辑
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<VideoAlbum> get(@PathVariable("id") Integer id) {
        return videoAlbumServiceFeign.get(id);
    }

    //编辑
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<VideoAlbum> update(@PathVariable("id") Integer id,
                                                 @RequestBody VideoAlbum videoAlbum) {
        videoAlbum.setId(id);
        videoAlbum.setUpdateTime(new Date());
        videoAlbum.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoAlbumServiceFeign.update(id, videoAlbum);
    }

    //分页查询
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public TableResultResponse<VideoAlbumInfo> get(@RequestParam Map<String, Object> params) {
        return videoAlbumServiceFeign.list(params);
    }

    //取消上线
    @RequestMapping(value = "/cancelPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse cancelPublish(@PathVariable("id") Integer id) {
        VideoAlbum videoAlbum = new VideoAlbum();
        videoAlbum.setIsPublish(0);
        videoAlbum.setId(id);
        return videoAlbumServiceFeign.update(id, videoAlbum);
    }

    //上线
    @RequestMapping(value = "/onPublish/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse onPublic(@PathVariable("id") Integer id) {
        VideoAlbum videoAlbum = new VideoAlbum();
        videoAlbum.setIsPublish(1);
        videoAlbum.setId(id);
        return videoAlbumServiceFeign.update(id, videoAlbum);
    }

    //删除 有字段
    //is_delete default 1 comment '是否已删除 1:未删除 0:已删除'
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse delete(@PathVariable("id") Integer id) {
        VideoAlbum videoAlbum = new VideoAlbum();
        videoAlbum.setId(id);
        videoAlbum.setIsDelete(0);
        return videoAlbumServiceFeign.update(id, videoAlbum);
    }

    //按专辑标题搜索  searchPage
    @RequestMapping(value = "/searchPage", method = RequestMethod.GET)
    public TableResultResponse<VideoAlbumInfo> getSearchPage(@RequestParam Integer page,
                                                             @RequestParam Integer limit,
                                                             @RequestParam String text) {
        return videoAlbumServiceFeign.searchList(page, limit, text);
    }

    @RequestMapping(value = "/recommendedExist",method = RequestMethod.GET)
    public ObjectRestResponse<Integer> getRecommendedExist(){
        return videoAlbumServiceFeign.getRecommendedExist();
    }
}
