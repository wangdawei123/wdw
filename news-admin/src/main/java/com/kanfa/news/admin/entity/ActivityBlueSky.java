package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:46:27
 */
@Table(name = "kf_activity_blue_sky")
public class ActivityBlueSky implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //被投票人的ID
    @Id
    private Integer id;
	
	    //投票id
    @Column(name = "activity_vote_id")
    private Integer activityVoteId;
	
	    //活动id
    @Column(name = "activity_id")
    private Integer activityId;
	
	    //候选人名字
    @Column(name = "name")
    private String name;
	
	    //候选人年龄
    @Column(name = "age")
    private Integer age;
	
	    //
    @Column(name = "area")
    private String area;
	
	    //身高
    @Column(name = "height")
    private String height;
	
	    //体重
    @Column(name = "weight")
    private String weight;
	
	    //排序
    @Column(name = "sort")
    private Integer sort;
	
	    //被投票人状态   1、正常  0 、删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //投票总数
    @Column(name = "vote_total")
    private Integer voteTotal;
	
	    //评论总数
    @Column(name = "comment_total")
    private Integer commentTotal;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //修改时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //图片
    @Column(name = "headimage")
    private String headimage;
	
	    //选手类别  1、专业选手  2、普通选手
    @Column(name = "type")
    private Integer type;
	
	    //血型
    @Column(name = "blood")
    private String blood;
	
	    //身份
    @Column(name = "position")
    private String position;
	
	    //宣言
    @Column(name = "description")
    private String description;
	
	    //视频
    @Column(name = "video")
    private String video;
	
	    //点赞数
    @Column(name = "loves")
    private Integer loves;
	
	    //视频地址
    @Column(name = "url")
    private String url;
	
	    //视频封面图
    @Column(name = "videoimage")
    private String videoimage;
	
	    //视频时长
    @Column(name = "duration")
    private String duration;
	
	    //简介
    @Column(name = "introduction")
    private String introduction;
	

	/**
	 * 设置：被投票人的ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：被投票人的ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：投票id
	 */
	public void setActivityVoteId(Integer activityVoteId) {
		this.activityVoteId = activityVoteId;
	}
	/**
	 * 获取：投票id
	 */
	public Integer getActivityVoteId() {
		return activityVoteId;
	}
	/**
	 * 设置：活动id
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动id
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：候选人名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：候选人名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：候选人年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：候选人年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置：身高
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * 获取：身高
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * 设置：体重
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * 获取：体重
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：被投票人状态   1、正常  0 、删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：被投票人状态   1、正常  0 、删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：投票总数
	 */
	public void setVoteTotal(Integer voteTotal) {
		this.voteTotal = voteTotal;
	}
	/**
	 * 获取：投票总数
	 */
	public Integer getVoteTotal() {
		return voteTotal;
	}
	/**
	 * 设置：评论总数
	 */
	public void setCommentTotal(Integer commentTotal) {
		this.commentTotal = commentTotal;
	}
	/**
	 * 获取：评论总数
	 */
	public Integer getCommentTotal() {
		return commentTotal;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：图片
	 */
	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}
	/**
	 * 获取：图片
	 */
	public String getHeadimage() {
		return headimage;
	}
	/**
	 * 设置：选手类别  1、专业选手  2、普通选手
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：选手类别  1、专业选手  2、普通选手
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：血型
	 */
	public void setBlood(String blood) {
		this.blood = blood;
	}
	/**
	 * 获取：血型
	 */
	public String getBlood() {
		return blood;
	}
	/**
	 * 设置：身份
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：身份
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置：宣言
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：宣言
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：视频
	 */
	public void setVideo(String video) {
		this.video = video;
	}
	/**
	 * 获取：视频
	 */
	public String getVideo() {
		return video;
	}
	/**
	 * 设置：点赞数
	 */
	public void setLoves(Integer loves) {
		this.loves = loves;
	}
	/**
	 * 获取：点赞数
	 */
	public Integer getLoves() {
		return loves;
	}
	/**
	 * 设置：视频地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：视频地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：视频封面图
	 */
	public void setVideoimage(String videoimage) {
		this.videoimage = videoimage;
	}
	/**
	 * 获取：视频封面图
	 */
	public String getVideoimage() {
		return videoimage;
	}
	/**
	 * 设置：视频时长
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * 获取：视频时长
	 */
	public String getDuration() {
		return duration;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
