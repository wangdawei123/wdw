package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.VideoType;
import com.kanfa.news.admin.vo.video.VideoTypeInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/2/28 11:00
 */

@FeignClient(name = "service-provider-news")
public interface IVideoTypeServiceFegin {

    //增加视频分类名称
    @RequestMapping(value = "/videoType", method = RequestMethod.POST)
    ObjectRestResponse<VideoType> add(@RequestBody VideoType entity);

    //编辑视频分类名称类型
    @RequestMapping(value = "/videoType/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<VideoType> get(@PathVariable("id") int id);

    //保存视频分类
    @RequestMapping(value = "/videoType/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<VideoType> update(@PathVariable("id") Integer id, @RequestBody VideoType entity);

    //删除视频分类
    @RequestMapping(value = "/videoType/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<VideoType> remove(@PathVariable("id") int id);

    //分页查询视频类型
    @RequestMapping(value = "/videoType/getPage")
    TableResultResponse<VideoTypeInfo> list(@RequestParam Map<String, Object> params);


    //视频分类字典
    @RequestMapping(value = "/videoType/all", method = RequestMethod.GET)
    @ResponseBody
    List<VideoType> all();
}
