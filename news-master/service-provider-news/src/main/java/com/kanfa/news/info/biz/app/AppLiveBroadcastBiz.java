package com.kanfa.news.info.biz.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.constant.UserConstant;
import com.kanfa.news.common.constant.UserEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.util.AliyunUtil;
import com.kanfa.news.common.util.DateUtil;
import com.kanfa.news.common.util.HttpSendUtil;
import com.kanfa.news.common.util.MD5Util;
import com.kanfa.news.info.entity.*;
import com.kanfa.news.info.mapper.*;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * app直播 Live broadcast 模块接口
 *
 * @author wdw
 * @email wangdawei@kanfanews.com
 * @date 2018-3-29 14:23:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AppLiveBroadcastBiz extends BaseBiz<ContentMapper, Content> {
    private Logger logger = LoggerFactory.getLogger(AppLiveBroadcastBiz.class);
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ChannelContentMapper channelContentMapper;
    @Autowired
    private ContentBroadcastMapper contentBroadcastMapper;
    @Autowired
    private ContentBroadcastBindMapper contentBroadcastBindMapper;
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String USER_VALIDATE = "USER:VALIDATE:";
    @Value("${app.video.album.shareUrl}")
    private String shareUrl;
    @Value("${aliyun.broadcast.domain}")
    private String domain;
    @Value("${aliyun.broadcast.key}")
    private String key;
    @Value("${aliyun.broadcast.AccessKey.id}")
    private String accessKeyId;
    private String appName = "AppName";
    private String streamName = "kf";
    private String action = "DescribeLiveStreamOnlineUserNum";
    private String hostName = "live.aliyuncs.com";
    private String version = "2016-11-01";

    /**
     * app 直播认证手机号验证码请求接口
     */
    public ObjectRestResponse<Map<String, Object>> checkSMSCode(String code, String authcode, String sessionid, Integer phone) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //检验手机号是否被绑定
        AppUser appUser = new AppUser();
        appUser.setPhone(String.valueOf(phone));
        List<AppUser> selectUser = appUserMapper.select(appUser);
        if (null == selectUser || selectUser.size() == 0) {
            channelObjectRestResponse.setMessage("手机号已经被绑定");
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            return channelObjectRestResponse;
        }
        //校验短信校验码
        String beforeSessionId = this.redisTemplate.opsForValue().get(USER_VALIDATE + sessionid);
        Assert.notNull(beforeSessionId, UserEnum.ValidateCodeEnum.VALIDATE_CODE_ERR.value());
        Assert.isTrue(code.equals(beforeSessionId), UserEnum.ValidateCodeEnum.VALIDATE_CODE_EXPIRE.value());
        Map<String, Object> returnMap = new HashMap<>(16);
        returnMap.put("id",selectUser.get(0).getId());
        returnMap.put("phone",phone);
        channelObjectRestResponse.setData(returnMap);
        return channelObjectRestResponse;
    }

    /**
     * app 直播获取地址接口
     */
    public ObjectRestResponse<Map<String, Object>> getBroadcastAddress(HttpServletRequest request) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        logger.info("query address start!");
        //校验参数合法性
        try {
            Assert.notNull(request.getHeader("Lng"), "Lng 不能为空");
            Assert.notNull(request.getHeader("Lat"), "Lat 不能为空");
        } catch (IllegalArgumentException e) {
            channelObjectRestResponse.setMessage(e.getMessage());
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            return channelObjectRestResponse;
        }
        //获取参数
        String lng = request.getHeader("Lng");
        String lat = request.getHeader("Lat");
        // 请求实例：http://api.map.baidu.com/geocoder/v2/?location=39.9097756858,116.5169140946&output=json&pois=1&ak=teGyPg2ASxm5Y7G9VRmfjZTOimmTKAiE
        String url = CommonConstants.BAIDU_MAP_URL + "?location=" + lat + "," + lng + "&output=json&pois=1&ak=" + CommonConstants.BAIDU_MAP_AK;
        String resultJson = HttpSendUtil.httpGet(url, "");
        if (null == resultJson || JSON.parseObject(resultJson) == null) {
            channelObjectRestResponse.setMessage("获取地址失败！");
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            return channelObjectRestResponse;
        }
        JSONObject jsonObject = JSON.parseObject(resultJson);
        String status = jsonObject.get("status").toString();
        if ("0".equals(status)) {
            String address = jsonObject.getJSONObject("result").get("formatted_address").toString();
            Map<String, Object> returnMap = new HashMap<>(16);
            returnMap.put("address", address);
            channelObjectRestResponse.setData(returnMap);
            return channelObjectRestResponse;
        }
        return channelObjectRestResponse;
    }

    /**
     * app 创建直播(阿里云)接口
     */
    public ObjectRestResponse<Map<String, Object>> getBroadcastInfo(String title, String address, String phone, String sessionid, Integer uid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        if (null == uid) {
            channelObjectRestResponse.setMessage("请登录!");
            channelObjectRestResponse.setStatus(CommonConstants.EX_USER_INVALID_CODE);
            return channelObjectRestResponse;
        }
        ContentInfo contentInfo = new ContentInfo();
        contentInfo.setCategory(1);
        contentInfo.setContentType(LiveCommonEnum.CONTENT_BROADCAST);
        contentInfo.setTitle(title);
        Date date = new Date();
        contentInfo.setCreateTime(date);
        contentInfo.setCreateUid(uid);
        contentInfo.setUpdateUid(uid);
        contentInfo.setUpdateTime(date);
        //set default value
        contentInfo.setCheckStatus(0);
        contentInfo.setLongTitle("");
        contentInfo.setContentState(1);
        contentInfo.setChannels(1);
        //save content
        int cid = contentMapper.addContent(contentInfo);
        ChannelContent channelContent = new ChannelContent();
        channelContent.setChannelId(158);
        channelContent.setContentId(cid);
        channelContent.setIsPublish(0);
        channelContent.setPublishTime(date);
        channelContent.setCardType(LiveCommonEnum.CONTENT_BROADCAST);
        //save default value
        channelContent.setTop(0);
        channelContent.setIsDelete(1);
        channelContent.setOrderNumber(0);
        channelContent.setRecommendWeight(60);
        channelContent.setFromType(1);
        channelContent.setType(0);
        //save channel content
        channelContentMapper.insert(channelContent);
        Map<String, Object> result = new HashMap(16);
        result.put("cid", cid);
        result.put("pushurl", getPushurl(String.valueOf(cid), uid.toString()));
        ContentBroadcast contentBroadcast = new ContentBroadcast();
        contentBroadcast.setCid(cid);
        contentBroadcast.setLiveSourceChannel(2);
        contentBroadcast.setTitle(title);
        contentBroadcast.setAppname(appName);
        contentBroadcast.setStreamsname(streamName);
        contentBroadcast.setBroadcastPhone(Long.valueOf(phone));
        contentBroadcast.setLiveAddress(address);
        contentBroadcast.setCreateTime(date);
        int id = contentBroadcastMapper.addContentBroadcast(contentBroadcast);
        result.put("id", id);
        Map share = new HashMap(16);
        share.put("title", title);
        share.put("content", title);
        share.put("share_image", shareUrl);
        share.put("share_url", CommonConstants.URL_PREFIX + "web/broadcast/index?cid=" + cid);
        result.put("share", share);
        channelObjectRestResponse.setData(result);
        return channelObjectRestResponse;
    }

    /**
     * 此方法根据php代码翻译过来的
     *
     * @param cid
     * @param uid
     * @return
     */
    private String getPushurl(String cid, String uid) {
        String strName = uid + streamName + cid;
        String thisAppName = appName;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
        long time = calendar.getTime().getTime();
        String md5 = MD5Util.md5("/" + thisAppName + "/" + strName + "-" + time + "-0-0" + key);
        String authKey = time + "-0-0-" + md5;
        return "rtmp://video-center.alivecdn.com/" + thisAppName + "/" + strName + "?vhost=" + domain + authKey;
    }

    /**
     * app 直播在线人数接口(阿里云)接口
     */
    public ObjectRestResponse<Map<String, Object>> getOnlineUserNum(Integer cid) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        //校验参数合法性
        try {
            Assert.notNull(cid, "title 不能为空");
        } catch (IllegalArgumentException e) {
            channelObjectRestResponse.setMessage(e.getMessage());
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            return channelObjectRestResponse;
        }
        ContentBroadcast contentBroadcast = new ContentBroadcast();
        contentBroadcast.setCid(cid);
        List<ContentBroadcast> contentBroadcastData = contentBroadcastMapper.select(contentBroadcast);
        if (null == contentBroadcastData || contentBroadcastData.size()==0) {
            channelObjectRestResponse.setMessage("未查询到相关数据！");
            channelObjectRestResponse.setStatus(UserConstant.PARAMETER_ILLEGALITY);
            return channelObjectRestResponse;
        }
        ContentBroadcast contentBroadcastDataMsg=contentBroadcastData.get(0);
        String appname = contentBroadcastDataMsg.getAppname();
        String streamsname = contentBroadcastDataMsg.getStreamsname();
        System.out.println("appname: "+appname);
        System.out.println("streamsname: "+streamsname);
        //生成私有参数，不同API需要修改
        Map<String, String> privateParams = new HashMap<>(16);
        privateParams.put("AppName", appname);
        privateParams.put("StreamName", streamsname);
        privateParams.put("Action", action);
        privateParams.put("DomainName", domain);
        //生成公共参数，不需要修改
        try {
            String result = AliyunUtil.requestAccess(privateParams);
            if (null != result) {
                System.out.println("aliyun return : "+result);
                JSONObject jsonObject = JSON.parseObject(result);
                Integer userNumber = jsonObject.getJSONObject("OnlineUserInfo").getJSONArray("LiveStreamOnlineUserNumInfo").getJSONObject(0).getInteger("UserNumber");
                Map<String, Object> returnMap = new HashMap<>(16);
                returnMap.put("personnum", userNumber);
                channelObjectRestResponse.setData(returnMap);
                return channelObjectRestResponse;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channelObjectRestResponse;
    }

    /**
     * app 新版律师来了接口(直播表)接口
     */
    public ObjectRestResponse<Map<String, Object>> getBroadcastListNew(Integer page, Integer pcount) {
        ObjectRestResponse channelObjectRestResponse = new ObjectRestResponse();
        List result = new ArrayList(16);
        //158 : 律师来了频道          22：新视频
        PageHelper.startPage(page, pcount);
        List<Map> lvshiBroadcastLivePage = contentBroadcastBindMapper.getLvshiBroadcastLivePage(158, 22);
        PageHelper.startPage(page, pcount);
        List<Map> lvshiBroadcastContentLivePage = contentBroadcastBindMapper.getLvshiBroadcastContentLivePage(158);
        result.addAll(lvshiBroadcastLivePage);
        result.addAll(lvshiBroadcastContentLivePage);
        HashMap<String, Object> objectObjectHashMap = new HashMap<>(16);
        objectObjectHashMap.put("list", result);
        channelObjectRestResponse.setData(objectObjectHashMap);
        return channelObjectRestResponse;
    }

    public String aliyunHttpRequest(){
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", "<your accessKey>", "<your accessSecret>");
        //DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "live", "live.aliyuncs.com"); //添加自定义endpoint。
        IAcsClient client = new DefaultAcsClient(profile);
        return null;
    }

}