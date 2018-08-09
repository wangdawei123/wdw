package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 活动--投票表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-10 17:01:20
 */
@Table(name = "kf_activity_vote")
public class ActivityVote implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //
    @Column(name = "activity_id")
    private Integer activityId;
	
	    //背景颜色
    @Column(name = "bgcolor")
    private String bgcolor;
	
	    //是否允许登录
    @Column(name = "need_login")
    private Integer needLogin;
	
	    //是否允许多选
    @Column(name = "vote_multi")
    private Integer voteMulti;
	
	    //多选,最多选择几张
    @Column(name = "vote_multi_num")
    private Integer voteMultiNum;
	
	    //状态
    @Column(name = "stat")
    private Integer stat;
	
	    //是否只允许投一次
    @Column(name = "vote_once")
    private Integer voteOnce;
	
	    //再次投票时间间隔
    @Column(name = "vote_period")
    private Integer votePeriod;
	
	    //是否显示数量
    @Column(name = "show_num")
    private Integer showNum;
	
	    //
    @Column(name = "vote_total")
    private Integer voteTotal;
	
	    //
    @Column(name = "comment_total")
    private Integer commentTotal;
	
	    //是否凌晨清空投票记数
    @Column(name = "flush_vote_num")
    private Integer flushVoteNum;
	

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
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：背景颜色
	 */
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	/**
	 * 获取：背景颜色
	 */
	public String getBgcolor() {
		return bgcolor;
	}
	/**
	 * 设置：是否允许登录
	 */
	public void setNeedLogin(Integer needLogin) {
		this.needLogin = needLogin;
	}
	/**
	 * 获取：是否允许登录
	 */
	public Integer getNeedLogin() {
		return needLogin;
	}
	/**
	 * 设置：是否允许多选
	 */
	public void setVoteMulti(Integer voteMulti) {
		this.voteMulti = voteMulti;
	}
	/**
	 * 获取：是否允许多选
	 */
	public Integer getVoteMulti() {
		return voteMulti;
	}
	/**
	 * 设置：多选,最多选择几张
	 */
	public void setVoteMultiNum(Integer voteMultiNum) {
		this.voteMultiNum = voteMultiNum;
	}
	/**
	 * 获取：多选,最多选择几张
	 */
	public Integer getVoteMultiNum() {
		return voteMultiNum;
	}
	/**
	 * 设置：状态
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：是否只允许投一次
	 */
	public void setVoteOnce(Integer voteOnce) {
		this.voteOnce = voteOnce;
	}
	/**
	 * 获取：是否只允许投一次
	 */
	public Integer getVoteOnce() {
		return voteOnce;
	}
	/**
	 * 设置：再次投票时间间隔
	 */
	public void setVotePeriod(Integer votePeriod) {
		this.votePeriod = votePeriod;
	}
	/**
	 * 获取：再次投票时间间隔
	 */
	public Integer getVotePeriod() {
		return votePeriod;
	}
	/**
	 * 设置：是否显示数量
	 */
	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}
	/**
	 * 获取：是否显示数量
	 */
	public Integer getShowNum() {
		return showNum;
	}
	/**
	 * 设置：
	 */
	public void setVoteTotal(Integer voteTotal) {
		this.voteTotal = voteTotal;
	}
	/**
	 * 获取：
	 */
	public Integer getVoteTotal() {
		return voteTotal;
	}
	/**
	 * 设置：
	 */
	public void setCommentTotal(Integer commentTotal) {
		this.commentTotal = commentTotal;
	}
	/**
	 * 获取：
	 */
	public Integer getCommentTotal() {
		return commentTotal;
	}
	/**
	 * 设置：是否凌晨清空投票记数
	 */
	public void setFlushVoteNum(Integer flushVoteNum) {
		this.flushVoteNum = flushVoteNum;
	}
	/**
	 * 获取：是否凌晨清空投票记数
	 */
	public Integer getFlushVoteNum() {
		return flushVoteNum;
	}
}
