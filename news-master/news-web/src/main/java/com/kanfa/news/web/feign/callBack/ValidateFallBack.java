package com.kanfa.news.web.feign.callBack;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.feign.ValidateFeign;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kanfa on 2018/3/27.
 */
@Service
public class ValidateFallBack implements ValidateFeign{

    @Override
    public ObjectRestResponse sendPhoneCode(String phone){return null;}

    @Override
    public ObjectRestResponse checkPhoneCode(String code, String sessionId){return null ;}

}
