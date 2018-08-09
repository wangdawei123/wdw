package com.kanfa.news.activity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 活动--投票--内容表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
@Table(name = "kf_activity_vote_content")
public class ActivityVoteContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //投票对应的活动的ID
    @Column(name = "activity_vote_id")
    private Integer activityVoteId;
	
	    //投票活动详情
    @Column(name = "content")
    private String content;
	

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
	 * 设置：投票对应的活动的ID
	 */
	public void setActivityVoteId(Integer activityVoteId) {
		this.activityVoteId = activityVoteId;
	}
	/**
	 * 获取：投票对应的活动的ID
	 */
	public Integer getActivityVoteId() {
		return activityVoteId;
	}
	/**
	 * 设置：投票活动详情
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：投票活动详情
	 */
	public String getContent() {
		return content;
	}
}
