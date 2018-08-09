package com.kanfa.news.user.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.common.constant.UserConstant;
import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.GetUserInfoUtil;
import com.kanfa.news.common.util.UUIDUtils;
import com.kanfa.news.user.biz.UserAuthBiz;
import com.kanfa.news.user.biz.UserBiz;
import com.kanfa.news.user.entity.User;
import com.kanfa.news.user.entity.UserAuth;
import com.kanfa.news.user.vo.admin.UserAuthInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("binding")
public class UserAuthController extends BaseController<UserAuthBiz,UserAuth> {
    @Autowired
    private  UserAuthBiz userAuthBiz;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static String USER_VALIDATE = "USER:VALIDATE:";
    /**
     * 用户性别
     */
    private final String MAN = "男";
    private final String WOMAN = "女";
    /**
     * 保存新用户
     * @param userAuth
     */
    @RequestMapping(value = "insertUser",method = RequestMethod.POST)
    public void insertUser(@RequestBody UserAuthInfo userAuth){
        //先保存user表，得到最新的主键id
        userBiz.insertBaseUser(userAuth);
        //把user表的id赋值给userAuth的uid
        userAuth.setUid(userAuth.getId());
        //再保存userAuth表
        userAuthBiz.insertUser(userAuth);
    }

    @RequestMapping(value = "wechatLogin",method = RequestMethod.POST)
    public UserAuthInfo wechatLogin(@RequestBody UserAuthInfo user){
        Integer platform = user.getPlatform();
        //第三方平台代码，1：微博；2：微信；3：QQ
        if(null == user.getUnionid() || null == user.getOpenid()){
            return null;
        }
        //如果存在该平台的绑定账号，则获取该用户信息
        UserAuthInfo userAuthInfo = getOneByweixPlatform(platform, user.getUnionid());
        if(userAuthInfo != null){
            if(userAuthInfo.getGzopenid() == null){
                //说明已经是app用户，微信账号存入公众账号gzopenid
                userAuthBiz.updateOneByweixUnionid(platform, user.getUnionid(), user.getOpenid());
            }
            return setInfo(userAuthInfo.getUid());
        }
        if(user.getImage() == null){
            user.setImage(LiveCommonEnum.URL_PREFIX + LiveCommonEnum.IMAGE_URL);
        }
        if(user.getGender() == null){
            user.setGender("m");
        }
        User u = new User();
        u.setNickname(user.getNickname());
        u.setIsDelete(1);
        User one = userBiz.selectOne(u);
        if(one != null){
            user.setNickname(user.getNickname() + UUIDUtils.generate(5));
        }
        u.setImage(user.getImage());
        u.setIntroduction(user.getIntroduction());
        u.setGender(user.getGender());
        userBiz.insert(u);
        if(u.getId() != null){
            UserAuth auth = new UserAuth();
            auth.setUid(u.getId());
            auth.setPlatform(platform);
            auth.setGzopenid(user.getGzopenid());
            auth.setAccessToken(user.getAccessToken());
            auth.setUnionid(user.getUnionid());
            auth.setCreateTime(new Date());
            userAuthBiz.insert(auth);
            if(auth.getId() != null){
                return setInfo(u.getId());
            }
        }
        return null;
    }


    private UserAuthInfo setInfo(Integer uid){
        if(null == uid){
            return null;
        }
        User u = new User();
        u.setId(uid);
        u.setIsDelete(1);
        User user = userBiz.selectOne(u);
        if(user != null){
            if(user.getIsBlock() == 1){
                //这里返回该用户已经被锁定的信息
                return null;
            }
            UserAuthInfo info = new UserAuthInfo();
            //org.springframework.beans包下的BeanUtils方法，把前面的赋值给后面的。但时间类型不能正常赋值
            BeanUtils.copyProperties(user,info);
      /*      info.setUid(user.getId());
            info.setId(user.getId());
            info.setNickname(user.getNickname());
            info.setImage(user.getImage());
            info.setPhone(user.getPhone());
            info.setGender(user.getGender());*/
            return info;
        }
        return null;

    }


