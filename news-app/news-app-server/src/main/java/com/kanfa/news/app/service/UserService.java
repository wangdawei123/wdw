package com.kanfa.news.app.service;

import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public ObjectRestResponse validateCode(String code, String sessionId) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();

        String tradeNo = redisTemplate.opsForValue().get("USER:VALIDATE:" + sessionId);
        if (StringUtils.isEmpty(tradeNo)) {
            objectRestResponse.setStatus(UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.key());
            objectRestResponse.setMessage(UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());
            return objectRestResponse;
        }
        if (!code.equals(tradeNo)) {
            objectRestResponse.setStatus(UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.key());
            objectRestResponse.setMessage(UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        }
        return objectRestResponse;
    }

}
