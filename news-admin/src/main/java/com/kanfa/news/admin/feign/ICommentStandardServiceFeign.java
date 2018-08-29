package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.CommentStandard;
import com.kanfa.news.admin.vo.comment.CommentStandardInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/24 17:30
 */
@FeignClient(name = "service-provider-news")
public interface ICommentStandardServiceFeign {
    /**
     * 分页查询（对应频道）
     * @param params
     * @return
     */
    @RequestMapping(value = "/commentStandard/getPage",method = RequestMethod.POST)
    TableResultResponse<CommentStandardInfo> page(@RequestBody Map<String, Object> params);

    @RequestMapping(value = "/commentStandard",method = RequestMethod.POST)
    ObjectRestResponse<CommentStandard> addCommentStandard(@RequestBody CommentStandard commentStandard);

    /**
     * 标准评论分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/commentStandard/getPageByTitle",method = RequestMethod.GET)
    TableResultResponse<CommentStandardInfo> getPageByTitle(@RequestParam Map<String, Object> params);

    /**
     * 获取单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/commentStandard/{id}",method = RequestMethod.GET)
    ObjectRestResponse<CommentStandardInfo> getCommentStandard(@PathVariable("id") Integer id);

    /**
     * 更新
     * @param id
     * @param commentStandard
     * @return
     */
    @RequestMapping(value = "/commentStandard/{id}",method = RequestMethod.PUT)
    ObjectRestResponse<CommentStandard> updateCommentStandard(@PathVariable("id") Integer id,@RequestBody CommentStandard commentStandard);
}
