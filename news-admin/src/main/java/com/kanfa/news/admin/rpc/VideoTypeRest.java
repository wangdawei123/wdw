package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.VideoType;
import com.kanfa.news.admin.feign.IVideoTypeServiceFegin;
import com.kanfa.news.admin.vo.video.VideoTypeIdName;
import com.kanfa.news.admin.vo.video.VideoTypeInfo;
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
 * on 2018/2/28 11:33
 * 视频分类
 */
@RestController
@RequestMapping("videoType")
public class VideoTypeRest {
    @Autowired
    private IVideoTypeServiceFegin videoTypeServiceFegin;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<VideoType> add(@RequestBody VideoType videoType) {
        videoType.setCreateTime(new Date());
        videoType.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoTypeServiceFegin.add(videoType);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<VideoType> get(@PathVariable("id") Integer id) {
        return videoTypeServiceFegin.get(id);
    }

    //修改视频分类名字
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<VideoType> update(@PathVariable("id") Integer id,@RequestBody VideoType videoType) {
        videoType.setId(id);
        videoType.setUpdateTime(new Date());
        videoType.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoTypeServiceFegin.update(id, videoType);
    }

    //视频分类分页查询
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public TableResultResponse<VideoTypeInfo> get(@RequestParam Map<String, Object> params) {
        return videoTypeServiceFegin.list(params);
    }

    //视频分类取消发布
    @RequestMapping(value = "/cancelPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse cancelPublish(@PathVariable("id") Integer id) {
        VideoType videoType = new VideoType();
        videoType.setIsPublish(0);
        videoType.setId(id);
        videoType.setUpdateTime(new Date());
        videoType.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoTypeServiceFegin.update(id, videoType);
    }

    //视频分类发布
    @RequestMapping(value = "/onPublish/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse onPublic(@PathVariable("id")Integer id){
        VideoType videoType = new VideoType();
        videoType.setIsPublish(1);
        videoType.setId(id);
        videoType.setUpdateTime(new Date());
        videoType.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return videoTypeServiceFegin.update(id, videoType);
    }

    //移除视频分类类别
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<VideoType> remove(@PathVariable("id") int id) {
        return videoTypeServiceFegin.remove(id);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ObjectRestResponse<List<VideoTypeIdName>> all(){
        List<VideoType> all = videoTypeServiceFegin.all();
        ArrayList<VideoTypeIdName> videoTypeIdNames = new ArrayList<>();
        for (VideoType videoType:all) {
            VideoTypeIdName videoTypeIdName = new VideoTypeIdName();
            BeanUtils.copyProperties(videoType,videoTypeIdName);
            videoTypeIdNames.add(videoTypeIdName);
        }
        ObjectRestResponse<List<VideoTypeIdName>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(videoTypeIdNames);
        return listObjectRestResponse;
    }



}
