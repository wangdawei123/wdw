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
 * @date 2018-04-17 11:23:12
 */
@Table(name = "kf_activity_blue_sky_comment")
public class ActivityBlueSkyComment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //志在蓝天候选人id
    @Column(name = "blue_sky_people_id")
    private Integer blueSkyPeopleId;
	
	    //评论内容
    @Column(name = "comment")
    private String comment;
	
	    //评论状态(1:未审核；2:已审核)
    @Column(name = "status")
    private Integer status;
	
	    //是否已删除(0:未删除；1:已删除)
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //用户id
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
	
	    //评论父id
    @Column(name = "pid")
    private Integer pid;
	
	    //点赞数
    @Column(name = "loves")
    private Integer loves;
	

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
	 * 设置：志在蓝天候选人id
	 */
	public void setBlueSkyPeopleId(Integer blueSkyPeopleId) {
		this.blueSkyPeopleId = blueSkyPeopleId;
	}
	/**
	 * 获取：志在蓝天候选人id
	 */
	public Integer getBlueSkyPeopleId() {
		return blueSkyPeopleId;
	}
	/**
	 * 设置：评论内容
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：评论内容
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：评论状态(1:未审核；2:已审核)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：评论状态(1:未审核；2:已审核)
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：是否已删除(0:未删除；1:已删除)
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否已删除(0:未删除；1:已删除)
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
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
	 * 设置：评论父id
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：评论父id
	 */
	public Integer getPid() {
		return pid;
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
}
