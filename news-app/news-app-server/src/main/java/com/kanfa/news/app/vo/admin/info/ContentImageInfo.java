package com.kanfa.news.app.vo.admin.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/5 15:58
 * 这个类包含 content content_image content_image_group三个表中的字段
 *
 */
public class ContentImageInfo implements Serializable{



    private Integer id;
    /**
     * 内容ID
     */
    private Integer cid;
    private Integer channelId;
    /**
     * 存入list集合
     */
    private List<Integer> channelIdList;

    /**
     * 存入tagId集合
    private List<Integer> tagIdList;

    /**
     * 存入记者Id集合
     */
    private List<Integer> reporterIdList;

    /**
     * 存入记者类型id集合
     */
    private List<Integer> reporterTypeList;


    /**
     * 0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
     */
    private Integer CardType;

    //内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
    private Integer contentType;

    //是否发布
    private Integer isPublish;

    //是否置顶
    private Integer isTop;

    //标题
    private String title;

    //标题
    private String longTitle;

    //标题
    private String introduction;

    private Integer sourceType;

    private Integer isLegal;

    private Integer videoType;

    //起始日期
    private Date beginTime;

    //截止日期
    private Date endTime;

    //创建人Id
    private Integer createUid;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //发布时间
    private Date publishTime;

    //状态，1：正常，0：删除
    private Integer contentState;

    //添加人
    private String createUser;

    //最后编辑人
    private String updateUser;

    private Integer page;

    private Integer limit;

    private Integer checkStatus;

    //文章详情
    private String contentDetail;

    //content表中的图片URL。同样可以根据其获取缩略图URl
    private String image;

    //content_image_group表中的图片
    private String imageGroup;

    //content_image_group表中图片集合
    private List<String> imageList = new ArrayList<>();
    /**
     * 否使用导语来做图片介绍，1：是，0：否'
     */
    private Integer descType;
    /**
     * 图集的图片数目
     */
    private Integer num;
    /**
     * 状态，1：正常，0：删除
     */
    private Integer stat;
    /**
    图片描述
     */
    private String desc;
    /**
     * 排序值，越大的值越靠前
     */
    private Integer order;

    /**
     * 视频表里，视频时长字段
     * @return
     */
    private String duration;


    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getDescType() {
        return descType;
    }

    public void setDescType(Integer descType) {
        this.descType = descType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public List<Integer> getChannelIdList() {
        return channelIdList;
    }

    public void setChannelIdList(List<Integer> channelIdList) {
        this.channelIdList = channelIdList;
    }


    public List<Integer> getReporterIdList() {
        return reporterIdList;
    }

    public void setReporterIdList(List<Integer> reporterIdList) {
        this.reporterIdList = reporterIdList;
    }

    public List<Integer> getReporterTypeList() {
        return reporterTypeList;
    }

    public void setReporterTypeList(List<Integer> reporterTypeList) {
        this.reporterTypeList = reporterTypeList;
    }

    public Integer getCardType() {
        return CardType;
    }

    public void setCardType(Integer cardType) {
        CardType = cardType;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(Integer isLegal) {
        this.isLegal = isLegal;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public String getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(String imageGroup) {
        this.imageGroup = imageGroup;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "ContentImageInfo{" +
                "id=" + id +
                ", cid=" + cid +
                ", channelId=" + channelId +
                ", contentType=" + contentType +
                ", title='" + title + '\'' +
                ", sourceType=" + sourceType +
                ", createTime=" + createTime +
                ", contentState=" + contentState +
                ", checkStatus=" + checkStatus +
                ", image='" + image + '\'' +
                ", imageGroup='" + imageGroup + '\'' +
                ", imageList=" + imageList +
                ", num=" + num +
                ", duration='" + duration + '\'' +
                '}';
    }
}
