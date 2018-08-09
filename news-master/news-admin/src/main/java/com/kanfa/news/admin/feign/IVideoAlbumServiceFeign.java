package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.VideoAlbum;
import com.kanfa.news.admin.vo.video.VideoAlbumInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Chen
 * on 2018/2/28 17:29
 */
@FeignClient(name = "service-provider-news")
public interface IVideoAlbumServiceFeign {

    //增加视频专辑列表
    @RequestMapping(value = "/videoAlbum", method = RequestMethod.POST)
    ObjectRestResponse<VideoAlbum> add(@RequestBody VideoAlbum entity);

    //编辑
    @RequestMapping(value = "/videoAlbum/{id}",method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<VideoAlbum> get(@PathVariable("id") int id);

    //保存
    @RequestMapping(value = "/videoAlbum/{id}",method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<VideoAlbum> update(@PathVariable("id") Integer id, @RequestBody VideoAlbum entity);

    //分页查询
    @RequestMapping(value = "/videoAlbum/getPage")
    TableResultResponse<VideoAlbumInfo> list(@RequestParam Map<String, Object> params);


    //按标题搜索
    @RequestMapping(value = "/videoAlbum/getSearchPage",method = RequestMethod.GET)
    TableResultResponse<VideoAlbumInfo> searchList(@RequestParam(name = "page") Integer page,
                                                   @RequestParam(name = "limit") Integer limit,
                                                   @RequestParam(name = "text") String text);

    //删除
    @RequestMapping(value = "/videoAlbum/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<VideoAlbum> remove(@PathVariable("id") int id);



    @RequestMapping(value = "/videoAlbum/recommendedExist",method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<Integer> getRecommendedExist();
}
