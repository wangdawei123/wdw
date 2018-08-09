package com.kanfa.news.common.constant.web;

/**
 * Created by kanfa on 2018/5/21.
 */
public class WebCommonParams {

    public final static Integer  CONTENT_LIVE_COURT_H5=23;
    /**
     * 通过code，请求得到token--第2步
     */
    public final static String SENDCODEPATH = "https://api.weibo.com/oauth2/access_token";
    /**
     * 微博登陆传递数据
     */
    public final static  String granyt_type = "authorization_code";
    /**
     当类型为专题
     */
    public final static  int CONTENT_TYPE_SUBJECT = 1;
    /**
     当类型为文章
     */
    public final static  int CONTENT_TYPE_ARTICLE = 2;
    /**
     * 当类型为图集
     */
    public final static  int CONTENT_TYPE_MAPS = 3;
    /**
     当类型为视频
     */
    public final static  int CONTENT_TYPE_VIDEO = 4;
    /**
     * 当返回的状态正常200时
     */
    public final static  int RETURN_STATUS = 200 ;
    /**
     * 内容正常的状态-未删除状态
     */
    public final static  int  CHECK_STATUS = 1;
    /**
     * 内容是审核通过状态
     */
    public final static  int CONTENT_STATA = 1;
    /**
     * cookie的id
     */
    public final static  String id = "websessionid";
    /**
     * 设置cookie寿命-30天
     */
    public final static  int CAPTCHA_EXPIRES = 60 * 60 * 24 * 30;
    /**
     * 用户添加反馈信息返回状态-100未登陆
     */
    public final static  int NO_LOGIN = 100;
    /**
     * 用户添加反馈信息返回状态-200成功
     */
    public final static  int SUCCESS = 200;
    /**
     * 用户添加反馈信息返回状态-500系统错误
     */
    public final static  int ERROR = 500;

}
