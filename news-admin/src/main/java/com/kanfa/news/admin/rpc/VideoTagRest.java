package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.VideoTag;
import com.kanfa.news.admin.feign.IVideoTagServiceFeign;
import com.kanfa.news.admin.vo.video.VideoTagInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/6 14:35
 */
@RestController
@RequestMapping("videoTag")
public class VideoTagRest {
    @Autowired
    private IVideoTagServiceFeign videoTagServiceFeign;


    //增加视频标签
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<VideoTag> add(@RequestBody VideoTag videoTag) {
        //初始化VideoTag的属性
        //默认为未删除
        videoTag.setIsDelete(1);
        videoTag.setCreateTime(new Date());
        videoTag.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoTagServiceFeign.add(videoTag);
    }

    //得到一个视频标签
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<VideoTag> get(@PathVariable("id") Integer id) {
        return videoTagServiceFeign.get(id);
    }


    //修改视频标签
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<VideoTag> update(@PathVariable("id") Integer id, @RequestBody VideoTag videoTag) {
        videoTag.setId(id);
        videoTag.setCreateTime(new Date());
        videoTag.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoTagServiceFeign.update(id, videoTag);
    }

    //删除
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse publish(@PathVariable("id") Integer id) {
        VideoTag videoTag = new VideoTag();
        videoTag.setId(id);
        //是否已删除 1:未删除 0:已删除
        videoTag.setIsDelete(0);
        return videoTagServiceFeign.update(id, videoTag);
    }
    //分页查询
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<VideoTagInfo> list(@RequestParam Map<String, Object> params) {
        return videoTagServiceFeign.list(params);
    }

    //按专辑标题搜索  searchPage
    @RequestMapping(value = "/searchPage", method = RequestMethod.GET)
    public TableResultResponse<VideoTagInfo> getSearchPage(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam String text) {
        return videoTagServiceFeign.searchList(page, limit, text);
    }



}