    private UserAuthInfo getOneByweixPlatform(Integer platform,String unionid){
        UserAuth auth = userAuthBiz.getOneByweixPlatform(platform, unionid);

        //把UserAuth 转换成 UserAuthInfo
        UserAuthInfo info = new UserAuthInfo();
        //org.springframework.beans包下的BeanUtils方法，把前面的赋值给后面的。但时间类型不能正常赋值
        BeanUtils.copyProperties(auth,info);
        info.setCreateTime(auth.getCreateTime());

//        info.setUid(auth.getUid());
//        info.setPlatform(platform);
//        info.setOpenid(auth.getOpenid());
//        info.setUnionid(unionid);
//        info.setAccessToken(auth.getAccessToken());
//        info.setCreateTime(auth.getCreateTime());
//        info.setStatus(auth.getStatus());
//        info.setGzopenid(auth.getGzopenid());
        return info;
    }


    /**
     * 根据用户微博 openId查找用户
     * @param uid
     * @return
     */
    @RequestMapping(value = "selecetByWiboOpenId",method = RequestMethod.POST)
    public UserAuthInfo selecetByWiboOpenId(@RequestParam("uid") String uid,
                                            @RequestParam("access_token") String access_token){
        UserAuth userAuth1 = new UserAuth();
        userAuth1.setOpenid(uid);
        UserAuth userAuth = userAuthBiz.selecetByOpenId(userAuth1);
        UserAuthInfo info = null;
        if(userAuth == null){
            //用户第一次登陆，进行保存操作
            //调用下面的方法
            info = insertWeiBoUser(uid, access_token);
        }else{
            //用户已经有记录
            info = selectUserByAuthId(userAuth.getUid());
        }
        return  info;
    }


    public UserAuthInfo insertWeiBoUser(String uid , String access_token){
        //通过token请求得到用户详细信息--调用common中工具类
        String info = GetUserInfoUtil.getWeiBoUserInfo(uid,access_token);
        //解析json数据
        JSONObject object = JSON.parseObject(info);
        String screen_name = object.getString("screen_name");
        String profile_image_url = object.getString("profile_image_url");
        String gender = object.getString("gender");

        UserAuthInfo userAuthInfo = new UserAuthInfo();
        userAuthInfo.setPlatform(1);
        userAuthInfo.setCreateTime(new Date());
        userAuthInfo.setOpenid(uid);
        userAuthInfo.setAccessToken(access_token);
        userAuthInfo.setNickname(screen_name);
        userAuthInfo.setImage(profile_image_url);
        userAuthInfo.setGender(gender);
        this.insertUser(userAuthInfo);
        return userAuthInfo;
    }


    /**
     * 根据用户QQ openId查找用户
     * @return
     */
    @RequestMapping(value = "selecetByQQOpenId",method = RequestMethod.POST)
    public UserAuthInfo selecetByQQOpenId(@RequestParam("openId") String openId,
                                          @RequestParam("accessToken") String accessToken,
                                          @RequestParam("qclient_id") String qclient_id){
        UserAuth userAuth1 = new UserAuth();
        userAuth1.setOpenid(openId);
        UserAuth userAuth = userAuthBiz.selecetByOpenId(userAuth1);
        UserAuthInfo info = null;
        if(userAuth == null){
            //用户第一次登陆，进行保存操作
            //调用下面的方法
            info = insertQQUser(openId, accessToken ,qclient_id);
        }else{
            //用户已经有记录
            info = selectUserByAuthId(userAuth.getUid());
        }
        return  info;
    }

    public UserAuthInfo insertQQUser(String openId , String access_token ,String qclient_id){
        //通过open_id,token获取个人信息--调用common中工具类
        String qqUserInfo = GetUserInfoUtil.getQQUserInfo(qclient_id, openId, access_token);
        //解析json数据
        JSONObject infoJson = JSON.parseObject(qqUserInfo);
        String screen_name = infoJson.getString("nickname");
        String profile_image_url = infoJson.getString("figureurl");
        String gender = infoJson.getString("gender");

        //新建封装类传入数据
        UserAuthInfo userAuthInfo = new UserAuthInfo();
        userAuthInfo.setPlatform(1);
        userAuthInfo.setCreateTime(new Date());
        userAuthInfo.setOpenid(openId);
        userAuthInfo.setAccessToken(access_token);
        userAuthInfo.setNickname(screen_name);
        userAuthInfo.setImage(profile_image_url);
        if(WOMAN.equals(gender)){
            userAuthInfo.setGender("f");
        }
        if(MAN.equals(gender)){
            userAuthInfo.setGender("m");
        }
        //执行操作数据库
        this.insertUser(userAuthInfo);
        return userAuthInfo;
    }


