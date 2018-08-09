package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.CommentBlacklist;
import com.kanfa.news.app.mapper.CommentBlacklistMapper;
import com.kanfa.news.app.vo.admin.comment.CommentBlacklistInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 评论的屏蔽用户
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-28 13:30:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentBlacklistBiz extends BaseBiz<CommentBlacklistMapper,CommentBlacklist> {

    @Autowired
    private CommentBlacklistMapper commentBlacklistMapper;

    /**
     * 查询所有黑名单
     * @param params
     * @return
     */
    public TableResultResponse<CommentBlacklistInfo> getBlackUserPage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<CommentBlacklistInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<CommentBlacklistInfo> list=commentBlacklistMapper.getBlackUserPage();
        return new TableResultResponse<>(result.getTotal(),list);
    }
}