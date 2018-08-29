package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.VideoColumnBiz;
import com.kanfa.news.info.entity.VideoColumn;
import com.kanfa.news.info.vo.admin.video.VideoColumnAddInfo;
import com.kanfa.news.info.vo.admin.video.VideoColumnPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author user
 */
@RestController
@RequestMapping("videoColumn")
public class VideoColumnController extends BaseController<VideoColumnBiz,VideoColumn> {

    @Autowired
    private VideoColumnBiz videoColumnBiz;

    /**
     * 新增视频栏目
     * @param
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ObjectRestResponse addVideoColumn(@RequestBody VideoColumnAddInfo entity){
        return videoColumnBiz.addVideoColumn(entity);
    }

    /**
     * 分页查询视频/直播栏目列表
     * @param entity
     * @returnage
     */
    @RequestMapping(value = "/getPage",method = RequestMethod.POST)
    public TableResultResponse<VideoColumnPageInfo> getPage(@RequestBody VideoColumnPageInfo entity){
       return videoColumnBiz.getPage(entity);
    }

    /**
     * 查询视频/直播栏目列表 字典
     * @param
     * @returnage
     */
    @RequestMapping(value = "/selectAll",method = RequestMethod.GET)
    public ObjectRestResponse<List<VideoColumn>> selectAll(){
        VideoColumn videoColumn = new VideoColumn();
        videoColumn.setStat(1);
        List<VideoColumn> videoColumns = videoColumnBiz.selectList(videoColumn);
        ObjectRestResponse<List < VideoColumn >> objectObjectRestResponse = new ObjectRestResponse<List<VideoColumn>>();
        objectObjectRestResponse.setData(videoColumns);
        return  objectObjectRestResponse;
    }

}