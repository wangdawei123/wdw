package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Comment;
import com.kanfa.news.info.vo.admin.comment.CommentInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 评论表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:01:59
 */
public interface CommentMapper extends Mapper<Comment> {

    List<CommentInfo> getPage(Map<String, Object> map);

    Comment selectById(@Param("id") Integer id);

    Integer updateStat(Comment comment);

    Integer updateread(Comment comment);

    Integer insertSelectiveComment(Comment comment);

    List<CommentInfo> getByCreator(Map<String ,Object> params);

    List<CommentInfo> getByReceiver(Map<String ,Object> params);

    /**
     * 分页查询（对应审核）
     * @param commentInfo
     * @return
     */
    List<CommentInfo> getPageForCheck(@Param("entity") CommentInfo commentInfo);

    List<CommentInfo> getListByConditions(@Param("cid") Integer cid,
                                          @Param("ops") Integer ops,
                                          @Param("type") Integer type,
                                          @Param("uid") Integer uid,
                                          @Param("p") Integer p, 
                                          @Param("pcount") Integer pcount);

    Long selectCountnum(@Param("id") Integer id);

    /**
     * 通过cid查询评伦集合
     * @param
     * @return
     */
    List<Comment> getCommentListByCid(@Param("cid")Integer cid);

    Integer getCountForDetail(@Param("cid") Integer cid,@Param("type") Integer type,@Param("uid") Integer uid,@Param("ops") Integer ops);

    List<CommentInfo> getListByConditions(Integer liveId, Integer type, Integer uid, int i, Integer count);
}
