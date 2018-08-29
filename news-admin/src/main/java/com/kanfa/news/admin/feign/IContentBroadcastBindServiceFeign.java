package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.ContentBroadcastBind;
import com.kanfa.news.admin.vo.video.ContentBroadcastBindInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/23 15:54
 */
@FeignClient(name = "service-provider-news")
public interface IContentBroadcastBindServiceFeign {
    /**
     * 分页显示关联视频
     * @param page,limit,id
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind/getPage", method = RequestMethod.GET)
    TableResultResponse<ContentBroadcastBindInfo> getPage(@RequestParam(name = "page") Integer page,
                                                          @RequestParam(name = "limit") Integer limit,
                                                          @RequestParam(name = "contentId") Integer contentId);




    /**
     * 搜索显示关联视频
     *
     * @param page,limit,text
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind/getSearchPage", method = RequestMethod.GET)
    TableResultResponse<ContentBroadcastBindInfo> getSearchPage(@RequestParam(name = "page") Integer page,
                                                    @RequestParam(name = "limit") Integer limit,
                                                    @RequestParam(name = "contentId") Integer contentId,
                                                    @RequestParam(name = "title")String title);
    /**
     * 新增视频列表的关联内容
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind", method = RequestMethod.POST)
    ObjectRestResponse<ContentBroadcastBind> add(@RequestBody ContentBroadcastBind entity);

    /**
     * 删除视频列表的关联内容
     * @param id
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<ContentBroadcastBind> remove(@PathVariable("id") int id);


    @RequestMapping(value = "/contentBroadcastBind/getMaxOrderNumber",method = RequestMethod.GET )
    Integer getMaxOrderNumber();


    @RequestMapping(value = "/contentBroadcastBind/deleteBind",method = RequestMethod.GET )
    void deleteBind(@RequestParam(name = "bindId")Integer bindId,@RequestParam("contentId")Integer contentId);

    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind/getBroadcastBind",method = RequestMethod.GET)
    List<ContentBroadcastBind> getBroadcastBind(@RequestParam("ids") List<Integer> ids, @RequestParam("contentId") Integer contentId);

    /**
     * 批量更新
     * @param contentBroadcastBindList
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind/batchUpdate",method = RequestMethod.POST)
    Integer batchUpdate(@RequestBody List<ContentBroadcastBind> contentBroadcastBindList);

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind/sortBindContent",method = RequestMethod.POST)
    ObjectRestResponse<Boolean> sortBindContent(@RequestBody Map<String, Object> params);

    /**
     * 文章绑定更新
     * @param ids
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/contentBroadcastBind/batchBindAndDelete",method = RequestMethod.GET)
    ObjectRestResponse batchBindAndDelete(@RequestBody List<Integer> ids, @RequestParam("contentId") Integer contentId);
}
