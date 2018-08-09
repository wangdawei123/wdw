package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.VideoSource;
import com.kanfa.news.admin.feign.IVideoSourceServiceFeign;
import com.kanfa.news.admin.vo.video.VideoSourceIdName;
import com.kanfa.news.admin.vo.video.VideoSourceInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/5 14:31
 */

@RestController
@RequestMapping("videoSource")
public class VideoSourceRest {
    @Autowired
    private IVideoSourceServiceFeign videoSourceServiceFeign;

    //新增 作者渠道列表
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<VideoSource> add(@RequestBody VideoSource videoSource) {
        //初始化 作者渠道列表的属性
        videoSource.setCreateTime(new Date());
        videoSource.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoSourceServiceFeign.add(videoSource);
    }

    //得到一个作者渠道信息
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<VideoSource> get(@PathVariable("id") Integer id) {
        return videoSourceServiceFeign.get(id);
    }

    //修改作者渠道名字+图片
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<VideoSource> update(@PathVariable("id") Integer id,@RequestBody VideoSource videoSource) {
        videoSource.setId(id);
        videoSource.setUpdateTime(new Date());
        videoSource.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoSourceServiceFeign.update(id, videoSource);
    }

    //作者渠道列表分页查询
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<VideoSourceInfo> getPage(@RequestParam Map<String, Object> params) {
        return videoSourceServiceFeign.list(params);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ObjectRestResponse<List<VideoSourceIdName>> all(){
        List<VideoSource> all = videoSourceServiceFeign.all();
        ArrayList<VideoSourceIdName> videoSourceIdNames = new ArrayList<>();
        for (VideoSource videoSource:all) {
            VideoSourceIdName videoSourceIdName = new VideoSourceIdName();
            BeanUtils.copyProperties(videoSource,videoSourceIdName);
            videoSourceIdNames.add(videoSourceIdName);
        }
        ObjectRestResponse<List<VideoSourceIdName>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(videoSourceIdNames);
        return listObjectRestResponse;
    }


}
