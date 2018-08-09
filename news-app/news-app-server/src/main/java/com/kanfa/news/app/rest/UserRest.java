package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.UserBiz;
import com.kanfa.news.app.config.AliOSSConfig;
import com.kanfa.news.app.feign.ICommentServiceFeign;
import com.kanfa.news.app.feign.ICommonServiceFeign;
import com.kanfa.news.app.feign.IUserServiceFeign;
import com.kanfa.news.app.service.UserService;
import com.kanfa.news.app.util.OSSClientUtil;
import com.kanfa.news.app.vo.user.PlatformUser;
import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.util.RegularVerifyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("user")
public class UserRest extends BaseRest {
    @Autowired
    private UserService userService;

    @Autowired
    private IUserServiceFeign userServiceFeign;

    @Autowired
    private ICommonServiceFeign commonServiceFeign;
    @Autowired
    private ICommentServiceFeign commentServiceFeign;
    @Autowired
    private UserBiz userBiz;

    @Resource
    private AliOSSConfig aliOSSConfig;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 检查短信验证码
     * @param code
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/checkSMSCode", method = RequestMethod.POST)
    public ObjectRestResponse validate(@RequestParam("code") String code, @RequestParam("sessionId") String sessionId) {
        return userService.validateCode(code, sessionId);
    }


