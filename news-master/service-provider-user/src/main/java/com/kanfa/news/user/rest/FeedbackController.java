package com.kanfa.news.user.rest;

import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.web.WebCommonParams;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.user.biz.FeedbackBiz;
import com.kanfa.news.user.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("feedback")
public class FeedbackController extends BaseController<FeedbackBiz,Feedback> {

    @Autowired
    private FeedbackBiz feedbackBiz;

    /**
     * web -- 保存信息-意见反馈
     * @param id
     * @param advice
     * @param phone
     */
    @RequestMapping(value = "/insertMessage",method = RequestMethod.POST)
    public ObjectRestResponse insertMessage(@RequestParam("id") Integer id,
                                            @RequestParam("advice") String advice,
                                            @RequestParam("phone") String phone){
        ObjectRestResponse result=new ObjectRestResponse();
        Feedback feedback = new Feedback();
        feedback.setAdvise(advice);
        feedback.setUid(id);
        feedback.setCreateTime(new Date());
        feedback.setPhone(phone);
        Integer i = feedbackBiz.insertMessage(feedback);
        if(i == 1){
            result.setStatus(WebCommonParams.SUCCESS);
        }else{
            result.setStatus(WebCommonParams.ERROR);
        }
        return result;
    }

    /**
     * app -- 提交意见反馈
     */
    @RequestMapping(value = "/feedbackAdd", method = RequestMethod.POST)
    public AppObjectResponse feedbackAdd(@RequestParam("advise") String advise,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("uid") Integer uid){
        AppObjectResponse response = new AppObjectResponse();
        Feedback feedback = new Feedback();
        feedback.setAdvise(advise);
        feedback.setCreateTime(new Date());
        feedback.setPhone(phone);
        feedback.setUid(uid);
        Integer integer = feedbackBiz.insertMessage(feedback);
        if(integer == 1){
            response.setStatus(AppCommonMessageEnum.OK.key());
            response.setMessage(AppCommonMessageEnum.OK.value());
            return response;
        }else{
            response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
            response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            return response;
        }
    }


}