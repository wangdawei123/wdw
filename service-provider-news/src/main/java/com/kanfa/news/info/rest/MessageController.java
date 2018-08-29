package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.MessageBiz;
import com.kanfa.news.info.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController extends BaseController<MessageBiz,Message> {

    @Autowired
    private MessageBiz messageBiz;

    /**
     * 添加推送
     * @param message
     * @return
     */
    @RequestMapping(value = "/addPushMessage",method = RequestMethod.POST)
    public ObjectRestResponse<Message> addPushMessage(@RequestBody Message message){
        ObjectRestResponse<Message> objectRestResponse = new ObjectRestResponse<>();
        objectRestResponse.setData(messageBiz.addPushMessage(message));
        return objectRestResponse;
    }

    /**
     * 搜索所有消息分页
     * @return
     */
    @RequestMapping(value = "/getMessagePage",method = RequestMethod.GET)
    public TableResultResponse<Message> getMessagePage(@RequestParam Map<String, Object> params){
        return messageBiz.getMessagePage(params);
    }
}