package com.kanfa.news.user.rest;

import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.user.biz.ProblemBiz;
import com.kanfa.news.user.biz.SettingBiz;
import com.kanfa.news.user.biz.UserBiz;
import com.kanfa.news.user.entity.*;
import com.kanfa.news.user.mapper.CommentMapper;
import com.kanfa.news.user.mapper.ProblemMapper;
import com.kanfa.news.user.mapper.UserAuthMapper;
import com.kanfa.news.user.mapper.UserMapper;
import com.kanfa.news.user.vo.admin.UserAuthInfo;
import com.kanfa.news.user.vo.app.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("login")
public class UserLoginController extends BaseController<UserBiz, User> {
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SettingBiz settingBiz;
    @Autowired
    private ProblemBiz problemBiz;

    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private CommentMapper commentMapper;

    private static String USER_VALIDATE = "USER:VALIDATE:";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * web -- 根据手机号查找用户
     * @param number
     * @return
     */
    @RequestMapping(value = "selectUserByPhone",method =  RequestMethod.POST)
    public UserAuthInfo selectUserByPhone(@RequestParam("isRemeber") Boolean isRemeber,
                                          @RequestParam("number") String number,
                                          @RequestParam("cookieId") String cookieId){
        User user = userBiz.selectUserByPhone(number);
        UserAuthInfo info = null;
        if(user == null){
            //说明该手机用户是第一次登陆，需要保存信息
            info = this.phoneUserLogin(number);
            this.updateRedisUser(isRemeber , info ,  cookieId);
        }else{
            //返回类型需要为UserAuthInfo，转换赋值
            info = new UserAuthInfo();
            info.setUid(user.getId());
            info.setId(user.getId());
            info.setNickname(user.getNickname());
            info.setImage(user.getImage());
            info.setPhone(user.getPhone());
            info.setGender(user.getGender());
            //用户信息已经存在，把它存在域中直接调用展示
            this.updateRedisUser(isRemeber , info ,  cookieId);
        }
        return info;
    }



    /**
     * app -- 个人中心首页
     */
    @RequestMapping(value = "/personal", method = RequestMethod.POST)
    public AppObjectResponse personal(@RequestParam("uid") Integer uid, @RequestParam("sessionid") String sessionid){

        AppObjectResponse response = new AppObjectResponse();
        Map<String ,Object> userMap = new HashMap<>(5);
        Map<String ,Object> map = new HashMap<>(5);
        User user = userMapper.selectByPrimaryKey(uid);
        userMap.put("block",user.getIsBlock());
        userMap.put("id",user.getId());
        userMap.put("phone",user.getPhone());
        userMap.put("image",user.getImage());
        userMap.put("nickname",user.getNickname());
        userMap.put("sessionid",sessionid);
        Problem p = new Problem();
        p.setCreateUid(user.getId());
        p.setStat(LiveCommonEnum.NO_DEL_STATUS);
        p.setIsRead(LiveCommonEnum.READ_FALSE);
        p.setOps(LiveCommonEnum.OPS_TRUE);
        long count = problemMapper.getCount(p);
        Integer coun = new Long(count).intValue();
        if(coun.equals(LiveCommonEnum.CONSTANT_ZERO)){
            userMap.put("is_problem",false);
        }else{
            userMap.put("is_problem",true);
        }
        String value = settingBiz.getByName("comment_ops");
        Comment c = new Comment();
        if(LiveCommonEnum.CONSTANT_ONE.equals(value)){
            c.setOps(LiveCommonEnum.OPS_TRUE);
        }
        c.setCreateUid(user.getId());
        c.setStat(LiveCommonEnum.IS_DEL_STATUS);
        c.setRead(LiveCommonEnum.READ_FALSE);
        c.setSens(LiveCommonEnum.SENS_FALSE);
        Comment comment = commentMapper.selectComment(c).get(0);
        if(comment == null){
            userMap.put("is_comment",false);
        }else{
            userMap.put("is_comment",true);
        }
        //获取第三方绑定信息
        UserAuth u = new UserAuth();
        u.setUid(user.getId());
        List<UserAuth> authList = userAuthMapper.select(u);
        Map<String ,Object> qqData = new HashMap<>(2);
        Map<String ,Object> weixinData = new HashMap<>(2);
        Map<String ,Object> weiboData = new HashMap<>(2);
        if(authList.size() > LiveCommonEnum.CONSTANT_ZERO){
            for(UserAuth auth : authList){
                if(auth.getPlatform().equals(LiveCommonEnum.WEIBO) && auth.getStatus().equals(LiveCommonEnum.CONSTANT_ONE)){
                    weiboData.put("status",true);
                    weiboData.put("openid",auth.getOpenid());
                }else if(auth.getPlatform().equals(LiveCommonEnum.WEIXIN) && auth.getStatus().equals(LiveCommonEnum.CONSTANT_ONE)){
                    weixinData.put("status",true);
                    weixinData.put("openid",auth.getOpenid());
                }else if(auth.getPlatform().equals(LiveCommonEnum.QQ) && auth.getStatus().equals(LiveCommonEnum.CONSTANT_ONE)){
                    qqData.put("status",true);
                    qqData.put("openid",auth.getOpenid());
                }else{
                    continue;
                }
            }
        }
        map.put("weibo",weiboData);
        map.put("weixin",weixinData);
        map.put("qq",qqData);
        map.put("user",userMap);
        response.setData(map);
        return response;
    }


