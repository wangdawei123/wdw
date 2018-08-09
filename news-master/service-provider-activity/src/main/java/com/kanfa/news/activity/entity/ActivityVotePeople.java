package com.kanfa.news.activity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 活动--投票--投票人信息表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
@Table(name = "kf_activity_vote_people")
public class ActivityVotePeople implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //被投票人的ID(例如警察)
    @Id
    private Integer id;
	
	    //投票对应的活动的ID
    @Column(name = "activity_vote_id")
    private Integer activityVoteId;
	
	    //候选人名字
    @Column(name = "name")
    private String name;
	
	    //简介
    @Column(name = "summary")
    private String summary;
	
	    //详情
    @Column(name = "content")
    private String content;
	
	    //排序
    @Column(name = "sort")
    private Integer sort;
	
	    //被投票人状态
    @Column(name = "stat")
    private Integer stat;
	
	    //预设票数
    @Column(name = "preset_vote")
    private Integer presetVote;
	
	    //投票总数
    @Column(name = "vote_total")
    private Integer voteTotal;
	
	    //预设评论数
    @Column(name = "preset_comment")
    private Integer presetComment;
	
	    //评论总数
    @Column(name = "comment_total")
    private Integer commentTotal;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //修改时间
    @Column(name = "update_time")
    private Date updateTime;
	

	/**
	 * 设置：被投票人的ID(例如警察)
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：被投票人的ID(例如警察)
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
	 * 设置：简介
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 获取：简介
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * 设置：详情
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：详情
	 */
	public String getContent() {
		return content;
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
	 * 设置：被投票人状态
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：被投票人状态
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：预设票数
	 */
	public void setPresetVote(Integer presetVote) {
		this.presetVote = presetVote;
	}
	/**
	 * 获取：预设票数
	 */
	public Integer getPresetVote() {
		return presetVote;
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
	 * 设置：预设评论数
	 */
	public void setPresetComment(Integer presetComment) {
		this.presetComment = presetComment;
	}
	/**
	 * 获取：预设评论数
	 */
	public Integer getPresetComment() {
		return presetComment;
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
}
