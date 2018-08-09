package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Comment;
import com.kanfa.news.admin.feign.ICommentServiceFeign;
import com.kanfa.news.admin.feign.ICommentStandardServiceFeign;
import com.kanfa.news.admin.vo.comment.CommentBlacklistInfo;
import com.kanfa.news.admin.vo.comment.CommentInfo;
import com.kanfa.news.admin.vo.setting.SettingInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/24 19:05
 */
@RestController
@RequestMapping("comment")
public class CommentRest extends BaseRPC {

    @Autowired
    private ICommentStandardServiceFeign commentStandardServiceFeign;
    @Autowired
    private ICommentServiceFeign commentServiceFeign;

    /**
     * 评论审核分页
     * @param commentInfo
     * @return
     */
    @RequestMapping(value = "/getPageForCheck", method = RequestMethod.POST)
    public TableResultResponse<CommentInfo> getPageForCheck(@RequestBody CommentInfo commentInfo) {
        if(commentInfo.getPage() == null || commentInfo.getLimit() == null){
            TableResultResponse<CommentInfo> tableResultResponse=new TableResultResponse<>();
            tableResultResponse.setStatus(506);
            tableResultResponse.setMessage("分页信息不能空");
            return tableResultResponse;
        }
        //搜索条件转换 日期的传入参数 //搜索类型 1-评论，2-ip，3-日期，4-文章id，5-直播Id
        if(NewsEnumsConsts.CommentInfoOfKeywordType.Content.key().equals(commentInfo.getKeywordType())){
            commentInfo.setContent(commentInfo.getKeyword());
        }else if(NewsEnumsConsts.CommentInfoOfKeywordType.Ip.key().equals(commentInfo.getKeywordType())){
            commentInfo.setIp(commentInfo.getKeyword());
        }else if(NewsEnumsConsts.CommentInfoOfKeywordType.ContentId.key().equals(commentInfo.getKeywordType())){
            commentInfo.setCid(Integer.valueOf(commentInfo.getKeyword()));
        }else if(NewsEnumsConsts.CommentInfoOfKeywordType.LiveId.key().equals(commentInfo.getKeywordType())){
            commentInfo.setLiveid(Integer.valueOf(commentInfo.getKeyword()));
        }
        //0-全部，1-今天，2-本周，3-本月
        if(NewsEnumsConsts.CommentInfoOfTimeType.Today.key().equals(commentInfo.getTimeType())){
            System.out.println(NewsEnumsConsts.CommentInfoOfTimeType.Today.getBeginTime());
            commentInfo.setBeginTime(NewsEnumsConsts.CommentInfoOfTimeType.Today.getBeginTime());
            commentInfo.setEndTime(NewsEnumsConsts.CommentInfoOfTimeType.Today.getEndTime());
        }else if(NewsEnumsConsts.CommentInfoOfTimeType.Week.key().equals(commentInfo.getTimeType())){
            commentInfo.setBeginTime(NewsEnumsConsts.CommentInfoOfTimeType.Week.getBeginTime());
            commentInfo.setEndTime(NewsEnumsConsts.CommentInfoOfTimeType.Week.getEndTime());
        }else if(NewsEnumsConsts.CommentInfoOfTimeType.Month.key().equals(commentInfo.getTimeType())){
            commentInfo.setBeginTime(NewsEnumsConsts.CommentInfoOfTimeType.Month.getBeginTime());
            commentInfo.setEndTime(NewsEnumsConsts.CommentInfoOfTimeType.Month.getEndTime());
        }
        //待审核-1 敏感-2 已删除-3 已审核-4
        if(commentInfo.getState().equals(1)){
            commentInfo.setOps(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
            commentInfo.setStat(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        }else if(commentInfo.getState().equals(2)){
            commentInfo.setSens(NewsEnumsConsts.CommentOfSens.Sens.key());
            commentInfo.setStat(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        }else if(commentInfo.getState().equals(3)){
            commentInfo.setStat(NewsEnumsConsts.ContentOfContentState.DELETE.key());
        }else if(commentInfo.getState().equals(4)){
            commentInfo.setOps(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
            commentInfo.setStat(NewsEnumsConsts.ContentOfContentState.NORMAL.key());
        }
        return commentServiceFeign.getPageForCheck(commentInfo);
    }

    /**
     * 审核通过
     * @param params
     * @return
     */
    @RequestMapping(value = "/batchPassComment", method = RequestMethod.POST)
    public ObjectRestResponse<Comment> batchPassComment(@RequestBody Map<String, List<Integer>> params) {
        if(params.get("ids") == null && params.get("ids").size()<=0){
            return getObjectRestResponse("评论id不能空");
        }
        return commentServiceFeign.batchPassComment(params.get("ids"));
    }

    @RequestMapping(value = "/passComment/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<Comment> passComment(@PathVariable("id") Integer id) {
        if(id == null){
            return getObjectRestResponse("评论id不能空");
        }
        return commentServiceFeign.passComment(id);
    }

    /**
     * 屏蔽  删除评论
     * @param commentInfo
     * @return
     */
    @RequestMapping(value = "/blockComment", method = RequestMethod.POST)
    public ObjectRestResponse<CommentInfo> blockComment(@RequestBody CommentInfo commentInfo) {
        if(commentInfo.getId() == null){
            return getObjectRestResponse("评论id不能空");
        }else if(commentInfo.getpUid()==null && StringUtils.isEmpty(commentInfo.getCreateDevid())){
            return getObjectRestResponse("评论用户不能空");
        }
        return commentServiceFeign.blockComment(commentInfo);
    }
    /**
     * 删除评论id
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<Comment> delete(@PathVariable("id") Integer id) {
        if(id == null){
            return getObjectRestResponse("删除id不能空");
        }
        Comment comment =new Comment();
        comment.setId(id);
        comment.setStat(NewsEnumsConsts.ChannelOfChannelStatus.DELETE.key());
        //更新内容的评论数，审核评论数
        return commentServiceFeign.updateContentStat(id);
    }

    /**
     * 批量删除评论  ids
     * @param params
     * @return
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> batchDelete(@RequestBody Map<String, List<Integer>> params) {
        return commentServiceFeign.batchDelete(params.get("ids"));
    }

    /**
     * 黑名单分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/blackUser", method = RequestMethod.GET)
    public TableResultResponse<CommentBlacklistInfo> blackUser(@RequestParam Map<String, Object> params) {
        return commentServiceFeign.getBlackUserPage(params);
    }

    /**
     * 解除屏蔽
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteBlackUser/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<CommentBlacklistInfo> deleteBlackUser(@PathVariable("id") Integer id) {
        if(id==null){
            return getObjectRestResponse("id不能为空");
        }
        return commentServiceFeign.deleteBlackUser(id);
    }

    /**
     * 查询评论设置
     * @return
     */
    @RequestMapping(value = "/getCommentSetting", method = RequestMethod.GET)
    public ObjectRestResponse<SettingInfo> getCommentSetting() {
        return commentServiceFeign.getCommentSetting();
    }

    /**
     * 设置评论 commentOps (1,2)  sensitiveWord(敏感)   illegalWord（非法）
     * @param params
     * @return
     */
    @RequestMapping(value = "/commentSetting", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> commentSetting(@RequestBody Map<String, Object> params) {
        return commentServiceFeign.saveCommentSetting(params);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setStatus(506);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }
}
