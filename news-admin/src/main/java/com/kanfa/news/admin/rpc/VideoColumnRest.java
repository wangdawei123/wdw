package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.VideoColumn;
import com.kanfa.news.admin.feign.IVideoColumnServiceFeign;
import com.kanfa.news.admin.vo.video.VideoColumnAddInfo;
import com.kanfa.news.admin.vo.video.VideoColumnPageInfo;
import com.kanfa.news.admin.vo.video.VideoColumnShow;
import com.kanfa.news.admin.vo.video.VideoColumnZi;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chen
 * on 2018/8/15 11:43
 * @author user
 */
@RestController
@RequestMapping("videoColumn")
public class VideoColumnRest {

    @Autowired
    private IVideoColumnServiceFeign iVideoColumnServiceFeign;

    /**
     * 新增视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ObjectRestResponse add(@RequestBody VideoColumnAddInfo entity){
        if (entity.getTitle() == null) {
            String msg = "栏目标题不能为空";
            return getObjectRestResponse(msg);
        }
        if (entity.getCoverImg() == null) {
            String msg = "封面图不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        return iVideoColumnServiceFeign.addVideoColumn(entity);
    }

    /**
     * 分页查询视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/getPage",method = RequestMethod.POST)
    public TableResultResponse<VideoColumnPageInfo> getPage(@RequestBody VideoColumnPageInfo entity){
        return iVideoColumnServiceFeign.getPage(entity);
    }


    /**
     * 得到一个查询视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/getOne",method = RequestMethod.GET)
    public ObjectRestResponse<VideoColumnShow> getOne(@RequestParam("id")Integer id){
        ObjectRestResponse<VideoColumn> videoColumnObjectRestResponse = iVideoColumnServiceFeign.get(id);
        VideoColumn data = videoColumnObjectRestResponse.getData();
        VideoColumnShow videoColumnShow = new VideoColumnShow();
        BeanUtils.copyProperties(data,videoColumnShow);
        ObjectRestResponse<VideoColumnShow> videoColumnAddInfoObjectRestResponse = new ObjectRestResponse<>();
        videoColumnAddInfoObjectRestResponse.setData(videoColumnShow);
        return videoColumnAddInfoObjectRestResponse;
    }


    /**
     * 修改查询视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ObjectRestResponse updateVideoColumn(@RequestBody VideoColumnAddInfo entity){
        VideoColumn videoColumn = new VideoColumn();
        BeanUtils.copyProperties(entity,videoColumn);
        videoColumn.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        videoColumn.setUpdateTime(new Date());
        return iVideoColumnServiceFeign.update(entity.getId(),videoColumn);
    }

    /**
     * 删除查询视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ObjectRestResponse delete(@RequestParam("id")Integer id){
        VideoColumn videoColumn = new VideoColumn();
        videoColumn.setId(id);
        videoColumn.setStat(0);
        videoColumn.setUpdateTime(new Date());
        videoColumn.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return iVideoColumnServiceFeign.update(id,videoColumn);
    }

    /**
     * 视频/直播栏目列表 字典
     * @param
     * @return  VideoColumnZi
     */
    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public ObjectRestResponse<List<VideoColumnZi>> getAll(){
        ObjectRestResponse<List<VideoColumn>> listObjectRestResponse = iVideoColumnServiceFeign.selectAll();
        List<VideoColumn> data = listObjectRestResponse.getData();
        List<VideoColumnZi> videoColumnZis = new ArrayList<VideoColumnZi>();
        for (VideoColumn videoColumn:data) {
            VideoColumnZi videoColumnZi = new VideoColumnZi();
            BeanUtils.copyProperties(videoColumn,videoColumnZi);
            videoColumnZis.add(videoColumnZi);
        }
        ObjectRestResponse<List<VideoColumnZi>> listObjectRestResponse1 = new ObjectRestResponse();
        listObjectRestResponse1.setData(videoColumnZis);
        return listObjectRestResponse1;
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