    /**
     * 提交意见反馈
     */
    @RequestMapping(value = "/feedbackAdd", method = RequestMethod.POST)
    public AppObjectResponse feedbackAdd(@RequestParam("advise") String advise,
                                          @RequestParam("phone") String phone,
                                          @RequestParam("uid") Integer uid) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if(uid == null){
            response.setStatus(AppCommonMessageEnum.APP_USER_NAME_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_USER_NAME_MISSING.value());
            return response;
        }
        if(phone == null){
            response.setStatus(AppCommonMessageEnum.APP_PHONE_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PHONE_MISSING.value());
            return response;
        }
        if(!RegularVerifyUtils.phoneVerify(phone)){
            response.setStatus(AppCommonMessageEnum.APP_PHONE_ILLEGALITY.key());
            response.setMessage(AppCommonMessageEnum.APP_PHONE_ILLEGALITY.value());
            return response;
        }
        return userServiceFeign.feedbackAdd(advise , phone ,uid );
    }

    /**
     *  文章取消收藏
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public AppObjectResponse del(@RequestParam(value = "uid") Integer uid,
                                 @RequestParam(value = "cid") Integer cid,
                                 @RequestParam(value = "type", defaultValue = "0") Integer type) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if(uid == null){
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        }
        if(cid == null){
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        }
        response = commentServiceFeign.delFav(uid,cid,type);
        return response;
    }

    /**
     * 更新用戶信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AppObjectResponse updateUserInfo(@RequestParam("gender") String gender,
                                            @RequestParam("nickname") String nickname,
                                            @RequestParam("uid") Integer uid) {
        AppObjectResponse response = new AppObjectResponse();
        if(null == uid){
            response.setStatus(AppCommonMessageEnum.APP_USER_NAME_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_USER_NAME_MISSING.value());
            return response;
        }
        if(gender == null){
            //请选择性别
            response.setStatus(AppCommonMessageEnum.APP_GENDER_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_GENDER_MISSING.value());
            return  response;
        }
        response = userBiz.updateUserInfo(uid,nickname , gender);
        return response;
    }

    /**
     * app端--用户退出
     * @param sessionId
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public AppObjectResponse removeAppUser(@RequestParam("sessionid") String sessionId) {
        //app端用户退出登陆
        userServiceFeign.removeAPPUser(sessionId);
        return new AppObjectResponse();
    }

    /**
     * app -- 个人中心首页
     */
    @RequestMapping(value = "/personal", method = RequestMethod.POST)
    public AppObjectResponse personal(@RequestParam(value = "sessionid") String sessionid){
        AppObjectResponse response = new AppObjectResponse();
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        if(o == null){
            //用户未登陆
            response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
            response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
            return response;
        }else{
            Integer uid = (Integer) o;
            response = userServiceFeign.personal(uid ,sessionid);
        }
        return response;
    }

    /**
     * app -- 获取用户部分信息
     */
    @RequestMapping(value = "/getusermessage", method = RequestMethod.GET)
    public AppObjectResponse getusermessage() {
        String sessionid = request.getSession().getId();
        AppObjectResponse response = new AppObjectResponse();
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        if(o == null){
            //用户未登陆
            response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
            response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
            return response;
        }else{
            Integer uid = (Integer) o;
            response = userServiceFeign.getusermessage(uid);
        }
        return response;
    }

    /**
     * app -- 我的问答
     */
    @RequestMapping(value = "/myProblem", method = RequestMethod.POST)
    public AppObjectResponse myProblem(@RequestParam(value = "page",defaultValue = "1") Integer page ,
                                       @RequestParam(value = "pcount",defaultValue = "8") Integer pcount ,
                                       @RequestParam("sessionid") String sessionid) {
        AppObjectResponse response = new AppObjectResponse();
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        if(o == null){
            response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
            response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
            return response;
        }
        Integer uid = (Integer)o;
        return userServiceFeign.myProblem(page ,pcount ,uid);
    }

    /**
     * app -- 控制IOS直播显示与否 -- 我的->律师来了
     */
    @RequestMapping(value = "/live", method = RequestMethod.POST)
    public AppObjectResponse live() {
        return userServiceFeign.live();
    }


    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/sendSMS",method = RequestMethod.POST)
    public ObjectRestResponse sendSMS(@RequestParam("phone") String phone) {
        ObjectRestResponse objectRestResponse = commonServiceFeign.sendValidateCode(phone);

        BeanUtils.copyProperties(objectRestResponse, appObjectResponse);
        return appObjectResponse;
    }

    /**
     * 手机号登录
     *
     * @param code
     * @param phone
     * @param sessionId
     * @param channelNum
     * @return
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public AppObjectResponse userLogin(@RequestParam("code") String code, @RequestParam("phone") String phone,
                                       @RequestParam("sessionId") String sessionId,
                                       @RequestParam("channel_num") String channelNum) {
        Assert.notNull(code, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        Assert.notNull(phone, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        Assert.notNull(sessionId, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        Assert.notNull(channelNum, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        return userServiceFeign.userLogin(code, phone, sessionId, channelNum);
    }

    /**
     * 第三方登录
     *
     * @param accessToken
     * @param openId
     * @param platform
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<PlatformUser> platformLogin(@RequestParam("access_token") String accessToken, @RequestParam("openid") String openId,
                                                          @RequestParam("platform") Integer platform) {
        Assert.notNull(accessToken, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        Assert.notNull(openId, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        Assert.notNull(platform, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        appObjectResponse = userServiceFeign.platformLogin(accessToken, openId, platform);
        return appObjectResponse;
    }

    /**
     * 更新头像
     *
     * @param uid
     * @param file
     * @return
     */
    @RequestMapping(value = "/portrait", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ObjectRestResponse<PlatformUser> portrait(@RequestParam("uid") Integer uid, @RequestPart(value = "pic_name") MultipartFile file) {
        Assert.notNull(uid, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        Assert.notNull(file, UserEnum.LoginExceptionEnum.PARAM_VALIDATE_FAIL.value());
        OSSClientUtil ossClient = new OSSClientUtil(aliOSSConfig);
        String name = null;
        try {
            name = ossClient.uploadImg2Oss(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String imgUrl = ossClient.getImgUrl(name);
        Assert.notNull(imgUrl, "上传失败");
        appObjectResponse = userServiceFeign.portrait(uid, imgUrl);
        Map<String, String> dataMap = new HashMap<>(16);
        dataMap.put("image", imgUrl);
        appObjectResponse.setData(dataMap);
        return appObjectResponse;
    }
}
