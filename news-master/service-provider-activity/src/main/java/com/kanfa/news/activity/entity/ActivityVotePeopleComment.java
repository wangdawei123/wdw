package com.kanfa.news.activity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 活动--投票--投票人评论信息表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
@Table(name = "kf_activity_vote_people_comment")
public class ActivityVotePeopleComment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //候选人id
    @Column(name = "activity_vote_people_id")
    private Integer activityVotePeopleId;
	
	    //评论
    @Column(name = "comment")
    private String comment;
	
	    //评论状态(1:未审核；2:已审核)
    @Column(name = "stat")
    private Integer stat;
	
	    //是否已删除(0:未删除；1:已删除)
    @Column(name = "deleted")
    private Integer deleted;
	
	    //注册用户id
    @Column(name = "user_id")
    private Integer userId;
	
	    //用户昵称
    @Column(name = "nick")
    private String nick;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //修改时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //图片
    @Column(name = "image")
    private String image;
	
	    //省份
    @Column(name = "province")
    private String province;
	
	    //身份
    @Column(name = "position")
    private String position;
	

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
	 * 设置：候选人id
	 */
	public void setActivityVotePeopleId(Integer activityVotePeopleId) {
		this.activityVotePeopleId = activityVotePeopleId;
	}
	/**
	 * 获取：候选人id
	 */
	public Integer getActivityVotePeopleId() {
		return activityVotePeopleId;
	}
	/**
	 * 设置：评论
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：评论
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：评论状态(1:未审核；2:已审核)
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：评论状态(1:未审核；2:已审核)
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：是否已删除(0:未删除；1:已删除)
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否已删除(0:未删除；1:已删除)
	 */
	public Integer getDeleted() {
		return deleted;
	}
	/**
	 * 设置：注册用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：注册用户id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：用户昵称
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getNick() {
		return nick;
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
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省份
	 */
	public String getProvince() {
		return province;
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
}
