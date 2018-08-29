package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.vo.admin.appuser.FeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.Feedback;
import com.kanfa.news.info.mapper.FeedbackMapper;
import com.kanfa.news.common.biz.BaseBiz;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈表
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-06-05 18:30:42
 */
@Service
public class FeedbackBiz extends BaseBiz<FeedbackMapper,Feedback> {

    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 分页
     * @param query
     * @return
     */
    public TableResultResponse<FeedbackInfo> getFeedbackPage(Query query) {
        Page<FeedbackInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<FeedbackInfo> list = feedbackMapper.getFeedbackPage();
        return new TableResultResponse<>(result.getTotal(),list);
    }

    /**
     * 批量删除
     * @param params
     * @return
     */
    public Integer batchDeleteFeedback(Map<String, List<Integer>> params) {
        List<Integer> list = params.get("ids");
        Integer flag=0;
        if(list!=null && list.size()>=0){
            for (Integer id : list) {
                int delete = feedbackMapper.deleteByPrimaryKey(id);
                if(delete>0){
                    flag++;
                }
            }
        }
        return flag;
    }
}