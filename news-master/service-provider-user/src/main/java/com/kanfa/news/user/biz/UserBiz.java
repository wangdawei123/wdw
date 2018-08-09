package com.kanfa.news.user.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.constant.app.APPCommonEnum;
import com.kanfa.news.common.exception.AppUserException;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.util.GetUserInfoUtil;
import com.kanfa.news.common.util.LogUtil;
import com.kanfa.news.user.config.RequestConfiguration;
import com.kanfa.news.user.entity.AppDevice;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.user.entity.User;
import com.kanfa.news.user.entity.UserAuth;
import com.kanfa.news.user.entity.UserDevice;
import com.kanfa.news.user.mapper.AppDeviceMapper;
import com.kanfa.news.user.mapper.UserAuthMapper;
import com.kanfa.news.user.mapper.UserDeviceMapper;
import com.kanfa.news.user.mapper.UserMapper;
import com.kanfa.news.user.vo.admin.UserAuthInfo;
import com.kanfa.news.user.vo.app.AppUser;
import com.kanfa.news.user.vo.app.PlatformUser;
import com.netflix.discovery.converters.Auto;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.kanfa.news.common.constant.UserEnum.LoginPlatformEnum.*;
import static org.apache.commons.lang3.ArrayUtils.toArray;

/**
 * 前台用户
 *
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 16:29:52
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class UserBiz extends BaseBiz<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;
    private static final int CAPTCHA_EXPIRES = 60 * 60 * 24 * 30;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Value("${app.user.imagePath}")
    private String imagePath;

    @Value("${app.user.nickName}")
    private String nickName;

    @Value("${app.authorization.qqClient}")
    private String qqClient;

    @Autowired
    protected HttpServletRequest request;

    @Resource
    private RequestConfiguration requestConfiguration;

    @Autowired
    private AppDeviceMapper appDeviceMapper;

    private Map<String, Object> info = new HashMap<>(16);
    private Map<String, Object> auth = new HashMap<>(16);
    private Map<String, Object> cache = new HashMap<>(16);
    private Map<String, Object> dataMap = new HashMap<>(16);
    private Integer uid;


    public Map<String, Integer> getapiuser(Integer id) {
        Map<String, Integer> map = new HashMap<>(1);
        User u = new User();
        u.setId(id);
        u.setIsDelete(1);
        User user = userMapper.selectOne(u);
        if (user != null) {
            map.put("id", user.getId());
            return map;
        }
        return map;

    }



    public void insertBaseUser(UserAuthInfo user) {
        userMapper.insertBaseUser(user);
    }

    /**
     * web端---把用户信息保存到redis中
     * 把用户信息保存到redis中
     * @param user
     * @param cookieId
     */
    public void updateRedisUser(UserAuthInfo user, String cookieId) {
        this.redisTemplate.opsForValue().set(LiveCommonEnum.USER_SESSIONID + cookieId, user, CAPTCHA_EXPIRES, TimeUnit.SECONDS);
    }


    /**
     * web端---把用户从redis中删除
     *
     * @param sessionId
     */
    public void removeRedisUser(String sessionId) {
        this.redisTemplate.opsForValue().set(LiveCommonEnum.USER_SESSIONID + sessionId, null, 1, TimeUnit.SECONDS);
    }


    /**
     * APP端---把用户从redis中删除
     *
     * @param sessionId
     */
    public void removeAPPUser(String sessionId) {
        this.redisTemplate.opsForValue().set(LiveCommonEnum.APP_USER_SESSIONID + sessionId, null, 1, TimeUnit.SECONDS);
    }


    /**
     * web端---查找用户信息从redis中
     * 查找用户信息从redis中
     * @param sessionId
     */
    public UserAuthInfo findRedisUser(String sessionId) {
        UserAuthInfo o = (UserAuthInfo) this.redisTemplate.opsForValue().get(LiveCommonEnum.USER_SESSIONID + sessionId);
        return o;
    }

    public User selectUserByPhone(String number) {
        User u = new User();
        u.setPhone(number);
        return userMapper.selectOne(u);
    }

    public AppUser insertUser(User user, String sessionId) {
        AppUser appUser = new AppUser();
        User currentUser = this.userMapper.selectOne(user);

        if (currentUser != null) {
            user.setId(currentUser.getId());
            user = this.userMapper.selectOne(user);
        } else {
            user.setNickname(nickName + user.getPhone().substring(0, 3) + "****" + user.getPhone().substring(7, user.getPhone().length()));
            user.setImage(imagePath);
            user.setCreateTime(new Date());
            user.setIsDelete(1);
            user.setIsBlock(0);
            userMapper.insertUser(user);
        }

        Example example = new Example(UserAuth.class);
        example.createCriteria().andEqualTo("uid", user.getId());
        List<UserAuth> userAuths = userAuthMapper.selectByExample(example);

        for (UserAuth userAuth1 : userAuths) {
            if (userAuth1.getPlatform().equals(WEI_BO.key())) {
                appUser.getWeibo().put("openid", userAuth1.getOpenid());
                appUser.getWeibo().put("status", userAuth1.getStatus());
            } else if (userAuth1.getPlatform().equals(WEI_XIN)) {
                appUser.getWeixin().put("openid", userAuth1.getOpenid());
                appUser.getWeixin().put("status", userAuth1.getStatus());
            } else if (userAuth1.getPlatform().equals(QQ)) {
                appUser.getQq().put("openid", userAuth1.getOpenid());
                appUser.getQq().put("status", userAuth1.getStatus());
            }
        }
        if (userAuths == null || userAuths.size() < 1) {
            appUser.getWeibo().put("openid", "");
            appUser.getWeibo().put("status", false);
            appUser.getWeixin().put("openid", "");
            appUser.getWeixin().put("status", false);
            appUser.getQq().put("openid", "");
            appUser.getQq().put("status", false);
        }
        BeanUtils.copyProperties(user, appUser);
        appUser.setCreate_time(user.getCreateTime());
        redisTemplate.opsForValue().set(LiveCommonEnum.APP_USER_SESSIONID + sessionId,
                appUser.getId(), 90L, TimeUnit.DAYS);
        return appUser;
    }

    /**
     * 第三方登录
     *
     * @param openId
     * @param platform
     * @param accessToken
     * @param sessionId
     * @return
     */
    public Map<String, Object> platformLogin(String openId, Integer platform, String accessToken, String sessionId) {
        //如果存在该平台的绑定账号，则获取该用户信息
        doLogin(openId, platform, accessToken);
        if (dataMap.get("uid") == null) {
            throw new AppUserException(AppUserException.APPUserEnum.USER_LOGIN_FAIL.value(),
                    AppUserException.APPUserEnum.USER_LOGIN_FAIL.key());
        }
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("sessionid", sessionId);
        resultMap.putAll(dataMap);
        redisTemplate.opsForValue().set(LiveCommonEnum.APP_USER_SESSIONID + sessionId,
                dataMap.get("uid"), 90L, TimeUnit.DAYS);
        resultMap.put("is_get_coupon", 0);

        String channelNum = "appStore";
        //领取优惠券并判断
        /*if ((Integer)dataMap.get("is_new_user") == 1 && StringUtils.isNotEmpty(channelNum)) {
            $coupon = new \Coupon();
            $result = $coupon->receiveCoupon($channel_num, $data['data']);
            if ($result['data'] == 2)
                $data['data']['is_get_coupon'] = 2;

            if ($result['data'] == 1)
                $data['data']['is_get_coupon'] = 1;
        }*/

        //获取第三方绑定信息
        UserAuth userAuthBindSearch = new UserAuth();
        userAuthBindSearch.setUid((Integer) dataMap.get("uid"));
        userAuthBindSearch.setStatus(1);
        List<UserAuth> userAuthBindList = userAuthMapper.select(userAuthBindSearch);
        for (UserAuth userAuth : userAuthBindList) {
            Map<String, Object> bindInfo = new HashMap<>(16);
            switch (UserEnum.LoginPlatformEnum.getByValue(userAuth.getPlatform())) {
                case WEI_BO:
                    bindInfo.put("status", true);
                    bindInfo.put("openid", userAuth.getOpenid());
                    resultMap.put("weibo", bindInfo);
                    break;
                case WEI_XIN:
                    bindInfo.put("status", true);
                    bindInfo.put("openid", userAuth.getOpenid());
                    resultMap.put("weixin", bindInfo);
                    break;
                case QQ:
                    bindInfo.put("status", true);
                    bindInfo.put("openid", userAuth.getOpenid());
                    resultMap.put("qq", bindInfo);
                    break;
                default:
                    break;
            }
        }

        String devid = request.getHeader(requestConfiguration.getDevId());
        String idfa = request.getHeader(requestConfiguration.getIdFa());
        String Platform = request.getHeader(requestConfiguration.getPlatform());
        String version = request.getHeader(requestConfiguration.getVersion());
        Integer deviceType = 0;
        if (StringUtils.isNotEmpty(Platform)) {
            if (APPCommonEnum.PLATFROM_ANDROID.value().equals(platform)) {
                deviceType = APPCommonEnum.PLATFROM_ANDROID.key();
            } else {
                deviceType = APPCommonEnum.PLATFORM_IOS.key();
            }
        }

        AppDevice appDeviceSearch = new AppDevice();
        if (APPCommonEnum.PLATFROM_ANDROID.key().equals(deviceType) && StringUtils.isNotEmpty(devid)) {
            appDeviceSearch.setDeviceId(devid);
            appDeviceSearch = appDeviceMapper.selectOne(appDeviceSearch);
        } else if (APPCommonEnum.PLATFORM_IOS.key().equals(deviceType) &&
                StringUtils.isNotEmpty(devid) &&
                StringUtils.isNotEmpty(idfa)) {
            appDeviceSearch.setIdfa(idfa);
            appDeviceSearch = appDeviceMapper.selectOne(appDeviceSearch);
        }

        if (appDeviceSearch == null || appDeviceSearch.getId() == null) {

            appDeviceSearch = new AppDevice();
            appDeviceSearch.setCreateDate((int) System.currentTimeMillis());
        } else if (dataMap.get("uid") != null) {
            appDeviceSearch.setUpdateTime((int) System.currentTimeMillis());
        }
        appDeviceSearch.setType(platform);
        appDeviceSearch.setUid((Integer) dataMap.get("uid"));
        appDeviceSearch.setIdfa(idfa);
        appDeviceSearch.setDeviceId(devid);
        appDeviceSearch.setVersion(version);
        if (appDeviceSearch.getId() != null) {
            appDeviceMapper.updateByPrimaryKeySelective(appDeviceSearch);
        } else {
            appDeviceMapper.insertSelective(appDeviceSearch);
        }
        resultMap.putAll(info);
        redisTemplate.opsForValue().set(LiveCommonEnum.APP_USER_INFO + dataMap.get("uid"),
                resultMap, 90L, TimeUnit.DAYS);
        return resultMap;
    }

    /**
     * 根据uid获取用户信息保存到成员变量中
     *
     * @param uid
     * @return
     */
    private Integer setInfo(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setIsDelete(1);
        user = userMapper.selectOne(user);
        if (user != null && user.getId() != null) {
            if (user.getIsBlock() != null && user.getIsBlock() == 1) {
                throw new AppUserException(AppUserException.APPUserEnum.USER_SHIELD.value(),
                        AppUserException.APPUserEnum.USER_SHIELD.key());
            }
            this.info.put("id", user.getId());
            this.info.put("phone", user.getPhone());
            this.info.put("nickname", user.getNickname());
            this.info.put("image", user.getImage());
            this.info.put("block", user.getIsBlock());
            return user.getId();
        }
        return null;
    }

    /**
     * 使用第三方平台登录
     *
     * @param openId
     * @param platform
     * @return
     */
    public void doLogin(String openId, Integer platform, String accessToken) {
        //如果存在该平台的绑定账号，则获取该用户信息
        UserAuth userAuth = new UserAuth();
        userAuth.setOpenid(openId);
        userAuth.setPlatform(platform);
        userAuth.setStatus(1);
        UserAuth beforeUserAuth = userAuthMapper.selectOne(userAuth);
        if (beforeUserAuth != null) {
            dataMap.put("uid", setInfo(beforeUserAuth.getUid()));
            dataMap.put("is_new_user", 0);
            return;
        }

        //如果不存在，则从api获取信息并新建用户
        PlatformUser platformUser = getInfoFromApi(openId, platform, accessToken);
        //融合微信pc和app端
        if (Objects.equals(platform, UserEnum.LoginPlatformEnum.WEI_XIN.key())) {
            UserAuth userAuthSearch = new UserAuth();
            userAuthSearch.setPlatform(platform);
            userAuthSearch.setUnionid(platformUser.getUnionid());
            userAuthSearch.setStatus(1);
            userAuthSearch = userAuthMapper.selectOne(userAuthSearch);
            if (userAuthSearch != null) {
                //说明已经是app用户，应用openid更新 Edit BY Liuym 20171214
                if (StringUtils.isEmpty(userAuthSearch.getOpenid())) {
                    updateOneByweixUnionid(platform, platformUser.getUnionid(), openId);
                }
                //说明不是新用户
                dataMap.put("uid", setInfo(userAuthSearch.getUid()));
                dataMap.put("is_new_user", 0);
                return;
            }
        }
        if (StringUtils.isEmpty(platformUser.getImage())) {
            platformUser.setImage(imagePath);
        }
        if (StringUtils.isEmpty(platformUser.getGender())) {
            platformUser.setGender("m");
        }


        User userSearch = new User();
        userSearch.setNickname(platformUser.getNickname());
        userSearch.setIsDelete(1);
        userSearch = userMapper.selectOne(userSearch);
        if (userSearch != null) {
            platformUser.setNickname(nickName + UUID.randomUUID().toString().replace("-", ""));
        }

        //注册新用户
        User userNew = new User();
        userNew.setNickname(platformUser.getNickname());
        userNew.setImage(platformUser.getImage());
        userNew.setIntroduction(platformUser.getIntro());
        userNew.setGender(platformUser.getGender());
        userNew.setCreateTime(new Date());
        userNew.setIsDelete(1);
        userMapper.insertUser(userNew);
        if (userNew == null || userNew.getId() == null) {
            return;
        }

        UserAuth userAuthNew = new UserAuth();
        userAuthNew.setUid(userNew.getId());
        userAuthNew.setPlatform(platform);
        userAuthNew.setOpenid(openId);
        userAuthNew.setAccessToken(accessToken);
        userAuthNew.setStatus(1);
        userAuthNew.setUnionid(platformUser.getUnionid());
        userAuthNew.setCreateTime(new Date());
        if (userAuthMapper.insertSelective(userAuthNew) > 0) {
            dataMap.put("uid", setInfo(userNew.getId()));
            //说明是新用户
            dataMap.put("is_new_user", 1);
        }
    }

    private PlatformUser getInfoFromApi(String openId, Integer platform, String accessToken) {
        PlatformUser platformUser = new PlatformUser();
        String result;
        JSONObject jsonObject;
        switch (UserEnum.LoginPlatformEnum.getByValue(platform)) {
            case WEI_BO:
                result = GetUserInfoUtil.getWeiBoUserInfo(openId, accessToken);
                jsonObject = JSON.parseObject(result);
                if (jsonObject == null) {
                    throw new AppUserException(AppUserException.APPUserEnum.USER_API_WEIBO_RETURN_ERROR.value(),
                            AppUserException.APPUserEnum.USER_API_WEIBO_RETURN_ERROR.key());
                }
                if (jsonObject.getInteger("error_code") != null) {
                    throw new AppUserException(AppUserException.APPUserEnum.USER_API_WEIBO_ERROR.value(),
                            AppUserException.APPUserEnum.USER_API_WEIBO_ERROR.key());
                }
                platformUser.setNickname(jsonObject.getString("screen_name"));
                platformUser.setImage(StringUtils.isBlank(jsonObject.getString("avatar_hd")) ?
                        jsonObject.getString("'avatar_large'") :
                        jsonObject.getString("avatar_hd"));
                platformUser.setGender("n".equals(jsonObject.getString("'gender'")) ? "" : jsonObject.getString("'gender'"));
                platformUser.setIntro(jsonObject.getString("'description'"));
                break;
            case WEI_XIN:
                result = GetUserInfoUtil.getWeiXinUserInfo(openId, accessToken);
                jsonObject = JSON.parseObject(result);
                if (jsonObject == null) {
                    throw new AppUserException(AppUserException.APPUserEnum.USER_API_WEIXIN_RETURN_ERROR.value(),
                            AppUserException.APPUserEnum.USER_API_WEIXIN_RETURN_ERROR.key());
                }
                if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
                    throw new AppUserException(AppUserException.APPUserEnum.USER_API_WEIXIN_ERROR.value(),
                            AppUserException.APPUserEnum.USER_API_WEIXIN_ERROR.key());
                }
                platformUser.setNickname(jsonObject.getString("nickname"));
                platformUser.setImage(jsonObject.getString("headimgurl"));
                platformUser.setGender(jsonObject.getInteger("sex") == 1 ? "m" : jsonObject.getInteger("sex") == 2 ? "f" : "");
                platformUser.setUnionid(jsonObject.getString("unionid"));
                break;
            case QQ:
                result = GetUserInfoUtil.getQQUserInfo(qqClient, openId, accessToken);
                jsonObject = JSON.parseObject(result);
                if (jsonObject == null) {
                    throw new AppUserException(AppUserException.APPUserEnum.USER_API_QQ_RETURN_ERROR.value(),
                            AppUserException.APPUserEnum.USER_API_QQ_RETURN_ERROR.key());
                }
                if (jsonObject.getInteger("ret") != 0) {
                    throw new AppUserException(AppUserException.APPUserEnum.USER_API_QQ_ERROR.value(),
                            AppUserException.APPUserEnum.USER_API_QQ_ERROR.key());
                }
                platformUser.setNickname(jsonObject.getString("nickname"));
                platformUser.setImage(StringUtils.isBlank(jsonObject.getString("figureurl_qq_2")) ?
                        jsonObject.getString("'figureurl_qq_1'") :
                        jsonObject.getString("figureurl_qq_2"));
                platformUser.setGender("男".equals(jsonObject.getString("'gender'")) ? "m" : "f");
                break;
            default:
                throw new AppUserException(AppUserException.APPUserEnum.USER_PLATFORM_ERROR.value(),
                        AppUserException.APPUserEnum.USER_PLATFORM_ERROR.key());
        }
        return platformUser;
    }


    public void updateOneByweixUnionid(Integer platform, String unionid, String openId) {
        updateOneByweixUnionid(platform, unionid, "", openId);
    }

    public void updateOneByweixUnionid(Integer platform, String unionid, String gzopenid, String openId) {
        UserAuth userAuth = new UserAuth();
        userAuth.setPlatform(platform);
        userAuth.setUnionid(unionid);
        userAuth.setStatus(1);
        userAuth = userAuthMapper.selectOne(userAuth);
        if (userAuth == null) {
            return;
        }
        if (StringUtils.isNotEmpty(gzopenid)) {
            userAuth.setGzopenid(gzopenid);
        }
        if (StringUtils.isNotEmpty(openId)) {
            userAuth.setOpenid(openId);
        }
        userAuthMapper.updateByPrimaryKeySelective(userAuth);
    }

}