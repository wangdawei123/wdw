package com.kanfa.news.web.vo.news;

import com.kanfa.news.web.vo.channel.ContentInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/5 15:58
 */
public class ContentDetailInfo implements Serializable{

    private Integer id;
    private String title;
    private String longTitle;
    private String introduction;
    private String createTime;
    private Integer contentType;
    private String source;
    private String contentDetail;
    private String appUrl;
    private String activityUrl;
    //收藏
    private Integer isFaved;
    //是否点赞
    private Integer isLove;
    private Integer loveNum;
    //视频
    private List<Map<String,Object>> extent;
    //评论
    private List<Map<String,Object>> commentList;
    //分享
    private Map<String,Object> share;
    //活动
    private List<Map<String,Object>> activitys;
    //绑定
    private List<ContentInfo> bindList;
    //分享的绑定
    private List<ContentDetailBindInfo> bindShareList;
    //公告判决书标题
    private String sourceTile;
    //设备id
    private String devid;
    //评论总数
    private Integer commentCount;
    //用户id
    private Integer uid;
    private Integer uuid;

    private Map<String ,Object> signPackage;

    //抓取，判决书(决定跳转的页面)
    private Integer contentStyle;

    private Integer sourceType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Integer getIsFaved() {
        return isFaved;
    }

    public void setIsFaved(Integer isFaved) {
        this.isFaved = isFaved;
    }

    public List<Map<String, Object>> getExtent() {
        return extent;
    }

    public void setExtent(List<Map<String, Object>> extent) {
        this.extent = extent;
    }

    public List<Map<String, Object>> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Map<String, Object>> commentList) {
        this.commentList = commentList;
    }

    public Map<String, Object> getShare() {
        return share;
    }

    public void setShare(Map<String, Object> share) {
        this.share = share;
    }

    public List<Map<String, Object>> getActivitys() {
        return activitys;
    }

    public void setActivitys(List<Map<String, Object>> activitys) {
        this.activitys = activitys;
    }

    public List<ContentInfo> getBindList() {
        return bindList;
    }

    public void setBindList(List<ContentInfo> bindList) {
        this.bindList = bindList;
    }

    public String getSourceTile() {
        return sourceTile;
    }

    public void setSourceTile(String sourceTile) {
        this.sourceTile = sourceTile;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Map<String, Object> getSignPackage() {
        return signPackage;
    }

    public void setSignPackage(Map<String, Object> signPackage) {
        this.signPackage = signPackage;
    }

    public Integer getContentStyle() {
        return contentStyle;
    }

    public void setContentStyle(Integer contentStyle) {
        this.contentStyle = contentStyle;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public Integer getIsLove() {
        return isLove;
    }

    public void setIsLove(Integer isLove) {
        this.isLove = isLove;
    }

    public List<ContentDetailBindInfo> getBindShareList() {
        return bindShareList;
    }

    public void setBindShareList(List<ContentDetailBindInfo> bindShareList) {
        this.bindShareList = bindShareList;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getLoveNum() {
        return loveNum;
    }

    public void setLoveNum(Integer loveNum) {
        this.loveNum = loveNum;
    }
}
