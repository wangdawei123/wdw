package com.kanfa.news.web.feign.callBack;

import com.kanfa.news.web.feign.IUserAuthFeign;
import com.kanfa.news.web.vo.channel.UserAuthInfo;
import org.springframework.stereotype.Service;

/**
 * Created by kanfa on 2018/3/27.
 */

@Service
public class UserAuthFallBack implements IUserAuthFeign {


    /**
     * 根据用户微博openId查找用户
     * @param uid
     * @return
     */
    @Override
    public UserAuthInfo selecetByWiboOpenId(String uid,String access_token){return null;}

    /**
     * 根据用户微信openId查找用户
     * @param openid
     * @return
     */
    @Override
    public UserAuthInfo selecetByWechatOpenId(String openid,String access_token){return null;}

    /**
     * 根据用户QQ openId查找用户
     * @return
     */
    @Override
    public UserAuthInfo selecetByQQOpenId(String openId,String accessToken,String qclient_id){return null;}

    @Override
    public UserAuthInfo wechatLogin(UserAuthInfo user){return null;}

}
