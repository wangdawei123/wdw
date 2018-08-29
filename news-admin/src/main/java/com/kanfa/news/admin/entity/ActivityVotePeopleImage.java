package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 活动--投票--投票人头像信息表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-12 16:01:30
 */
@Table(name = "kf_activity_vote_people_image")
public class ActivityVotePeopleImage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //被投票人的ID(例如警察)
    @Column(name = "activity_vote_people_id")
    private Integer activityVotePeopleId;
	
	    //图片
    @Column(name = "image")
    private String image;
	
	    //
    @Column(name = "status")
    private Integer status;
	
	    //
    @Column(name = "sort")
    private Integer sort;
	

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
	 * 设置：被投票人的ID(例如警察)
	 */
	public void setActivityVotePeopleId(Integer activityVotePeopleId) {
		this.activityVotePeopleId = activityVotePeopleId;
	}
	/**
	 * 获取：被投票人的ID(例如警察)
	 */
	public Integer getActivityVotePeopleId() {
		return activityVotePeopleId;
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
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：
	 */
	public Integer getSort() {
		return sort;
	}
}
