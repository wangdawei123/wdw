package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.VideoColumn;
import com.kanfa.news.admin.vo.video.VideoColumnAddInfo;
import com.kanfa.news.admin.vo.video.VideoColumnPageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Chen
 * on 2018/8/15 11:37
 */
@FeignClient(name = "service-provider-news")
public interface IVideoColumnServiceFeign {


    /**
     * 新增视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/videoColumn/add",method = RequestMethod.POST)
    ObjectRestResponse addVideoColumn(@RequestBody VideoColumnAddInfo entity);

    /**
     * 分页查询视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/videoColumn/getPage",method = RequestMethod.POST)
    TableResultResponse<VideoColumnPageInfo> getPage(@RequestBody VideoColumnPageInfo entity);

    /**
     * 得到一个查询视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/videoColumn/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<VideoColumn> get(@PathVariable("id") int id);

    /**
     * 修改视频/直播栏目列表
     * @param
     * @return
     */
    @RequestMapping(value = "/videoColumn/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<VideoColumn> update(@PathVariable("id") Integer id, @RequestBody VideoColumn entity);

    /**
     * 视频/直播栏目列表 字典
     * @param
     * @return
     */
    @RequestMapping(value = "/videoColumn/selectAll",method = RequestMethod.GET)
    ObjectRestResponse<List<VideoColumn>> selectAll();


}
