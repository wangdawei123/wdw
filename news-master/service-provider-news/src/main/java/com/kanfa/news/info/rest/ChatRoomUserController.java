package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ChatroomUserBiz;
import com.kanfa.news.info.entity.ChatroomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("/chatRoom")
public class ChatRoomUserController extends BaseController<ChatroomUserBiz, ChatroomUser> {
    @Autowired
    private ChatroomUserBiz chatroomUserBiz;

    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse getToken(@RequestParam(value = "userId") Integer userId,
                                        @RequestParam(value = "devId") String devId,
                                        @RequestParam(value = "IDFA") String IDFA,
                                        @RequestParam(value = "PLATFORM") String PLATFORM,
                                        @RequestParam(value = "chatroomID") String chatroomId) {
        return chatroomUserBiz.getToken(userId, devId, IDFA, PLATFORM, chatroomId);
    }

}