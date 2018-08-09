package com.kanfa.news.common.constant.app;

import java.util.*;

/**
 * Created by kanfa on 2018/3/29.
 */
public class LiveCommonEnum {
    //专题
    public final static Integer  CONTENT_SPECIAL = 1;
    //文章
    public final static Integer  CONTENT_NEWS = 2;
    //图集
    public final static Integer  CONTENT_ALBUM = 3;
    //视频
    public final static Integer  CONTENT_VIDEO = 4;
    //生活
    public final static Integer  CONTENT_LOCAL_LIFE = 5;
    //商铺
    public final static Integer  CONTENT_SHOP = 6;
    //投票
    public final static Integer  CONTENT_VOTE = 7;

    //直播
    public final static Integer  CONTENT_BROADCAST = 9;
    //自定义
    public final static Integer  CONTENT_CUSTOMIZE = 10;
    // 直播
    public final static Integer  CONTENT_VR = 11;
    public final static Integer  CARD_TYPE_SPECAIL = 12;

    // 活动
    public final static Integer  CONTENT_ACTIVITY = 13;
    // 判决
    public final static Integer  CONTENT_JUDGMENT = 14;
    // 公告
    public final static Integer  CONTENT_POST = 15;

    //首页快捷入口
    public final static Integer  CONTENT_LINK = 20;
    // 律师来了
    public final static Integer  CONTENT_LIVE_LAW=21;

    public final static Integer LIVE_TYPE_TSH = 1;
    public final static Integer LIVE_TYPE_LSH = 2;
    // 直播--总的
    public final static Integer CONTENT_LIVE = 22;
    // 庭审原生页面
    public final static Integer CONTENT_LIVE_COURT = 24;
    // 庭审h5页面
    public final static Integer  CONTENT_LIVE_COURT_H5=23;
    //庭审id
    public final static Integer CONTENT_LIVE_ID = 95;
    //政法先锋
    public final static Integer  CONTENT_LAW = 25;
    //推荐频道id
    public final static Integer RECOMMEND_CHANNEL_ID=2;
    //推广id
    public final static Integer EXTENSION_LIVE_ID=1031393;
    //推广id所在的频道
    public final static Integer EXTENSION_CHANNEL_ID=2;
    //推广id想放到的位置，0为位置1
    public final static Integer EXTENSION_POSITION=2;

    //政法先锋id
    public final static Integer LAW_ID=159;

    //青春微普法大赛id
    public final static Integer MICRO_ID=5;


    public final static Integer FROM_TYPE_CONTENT=1;
    public final static Integer FROM_TYPE_LIVE=2;






    public final static String SEARCH_KEYWORD = "想搜什么来试试吧~";
    public final static String CHATROOM_NOTICE = "系统提示：看法新闻直播间倡导绿色直播，文明观看，如出现低俗涉黄、涉暴内容，欢迎举报，我们将进行封禁处理！";
    public final static String DEFAULT_IMAGE = "http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png";
    public final static String ACTIVITY_LAW_TITLE = "首都政法先锋";
    public final static String ACTIVITY_LAW_DESC = "第十届北京市“人民满意的政法干警（单位）”暨“首都政法先锋”评选表彰";
    public final static String ACTIVITY_LAW_ICON = "http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png";
    public final static String ACTIVITY_LAW_URL = "/web/activity/politics/index.html";
    public final static String IMAGE_URL = "/assets/img/default-head-3x.png";
    public final static String WEB_IMAGE_URL = "/assets/web/img1/default-head-2x.png";
    public final static String IMAGE_404_URL = "/assets/web/img/404.jpg";

    //web 服务 域名                app工程域名      http://java-test.vrcdkj.cn/
    public final static String URL_PREFIX = "http://java-web.vrcdkj.cn/";
    public final static String STATUS_SUCCESS = "200";


    public final static String USER_SESSIONID = "WEBUSER:SESSIONID:";

    public final static String APP_USER_SESSIONID = "APPUSER:SESSIONID:";
    public final static String APP_USER_INFO = "APPUSER:USERINFO:";
    public final static String APP_WEIXIN_TICKET = "weixin_jsapiticket:";
    public final static long WEB_SESSIONID_TIME = 3600*24*7;

    /**
     * 中奖 1特等奖  2一等奖  3二等奖  4三等奖  5四等奖
     */
    public final static Integer   AWARD_LEVEL_APECIAL = 1;
    public final static Integer   AWARD_LEVEL_ONE = 2;
    public final static Integer   AWARD_LEVEL_TWO = 3;
    public final static Integer   AWARD_LEVEL_FHREE = 4;
    public final static Integer   AWARD_LEVEL_FOUR = 5;
    /**
     *
     */
    public final static Integer  APP_DEVICE_ANDRIOID = 1;
    public final static Integer  APP_DEVICE_IOS = 2;

