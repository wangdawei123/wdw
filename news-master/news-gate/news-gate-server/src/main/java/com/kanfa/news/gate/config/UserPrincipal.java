package com.kanfa.news.gate.config;

import com.kanfa.news.auth.client.config.UserAuthConfig;
import com.kanfa.news.auth.client.jwt.UserAuthUtil;
import com.kanfa.news.auth.common.util.jwt.IJWTInfo;
import com.kanfa.news.gate.ratelimit.config.IUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ace on 2017/9/23.
 */

public class UserPrincipal implements IUserPrincipal {

    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private UserAuthUtil userAuthUtil;

    @Override
    public String getName(HttpServletRequest request) {
        IJWTInfo infoFromToken = getJwtInfo(request);
        return infoFromToken == null ? null : infoFromToken.getUniqueName();
    }

    private IJWTInfo getJwtInfo(HttpServletRequest request) {
        IJWTInfo infoFromToken = null;
        try {
            String authToken = request.getHeader(userAuthConfig.getTokenHeader());
            if(StringUtils.isEmpty(authToken)) {
                infoFromToken = null;
            } else {
                infoFromToken = userAuthUtil.getInfoFromToken(authToken);
            }
        } catch (Exception e) {
            infoFromToken = null;
        }
        return infoFromToken;
    }

}
