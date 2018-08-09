package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-16 17:06:48
 */
@Table(name = "kf_activity_sky")
public class ActivitySky implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //
    @Column(name = "activity_id")
    private Integer activityId;
	
	    //
    @Column(name = "need_login")
    private Integer needLogin;
	
	    //
    @Column(name = "status")
    private Integer status;
	
	    //
    @Column(name = "vote_period")
    private Integer votePeriod;
	

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
	 * 设置：
	 */
	public void setNeedLogin(Integer needLogin) {
		this.needLogin = needLogin;
	}
	/**
	 * 获取：
	 */
	public Integer getNeedLogin() {
		return needLogin;
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
	public void setVotePeriod(Integer votePeriod) {
		this.votePeriod = votePeriod;
	}
	/**
	 * 获取：
	 */
	public Integer getVotePeriod() {
		return votePeriod;
	}
}
