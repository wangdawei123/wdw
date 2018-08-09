package com.kanfa.news.common.rest;

import com.kanfa.news.common.constant.UserConstant;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wdw on 2018/3/13.
 */
@RestController
@RequestMapping("redis")
public class RedisTemplateController {
    @Autowired
    private ValueOperations<String, Object> valueOperations;

    /**
     * 第三方账号绑定
     * @param
     * @return
     */
    @RequestMapping(value = "/setString",method = RequestMethod.GET)
    public ObjectRestResponse<String> set(@RequestParam String key,@RequestParam String value){
        ObjectRestResponse<String> channelObjectRestResponse = new ObjectRestResponse<String>();
        //校验参数合法性
        try{
            Assert.notNull(key,"key 不能为空");
            Assert.notNull(value ,"value 不能为空");
        }catch (IllegalArgumentException e){
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage(e.getMessage());
            return channelObjectRestResponse;
        }
        valueOperations.set(key,value);
        return channelObjectRestResponse;
    }

}