    /**
     * app -- 获取用户部分信息--此接口已作废
     */
    @RequestMapping(value = "/getusermessage", method = RequestMethod.GET)
    public AppObjectResponse getusermessage(@RequestParam("uid") Integer uid) {
        AppObjectResponse response = new AppObjectResponse();
        Map<String ,Object> data = new HashMap<>(5);
        User user = userMapper.selectByPrimaryKey(uid);
        Integer id = user.getId();
        data.put("id",id);
        data.put("nickname",user.getNickname());
        data.put("gender",user.getGender());
        data.put("image",user.getImage());
        data.put("phone",user.getPhone());
        //获取第三方绑定信息
        UserAuth u = new UserAuth();
        u.setUid(id);
        List<UserAuth> authList = userAuthMapper.select(u);
        if(authList.size() > LiveCommonEnum.CONSTANT_ZERO){
            for(UserAuth auth : authList){
                if(auth.getPlatform().equals(LiveCommonEnum.WEIBO) && auth.getStatus().equals(LiveCommonEnum.CONSTANT_ONE)){
                    Map<String ,Object> weiboData = new HashMap<>(2);
                    weiboData.put("status",true);
                    weiboData.put("openid",auth.getOpenid());
                    data.put("weibo",weiboData);
                }else if(auth.getPlatform().equals(LiveCommonEnum.WEIXIN) && auth.getStatus().equals(LiveCommonEnum.CONSTANT_ONE)){
                    Map<String ,Object> weixinData = new HashMap<>(2);
                    weixinData.put("status",true);
                    weixinData.put("openid",auth.getOpenid());
                    data.put("weixin",weixinData);
                }else if(auth.getPlatform().equals(LiveCommonEnum.QQ) && auth.getStatus().equals(LiveCommonEnum.CONSTANT_ONE)){
                    Map<String ,Object> qqData = new HashMap<>(2);
                    qqData.put("status",true);
                    qqData.put("openid",auth.getOpenid());
                    data.put("qq",qqData);
                }else{
                    continue;
                }
            }
        }
        data.put("burst_show",isBurstShow());
        response.setData(data);
        return response;
    }

    /**
     * app -- 控制IOS直播显示与否 -- 我的->律师来了
     */
    @RequestMapping(value = "/live", method = RequestMethod.POST)
    public AppObjectResponse live() {
        AppObjectResponse response = new AppObjectResponse();
        Map<String ,Object> live = new HashMap<>(5);
        // 避免ios审核,添加的是否显示状态
        live.put("show_live" ,LiveCommonEnum.CONSTANT_ONE);
        live.put("burst_show" ,isBurstShow());
        response.setData(live);
        return response;
    }

    /**
     * 控制 ios & Android 是否显示爆料
     */
    public Integer isBurstShow(){
        Setting setting = settingBiz.adminGet("burst_show");
        if(Integer.parseInt(setting.getValue()) == LiveCommonEnum.CONSTANT_ONE){
            return LiveCommonEnum.CONSTANT_ONE;
        }else {
            return LiveCommonEnum.CONSTANT_ZERO;
        }
    }

    /**
     * web -- 根据id查找用户
     * @param uid
     * @return
     */
    @RequestMapping(value = "selectUserById",method =  RequestMethod.POST)
    public UserAuthInfo selectUserById(@RequestParam("uid")  Integer uid){
        User user = userBiz.selectById(uid);
        //返回类型需要为UserAuthInfo，转换赋值
        UserAuthInfo info = new UserAuthInfo();
        info.setUid(uid);
        info.setId(uid);
        info.setImage(user.getImage());
        info.setNickname(user.getNickname());
        info.setPhone(user.getPhone());
        info.setGender(user.getGender());
        return info;
    }

    /**
     * app -- 我的问答
     */
    @RequestMapping(value = "/myProblem", method = RequestMethod.POST)
    public AppObjectResponse myProblem(@RequestParam("page") Integer page ,@RequestParam("pcount") Integer pcount , @RequestParam("uid") Integer uid) {
        AppObjectResponse response = new AppObjectResponse();
        Map<Object,Object> map = new HashMap<>(6);
        List<Map<String ,Object>> getmyposts = problemBiz.getmyposts(page, pcount, uid);
        map.put("posts",getmyposts);
        problemBiz.updateRead(uid);
        response.setData(map);
        return response;
    }

