package com.kanfa.news.app.interceptor;

import com.kanfa.news.app.security.ClientAuthSecurity;
import com.kanfa.news.common.exception.auth.ClientForbiddenException;
import com.kanfa.news.common.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jezy
 */
@Component
@Slf4j
public class ParameterInterceptor implements HandlerInterceptor {
    public static final String VERIFY_FAIL_MSG = "请求参数签名验证失败";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        // 渠道编号
        String clientCode = request.getHeader("clientCode");
        // 签名
        String signStr = request.getHeader("sign");

        if (StringUtils.isBlank(clientCode) || StringUtils.isBlank(signStr)) {
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            throw new ClientForbiddenException(VERIFY_FAIL_MSG);
        }

        if(!ClientAuthSecurity.ClientAuthEnum.SECURITY_APP.key().equals(Integer.valueOf(clientCode))){
            throw new ClientForbiddenException(VERIFY_FAIL_MSG);
        }

        String  encryptKey = ClientAuthSecurity.ClientAuthEnum.SECURITY_APP.value();
        boolean right = MD5Util.verifySign(encryptKey, request);
        if (right) {
            return true;
        }else{
            throw new ClientForbiddenException(VERIFY_FAIL_MSG);
        }
    }
}
