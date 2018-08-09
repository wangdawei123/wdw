package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.biz.UserBiz;
import com.kanfa.news.admin.entity.BaseUser;
import com.kanfa.news.admin.entity.User;
import com.kanfa.news.admin.feign.IBaseUserServiceFeign;
import com.kanfa.news.common.constant.UserConstant;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.BaseResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/5/15 15:27
 */
@RestController
@RequestMapping("/baseUser")
public class BaseUserRest {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    @Autowired
    private IBaseUserServiceFeign baseUserServiceFeign;
    @Autowired
    private UserBiz userBiz;

    //修改头像
    @RequestMapping(value = "/updateheadPortrait", method = RequestMethod.GET)
    public BaseResponse update(@RequestParam("headPortrait")String headPortrait) {
        BaseResponse baseResponse = new BaseResponse();
        BaseUser baseUser = new BaseUser();
        baseUser.setId(Integer.valueOf(BaseContextHandler.getUserID()));
        baseUser.setUpdTime(new Date());
        baseUser.setUpdUser(BaseContextHandler.getUserID());
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            baseUser.setUpdHost(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        baseUser.setHeadPortrait(headPortrait);
        baseUserServiceFeign.update(baseUser.getId(), baseUser);
        ObjectRestResponse<BaseUser> baseUserObjectRestResponse = baseUserServiceFeign.get(baseUser.getId());
        BaseUser data = baseUserObjectRestResponse.getData();
        String username = data.getUsername();
        User userByUsername = userBiz.getUserByUsername(username);
        User user = new User();
        BeanUtils.copyProperties(userByUsername,user);
        user.setHeadPortrait(headPortrait);
        userBiz.updateSelectiveById(user);
        String str = "更新头像成功";
        baseResponse.setStatus(200);
        baseResponse.setMessage(str);
        return baseResponse;
    }

    //修改密码
    @RequestMapping(value = "/updatePassword",method = RequestMethod.GET)
    public BaseResponse updatePassword(@RequestParam("oldPassword")String oldPassword,
                                                       @RequestParam("newPassword")String newPassword,
                                                       @RequestParam("secondPassword")String secondPassword) {
        BaseUser baseUser = new BaseUser();
        baseUser.setId(Integer.valueOf(BaseContextHandler.getUserID()));

        ObjectRestResponse<BaseUser> baseUserObjectRestResponse = baseUserServiceFeign.get(baseUser.getId());
        BaseUser data = baseUserObjectRestResponse.getData();
        BaseResponse baseResponse = new BaseResponse();
        if (data==null){
            String str = "沒有此用戶";
            baseResponse.setStatus(500);
            baseResponse.setMessage(str);
            return baseResponse;
        }
        if (!encoder.matches(oldPassword, data.getPassword())) {
            String str = "原密碼輸入錯誤";
            baseResponse.setStatus(500);
            baseResponse.setMessage(str);
            return baseResponse;
        }
        if (!newPassword.equals(secondPassword)) {
            String str = "两次密码输入不一致";
            baseResponse.setStatus(500);
            baseResponse.setMessage(str);
            return baseResponse;
        }
        String password1 = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(newPassword);
        baseUser.setPassword(password1);
        baseUser.setUpdTime(new Date());
        baseUser.setUpdUser(BaseContextHandler.getUserID());
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            baseUser.setUpdHost(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        baseUserServiceFeign.update(baseUser.getId(), baseUser);
        String username = data.getUsername();
        User userByUsername = userBiz.getUserByUsername(username);
        User user = new User();
        BeanUtils.copyProperties(userByUsername,user);
        user.setPassword(newPassword);
        userBiz.updateSelectiveById(user);
        String str = "更新密码成功";
        baseResponse.setStatus(200);
        baseResponse.setMessage(str);
        return baseResponse;
    }


}