    /**
     * web -- 手机用户登陆
     * @param phone
     * @return
     */
    @RequestMapping(value = "/phoneUserLogin", method = RequestMethod.POST)
    public UserAuthInfo phoneUserLogin(@RequestParam("phone") String phone) {
        User u = new User();
        u.setPhone(phone);
        AppUser user = userBiz.insertUser(u,"");
        //返回类型需要为UserAuthInfo，转换赋值
        UserAuthInfo info = new UserAuthInfo();
        info.setUid(user.getId());
        info.setId(user.getId());
        info.setImage(user.getImage());
        info.setNickname(user.getNickname());
        info.setGender(user.getGender());
        return info;
    }

    /**
     * web -- 更新redis用户信息
     * @param
     * @return
     */
    @RequestMapping(value = "/updateRedisUser", method = RequestMethod.POST)
    public void updateRedisUser(@RequestParam("isRemeber") Boolean isRemeber,
                                @RequestBody UserAuthInfo info,
                                @RequestParam("cookieId") String cookieId) {
        if (isRemeber) {
            //用户选择了一个月免登陆
            userBiz.updateRedisUser(info, cookieId);
        }
    }

    /**
     * app -- 验证用户昵称是否存在
     */
    @RequestMapping(value = "/nameCheck", method = RequestMethod.GET)
    public AppObjectResponse nameCheck(@RequestParam("nickname") String nickname) {
        AppObjectResponse response = new AppObjectResponse();
        User u = new User();
        u.setNickname(nickname);
        User user = userBiz.selectOne(u);
        if(user != null){
            response.setStatus(AppCommonMessageEnum.USER_NAME_EXIST.key());
            response.setMessage(AppCommonMessageEnum.USER_NAME_EXIST.value());
            return response;
        }
        return response;
    }

    /**
     * web端--用户退出
     * @param sessionId
     */
    @RequestMapping(value = "/removeRedisUser", method = RequestMethod.POST)
    public void removeRedisUser(@RequestParam("sessionId") String sessionId) {
        //用户退出登陆
        userBiz.removeRedisUser(sessionId);
            //web端用户退出登陆
            userBiz.removeRedisUser(sessionId);
    }

    /**
     * app端--用户退出
     * @param sessionId
     */
    @RequestMapping(value = "/removeAppUser", method = RequestMethod.POST)
    public void removeAppUser(@RequestParam("sessionId") String sessionId) {
            //app端用户退出登陆
            userBiz.removeAPPUser(sessionId);
    }

    @RequestMapping(value = "/findRedisUser", method = RequestMethod.POST)
    public UserAuthInfo findRedisUser(@RequestParam("sessionId") String sessionId) {
        //用户退出登陆
        UserAuthInfo user = userBiz.findRedisUser(sessionId);
        return user;
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ObjectRestResponse<AppUser> userLogin(@RequestParam("code") String code,
                                                 @RequestParam("phone") String phone,
                                                 @RequestParam("sessionId") String sessionId,
                                                 @RequestParam(value = "channel_num", defaultValue = "0") String channelNum) {
        Integer beforeSessionId = (Integer) this.redisTemplate.opsForValue().get(USER_VALIDATE + sessionId);
        Assert.isTrue(Integer.valueOf(code).equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        String httpSessionId = this.request.getSession().getId();
        ObjectRestResponse<AppUser> channelObjectRestResponse = new ObjectRestResponse<>();
        User user = new User();
        user.setPhone(phone);
        AppUser appUser = userBiz.insertUser(user, httpSessionId);
        appUser.setSessionid(httpSessionId);
        channelObjectRestResponse.setData(appUser);
        return channelObjectRestResponse;
    }

    @RequestMapping(value = "/platformLogin", method = RequestMethod.POST)
    public ObjectRestResponse platformLogin(@RequestParam("accessToken") String accessToken,
                                            @RequestParam("openId") String openId,
                                            @RequestParam("platform") Integer platform) {
        ObjectRestResponse<Map> channelObjectRestResponse = new ObjectRestResponse<>();
        Map<String, Object> dataMap = userBiz.platformLogin(openId, platform, accessToken, request.getSession().getId());
        channelObjectRestResponse.setData(dataMap);
        return channelObjectRestResponse;

    }

    @RequestMapping(value = "/portrait", method = RequestMethod.POST)
    public ObjectRestResponse portrait(@RequestParam("uid") Integer uid,
                                       @RequestParam("imagePath") String imagePath) {
        User user = new User();
        user.setId(uid);
        user.setImage(imagePath);
        userBiz.updateSelectiveById(user);
        return new ObjectRestResponse<>();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectRestResponse upload(@RequestParam("file") MultipartFile file) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        try {
            Assert.notNull(file, "图片不能为空");
            objectRestResponse.setStatus(200);
        } catch (Exception ex) {
            objectRestResponse.setRel(true);
            objectRestResponse.setStatus(500);
            objectRestResponse.setMessage(ex.getMessage());
        } finally {

        }
        return objectRestResponse;
    }
}