    /**
     * 根据用户微信 openId查找用户
     * @return
     */
    @RequestMapping(value = "selecetByWechatOpenId",method = RequestMethod.POST)
    public UserAuthInfo selecetByWechatOpenId(@RequestParam("openid") String openid,
                                              @RequestParam("access_token") String access_token){
        UserAuth userAuth1 = new UserAuth();
        userAuth1.setOpenid(openid);
        UserAuth userAuth = userAuthBiz.selecetByOpenId(userAuth1);
        UserAuthInfo info = null;
        if(userAuth == null){
            //用户第一次登陆，进行保存操作
            //调用下面的方法
            info = insertWechatUser(openid, access_token);
        }else{
            //用户已经有记录
            info = selectUserByAuthId(userAuth.getUid());
        }
        return  info;
    }

    public UserAuthInfo insertWechatUser(String openid , String access_token){
        //通过open_id,token获取个人信息--调用 common中工具类
        String info = GetUserInfoUtil.getWeiXinUserInfo(openid , access_token);
        //解析json数据
        JSONObject infoJson = JSON.parseObject(info);
        String screen_name = infoJson.getString("nickname");
        String profile_image_url = infoJson.getString("headimgurl");
        String gender = infoJson.getString("sex");

        //新建封装类传入数据
        UserAuthInfo userAuthInfo = new UserAuthInfo();
        if(LiveCommonEnum.CONSTANT_ONE.equals(gender)){
            userAuthInfo.setGender("f");
        }else if(LiveCommonEnum.CONSTANT_ZERO.equals(gender)){
            userAuthInfo.setGender("m");
        }
        userAuthInfo.setPlatform(1);
        userAuthInfo.setCreateTime(new Date());
        userAuthInfo.setOpenid(openid);
        userAuthInfo.setAccessToken(access_token);
        userAuthInfo.setNickname(screen_name);
        userAuthInfo.setImage(profile_image_url);

        //执行操作数据库
        this.insertUser(userAuthInfo);
        return userAuthInfo;
    }

    /**
     *  web第三方登陆使用
     * @param id
     * @return
     */
    public UserAuthInfo selectUserByAuthId(Integer id){
        UserAuthInfo info = new UserAuthInfo();
        User user = userBiz.selectById(id);
        //org.springframework.beans包下的BeanUtils方法，把前面的赋值给后面的。但时间类型不能正常赋值
        BeanUtils.copyProperties(user,info);
        info.setUid(user.getId());
        /*info.setId(user.getId());
        info.setNickname(user.getNickname());
        info.setImage(user.getImage());
        info.setPhone(user.getPhone());
        info.setGender(user.getGender())*/;
        return info;
    }



