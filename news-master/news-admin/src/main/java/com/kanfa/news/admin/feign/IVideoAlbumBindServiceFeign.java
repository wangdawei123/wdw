package com.kanfa.news.admin.feign;


import com.kanfa.news.admin.entity.VideoAlbumBind;
import com.kanfa.news.admin.vo.video.VideoAlbumBindInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/3/5 11:43
 */
@FeignClient(name = "service-provider-news")
public interface IVideoAlbumBindServiceFeign {
    /**
     * 新增视频关联内容
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/videoAlbumBind", method = RequestMethod.POST)
    ObjectRestResponse<VideoAlbumBind> add(@RequestBody VideoAlbumBind entity);

    /**
     * 分页显示关联视频
     *
     * @param page,limit,id
     * @return
     */
    @RequestMapping(value = "/videoAlbumBind/getPage", method = RequestMethod.GET)
    TableResultResponse<VideoAlbumBindInfo> getPage(@RequestParam(name = "page") Integer page,
                                                    @RequestParam(name = "limit") Integer limit,
                                                    @RequestParam(name = "id") Integer id);

//    /**
//     * 删除视频关联
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/videoAlbumBind/{id}",method = RequestMethod.DELETE)
//    @ResponseBody
//    ObjectRestResponse<VideoAlbumBind> remove(@PathVariable("id") int id);

    /**
     * 分页显示关联视频
     *
     * @param page,limit,text
     * @return
     */
    @RequestMapping(value = "/videoAlbumBind/getContentPage", method = RequestMethod.GET)
    TableResultResponse<VideoAlbumBindInfo> getPage(@RequestParam(name = "page") Integer page,
                                                    @RequestParam(name = "limit") Integer limit,
                                                    @RequestParam(name = "videoAlbumId") Integer videoAlbumId,
                                                    @RequestParam(name = "text") String text);


    @RequestMapping(value = "/videoAlbumBind/deleteBind",method = RequestMethod.GET)
    ObjectRestResponse<String> remove(@RequestParam("videoAlbumId")Integer videoAlbumId,
                                      @RequestParam("video_id")Integer videoId);

}