    /**
     * 4 视频  9 直播
     */
    public final static Integer   TYPE_STATUS_FOUR = 4;
    public final static Integer   TYPE_STATUS_NINE = 9;
    /**
     * 评论-- 文章的评论
     */
    public final static Integer  NEWS_COMMENT = 0;
    /**
     * 圈子评论
     */
    public final static Integer   REVELATION_COMMENT = 1;
    /**
     * 问政评论
     */
    /**
     * content 的source_type 1 原创 2 转载 3 抓取
     */
    public final static Integer   SOURCE_TYPE_ORIGINAL = 1;
    public final static Integer   SOURCE_TYPE_REPRINT = 2;
    public final static Integer   SOURCE_TYPE_CAPTRUE = 3;
    /**
     * content 的content_style 抓取文章类型(0:默认;1:判决书;2:公告
     */
    public final static Integer   CONTENT_STYLE = 0;
    public final static Integer   CONTENT_STYLE_BOOK = 1;
    public final static Integer   CONTENT_STYLE_ADV = 2;

    /**
     * 政治评论
     */
    public final static Integer   POLITICAL_COMMENT = 2;
    /**
     * 活动评论
     */
    public final static Integer   ACTIVITY_COMMENT = 3;
    /**
     * 评论--直播的评论
     */
    public final static Integer   LIVE_COMMENT = 22;

    /**
     * 删除状态
     */
    public final static Integer   IS_DEL_STATUS = 0;
    public final static Integer   NO_DEL_STATUS = 1;
    /**
     * 直播类型 1 直播  2.庭审
     */
    public final static Integer   LIVE_TYPE_ONLINE = 1;
    public final static Integer   LIVE_TYPE_COURT = 2;
    /**
     * 已读状态
     */
    public final static Integer   READ_TRUE = 1;
    public final static Integer   READ_FALSE = 0;

    /**
     * 审核状态  是否已审核，1：已审核，0：未审核
     */
    public final static Integer   OPS_TRUE = 1;
    public final static Integer   OPS_FALSE = 0;
    /**
     * 直播状态  直播状态 0: 预告 1:直播中 2:回顾',
     */
    public final static Integer   PRIVIEW_STATUS = 0;
    public final static Integer   LIVE_STATUS = 1;
    public final static Integer   REVIEW_STATUS = 2;
    /**
     * 直播状态1  直播状态 1: 预告 2:直播中 3:回顾',
     */
    public final static Integer   PRIVIEW_STATUS1 = 1;
    public final static Integer   LIVE_STATUS1 = 2;
    public final static Integer   REVIEW_STATUS1 = 3;
    /**
     * 0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
     * channelContentCard 的cardtype字段
     */
    public final static Integer   CHANNEL_CARDTYPE_SMALL = 1;
    public final static Integer   CHANNEL_CARDTYPE_NO = 0;
    public final static Integer   CHANNEL_CARDTYPE_BIG = 2;
    public final static Integer   CHANNEL_CARDTYPE_THREE = 3;
    public final static Integer   CHANNEL_CARDTYPE_FOUR = 4;
    public final static Integer   CHANNEL_CARDTYPE_FIVE = 5;
    public final static Integer   CHANNEL_CARDTYPE_LIVE = 9;
    public final static Integer   CHANNEL_CARDTYPE_TEN = 10;
    public final static Integer   CARD_TYPE_LIVE = 21;
    public final static Integer   CARD_TYPE_PREVIEW = 20;
    public final static Integer   CARD_TYPE_COLUMN = 22;
    /**
     * 是否包含敏感词
     */
    public final static Integer   SENS_TRUE = 1;
    public final static Integer   SENS_FALSE = 0;
    /**
     * 直播专题类型  0为普通 1为经典回顾 2为直播',
     */
    public final static Integer   SPECIAL_TYPE_NORMAL = 0;
    public final static Integer   SSPECIAL_TYPE_REVIEW = 1;
    public final static Integer   SSPECIAL_TYPE_LIVE = 2;
    /**
     * 频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
     */
    public final static Integer   CATE_APP_PORTAL = 1;
    public final static Integer   CATE_APP_VIDEO = 2;
    public final static Integer   CATE_APP_VR = 3;
    public final static Integer   CATE_PC = 3;
    /**
     * 是否开启聊天室，1开启，0关闭
     */
    public final static Integer   OPEN_CHATROOM_TRUE = 1;
    public final static Integer   OPEN_CHATROOT_FALSE = 0;
    /**
     * 跳转类型  		跳转类型，1为直播 2为庭审
     */
    public final static Integer   JUMP_TYPE_LIVE = 1;
    public final static Integer   JUMP_TYPE_COURT = 2;
    /**
     * focus-跳转类型  	1为app 2为H5
     */
    public final static Integer   FOCUS_JUMP_TYPE_APP = 1;
    public final static Integer   FOCUS_JUMP_TYPE_H5 = 2;
    /**
     * 图片大小类型，1为小图，2为大图
     */
    public final static Integer   IMAGE_TYPE_SMALL = 1;
    public final static Integer   IMAGE_TYPE_BIG = 2;
    /**
     * 标题类型，1为副标题 2为法院
     */
    public final static Integer   TITLE_TYPE_FU = 1;
    public final static Integer   TITLE_TYPE_COURT = 2;
    /**
     * 常量，数量
     */
    public final static Integer   CONSTANT_TEN = 10;
    public final static Integer   CONSTANT_EIGHT = 8;
    public final static Integer   CONSTANT_TWENTY_TWO = 22;
    public final static Integer   CONSTANT_SEXTEEN = 16;
    public final static Integer   CONSTANT_four = 4;
    public final static Integer   CONSTANT_TWO = 2;
    public final static Integer   CONSTANT_THREE = 3;
    public final static Integer   CONSTANT_ONE = 1;
    public final static Integer   CONSTANT_ZERO = 0;
    public final static Integer   CONSTANT_TWO_FU = -2;
    /**
     * 评论类型 //1:默认为回复评论 2：自己发布的评论
     */
    public final static Integer   COMMENT_ANSWER = 1;
    public final static Integer   COMMENT_PUBLISH = 2;
    /**
     * 评论审核规则:  0-先审后发, 1-先发后审
     */
    public final static Integer   COMMENT_SHEN = 0;
    public final static Integer   COMMENT_SEND = 1;


