package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.ChatroomUserBiz;
import com.kanfa.news.app.feign.IChatRoomServiceFeign;
import com.kanfa.news.app.vo.sys.AppHeader;
import com.kanfa.news.common.constant.app.APPCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("chatRoom")
public class ChatRoomRest extends BaseRest {
    @Autowired
    private ChatroomUserBiz chatroomUserBiz;

    /**
     * 通过uid获取token
     *
     * @param sessionId
     * @param chatroomId
     * @return
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    public AppObjectResponse getalbum(@RequestParam(value = "sessionid") String sessionId,
                                      @RequestParam(value = "chatroomID") String chatroomId) {
        AppHeader appHeader = this.getAppHeader();
        Assert.hasText(appHeader.getDevId(), APPCommonEnum.PARAM_VALIDATE_FAIL.value());
        Assert.hasText(appHeader.getIdFa(), APPCommonEnum.PARAM_VALIDATE_FAIL.value());
        Assert.hasText(appHeader.getPlatform(), APPCommonEnum.PARAM_VALIDATE_FAIL.value());
        ObjectRestResponse objectRestResponse = chatroomUserBiz.getToken(this.getUserId(), appHeader.getDevId(), appHeader.getIdFa(), appHeader.getPlatform(), chatroomId);


        BeanUtils.copyProperties(objectRestResponse, appObjectResponse);
        return appObjectResponse;
    }

}
