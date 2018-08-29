package com.kanfa.news.admin.rpc.message;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.kanfa.news.admin.constant.AdminCommonConstant;
import com.kanfa.news.admin.vo.message.MessageInfo;
import com.kanfa.news.common.constant.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;


/**
 * @author Jiqc
 * @date 2018/4/11 13:41
 */
public class Jdpush {
    protected static final Logger LOG = LoggerFactory.getLogger(Jdpush.class);

    public  static JPushClient jpushClient=null;

    public static String pushIOS(String segment, MessageInfo messageInfo,Map<String,String> extra) {
        jpushClient = new JPushClient(CommonConstants.JIGUANG_MASTER_SECRET, CommonConstants.JIGUANG_APPKEY, 3);
        //生成推送的内容，这里我们先测试全部推送
        Map<String,String> alert = new HashMap<>(2);
        alert.put("title",messageInfo.getTitle());
        alert.put("body",messageInfo.getContent());
        System.out.println(JSON.toJSONString(alert));
        Long time = System.currentTimeMillis()/1000;
        Audience tag=Audience.all();
        if(StringUtils.isNotEmpty(segment)){
             tag = Audience.segment("test");
        }
        PushPayload payload=buildPushObjectIos(tag,JSON.toJSONString(alert),extra,time.intValue());
        try {
            PushResult result = jpushClient.sendPush(payload);
            return result.error.getMessage();
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
        return null;
    }

    public static String pushAndroid(String segment, MessageInfo messageInfo,Map<String,String> extra) {
        jpushClient = new JPushClient(CommonConstants.JIGUANG_MASTER_SECRET, CommonConstants.JIGUANG_APPKEY, 3);
        //生成推送的内容，这里我们先测试全部推送
        Long time = System.currentTimeMillis()/1000;
        Audience tag=Audience.all();
        if(StringUtils.isNotEmpty(segment)){
             tag = Audience.segment(segment);
        }
//        Audience audience,String title,JsonObject extra ,String content,Integer time
        PushPayload payload=buildPushObjectAndroid(tag,messageInfo.getTitle(),extra,messageInfo.getContent(),time.intValue());
        try {
            PushResult result = jpushClient.sendPush(payload);
            return result.error.getMessage();
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
        return null;
    }

    public static PushPayload buildPushObject_all_all_alert(String alert) {
        return PushPayload.alertAll(alert);
    }

    public static PushPayload buildPushObject_all_alias_alert(String alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.alert(alert))
                .build();
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle(String alert,String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.android(alert, title, null))
                .build();
    }

    public static PushPayload buildPushObject_android_and_ios() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.newBuilder()
                        .setAlert("alert content")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("Android Title").build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }

    /**
     * 苹果 指定发送对象，alert，extra，
     * @return
     */
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String alert,String extra,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(content))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .build())
                .build();
    }

    /**
     * 苹果 指定发送对象，alert，extra，
     * @return
     */
    public static PushPayload buildPushObjectIos(Audience audience , Object alert , Map<String,String> extra , Integer time) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(audience)
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(1)
                                .setSound("default")
//                                .addExtra("extras", extra)
                                .addExtras(extra)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setSendno(time)
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    /**
     * 发送安卓
     * @param audience
     * @param title
     * @param extra
     * @param content
     * @param time
     * @return
     */
    public static PushPayload buildPushObjectAndroid(Audience audience,String title, Map<String,String> extra ,String content,Integer time) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(audience)
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(content)
                                .setTitle(title)
                                .setBuilderId(1)
//                                .addExtra("extras",extra)
                                .addExtras(extra)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setSendno(time)
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String extra,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }
}