    public final static Integer   WEB_SUCCESS = 1;

    /**
     * 用户登陆平台
     * @return
     */
    public final static Integer   WEIBO = 1;
    public final static Integer   WEIXIN = 2;
    public final static Integer   QQ = 3;

    /**
     * 用户性别 1为男性，2为女性
     * @return
     */
    public final static Integer  GENDER_MAN = 1;
    public final static Integer   GENDER_WONAN = 2;

    public static Map<Integer , String> getLiveMap(){
        Map<Integer , String> liveMap = new LinkedHashMap<>(3);
        liveMap.put(-1 , "全部");
        liveMap.put(1 , "正在直播");
        liveMap.put(0 , "即将播出");
        liveMap.put(2 , "节目回顾");
        return liveMap;
    }

    public static Map<Integer , String> getCaseType(){
        Map<Integer , String> caseTypeMap = new LinkedHashMap<>(3);
        caseTypeMap.put(-1 , "全部");
        caseTypeMap.put(0 , "未知");
        caseTypeMap.put(1 , "刑事案件");
        caseTypeMap.put(2 , "民事案件");
        caseTypeMap.put(3 , "行政案件");
        return caseTypeMap;
    }
    public static Map<Integer , String> getCourtLevel(){
        Map<Integer , String> courtLevelMap = new LinkedHashMap<>(3);
        courtLevelMap.put(-1 , "全部");
        courtLevelMap.put(1 , "最高法院");
        courtLevelMap.put(2 , "高级法院");
        courtLevelMap.put(3 , "中级法院");
        courtLevelMap.put(4 , "基层法院");
        return courtLevelMap;
    }
    public static Map<Integer , String> getFav(){
        Map<Integer , String> favMap = new LinkedHashMap<>(3);
        favMap.put(2 , "文章");
        favMap.put(3 , "图集");
        favMap.put(4 , "视频");
        favMap.put(11 , "VR");
        favMap.put(9 , "直播");
        return favMap;
    }

    public static Map<String , Map<String , String>> getActivity(){
        Map<String , Map<String , String>> map = new LinkedHashMap<>(3);
        Map<String , String> one = new HashMap<>(3);
        one.put("ico","https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png");
        one.put("title","欲知后事如何？请看APP分解。");

        Map<String , String> two = new HashMap<>(3);
        two.put("ico","https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png");
        two.put("title","转发新闻赢iPhoneX，千万人都在玩。");
        map.put("one",one);
        map.put("tow",two);
        return map;
    }


}
