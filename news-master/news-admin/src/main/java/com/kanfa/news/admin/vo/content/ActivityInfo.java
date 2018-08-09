package com.kanfa.news.admin.vo.content;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/29 11:12
 */
public class ActivityInfo implements Serializable {
    private Integer id;

    /**
     * app频道集合
     */
    private List<Integer> appChannelIdList;

    //标题
    private String title;

    /**
     * 栏目，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
     */
    private Integer category;

    //导语
    private String introduction;

    //图片路径
    private String image;

    private String customUrl;

    //0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
    private Integer cardType;

    //内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
    private Integer contentType;

    //创建人Id
    private Integer createUid;

    //创建时间
    private Date createTime;

    //最后修改人Id
    private Integer updateUid;

    //更新时间
    private Date updateTime;

    //状态，1：正常，0：删除
    private Integer contentState;

    //推荐权重
    private Integer recommendWeight;

    private Integer isLegal;

    //0 在审核列表不显示　１显示
    private Integer isCheck;

    //审核状态（0待审核，1审核通过，2审核不通过）
    private Integer checkStatus;

    /**
     * 客户端的ip
     */
    private String ip;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getContentState() {
        return contentState;
    }

    public void setContentState(Integer contentState) {
        this.contentState = contentState;
    }

    public Integer getRecommendWeight() {
        return recommendWeight;
    }

    public void setRecommendWeight(Integer recommendWeight) {
        this.recommendWeight = recommendWeight;
    }

    public Integer getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(Integer isLegal) {
        this.isLegal = isLegal;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getAppChannelIdList() {
        return appChannelIdList;
    }

    public void setAppChannelIdList(List<Integer> appChannelIdList) {
        this.appChannelIdList = appChannelIdList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
