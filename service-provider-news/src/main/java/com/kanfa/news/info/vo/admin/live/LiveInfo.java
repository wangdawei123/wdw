package com.kanfa.news.info.vo.admin.live;

import com.kanfa.news.info.vo.admin.live.courtinfo.CourtInfo;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.*;

/**
 * @author Jiqc
 * @date 2018/3/16 10:36
 */
public class LiveInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer liveSpecialId;
    private Integer specialType;
    //副标题
    private String subtitle;

    private Integer titleType;

    //直播描述/导语
    private String liveContent;

    //直播状态 0: 预告 1:直播中 2:回顾
    private Integer liveStatus;

    //直播url
    private String liveUrl;

    //直播开始时间
    private Date startTime;
    //
    private String start_time;
    private Integer pcount;
    //
    private String liveDuration;

    //案件类型 0:未知 1:刑事案件 2:民事案件 3:行政案件
    private Integer caseType;

    //法院级别 0:未知 1:最高法院 2:高级法院 3:中级法院 4:基层法院
    private Integer courtLevel;

    //法院id
    private Integer courtId;

    //法院名称
    private String courtName;

    //来源URL地址
    private String sourceUrl;

    //预告视频url
    private String previewUrl;

    //预告图片地址
    private String previewImg;

    //预告简介
    private String previewSummary;

    //预告描述
    private String previewDesc;

    //上否显示在预告模块
    private Integer previewShow;

    //是否置顶
    private Integer isTop;

    //回顾视频url
    private String reviewUrl;

    //flash对象地址
    private String flashObj;

    //封面图片地址
    private String coverImg;

    //案件信息
    private String caseInfo;

    //庭审信息
    private String courtInfo;

    //访问量
    private Integer viewCount;

    //直播上线下线状态 1:上线 0:下线
    private Integer isPublish;

    //是否删除
    private Integer isDelete;

    //是否锁定
    private Integer isLock;


    //审核状态（0待审核，1审核通过，2审核不通过
    private Integer checkState;

    //审核时间
    private Integer checkTime;

    //评论数
    private Integer commentCount;

    //审核后的评论数
    private Integer commentCheckedCount;

    //0为先审后发 1位先发后审
    private Integer commentType;

    //是否开启聊天室，1开启，0关闭
    private Integer chatroomStatus;

    //聊天室是否保活，1保活，0不需要保活
    private Integer chatroomKeeplive;

    //聊天室公告，特殊设置，没有为空
    private String chatroomNotice;

    //作者来源id
    private Integer sourceId;

    //收藏数
    private Integer collectCount;

    //推荐权重
    private Integer liveRecommendWeight;

    //参与聊天室人数
    private Integer chatroomAllcount;

    //聊天室系统独立用户数
    private Integer chatroomAppcount;

    //0允许评论  1不允许评论
    private Integer commentStatus;

    //是否显示评论框 1显示 0不显示
    private Integer reviewBox;

    //直播来源信息备注
    private String fromRemark;

    //直播类别 0 无 1栏目 2其他
    private Integer liveType;

    //播放量
    private Integer videoView;

    //app端视频点击量
    private Integer appvideoView;


    //以下为liveFocus表字段
    //焦点图标题
    private String title;

    //直播类型id,0代表是直播首页
    private Integer liveTypeId;

    //图片地址
    private String image;

    private Integer imageType;

    //0:下线 1:上线
    private Integer pub;

    //排序
    private Integer sort;

    //跳转链接
    private String url;

    //要跳转到的直播id
    private Integer liveId;

    //1、站内内容 2、url 3、app 4、URL不跳转
    private Integer jump;
    private Integer jumpType;

    private Integer type;
    private Integer[] types;

    //状态(0:已删除;1:正常)
    private Integer stat;

    //创建人
    private Integer createUid;

    private String nickname;

    //创建日期
    private Date createTime;

    //最后编辑人
    private Integer updateUid;

    //最后更新日期
    private Date updateTime;

    private Integer page;

    private Integer limit;

    private Date signtime;

    //以下为法院字段
    private String name;
    private Integer liveCount ;
    private String avatar;

    private Integer is_liked;
    private Integer likes;
    private Integer offset;


    public Integer[] getTypes() {
        return types;
    }

    public void setTypes(Integer[] types) {
        this.types = types;
    }

    public Integer getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTitleType() {
        return titleType;
    }

    public void setTitleType(Integer titleType) {
        this.titleType = titleType;
    }

    public Integer getJumpType() {
        return jumpType;
    }

    public void setJumpType(Integer jumpType) {
        this.jumpType = jumpType;
    }

    private HashMap<String ,String> share = null;


    private ArrayList<CourtInfo> court_info = null;

    public Integer getImageType() {
        return imageType;
    }

    public Integer getSpecialType() {
        return specialType;
    }

    public void setSpecialType(Integer specialType) {
        this.specialType = specialType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public Integer getIs_liked() {
        return is_liked;
    }

    public void setIs_liked(Integer is_liked) {
        this.is_liked = is_liked;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }


    public HashMap<String, String> getShare() {
        return share;
    }

    public void setShare(HashMap<String, String> share) {
        this.share = share;
    }

    public ArrayList<CourtInfo> getCourt_info() {
        return court_info;
    }

    public void setCourt_info(ArrayList<CourtInfo> court_info) {
        this.court_info = court_info;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLiveTypeId() {
        return liveTypeId;
    }

    public void setLiveTypeId(Integer liveTypeId) {
        this.liveTypeId = liveTypeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPub() {
        return pub;
    }

    public void setPub(Integer pub) {
        this.pub = pub;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLiveId() {
        return liveId;
    }

    public void setLiveId(Integer liveId) {
        this.liveId = liveId;
    }

    public Integer getJump() {
        return jump;
    }

    public void setJump(Integer jump) {
        this.jump = jump;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
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

    public Integer getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(Integer liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getLiveContent() {
        return liveContent;
    }

    public void setLiveContent(String liveContent) {
        this.liveContent = liveContent;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getLiveDuration() {
        return liveDuration;
    }

    public void setLiveDuration(String liveDuration) {
        this.liveDuration = liveDuration;
    }

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Integer getCourtLevel() {
        return courtLevel;
    }

    public void setCourtLevel(Integer courtLevel) {
        this.courtLevel = courtLevel;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getPreviewImg() {
        return previewImg;
    }

    public void setPreviewImg(String previewImg) {
        this.previewImg = previewImg;
    }

    public String getPreviewSummary() {
        return previewSummary;
    }

    public void setPreviewSummary(String previewSummary) {
        this.previewSummary = previewSummary;
    }

    public String getPreviewDesc() {
        return previewDesc;
    }

    public void setPreviewDesc(String previewDesc) {
        this.previewDesc = previewDesc;
    }

    public Integer getPreviewShow() {
        return previewShow;
    }

    public void setPreviewShow(Integer previewShow) {
        this.previewShow = previewShow;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }

    public String getFlashObj() {
        return flashObj;
    }

    public void setFlashObj(String flashObj) {
        this.flashObj = flashObj;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
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

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
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

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    public Integer getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Integer checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCommentCheckedCount() {
        return commentCheckedCount;
    }

    public void setCommentCheckedCount(Integer commentCheckedCount) {
        this.commentCheckedCount = commentCheckedCount;
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

    public Integer getChatroomKeeplive() {
        return chatroomKeeplive;
    }

    public void setChatroomKeeplive(Integer chatroomKeeplive) {
        this.chatroomKeeplive = chatroomKeeplive;
    }

    public String getChatroomNotice() {
        return chatroomNotice;
    }

    public void setChatroomNotice(String chatroomNotice) {
        this.chatroomNotice = chatroomNotice;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getLiveRecommendWeight() {
        return liveRecommendWeight;
    }

    public void setLiveRecommendWeight(Integer liveRecommendWeight) {
        this.liveRecommendWeight = liveRecommendWeight;
    }

    public Integer getChatroomAllcount() {
        return chatroomAllcount;
    }

    public void setChatroomAllcount(Integer chatroomAllcount) {
        this.chatroomAllcount = chatroomAllcount;
    }

    public Integer getChatroomAppcount() {
        return chatroomAppcount;
    }

    public void setChatroomAppcount(Integer chatroomAppcount) {
        this.chatroomAppcount = chatroomAppcount;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getReviewBox() {
        return reviewBox;
    }

    public void setReviewBox(Integer reviewBox) {
        this.reviewBox = reviewBox;
    }

    public String getFromRemark() {
        return fromRemark;
    }

    public void setFromRemark(String fromRemark) {
        this.fromRemark = fromRemark;
    }

    public Integer getLiveType() {
        return liveType;
    }

    public void setLiveType(Integer liveType) {
        this.liveType = liveType;
    }

    public Integer getVideoView() {
        return videoView;
    }

    public void setVideoView(Integer videoView) {
        this.videoView = videoView;
    }

    public Integer getAppvideoView() {
        return appvideoView;
    }

    public void setAppvideoView(Integer appvideoView) {
        this.appvideoView = appvideoView;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLiveSpecialId() {
        return liveSpecialId;
    }

    public void setLiveSpecialId(Integer liveSpecialId) {
        this.liveSpecialId = liveSpecialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(Integer liveCount) {
        this.liveCount = liveCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
