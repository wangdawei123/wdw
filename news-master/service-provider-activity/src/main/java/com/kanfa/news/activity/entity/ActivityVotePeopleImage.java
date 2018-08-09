package com.kanfa.news.activity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 活动--投票--投票人头像信息表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
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
    @Column(name = "stat")
    private Integer stat;
	
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
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：
	 */
	public Integer getStat() {
		return stat;
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
