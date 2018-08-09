package com.kanfa.news.admin.vo.video;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Chen
 * on 2018/3/12 11:05
 */
public class VideoContentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 存入appChannelList集合
     */
    private List<Integer> appChannelIdList;
    /**
     * 存入appChannelList集合
     */
    private List<Integer> pcChannelIdList;

    public List<Integer> getAppChannelIdList() {
        return appChannelIdList;
    }

    public void setAppChannelIdList(List<Integer> appChannelIdList) {
        this.appChannelIdList = appChannelIdList;
    }

    public List<Integer> getPcChannelIdList() {
        return pcChannelIdList;
    }

    public void setPcChannelIdList(List<Integer> pcChannelIdList) {
        this.pcChannelIdList = pcChannelIdList;
    }

    /**
     * 存入tagNameList集合
     */
    private List<String> tagNameList;

    public List<String> getTagNameList() {
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }

    //来源作者的Id
    private Integer sourceId;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    //内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
    private Integer contentType;


    //是否发布
    private Integer isPublish;

    //videoDuration  视频时长
    private String videoDuration;

    public String getVideoDuration() {
        return StringUtils.isEmpty(videoDuration) ? "" : videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    //是否置顶
    private Integer isTop;

    //标题
    private String title;

    //标题
    private String longTitle;

    //导语
    private String introduction;

    //图片路径
    private String image;

    //1 原创 2 转载 3 抓取
    private Integer sourceType;

    //推荐权重
    private Integer recommendWeight;

    //审核状态
    private Integer checkStatus;

    //是否为法治类文章 0 不是 1 是
    private Integer isLegal;

    //视频地址Id
    private VideoDemand videoDemand;

    //视频类型Id
    private Integer videoType;

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    //来源
    private String source;


    //创建人Id
    private Integer createUid;

    //添加人
    private String createUser;

    //创建时间
    private Date createTime;

    //修改人
    private Integer updateUid;

    //最后编辑人
    private String updateUser;

    //修改时间
    private Date updateTime;

    //浏览量
    private Integer viewCount;

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    //状态，1：正常，0：删除
    private Integer contentState;

    private  Integer category;

    private Integer isCheck;

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    //是否展示关联内容 is_relation 视频频道是否展示关联内容(0:不展示;1:展示)
    private Integer isRelation;

    public Integer getIsRelation() {
        return isRelation;
    }

    public void setIsRelation(Integer isRelation) {
        this.isRelation = isRelation;
    }

    //评论 commentContentList
    private List<String> commentContentList;


    //爆料Id
    private Integer burstId;

    public Integer getBurstId() {
        return burstId;
    }

    public void setBurstId(Integer burstId) {
        this.burstId = burstId;
    }

    public List<String> getCommentContentList() {
        return commentContentList;
    }

    public void setCommentContentList(List<String> commentContentList) {
        this.commentContentList = commentContentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
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
        return StringUtils.isEmpty(introduction) ? "" : introduction;
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


    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getSource() {
        return StringUtils.isEmpty(source) ? "" : source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getRecommendWeight() {
        return recommendWeight;
    }

    public void setRecommendWeight(Integer recommendWeight) {
        this.recommendWeight = recommendWeight;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(Integer isLegal) {
        this.isLegal = isLegal;
    }

    public VideoDemand getVideoDemand() {
        return videoDemand;
    }

    public void setVideoDemand(VideoDemand videoDemand) {
        this.videoDemand = videoDemand;
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

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return StringUtils.isEmpty(updateUser) ? "" : updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
