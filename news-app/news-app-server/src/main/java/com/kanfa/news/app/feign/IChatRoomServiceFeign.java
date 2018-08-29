package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-news")
public interface IChatRoomServiceFeign {
    @RequestMapping(value = "/chatRoom/getToken", method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse getToken(@RequestParam(value = "userId") Integer userId,
                                @RequestParam(value = "devId") String devId,
                                @RequestParam(value = "IDFA") String IDFA,
                                @RequestParam(value = "PLATFORM") String PLATFORM,
                                @RequestParam(value = "chatroomID") String chatroomId);
}
