package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.FeedbackBiz;
import com.kanfa.news.info.entity.Feedback;
import com.kanfa.news.info.vo.admin.appuser.FeedbackInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("feedback")
public class FeedbackController extends BaseController<FeedbackBiz,Feedback> {

    @Autowired
    private FeedbackBiz feedbackBiz;

    /**
     * 反馈分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/getPage",method = RequestMethod.GET)
    public TableResultResponse<FeedbackInfo> getFeedbackPage(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        return feedbackBiz.getFeedbackPage(query);
    }

    /**
     * 批量删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/batchDeleteFeedback",method = RequestMethod.POST)
    public ObjectRestResponse<Integer> batchDeleteFeedback(@RequestBody Map<String, List<Integer>> params){
        Integer deleteCount=feedbackBiz.batchDeleteFeedback(params);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(deleteCount);
        return objectRestResponse;
    }
}