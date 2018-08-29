package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.CommentStandard;
import com.kanfa.news.app.mapper.CommentStandardMapper;
import com.kanfa.news.app.vo.admin.comment.CommentStandardInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-23 20:01:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentStandardBiz extends BaseBiz<CommentStandardMapper,CommentStandard> {

    @Autowired
    private CommentStandardMapper commentStandardMapper;

    /**
     * 分页查询标准评论
     * @param params
     * @return
     */
    public TableResultResponse<CommentStandardInfo> getPage(Map<String, Object> params) {
        Query query=new Query(params);
        Page<CommentStandardInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Integer> channelIds = (List<Integer>) params.get("channelIds");
        System.out.println(channelIds.toString());
        if(channelIds!=null && channelIds.size() > 0){
            StringBuffer stringBuffer=new StringBuffer();
            for (Integer channelId : channelIds) {
                stringBuffer.append("find_in_set('"+channelId+"',channels) OR ");
            }
            params.put("str",stringBuffer.substring(0,stringBuffer.length()-3));
        }
        List<CommentStandardInfo> list = commentStandardMapper.getPage(params);
        return new TableResultResponse<>(result.getTotal(), list);
    }

    /**
     * 分页查询根据content
     * @param params
     * @return
     */
    public TableResultResponse<CommentStandardInfo> getPageByTitle(Map<String, Object> params) {
        Query query=new Query(params);
        Page<CommentStandardInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<CommentStandardInfo> list = commentStandardMapper.getPageByTitle(params);
        return new TableResultResponse<>(result.getTotal(), list);
    }
}