    /**
     * 第三方账号绑定
     * @param uid
     * @param access_token
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/bindSocialAccount",method = RequestMethod.POST)
    public ObjectRestResponse<UserAuth> bindSocialAccount(@RequestParam("uid") Integer uid,
                                                          @RequestParam("access_token") String access_token,
                                                          @RequestParam("openid") String openid,
                                                          @RequestParam("platform") Integer platform){
        ObjectRestResponse<UserAuth> channelObjectRestResponse = new ObjectRestResponse<UserAuth>();
        //校验账号openId是否已被绑定
        UserAuth user=new UserAuth();
        user.setOpenid(openid);
        user.setPlatform(platform);
        user.setStatus(1);
        UserAuth userAuth = userAuthBiz.selecetByOpenId(user);
        if(null!=userAuth){
            User us=new User();
            us.setId(userAuth.getUid());
            User user1 = userBiz.selectOne(us);
            String nickname=user1.getNickname();
            //账号已被占用
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage(nickname.substring(0,1)+"***"+nickname.substring(nickname.length()-1,nickname.length()));
            return channelObjectRestResponse;
        }
        UserAuth user2=new UserAuth();
        user2.setOpenid(openid);
        user2.setUid(uid);
        UserAuth userAuth2 = userAuthBiz.selecetByOpenId(user2);
        if(null!=userAuth2){
            userAuth2.setStatus(1);
            userAuthBiz.updateById(userAuth2);
        }else{
            //默认值
            UserAuth entity=new UserAuth();
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
     * 第三方账号解绑并绑定新账户
     * @param uid
     * @param access_token
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/newBindAccount",method = RequestMethod.POST)
    public ObjectRestResponse<UserAuth> newBindAccount(@RequestParam("uid") Integer uid,
                                                       @RequestParam("access_token") String access_token,
                                                       @RequestParam("openid") String openid,
                                                       @RequestParam("platform") Integer platform){
        ObjectRestResponse<UserAuth> channelObjectRestResponse = new ObjectRestResponse<UserAuth>();
        //查询已绑定账号
        UserAuth user=new UserAuth();
        user.setOpenid(openid);
        user.setPlatform(platform);
        user.setStatus(1);
        UserAuth userAuth = userAuthBiz.selecetByOpenId(user);
        //解绑
        userAuth.setStatus(0);
        userAuthBiz.updateById(userAuth);
        //绑定新账号
        UserAuth user2=new UserAuth();
        user2.setOpenid(openid);
        user2.setUid(uid);
        UserAuth userAuth2 = userAuthBiz.selecetByOpenId(user2);
        if(null!=userAuth2){
            userAuth2.setStatus(1);
            userAuthBiz.updateById(userAuth2);
        }else{
            UserAuth entity=new UserAuth();
            entity.setUid(uid);
            entity.setPlatform(platform);
            entity.setOpenid(openid);
            entity.setAccessToken(access_token);
            entity.setCreateTime(new Date());
            entity.setStatus(1);
            entity.setUnionid("");
            userAuthBiz.insert(entity);
        }
        UserAuth returnuser=new UserAuth();
        returnuser.setOpenid(openid);
        channelObjectRestResponse.setData(returnuser);
        return channelObjectRestResponse;
    }

    /**
     * 校验带绑定手机号
     * @param phone
     * @return
     */
    @RequestMapping(value = "/checkPhone",method = RequestMethod.POST)
    public ObjectRestResponse<User> checkPhone(@RequestParam("phone") String phone){
        ObjectRestResponse<User> channelObjectRestResponse = new ObjectRestResponse<User>();
        //校验参数合法性
        if(null==phone ){
            //参数不能为空
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("手机号不能为空");
            return channelObjectRestResponse;
        }
        //校验账号openId是否已被绑定
        User thisuser=new User();
        thisuser.setPhone(phone);
        User user = userBiz.selectOne(thisuser);
        if(null!=user){
            //账号已被占用
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该手机已被别人绑定!请换手机号");
            return channelObjectRestResponse;
        }
        return channelObjectRestResponse;
    }

