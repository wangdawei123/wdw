package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.VideoSource;
import com.kanfa.news.admin.vo.video.VideoSourceInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/5 14:27
 * 作者渠道列表
 */

@FeignClient(name = "service-provider-news")
public interface IVideoSourceServiceFeign {
    //增加作者渠道列表
    @RequestMapping(value = "/videoSource", method = RequestMethod.POST)
    ObjectRestResponse<VideoSource> add(@RequestBody VideoSource entity);

    //编辑作者渠道列表
    @RequestMapping(value = "/videoSource/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<VideoSource> get(@PathVariable("id") int id);

    //保存作者渠道列表
    @RequestMapping(value = "/videoSource/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<VideoSource> update(@PathVariable("id") Integer id, @RequestBody VideoSource entity);

    //删除作者渠道列表
    @RequestMapping(value = "/videoSource/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<VideoSource> remove(@PathVariable("id") int id);

    //分页查询作者渠道列表
    @RequestMapping(value = "/videoSource/getPage")
    TableResultResponse<VideoSourceInfo> list(@RequestParam Map<String, Object> params);



    //得到所有的作者渠道列表
    @RequestMapping(value = "/videoSource/all", method = RequestMethod.GET)
    @ResponseBody
    List<VideoSource> all();
}
