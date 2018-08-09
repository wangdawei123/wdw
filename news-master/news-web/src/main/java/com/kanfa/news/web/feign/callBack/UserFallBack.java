package com.kanfa.news.web.feign.callBack;

import com.kanfa.news.web.feign.IUserFeign;
import com.kanfa.news.web.vo.channel.UserAuthInfo;
import org.springframework.stereotype.Service;

/**
 * Created by kanfa on 2018/3/27.
 */
@Service
public class UserFallBack implements IUserFeign{

    @Override
    public  UserAuthInfo selectUserByPhone(Boolean isRemeber, String number,String cookieId){return null;}


    @Override
    public void removeRedisUser(String sessionId){}

    @Override
    public UserAuthInfo findRedisUser(String sessionId){return null;}
}
