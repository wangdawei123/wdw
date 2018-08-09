package com.kanfa.news.search.databatchimport.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 内容表（含专题，文章，图集，视频类型）
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-26 12:58:06
 */
@Table(name = "kf_content")
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;
	    //
    @Id
    private Integer id;
	
	    //所属栏目分类，与channel的cate字段相同。创建后不可修改（此字段数据不可用）
    @Column(name = "category")
    private Integer category;
	
	    //内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
    @Column(name = "content_type")
    private Integer contentType;
	
	    //标题
    @Column(name = "title")
    private String title;
	
	    //文章长标题
    @Column(name = "long_title")
    private String longTitle;
	
	    //导语
    @Column(name = "introduction")
    private String introduction;
	
	    //缩略图
    @Column(name = "image")
    private String image;
	
	    //专题的封面图
    @Column(name = "cover_img")
    private String coverImg;
	
	    //浏览量
    @Column(name = "view_count")
    private Integer viewCount;
	
	    //喜欢数
    @Column(name = "like_count")
    private Integer likeCount;
	
	    //收藏数
    @Column(name = "collect_count")
    private Integer collectCount;
	
	    //标签
    @Column(name = "tag")
    private String tag;
	
	    //被绑定到的频道数
    @Column(name = "channels")
    private Integer channels;
	
	    //总评论数
    @Column(name = "comment_count")
    private Integer commentCount;
	
	    //审核通过的评论数
    @Column(name = "comment_checked_count")
    private Integer commentCheckedCount;
	
	    //最后更新作者
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //创建者
    @Column(name = "create_uid")
    private Long createUid;
	
	    //
    @Column(name = "create_time")
    private Date createTime;
	
	    //
    @Column(name = "update_time")
    private Date updateTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "content_state")
    private Integer contentState;
	
	    //删除时间
    @Column(name = "delete_time")
    private Date deleteTime;
	
	    //删除者ID
    @Column(name = "delete_uid")
    private Integer deleteUid;
	
	    //活动id
    @Column(name = "activity_id")
    private String activityId;
	
	    //投票id
    @Column(name = "activity_vote_id")
    private String activityVoteId;
	
	    //自定义按钮名称
    @Column(name = "custom_btn")
    private String customBtn;
	
	    //自定义链接跳转
    @Column(name = "custom_url")
    private String customUrl;
	
	    //积分任务id，xm_score_task
    @Column(name = "score_task_id")
    private Integer scoreTaskId;
	
	    //显示状态，0审核列不显示，1审核列表显示
    @Column(name = "is_check")
    private Integer isCheck;
	
	    //审核状态（0待审核，1审核通过，2审核不通过）
    @Column(name = "check_status")
    private Integer checkStatus;
	
	    //审核时间
    @Column(name = "check_time")
    private Integer checkTime;
	
	    //mongo中_id
    @Column(name = "mongo_id")
    private String mongoId;
	
	    //记者id,姓名,权重值
    @Column(name = "author")
    private String author;
	
	    //抓取文章类型(0:默认;1:判决书;2:公告)
    @Column(name = "content_style")
    private Integer contentStyle;
	
	    //1 原创 2 转载 3 抓取
    @Column(name = "source_type")
    private Integer sourceType;
	
	    //1 是法制类 0 不是法制类
    @Column(name = "is_legal")
    private Integer isLegal;
	
	    //发布平台  1、IOS 2、安卓  0、全部
    @Column(name = "platform")
    private Integer platform;
	
	    //视频类型
    @Column(name = "video_type")
    private Integer videoType;
	
	    //视频播放量
    @Column(name = "video_view")
    private Integer videoView;
	
	    //app端视频点击量
    @Column(name = "appvideo_view")
    private Integer appvideoView;
	
	    //第一次审核/上线时间
    @Column(name = "first_check_time")
    private Date firstCheckTime;
	
	    //视频频道是否展示关联内容(0:不展示;1:展示)
    @Column(name = "is_relation")
    private Integer isRelation;
	
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
	 * 设置：所属栏目分类，与channel的cate字段相同。创建后不可修改（此字段数据不可用）
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}
	/**
	 * 获取：所属栏目分类，与channel的cate字段相同。创建后不可修改（此字段数据不可用）
	 */
	public Integer getCategory() {
		return category;
	}
	/**
	 * 设置：内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
	 */
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	/**
	 * 获取：内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
	 */
	public Integer getContentType() {
		return contentType;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：文章长标题
	 */
	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}
	/**
	 * 获取：文章长标题
	 */
	public String getLongTitle() {
		return longTitle;
	}
	/**
	 * 设置：导语
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * 获取：导语
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * 设置：缩略图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：缩略图
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：专题的封面图
	 */
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	/**
	 * 获取：专题的封面图
	 */
	public String getCoverImg() {
		return coverImg;
	}
	/**
	 * 设置：浏览量
	 */
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	/**
	 * 获取：浏览量
	 */
	public Integer getViewCount() {
		return viewCount;
	}
	/**
	 * 设置：喜欢数
	 */
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	/**
	 * 获取：喜欢数
	 */
	public Integer getLikeCount() {
		return likeCount;
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
	 * 设置：标签
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/**
	 * 获取：标签
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * 设置：被绑定到的频道数
	 */
	public void setChannels(Integer channels) {
		this.channels = channels;
	}
	/**
	 * 获取：被绑定到的频道数
	 */
	public Integer getChannels() {
		return channels;
	}
	/**
	 * 设置：总评论数
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	/**
	 * 获取：总评论数
	 */
	public Integer getCommentCount() {
		return commentCount;
	}
	/**
	 * 设置：审核通过的评论数
	 */
	public void setCommentCheckedCount(Integer commentCheckedCount) {
		this.commentCheckedCount = commentCheckedCount;
	}
	/**
	 * 获取：审核通过的评论数
	 */
	public Integer getCommentCheckedCount() {
		return commentCheckedCount;
	}
	/**
	 * 设置：最后更新作者
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：最后更新作者
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建者
	 */
	public Long getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：状态，1：正常，0：删除
	 */
	public void setContentState(Integer contentState) {
		this.contentState = contentState;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getContentState() {
		return contentState;
	}
	/**
	 * 设置：删除时间
	 */
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	/**
	 * 获取：删除时间
	 */
	public Date getDeleteTime() {
		return deleteTime;
	}
	/**
	 * 设置：删除者ID
	 */
	public void setDeleteUid(Integer deleteUid) {
		this.deleteUid = deleteUid;
	}
	/**
	 * 获取：删除者ID
	 */
	public Integer getDeleteUid() {
		return deleteUid;
	}
	/**
	 * 设置：活动id
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动id
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * 设置：投票id
	 */
	public void setActivityVoteId(String activityVoteId) {
		this.activityVoteId = activityVoteId;
	}
	/**
	 * 获取：投票id
	 */
	public String getActivityVoteId() {
		return activityVoteId;
	}
	/**
	 * 设置：自定义按钮名称
	 */
	public void setCustomBtn(String customBtn) {
		this.customBtn = customBtn;
	}
	/**
	 * 获取：自定义按钮名称
	 */
	public String getCustomBtn() {
		return customBtn;
	}
	/**
	 * 设置：自定义链接跳转
	 */
	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}
	/**
	 * 获取：自定义链接跳转
	 */
	public String getCustomUrl() {
		return customUrl;
	}
	/**
	 * 设置：积分任务id，xm_score_task
	 */
	public void setScoreTaskId(Integer scoreTaskId) {
		this.scoreTaskId = scoreTaskId;
	}
	/**
	 * 获取：积分任务id，xm_score_task
	 */
	public Integer getScoreTaskId() {
		return scoreTaskId;
	}
	/**
	 * 设置：显示状态，0审核列不显示，1审核列表显示
	 */
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	/**
	 * 获取：显示状态，0审核列不显示，1审核列表显示
	 */
	public Integer getIsCheck() {
		return isCheck;
	}
	/**
	 * 设置：审核状态（0待审核，1审核通过，2审核不通过）
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取：审核状态（0待审核，1审核通过，2审核不通过）
	 */
	public Integer getCheckStatus() {
		return checkStatus;
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
	 * 设置：mongo中_id
	 */
	public void setMongoId(String mongoId) {
		this.mongoId = mongoId;
	}
	/**
	 * 获取：mongo中_id
	 */
	public String getMongoId() {
		return mongoId;
	}
	/**
	 * 设置：记者id,姓名,权重值
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：记者id,姓名,权重值
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：抓取文章类型(0:默认;1:判决书;2:公告)
	 */
	public void setContentStyle(Integer contentStyle) {
		this.contentStyle = contentStyle;
	}
	/**
	 * 获取：抓取文章类型(0:默认;1:判决书;2:公告)
	 */
	public Integer getContentStyle() {
		return contentStyle;
	}
	/**
	 * 设置：1 原创 2 转载 3 抓取
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * 获取：1 原创 2 转载 3 抓取
	 */
	public Integer getSourceType() {
		return sourceType;
	}
	/**
	 * 设置：1 是法制类 0 不是法制类
	 */
	public void setIsLegal(Integer isLegal) {
		this.isLegal = isLegal;
	}
	/**
	 * 获取：1 是法制类 0 不是法制类
	 */
	public Integer getIsLegal() {
		return isLegal;
	}
	/**
	 * 设置：发布平台  1、IOS 2、安卓  0、全部
	 */
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	/**
	 * 获取：发布平台  1、IOS 2、安卓  0、全部
	 */
	public Integer getPlatform() {
		return platform;
	}
	/**
	 * 设置：视频类型
	 */
	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}
	/**
	 * 获取：视频类型
	 */
	public Integer getVideoType() {
		return videoType;
	}
	/**
	 * 设置：视频播放量
	 */
	public void setVideoView(Integer videoView) {
		this.videoView = videoView;
	}
	/**
	 * 获取：视频播放量
	 */
	public Integer getVideoView() {
		return videoView;
	}
	/**
	 * 设置：app端视频点击量
	 */
	public void setAppvideoView(Integer appvideoView) {
		this.appvideoView = appvideoView;
	}
	/**
	 * 获取：app端视频点击量
	 */
	public Integer getAppvideoView() {
		return appvideoView;
	}
	/**
	 * 设置：第一次审核/上线时间
	 */
	public void setFirstCheckTime(Date firstCheckTime) {
		this.firstCheckTime = firstCheckTime;
	}
	/**
	 * 获取：第一次审核/上线时间
	 */
	public Date getFirstCheckTime() {
		return firstCheckTime;
	}
	/**
	 * 设置：视频频道是否展示关联内容(0:不展示;1:展示)
	 */
	public void setIsRelation(Integer isRelation) {
		this.isRelation = isRelation;
	}
	/**
	 * 获取：视频频道是否展示关联内容(0:不展示;1:展示)
	 */
	public Integer getIsRelation() {
		return isRelation;
	}
}
