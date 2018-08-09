package com.kanfa.news.app.entity;

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
 * @date 2018-04-17 11:23:11
 */
@Table(name = "kf_activity_blue_sky_top")
public class ActivityBlueSkyTop implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //志在蓝天候选人id
    @Column(name = "bluesky_peopleid")
    private Integer blueskyPeopleid;
	
	    //票数
    @Column(name = "vote_total")
    private Integer voteTotal;
	
	    //统计日期
    @Column(name = "bluesky_date")
    private Date blueskyDate;
	
	    //排名
    @Column(name = "ranking")
    private Integer ranking;
	

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
	public void setBlueskyPeopleid(Integer blueskyPeopleid) {
		this.blueskyPeopleid = blueskyPeopleid;
	}
	/**
	 * 获取：志在蓝天候选人id
	 */
	public Integer getBlueskyPeopleid() {
		return blueskyPeopleid;
	}
	/**
	 * 设置：票数
	 */
	public void setVoteTotal(Integer voteTotal) {
		this.voteTotal = voteTotal;
	}
	/**
	 * 获取：票数
	 */
	public Integer getVoteTotal() {
		return voteTotal;
	}
	/**
	 * 设置：统计日期
	 */
	public void setBlueskyDate(Date blueskyDate) {
		this.blueskyDate = blueskyDate;
	}
	/**
	 * 获取：统计日期
	 */
	public Date getBlueskyDate() {
		return blueskyDate;
	}
	/**
	 * 设置：排名
	 */
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	/**
	 * 获取：排名
	 */
	public Integer getRanking() {
		return ranking;
	}
}
