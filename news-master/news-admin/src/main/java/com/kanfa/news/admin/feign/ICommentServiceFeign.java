package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Comment;
import com.kanfa.news.admin.entity.CommentStandard;
import com.kanfa.news.admin.vo.comment.CommentBlacklistInfo;
import com.kanfa.news.admin.vo.comment.CommentInfo;
import com.kanfa.news.admin.vo.setting.SettingInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/24 17:30
 */
@FeignClient(name = "service-provider-news")
public interface ICommentServiceFeign {
    /**
     * 分页查询（对应审核）
     * @param commentInfo
     * @return
     */
    @RequestMapping(value = "/comment/getPageForCheck",method = RequestMethod.POST)
    TableResultResponse<CommentInfo> getPageForCheck(@RequestBody CommentInfo commentInfo);

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    ObjectRestResponse<CommentStandard> addCommentStandard(@RequestBody CommentStandard commentStandard);

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.PUT)
    ObjectRestResponse<Comment> update(@PathVariable("id") Integer id, @RequestBody Comment comment);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/comment/batchDelete",method = RequestMethod.POST)
    ObjectRestResponse<Integer> batchDelete(@RequestBody List<Integer> ids);

    /**
     * 黑名单分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/commentBlacklist/getBlackUserPage",method = RequestMethod.GET)
    TableResultResponse<CommentBlacklistInfo> getBlackUserPage(@RequestParam Map<String, Object> params);

    /**
     * 设置评论
     * @param params
     * @return
     */
    @RequestMapping(value = "/comment/saveCommentSetting",method = RequestMethod.GET)
    ObjectRestResponse<Integer> saveCommentSetting(@RequestParam Map<String, Object> params);

    /**
     * 通过审核
     * @param id
     * @return
     */
    @RequestMapping(value = "/comment/passComment/{id}",method = RequestMethod.PUT)
    ObjectRestResponse<Comment> passComment(@PathVariable("id") Integer id);

    /**
     * 批量通过
     * @param ids
     * @return
     */
    @RequestMapping(value = "/comment/batchPassComment",method = RequestMethod.GET)
    ObjectRestResponse<Comment> batchPassComment(@RequestParam("ids") List<Integer> ids);

    /**
     * 删除单个评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/comment/updateContentStat",method = RequestMethod.PUT)
    ObjectRestResponse<Comment> updateContentStat(@RequestParam("id") Integer id);

    /**
     * 屏蔽
     * @param commentInfo
     * @return
     */
    @RequestMapping(value = "/comment/blockComment",method = RequestMethod.POST)
    ObjectRestResponse<CommentInfo> blockComment(@RequestBody CommentInfo commentInfo);

    /**
     * 解除屏蔽
     * @param id
     * @return
     */
    @RequestMapping(value = "/commentBlacklist/{id}",method = RequestMethod.DELETE)
    ObjectRestResponse<CommentBlacklistInfo> deleteBlackUser(@PathVariable("id") Integer id);

    /**
     * 获取设置信息
     * @return
     */
    @RequestMapping(value = "/setting/getCommentSetting",method = RequestMethod.GET)
    ObjectRestResponse<SettingInfo> getCommentSetting();
}