    /**
     * 第三方登录后绑定手机号
     * @param uid
     * @param code
     * @param phone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/phoneBinding",method = RequestMethod.POST)
    public ObjectRestResponse<User> phoneBinding(@RequestParam("uid") Integer uid,
                                                 @RequestParam("code") String code,
                                                 @RequestParam("phone") String phone,
                                                 @RequestParam("verificatcode") String verificatcode){
        ObjectRestResponse<User> channelObjectRestResponse = new ObjectRestResponse<User>();
        //校验短信校验码
        String beforeSessionId = this.redisTemplate.opsForValue().get(USER_VALIDATE + verificatcode);
        Assert.notNull(beforeSessionId, UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        Assert.isTrue(code.equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());

        //校验phone是否已被绑定
        User thisuser=new User();
        thisuser.setPhone(phone);
        User user = userBiz.selectOne(thisuser);
        if(null!=user){
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该手机已被绑定!");
            return channelObjectRestResponse;
        }
        User bindphone=new User();
        bindphone.setId(uid);
        User phone2 = userBiz.selectOne(bindphone);
        if(phone2!=null){
            phone2.setPhone(phone);
            phone2.setIsDelete(1);
            userBiz.updateById(phone2);
        }
        return channelObjectRestResponse;
    }

    /**
     * 手机账号解绑并绑定新账户
     * @param uid
     * @param code
     * @param phone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/newPhoneBinding",method = RequestMethod.POST)
    public ObjectRestResponse<User> newPhoneBinding(@RequestParam("uid") Integer uid,
                                                    @RequestParam("code") String code,
                                                    @RequestParam("phone") String phone,
                                                    @RequestParam("verificatcode") String verificatcode){
        ObjectRestResponse<User> channelObjectRestResponse = new ObjectRestResponse<User>();
        //校验短信校验码
        String beforeSessionId = this.redisTemplate.opsForValue().get(USER_VALIDATE + verificatcode);
        Assert.notNull(beforeSessionId, UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        Assert.isTrue(code.equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());

        //查询已绑定旧手机号
        User thisuser=new User();
        thisuser.setPhone(phone);
        User user = userBiz.selectOne(thisuser);
        if(null!=user){
            //解绑
            user.setPhone("");
            userBiz.updateById(user);
            //绑定新账户
            User newuser=new User();
            newuser.setId(uid);
            User binduser = userBiz.selectOne(newuser);
            binduser.setPhone(phone);
            binduser.setIsDelete(1);
            userBiz.updateById(binduser);
        }else{
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该手机号解绑失败");
            return channelObjectRestResponse;
        }
        return channelObjectRestResponse;
    }

    /**
     * 第三方账号解绑
     * @param uid
     * @param openid
     * @param platform
     * @return
     */
    @RequestMapping(value = "/unBindSocialAccount",method = RequestMethod.POST)
    public ObjectRestResponse<UserAuth> unBindSocialAccount(@RequestParam("uid") Integer uid,
                                                            @RequestParam("openid") String openid,
                                                            @RequestParam("platform") Integer platform){
        ObjectRestResponse<UserAuth> channelObjectRestResponse = new ObjectRestResponse<UserAuth>();
        //校验是否已被绑定手机号
        User thisuser=new User();
        thisuser.setId(uid);
        User userphone = userBiz.selectOne(thisuser);
        UserAuth userauth=new UserAuth();
        userauth.setUid(uid);
        List<UserAuth> userAuths = userAuthBiz.selectList(userauth);
        if(userphone!=null){
            if((null==userphone.getPhone()|| "".equals(userphone.getPhone())) && userAuths!=null && userAuths.size()==1){
                channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
                channelObjectRestResponse.setMessage(" 解绑后将无法登录，请先绑定手机号");
                return channelObjectRestResponse;
            }
        }else{
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("未查询到该用户信息，解绑失败！");
            return channelObjectRestResponse;
        }
        UserAuth user=new UserAuth();
        user.setUid(uid);
        user.setOpenid(openid);
        UserAuth userAuth = userAuthBiz.selectOne(user);
        if(userAuth!=null){
            userAuth.setStatus(0);
            userAuthBiz.updateById(userAuth);
        }else{
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该用户不存在，绑定失败");
            return channelObjectRestResponse;
        }
        return channelObjectRestResponse;
    }

    /**
     * 修改绑定的手机号
     * @param uid
     * @param code
     * @param oldPhone
     * @param newPhone
     * @param verificatcode
     * @return
     */
    @RequestMapping(value = "/updatePhone",method = RequestMethod.POST)
    public ObjectRestResponse<User> updatePhone(@RequestParam("uid") Integer uid,
                                                @RequestParam("code") String code,
                                                @RequestParam("oldPhone") String oldPhone,
                                                @RequestParam("newPhone") String newPhone,
                                                @RequestParam("verificatcode") String verificatcode){
        ObjectRestResponse<User> channelObjectRestResponse = new ObjectRestResponse<User>();
        System.out.println("sessionid:"+verificatcode);
        //校验短信校验码
        String beforeSessionId = this.redisTemplate.opsForValue().get(USER_VALIDATE + verificatcode);
        System.out.println("beforeSessionId:"+beforeSessionId);
        Assert.notNull(beforeSessionId, UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        Assert.isTrue(code.equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());

        //校验新手机号是否已被绑定
        User thisuser=new User();
        thisuser.setPhone(newPhone );
        User user = userBiz.selectOne(thisuser);
        if(null!=user){
            //手机号已被占用
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            channelObjectRestResponse.setMessage("该手机已被绑定!");
        }
        User oldUserPhone=new User();
        oldUserPhone.setId(uid);
        User userphone = userBiz.selectOne(oldUserPhone);
        if(userphone!=null){
            userphone.setPhone(newPhone);
            userphone.setIsDelete(1);
            userBiz.updateById(userphone);
        }
        return channelObjectRestResponse;
    }
}