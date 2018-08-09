package com.kanfa.news.info.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-19 15:53:57
 */
@Table(name = "kf_activity_award")
public class ActivityAward implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //活动id
    @Column(name = "activity_id")
    private Integer activityId;
	
	    //
    @Column(name = "pid")
    private Integer pid;
	
	    //奖品名称
    @Column(name = "name")
    private String name;
	
	    //奖项等级
    @Column(name = "level")
    private String level;
	
	    //抽奖频次
    @Column(name = "raffle_time")
    private Integer raffleTime;
	
	    //奖品数量
    @Column(name = "num")
    private Integer num;
	
	    //是否已发放 0 未发放 1 已发放
    @Column(name = "is_use")
    private Integer isUse;
	

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
	 * 设置：
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * 设置：奖品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：奖品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：奖项等级
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：奖项等级
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置：抽奖频次
	 */
	public void setRaffleTime(Integer raffleTime) {
		this.raffleTime = raffleTime;
	}
	/**
	 * 获取：抽奖频次
	 */
	public Integer getRaffleTime() {
		return raffleTime;
	}
	/**
	 * 设置：奖品数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：奖品数量
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：是否已发放 0 未发放 1 已发放
	 */
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	/**
	 * 获取：是否已发放 0 未发放 1 已发放
	 */
	public Integer getIsUse() {
		return isUse;
	}
}
