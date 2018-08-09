package com.kanfa.news.common.constant;

/**
 * Created by ace on 2017/8/29.
 */
public class CommonConstants {
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";
    // 用户token异常
    public static final Integer EX_USER_INVALID_CODE = 40101;
    // 客户端token异常
    public static final Integer EX_CLIENT_INVALID_CODE = 40301;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    public static final Integer EX_OTHER_CODE = 500;
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_NAME = "currentUser";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "name";

    public static final String URL_PREFIX ="http://java-web.vrcdkj.cn/";
    //百度获取地址位置接口
    public static final String BAIDU_MAP_URL ="http://api.map.baidu.com/geocoder/v2/";
    //百度获取地址位置接口 AK
    public static final String BAIDU_MAP_AK ="teGyPg2ASxm5Y7G9VRmfjZTOimmTKAiE";

    //专题
    public static final int  CONTENT_SPECIAL = 1;
    //文章
    public static final int  CONTENT_NEWS = 2;
    //图集
    public static final int CONTENT_ALBUM = 3;
    //视频
    public static final int CONTENT_VIDEO = 4;
    //生活
    public static final int CONTENT_LOCAL_LIFE = 5;
    //商铺
    public static final int CONTENT_SHOP = 6;
    //投票
    public static final int CONTENT_VOTE = 7;

    //直播
    public static final int CONTENT_BROADCAST = 9;
    //自定义
    public static final int CONTENT_CUSTOMIZE = 10;
    // 直播
    public static final int CONTENT_VR = 11;
    // 活动
    public static final int CONTENT_ACTIVITY = 13;
    // 判决
    public static final int CONTENT_JUDGMENT = 14;
    // 公告
    public static final int CONTENT_POST = 15;

    //首页快捷入口
    public static final int CONTENT_LINK = 20;
    // 律师来了
    public static final int CONTENT_LIVE_LAW=21;
    // 直播--总的
    public static final int CONTENT_LIVE=22;
    // 庭审h5页面
    public static final int CONTENT_LIVE_COURT_H5=23;
    // 庭审原生页面
    public static final int CONTENT_LIVE_COURT=24;
    //政法先锋
    public static final int CONTENT_LAW = 25;

    //session前缀用于取uid
    public static final String APPUSERSESSIONID = "APPUSER:SESSIONID:";

    //极光验证AppKey: efa865371ef55d5b28e3d471
    //Master Secret:  95c1e5e142f21ad15d91c815
    public final static String JIGUANG_APPKEY = "efa865371ef55d5b28e3d471";
    public final static String JIGUANG_MASTER_SECRET = "95c1e5e142f21ad15d91c815";
}
