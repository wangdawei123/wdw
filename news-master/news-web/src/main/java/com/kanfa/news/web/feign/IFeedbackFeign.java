package com.kanfa.news.web.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.entity.Feedback;
import com.kanfa.news.web.feign.callBack.FeedbackFallBack;
import io.swagger.models.auth.In;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kanfa on 2018/3/21.
 */

@FeignClient(name = "service-provider-user", fallback = FeedbackFallBack.class)
public interface IFeedbackFeign {

    /**
     * 保存用户反馈信息
     * @param id
     * @param advice
     * @param phone
     */
    @RequestMapping(value = "/feedback/insertMessage",method = RequestMethod.POST)
    ObjectRestResponse insertMessage(@RequestParam("id") Integer id,
                                     @RequestParam("advice") String advice,
                                     @RequestParam("phone") String phone);
}
