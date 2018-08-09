package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 内容表（含专题，文章，图集，视频类型）
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-06 16:41:42
 */
@Table(name = "xm_content")
public class XmContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //所属栏目分类，与channel的cate字段相同。创建后不可修改（此字段数据不可用）
    @Column(name = "cate")
    private Integer cate;
	
	    //内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
    @Column(name = "type")
    private Integer type;
	
	    //标题
    @Column(name = "title")
    private String title;
	
	    //文章长标题
    @Column(name = "long_title")
    private String longTitle;
	
	    //导语
    @Column(name = "desc")
    private String desc;
	
	    //缩略图
    @Column(name = "image")
    private String image;
	
	    //专题的封面图
    @Column(name = "img")
    private String img;
	
	    //浏览量
    @Column(name = "views")
    private Integer views;
	
	    //喜欢数
    @Column(name = "loves")
    private Integer loves;
	
	    //收藏数
    @Column(name = "favs")
    private Integer favs;
	
	    //标签
    @Column(name = "tag")
    private String tag;
	
	    //被绑定到的频道数
    @Column(name = "channels")
    private Integer channels;
	
	    //总评论数
    @Column(name = "comments")
    private Integer comments;
	
	    //审核通过的评论数
    @Column(name = "comments_ops")
    private Integer commentsOps;
	
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
    @Column(name = "stat")
    private Integer stat;
	
	    //删除时间
    @Column(name = "rm_time")
    private Date rmTime;
	
	    //删除者ID
    @Column(name = "rm_uid")
    private Integer rmUid;
	
	    //活动id
    @Column(name = "aid")
    private String aid;
	
	    //投票id
    @Column(name = "vid")
    private String vid;
	
	    //自定义按钮名称
    @Column(name = "custom_btn")
    private String customBtn;
	
	    //自定义链接跳转
    @Column(name = "custom_url")
    private String customUrl;
	
	    //积分任务id，xm_score_task
    @Column(name = "stid")
    private Integer stid;
	
	    //显示状态，0审核列不显示，1审核列表显示
    @Column(name = "is_check")
    private Integer isCheck;
	
	    //审核状态（0待审核，1审核通过，2审核不通过）
    @Column(name = "check")
    private Integer check;
	
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
	
	    //是否值班
    @Column(name = "is_duty")
    private Integer isDuty;
	

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
	public void setCate(Integer cate) {
		this.cate = cate;
	}
	/**
	 * 获取：所属栏目分类，与channel的cate字段相同。创建后不可修改（此字段数据不可用）
	 */
	public Integer getCate() {
		return cate;
	}
	/**
	 * 设置：内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
	 */
	public Integer getType() {
		return type;
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
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：导语
	 */
	public String getDesc() {
		return desc;
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
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：专题的封面图
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：浏览量
	 */
	public void setViews(Integer views) {
		this.views = views;
	}
	/**
	 * 获取：浏览量
	 */
	public Integer getViews() {
		return views;
	}
	/**
	 * 设置：喜欢数
	 */
	public void setLoves(Integer loves) {
		this.loves = loves;
	}
	/**
	 * 获取：喜欢数
	 */
	public Integer getLoves() {
		return loves;
	}
	/**
	 * 设置：收藏数
	 */
	public void setFavs(Integer favs) {
		this.favs = favs;
	}
	/**
	 * 获取：收藏数
	 */
	public Integer getFavs() {
		return favs;
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
	public void setComments(Integer comments) {
		this.comments = comments;
	}
	/**
	 * 获取：总评论数
	 */
	public Integer getComments() {
		return comments;
	}
	/**
	 * 设置：审核通过的评论数
	 */
	public void setCommentsOps(Integer commentsOps) {
		this.commentsOps = commentsOps;
	}
	/**
	 * 获取：审核通过的评论数
	 */
	public Integer getCommentsOps() {
		return commentsOps;
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
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：删除时间
	 */
	public void setRmTime(Date rmTime) {
		this.rmTime = rmTime;
	}
	/**
	 * 获取：删除时间
	 */
	public Date getRmTime() {
		return rmTime;
	}
	/**
	 * 设置：删除者ID
	 */
	public void setRmUid(Integer rmUid) {
		this.rmUid = rmUid;
	}
	/**
	 * 获取：删除者ID
	 */
	public Integer getRmUid() {
		return rmUid;
	}
	/**
	 * 设置：活动id
	 */
	public void setAid(String aid) {
		this.aid = aid;
	}
	/**
	 * 获取：活动id
	 */
	public String getAid() {
		return aid;
	}
	/**
	 * 设置：投票id
	 */
	public void setVid(String vid) {
		this.vid = vid;
	}
	/**
	 * 获取：投票id
	 */
	public String getVid() {
		return vid;
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
	public void setStid(Integer stid) {
		this.stid = stid;
	}
	/**
	 * 获取：积分任务id，xm_score_task
	 */
	public Integer getStid() {
		return stid;
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
	public void setCheck(Integer check) {
		this.check = check;
	}
	/**
	 * 获取：审核状态（0待审核，1审核通过，2审核不通过）
	 */
	public Integer getCheck() {
		return check;
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
	/**
	 * 设置：是否值班
	 */
	public void setIsDuty(Integer isDuty) {
		this.isDuty = isDuty;
	}
	/**
	 * 获取：是否值班
	 */
	public Integer getIsDuty() {
		return isDuty;
	}
}
