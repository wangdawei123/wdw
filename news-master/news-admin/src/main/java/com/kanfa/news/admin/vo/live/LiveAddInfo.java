package com.kanfa.news.admin.vo.live;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kanfa.news.admin.vo.kpicount.KpiCountContentInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Chen
 * on 2018/3/24 16:05
 */
public class LiveAddInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    //直播的标题
    private String  title;

    //直播的副标题
    private String subtitle;

    //app频道
    private List<Integer> appChannelList;

    //直播的类型Id live_type_id
    private Integer liveTypeId;

    //直播状态 live_status  直播状态 0: 预告 1:直播中 2:回顾
    private Integer liveStatus;

    //频道推荐的权重 xm_channel_content中的recommend_weight
    private Integer recommendWeight;

    //直播大厅的推荐权重 live_recommend_weight
    private Integer liveRecommendWeight;

    //直播的类别 live_type 直播类别 0 无 1栏目 2其他
    private Integer liveType;

    //xm_kpi_video  uid 人员id 工作类型 1视频编辑 2动画 3配音 6直播运营出境 7直播栏目运营主播
    //工作类型 1视频编辑 2动画 3配音 6直播运营出境 7直播栏目运营主播 work_type
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private List<KpiCountContentInfo> kpiCountList;

    public List<KpiCountContentInfo> getKpiCountList() {
        return kpiCountList;
    }

    public void setKpiCountList(List<KpiCountContentInfo> kpiCountList) {
        this.kpiCountList = kpiCountList;
    }

    //来源作者 sourceId
    private Integer sourceId;

    //是否允许 评论 comment_status 0允许评论  1不允许评论
    private Integer commentStatus;

    //评论类型 先审后发 先发后审 comment_type 0为先审后发 1位先发后审
    private Integer commentType;

    //是否开启互动聊天室  chatroom_status 是否开启聊天室，1开启，0关闭
    private Integer chatroomStatus;

    //是否显示在预告模块preview_show 上否显示在预告模块 0 不 1 是 默认1
    private Integer previewShow;

    //直播开始的时间 start_time 直播开始时间'
    private Date startTime;

    //预告视频的url preview_url
    private String previewUrl;

    //封面图片 cover_img
    private String coverImg;

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    //直播内容 live_content
    private String liveContent;

    //来源信息备注 from_remark
    private String  fromRemark;


    public String getFromRemark() {
        return fromRemark;
    }

    public void setFromRemark(String fromRemark) {
        this.fromRemark = fromRemark;
    }

    //聊天室公告，特殊设置，没有为空
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String chatroomNotice;

    public String getChatroomNotice() {
        return chatroomNotice;
    }

    public void setChatroomNotice(String chatroomNotice) {
        this.chatroomNotice = chatroomNotice;
    }


    //案件类型 0:未知 1:刑事案件 2:民事案件 3:行政案件
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Integer caseType;

    //法院名称
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String courtName;

    //案件信息
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String caseInfo;

    //庭审信息
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String courtInfo;



    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(String caseInfo) {
        this.caseInfo = caseInfo;
    }

    public String getCourtInfo() {
        return courtInfo;
    }

    public void setCourtInfo(String courtInfo) {
        this.courtInfo = courtInfo;
    }



    //创建人
    private Integer createUid;
    //创建人
    private String  createUser;
    //创建时间 create_time
    private Date createTime;

    //直播上线下线状态 1:上线 0:下线 默认
    private Integer isPublish;
    //deleted 是否删除 0不 1是
    private Integer isDelete;
    //locked 是否锁定 0默认
    private Integer isLock;

    //0 默认
    private Integer isTop;

    //修改人update_uid
    private Integer updateUid;

    private Date updateTime;

    //审核 check_state 0待审核，1审核通过，2审核不通过
    private Integer checkState;

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<Integer> getAppChannelList() {
        return appChannelList;
    }

    public void setAppChannelList(List<Integer> appChannelList) {
        this.appChannelList = appChannelList;
    }

    public Integer getLiveTypeId() {
        return liveTypeId;
    }

    public void setLiveTypeId(Integer liveTypeId) {
        this.liveTypeId = liveTypeId;
    }

    public Integer getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    public Integer getRecommendWeight() {
        return recommendWeight;
    }

    public void setRecommendWeight(Integer recommendWeight) {
        this.recommendWeight = recommendWeight;
    }

    public Integer getLiveRecommendWeight() {
        return liveRecommendWeight;
    }

    public void setLiveRecommendWeight(Integer liveRecommendWeight) {
        this.liveRecommendWeight = liveRecommendWeight;
    }

    public Integer getLiveType() {
        return liveType;
    }

    public void setLiveType(Integer liveType) {
        this.liveType = liveType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getCommentType() {
        return commentType;
    }

    public void setCommentType(Integer commentType) {
        this.commentType = commentType;
    }

    public Integer getChatroomStatus() {
        return chatroomStatus;
    }

    public void setChatroomStatus(Integer chatroomStatus) {
        this.chatroomStatus = chatroomStatus;
    }

    public Integer getPreviewShow() {
        return previewShow;
    }

    public void setPreviewShow(Integer previewShow) {
        this.previewShow = previewShow;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getLiveContent() {
        return liveContent;
    }

    public void setLiveContent(String liveContent) {
        this.liveContent = liveContent;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
