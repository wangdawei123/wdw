package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.VideoTag;
import com.kanfa.news.admin.vo.video.VideoTagInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/6 14:31
 * 视频标签表
 */
@FeignClient(name = "service-provider-news")
public interface IVideoTagServiceFeign {
    //增加视频标签名称
    @RequestMapping(value = "/videoTag", method = RequestMethod.POST)
    ObjectRestResponse<VideoTag> add(@RequestBody VideoTag entity);

    //编辑视频标签
    @RequestMapping(value = "/videoTag/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<VideoTag> get(@PathVariable("id") int id);

    //保存视频标签
    @RequestMapping(value = "/videoTag/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<VideoTag> update(@PathVariable("id") Integer id, @RequestBody VideoTag entity);

    //删除视频标签
    @RequestMapping(value = "/videoTag/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<VideoTag> remove(@PathVariable("id") int id);

    //分页查询
    @RequestMapping(value = "/videoTag/getPage")
    TableResultResponse<VideoTagInfo> list(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/videoTag/getSearchPage",method = RequestMethod.GET)
    TableResultResponse<VideoTagInfo> searchList(@RequestParam(name = "page") Integer page,
                                                   @RequestParam(name = "limit") Integer limit,
                                                   @RequestParam(name = "text") String text);

}
