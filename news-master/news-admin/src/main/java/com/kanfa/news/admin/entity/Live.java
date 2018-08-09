package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 直播表
 * 
 * @author wdw
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-15 20:17:26
 */
@Table(name = "kf_live")
public class Live implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //
    @Column(name = "title")
    private String title;
	
	    //副标题
    @Column(name = "subtitle")
    private String subtitle;
	
	    //直播描述/导语
    @Column(name = "live_content")
    private String liveContent;
	
	    //直播状态 0: 预告 1:直播中 2:回顾
    @Column(name = "live_status")
    private Integer liveStatus;
	
	    //直播url
    @Column(name = "live_url")
    private String liveUrl;
	
	    //直播开始时间
    @Column(name = "start_time")
    private Date startTime;
	
	    //直播时长  1:10:45   0:12:15
    @Column(name = "live_duration")
    private String liveDuration;
	
	    //直播类型id
    @Column(name = "live_type_id")
    private Integer liveTypeId;
	
	    //案件类型 0:未知 1:刑事案件 2:民事案件 3:行政案件
    @Column(name = "case_type")
    private Integer caseType;
	
	    //法院级别 0:未知 1:最高法院 2:高级法院 3:中级法院 4:基层法院
    @Column(name = "court_level")
    private Integer courtLevel;
	
	    //法院id
    @Column(name = "court_id")
    private Integer courtId;
	
	    //法院名称
    @Column(name = "court_name")
    private String courtName;
	
	    //来源URL地址
    @Column(name = "source_url")
    private String sourceUrl;
	
	    //预告视频url
    @Column(name = "preview_url")
    private String previewUrl;
	
	    //预告图片地址
    @Column(name = "preview_img")
    private String previewImg;
	
	    //预告简介
    @Column(name = "preview_summary")
    private String previewSummary;
	
	    //预告描述
    @Column(name = "preview_desc")
    private String previewDesc;
	
	    //上否显示在预告模块
    @Column(name = "preview_show")
    private Integer previewShow;
	
	    //是否置顶
    @Column(name = "is_top")
    private Integer isTop;
	
	    //回顾视频url
    @Column(name = "review_url")
    private String reviewUrl;
	
	    //flash对象地址
    @Column(name = "flash_obj")
    private String flashObj;
	
	    //封面图片地址
    @Column(name = "cover_img")
    private String coverImg;
	
	    //案件信息
    @Column(name = "case_info")
    private String caseInfo;
	
	    //庭审信息
    @Column(name = "court_info")
    private String courtInfo;
	
	    //访问量
    @Column(name = "view_count")
    private Integer viewCount;
	
	    //直播上线下线状态 1:上线 0:下线
    @Column(name = "is_publish")
    private Integer isPublish;
	
	    //是否删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //是否锁定
    @Column(name = "is_lock")
    private Integer isLock;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建日期
    @Column(name = "create_time")
    private Date createTime;
	
	    //最后编辑人
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //最后更新日期
    @Column(name = "update_time")
    private Date updateTime;
	
	    //审核状态（0待审核，1审核通过，2审核不通过
    @Column(name = "check_state")
    private Integer checkState;
	
	    //审核时间
    @Column(name = "check_time")
    private Integer checkTime;
	
	    //评论数
    @Column(name = "comment_count")
    private Integer commentCount;
	
	    //审核后的评论数
    @Column(name = "comment_checked_count")
    private Integer commentCheckedCount;
	
	    //0为先审后发 1位先发后审
    @Column(name = "comment_type")
    private Integer commentType;
	
	    //是否开启聊天室，1开启，0关闭
    @Column(name = "chatroom_status")
    private Integer chatroomStatus;
	
	    //聊天室是否保活，1保活，0不需要保活
    @Column(name = "chatroom_keeplive")
    private Integer chatroomKeeplive;
	
	    //聊天室公告，特殊设置，没有为空
    @Column(name = "chatroom_notice")
    private String chatroomNotice;
	
	    //作者来源id
    @Column(name = "source_id")
    private Integer sourceId;
	
	    //收藏数
    @Column(name = "collect_count")
    private Integer collectCount;
	
	    //推荐权重
    @Column(name = "live_recommend_weight")
    private Integer liveRecommendWeight;
	
	    //参与聊天室人数
    @Column(name = "chatroom_allcount")
    private Integer chatroomAllcount;
	
	    //聊天室系统独立用户数
    @Column(name = "chatroom_appcount")
    private Integer chatroomAppcount;
	
	    //0允许评论  1不允许评论
    @Column(name = "comment_status")
    private Integer commentStatus;
	
	    //是否显示评论框 1显示 0不显示
    @Column(name = "review_box")
    private Integer reviewBox;
	
	    //直播来源信息备注
    @Column(name = "from_remark")
    private String fromRemark;
	
	    //直播类别 0 无 1栏目 2其他
    @Column(name = "live_type")
    private Integer liveType;
	

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：副标题
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * 获取：副标题
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * 设置：直播描述/导语
	 */
	public void setLiveContent(String liveContent) {
		this.liveContent = liveContent;
	}
	/**
	 * 获取：直播描述/导语
	 */
	public String getLiveContent() {
		return liveContent;
	}
	/**
	 * 设置：直播状态 0: 预告 1:直播中 2:回顾
	 */
	public void setLiveStatus(Integer liveStatus) {
		this.liveStatus = liveStatus;
	}
	/**
	 * 获取：直播状态 0: 预告 1:直播中 2:回顾
	 */
	public Integer getLiveStatus() {
		return liveStatus;
	}
	/**
	 * 设置：直播url
	 */
	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}
	/**
	 * 获取：直播url
	 */
	public String getLiveUrl() {
		return liveUrl;
	}
	/**
	 * 设置：直播开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：直播开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：直播时长  1:10:45   0:12:15
	 */
	public void setLiveDuration(String liveDuration) {
		this.liveDuration = liveDuration;
	}
	/**
	 * 获取：直播时长  1:10:45   0:12:15
	 */
	public String getLiveDuration() {
		return liveDuration;
	}
	/**
	 * 设置：直播类型id
	 */
	public void setLiveTypeId(Integer liveTypeId) {
		this.liveTypeId = liveTypeId;
	}
	/**
	 * 获取：直播类型id
	 */
	public Integer getLiveTypeId() {
		return liveTypeId;
	}
	/**
	 * 设置：案件类型 0:未知 1:刑事案件 2:民事案件 3:行政案件
	 */
	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}
	/**
	 * 获取：案件类型 0:未知 1:刑事案件 2:民事案件 3:行政案件
	 */
	public Integer getCaseType() {
		return caseType;
	}
	/**
	 * 设置：法院级别 0:未知 1:最高法院 2:高级法院 3:中级法院 4:基层法院
	 */
	public void setCourtLevel(Integer courtLevel) {
		this.courtLevel = courtLevel;
	}
	/**
	 * 获取：法院级别 0:未知 1:最高法院 2:高级法院 3:中级法院 4:基层法院
	 */
	public Integer getCourtLevel() {
		return courtLevel;
	}
	/**
	 * 设置：法院id
	 */
	public void setCourtId(Integer courtId) {
		this.courtId = courtId;
	}
	/**
	 * 获取：法院id
	 */
	public Integer getCourtId() {
		return courtId;
	}
	/**
	 * 设置：法院名称
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * 获取：法院名称
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * 设置：来源URL地址
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	/**
	 * 获取：来源URL地址
	 */
	public String getSourceUrl() {
		return sourceUrl;
	}
	/**
	 * 设置：预告视频url
	 */
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	/**
	 * 获取：预告视频url
	 */
	public String getPreviewUrl() {
		return previewUrl;
	}
	/**
	 * 设置：预告图片地址
	 */
	public void setPreviewImg(String previewImg) {
		this.previewImg = previewImg;
	}
	/**
	 * 获取：预告图片地址
	 */
	public String getPreviewImg() {
		return previewImg;
	}
	/**
	 * 设置：预告简介
	 */
	public void setPreviewSummary(String previewSummary) {
		this.previewSummary = previewSummary;
	}
	/**
	 * 获取：预告简介
	 */
	public String getPreviewSummary() {
		return previewSummary;
	}
	/**
	 * 设置：预告描述
	 */
	public void setPreviewDesc(String previewDesc) {
		this.previewDesc = previewDesc;
	}
	/**
	 * 获取：预告描述
	 */
	public String getPreviewDesc() {
		return previewDesc;
	}
	/**
	 * 设置：上否显示在预告模块
	 */
	public void setPreviewShow(Integer previewShow) {
		this.previewShow = previewShow;
	}
	/**
	 * 获取：上否显示在预告模块
	 */
	public Integer getPreviewShow() {
		return previewShow;
	}
	/**
	 * 设置：是否置顶
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	/**
	 * 获取：是否置顶
	 */
	public Integer getIsTop() {
		return isTop;
	}
	/**
	 * 设置：回顾视频url
	 */
	public void setReviewUrl(String reviewUrl) {
		this.reviewUrl = reviewUrl;
	}
	/**
	 * 获取：回顾视频url
	 */
	public String getReviewUrl() {
		return reviewUrl;
	}
	/**
	 * 设置：flash对象地址
	 */
	public void setFlashObj(String flashObj) {
		this.flashObj = flashObj;
	}
	/**
	 * 获取：flash对象地址
	 */
	public String getFlashObj() {
		return flashObj;
	}
	/**
	 * 设置：封面图片地址
	 */
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	/**
	 * 获取：封面图片地址
	 */
	public String getCoverImg() {
		return coverImg;
	}
	/**
	 * 设置：案件信息
	 */
	public void setCaseInfo(String caseInfo) {
		this.caseInfo = caseInfo;
	}
	/**
	 * 获取：案件信息
	 */
	public String getCaseInfo() {
		return caseInfo;
	}
	/**
	 * 设置：庭审信息
	 */
	public void setCourtInfo(String courtInfo) {
		this.courtInfo = courtInfo;
	}
	/**
	 * 获取：庭审信息
	 */
	public String getCourtInfo() {
		return courtInfo;
	}
	/**
	 * 设置：访问量
	 */
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	/**
	 * 获取：访问量
	 */
	public Integer getViewCount() {
		return viewCount;
	}
	/**
	 * 设置：直播上线下线状态 1:上线 0:下线
	 */
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	/**
	 * 获取：直播上线下线状态 1:上线 0:下线
	 */
	public Integer getIsPublish() {
		return isPublish;
	}
	/**
	 * 设置：是否删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：是否锁定
	 */
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	/**
	 * 获取：是否锁定
	 */
	public Integer getIsLock() {
		return isLock;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：最后编辑人
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：最后编辑人
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：最后更新日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：审核状态（0待审核，1审核通过，2审核不通过
	 */
	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}
	/**
	 * 获取：审核状态（0待审核，1审核通过，2审核不通过
	 */
	public Integer getCheckState() {
		return checkState;
	}
	/**
	 * 设置：审核时间
	 */
	public void setCheckTime(Integer checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * 获取：审核时间
	 */
	public Integer getCheckTime() {
		return checkTime;
	}
	/**
	 * 设置：评论数
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * 获取：评论数
	 */
	public Integer getCommentCount() {
		return commentCount;
	}
	/**
	 * 设置：审核后的评论数
	 */
	public void setCommentCheckedCount(Integer commentCheckedCount) {
		this.commentCheckedCount = commentCheckedCount;
	}
	/**
	 * 获取：审核后的评论数
	 */
	public Integer getCommentCheckedCount() {
		return commentCheckedCount;
	}
	/**
	 * 设置：0为先审后发 1位先发后审
	 */
	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}
	/**
	 * 获取：0为先审后发 1位先发后审
	 */
	public Integer getCommentType() {
		return commentType;
	}
	/**
	 * 设置：是否开启聊天室，1开启，0关闭
	 */
	public void setChatroomStatus(Integer chatroomStatus) {
		this.chatroomStatus = chatroomStatus;
	}
	/**
	 * 获取：是否开启聊天室，1开启，0关闭
	 */
	public Integer getChatroomStatus() {
		return chatroomStatus;
	}
	/**
	 * 设置：聊天室是否保活，1保活，0不需要保活
	 */
	public void setChatroomKeeplive(Integer chatroomKeeplive) {
		this.chatroomKeeplive = chatroomKeeplive;
	}
	/**
	 * 获取：聊天室是否保活，1保活，0不需要保活
	 */
	public Integer getChatroomKeeplive() {
		return chatroomKeeplive;
	}
	/**
	 * 设置：聊天室公告，特殊设置，没有为空
	 */
	public void setChatroomNotice(String chatroomNotice) {
		this.chatroomNotice = chatroomNotice;
	}
	/**
	 * 获取：聊天室公告，特殊设置，没有为空
	 */
	public String getChatroomNotice() {
		return chatroomNotice;
	}
	/**
	 * 设置：作者来源id
	 */
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	/**
	 * 获取：作者来源id
	 */
	public Integer getSourceId() {
		return sourceId;
	}
	/**
	 * 设置：收藏数
	 */
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	/**
	 * 获取：收藏数
	 */
	public Integer getCollectCount() {
		return collectCount;
	}
	/**
	 * 设置：推荐权重
	 */
	public void setLiveRecommendWeight(Integer liveRecommendWeight) {
		this.liveRecommendWeight = liveRecommendWeight;
	}
	/**
	 * 获取：推荐权重
	 */
	public Integer getLiveRecommendWeight() {
		return liveRecommendWeight;
	}
	/**
	 * 设置：参与聊天室人数
	 */
	public void setChatroomAllcount(Integer chatroomAllcount) {
		this.chatroomAllcount = chatroomAllcount;
	}
	/**
	 * 获取：参与聊天室人数
	 */
	public Integer getChatroomAllcount() {
		return chatroomAllcount;
	}
	/**
	 * 设置：聊天室系统独立用户数
	 */
	public void setChatroomAppcount(Integer chatroomAppcount) {
		this.chatroomAppcount = chatroomAppcount;
	}
	/**
	 * 获取：聊天室系统独立用户数
	 */
	public Integer getChatroomAppcount() {
		return chatroomAppcount;
	}
	/**
	 * 设置：0允许评论  1不允许评论
	 */
	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}
	/**
	 * 获取：0允许评论  1不允许评论
	 */
	public Integer getCommentStatus() {
		return commentStatus;
	}
	/**
	 * 设置：是否显示评论框 1显示 0不显示
	 */
	public void setReviewBox(Integer reviewBox) {
		this.reviewBox = reviewBox;
	}
	/**
	 * 获取：是否显示评论框 1显示 0不显示
	 */
	public Integer getReviewBox() {
		return reviewBox;
	}
	/**
	 * 设置：直播来源信息备注
	 */
	public void setFromRemark(String fromRemark) {
		this.fromRemark = fromRemark;
	}
	/**
	 * 获取：直播来源信息备注
	 */
	public String getFromRemark() {
		return fromRemark;
	}
	/**
	 * 设置：直播类别 0 无 1栏目 2其他
	 */
	public void setLiveType(Integer liveType) {
		this.liveType = liveType;
	}
	/**
	 * 获取：直播类别 0 无 1栏目 2其他
	 */
	public Integer getLiveType() {
		return liveType;
	}
}
