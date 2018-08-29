package com.kanfa.news.admin.vo.video;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chen
 * on 2018/4/9 14:13
 */
public class VideoContentShow implements Serializable {

    private Integer id;

    /**
     * 存入appChannelList集合
     */
    private List<Integer> appChannelIdList;
    /**
     * 存入appChannelList集合
     */
    private List<Integer> pcChannelIdList;

    /**
     * 存入tagNameList集合
     */
    private List<String> tagNameList;

    //来源作者的Id
    private Integer sourceId;


    //videoDuration  视频时长
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String videoDuration;

    public String getVideoDuration() {
        return StringUtils.isEmpty(videoDuration) ? "" : videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    //标题
    private String title;

    //标题
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String longTitle;

    //导语
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String introduction;

    //图片路径
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String image;

    //1 原创 2 转载 3 抓取
    private Integer sourceType;

    //推荐权重
    private Integer recommendWeight;


    //视频
    private VideoDemand videoDemand;

    //视频类型Id
    private Integer videoType;

    //来源
    private String source;

    private Integer isLegal;

    //是否推荐 is_recommend
    private Integer isRecommend;
    //是否值班 is_duty  0 不 1 是
    private Integer isDuty;
    //videoColumnId
    private Integer videoColumnId;

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsDuty() {
        return isDuty;
    }

    public void setIsDuty(Integer isDuty) {
        this.isDuty = isDuty;
    }

    public Integer getVideoColumnId() {
        return videoColumnId;
    }

    public void setVideoColumnId(Integer videoColumnId) {
        this.videoColumnId = videoColumnId;
    }

    public Integer getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(Integer isLegal) {
        this.isLegal = isLegal;
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

    public List<String> getTagNameList() {
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
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

    public Integer getRecommendWeight() {
        return recommendWeight;
    }

    public void setRecommendWeight(Integer recommendWeight) {
        this.recommendWeight = recommendWeight;
    }

    public VideoDemand getVideoDemand() {
        return videoDemand;
    }

    public void setVideoDemand(VideoDemand videoDemand) {
        this.videoDemand = videoDemand;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
