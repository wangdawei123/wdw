package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.UserAuthBiz;
import com.kanfa.news.app.biz.UserBiz;
import com.kanfa.news.app.entity.User;
import com.kanfa.news.app.entity.UserAuth;
import com.kanfa.news.common.constant.UserConstant;
import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wdw
 */
@RestController
@RequestMapping("binding")
public class AccountBindingRest extends BaseRest {
    @Autowired
    private UserAuthBiz userAuthBiz;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static String USER_VALIDATE = "USER:VALIDATE:";

    /**
     * 第三方账号绑定
     *
     * @param uid
     * @param access_token
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/bindSocialAccount", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> bindSocialAccount(@RequestParam("uid") Integer uid,
                                                                     @RequestParam("access_token") String access_token,
                                                                     @RequestParam("openid") String openid,
                                                                     @RequestParam("platform") Integer platform) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse<UserAuth>();
        //校验账号openId是否已被绑定
        UserAuth user = new UserAuth();
        user.setOpenid(openid);
        user.setPlatform(platform);
        user.setStatus(1);
        UserAuth userAuth = userAuthBiz.selecetByOpenId(user);
        if (null != userAuth) {
            User us = new User();
            us.setId(userAuth.getUid());
            User user1 = userBiz.selectOne(us);
            String nickname = user1.getNickname();
            //账号已被占用
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage(nickname.substring(0, 1) + "***" + nickname.substring(nickname.length() - 1, nickname.length()));
            return channelObjectRestResponse;
        }
        UserAuth user2 = new UserAuth();
        user2.setOpenid(openid);
        user2.setUid(uid);
        user2.setStatus(1);
        UserAuth userAuth2 = userAuthBiz.selecetByOpenId(user2);
        if (null != userAuth2) {
            userAuth2.setStatus(1);
            userAuthBiz.updateById(userAuth2);
        } else {
            //默认值
            UserAuth entity = new UserAuth();
            entity.setCreateTime(new Date());
            entity.setUid(uid);
            entity.setPlatform(platform);
            entity.setOpenid(openid);
            entity.setAccessToken(access_token);
            entity.setUnionid("");
            entity.setStatus(1);
            userAuthBiz.insert(entity);
        }
        channelObjectRestResponse.setData(user);
        return channelObjectRestResponse;
    }

    /**
     * 验证待绑定手机号
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> checkPhone(@RequestParam("phone") String phone) {
        ObjectRestResponse channelObjectRestResponse = this.appObjectResponse;
        //校验参数合法性
        if (null == phone) {
            //参数不能为空
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("手机号不能为空");
            return channelObjectRestResponse;
        }
        //校验账号openId是否已被绑定
        User thisuser = new User();
        thisuser.setPhone(phone);
        User user = userBiz.selectOne(thisuser);
        if (null != user) {
            //账号已被占用
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该手机已被别人绑定!请换手机号");
            return channelObjectRestResponse;
        }
        return channelObjectRestResponse;
    }

    /**
     * 第三方账号解绑并绑定新账户
     *
     * @param uid
     * @param access_token
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/newBindAccount", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> newBindAccount(@RequestParam("uid") Integer uid,
                                                                  @RequestParam("access_token") String access_token,
                                                                  @RequestParam("openid") String openid,
                                                                  @RequestParam("platform") Integer platform) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse<UserAuth>();
        //查询已绑定账号
        UserAuth user = new UserAuth();
        user.setOpenid(openid);
        user.setPlatform(platform);
        user.setStatus(1);
        UserAuth userAuth = userAuthBiz.selecetByOpenId(user);
        //解绑
        userAuth.setStatus(0);
        userAuthBiz.updateById(userAuth);
        //绑定新账号
        UserAuth user2 = new UserAuth();
        user2.setOpenid(openid);
        user2.setUid(uid);
        user2.setStatus(1);
        UserAuth userAuth2 = userAuthBiz.selecetByOpenId(user2);
        if (null != userAuth2) {
            userAuth2.setStatus(1);
            userAuthBiz.updateById(userAuth2);
        } else {
            UserAuth entity = new UserAuth();
            entity.setUid(uid);
            entity.setPlatform(platform);
            entity.setOpenid(openid);
            entity.setAccessToken(access_token);
            entity.setCreateTime(new Date());
            entity.setStatus(1);
            entity.setUnionid("");
            userAuthBiz.insert(entity);
        }
        UserAuth returnuser = new UserAuth();
        returnuser.setOpenid(openid);
        channelObjectRestResponse.setData(returnuser);
        return channelObjectRestResponse;
    }

    /**
     * 手机账号解绑并绑定新账户
     *
     * @param uid
     * @param code
     * @param phone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/newPhoneBinding", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> newPhoneBinding(@RequestParam("uid") Integer uid,
                                                                   @RequestParam("code") String code,
                                                                   @RequestParam("phone") String phone,
                                                                   @RequestParam("verificatcode") String verificatcode) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse<User>();
        //校验短信校验码
        String beforeSessionId = this.redisTemplate.opsForValue().get(USER_VALIDATE + verificatcode);
        Assert.notNull(beforeSessionId, UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        Assert.isTrue(code.equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());

        //查询已绑定旧手机号
        User thisuser = new User();
        thisuser.setPhone(phone);
        User user = userBiz.selectOne(thisuser);
        if (null != user) {
            //解绑
            user.setPhone("");
            userBiz.updateById(user);
            //绑定新账户
            User newuser = new User();
            newuser.setId(uid);
            User binduser = userBiz.selectOne(newuser);
            binduser.setPhone(phone);
            binduser.setIsDelete(1);
            userBiz.updateById(binduser);
        } else {
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该手机号解绑失败");
            return channelObjectRestResponse;
        }
        return channelObjectRestResponse;
    }

    /**
     * 第三方登录后绑定手机号
     *
     * @param uid
     * @param code
     * @param phone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/phoneBinding", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> phoneBinding(@RequestParam("uid") Integer uid,
                                                                @RequestParam("code") String code,
                                                                @RequestParam("phone") String phone,
                                                                @RequestParam("verificatcode") String verificatcode) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse<User>();
        //校验短信校验码
        String beforeSessionId = this.redisTemplate.opsForValue().get(USER_VALIDATE + verificatcode);
        Assert.notNull(beforeSessionId, UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        Assert.isTrue(code.equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());

        //校验phone是否已被绑定
        User thisuser = new User();
        thisuser.setPhone(phone);
        User user = userBiz.selectOne(thisuser);
        if (null != user) {
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该手机已被绑定!");
            return channelObjectRestResponse;
        }
        User bindphone = new User();
        bindphone.setId(uid);
        User phone2 = userBiz.selectOne(bindphone);
        if (phone2 != null) {
            phone2.setPhone(phone);
            phone2.setIsDelete(1);
            userBiz.updateById(phone2);
        }
        return channelObjectRestResponse;
    }

    /**
     * 第三方账号解绑
     *
     * @param uid
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/unBindSocialAccount", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> unBindSocialAccount(@RequestParam("uid") Integer uid,
                                                                       @RequestParam("openid") String openid,
                                                                       @RequestParam("platform") Integer platform) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse<UserAuth>();
        //校验是否已被绑定手机号
        User thisuser = new User();
        thisuser.setId(uid);
        User userphone = userBiz.selectOne(thisuser);
        UserAuth userauth = new UserAuth();
        userauth.setUid(uid);
        userauth.setStatus(1);
        List<UserAuth> userAuths = userAuthBiz.selectList(userauth);
        if (userphone != null) {
            if ((null == userphone.getPhone() || "".equals(userphone.getPhone())) && userAuths != null && userAuths.size() == 1) {
                channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
                channelObjectRestResponse.setMessage(" 解绑后将无法登录，请先绑定手机号");
                return channelObjectRestResponse;
            }
        } else {
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("未查询到该用户信息，解绑失败！");
            return channelObjectRestResponse;
        }
        UserAuth user = new UserAuth();
        user.setUid(uid);
        user.setOpenid(openid);
        UserAuth userAuth = userAuthBiz.selectOne(user);
        if (userAuth != null) {
            userAuth.setStatus(0);
            userAuthBiz.updateById(userAuth);
        } else {
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该用户不存在，绑定失败");
            return channelObjectRestResponse;
        }
        return channelObjectRestResponse;
    }

    /**
     * 修改绑定的手机号
     *
     * @param uid
     * @param code
     * @param oldPhone
     * @param newPhone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Map<String, Object>> updatePhone(@RequestParam("uid") Integer uid,
                                                               @RequestParam("code") String code,
                                                               @RequestParam("oldPhone") String oldPhone,
                                                               @RequestParam("newPhone") String newPhone,
                                                               @RequestParam("verificatcode") String verificatcode) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse<User>();
        System.out.println("sessionid:" + verificatcode);
        //校验短信校验码
        String beforeSessionId = this.redisTemplate.opsForValue().get(USER_VALIDATE + verificatcode);
        System.out.println("beforeSessionId:" + beforeSessionId);
        Assert.notNull(beforeSessionId, UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        Assert.isTrue(code.equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());

        //校验新手机号是否已被绑定
        User thisuser = new User();
        thisuser.setPhone(newPhone);
        User user = userBiz.selectOne(thisuser);
        if (null != user) {
            //手机号已被占用
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该手机已被绑定!");
        }
        User oldUserPhone = new User();
        oldUserPhone.setId(uid);
        User userphone = userBiz.selectOne(oldUserPhone);
        if (userphone != null) {
            userphone.setPhone(newPhone);
            userphone.setIsDelete(1);
            userBiz.updateById(userphone);
        }
        return channelObjectRestResponse;
    }

}
