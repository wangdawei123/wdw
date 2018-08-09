package com.kanfa.news.activity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-20 15:34:40
 */
@Table(name = "kf_active_award_result")
public class ActiveAwardResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //ID
    @Id
    private Integer id;
	
	    //中奖时间
    @Column(name = "prize_time")
    private Integer prizeTime;
	
	    //奖品id
    @Column(name = "prize_id")
    private Integer prizeId;
	
	    //奖品类型
    @Column(name = "prize_type")
    private Integer prizeType;
	
	    //中奖UID
    @Column(name = "uid")
    private Integer uid;
	
	    //活动ID
    @Column(name = "activity_id")
    private Integer activityId;
	
	    //奖品名称
    @Column(name = "prize_name")
    private String prizeName;
	

	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：中奖时间
	 */
	public void setPrizeTime(Integer prizeTime) {
		this.prizeTime = prizeTime;
	}
	/**
	 * 获取：中奖时间
	 */
	public Integer getPrizeTime() {
		return prizeTime;
	}
	/**
	 * 设置：奖品id
	 */
	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}
	/**
	 * 获取：奖品id
	 */
	public Integer getPrizeId() {
		return prizeId;
	}
	/**
	 * 设置：奖品类型
	 */
	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}
	/**
	 * 获取：奖品类型
	 */
	public Integer getPrizeType() {
		return prizeType;
	}
	/**
	 * 设置：中奖UID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：中奖UID
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：活动ID
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动ID
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：奖品名称
	 */
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	/**
	 * 获取：奖品名称
	 */
	public String getPrizeName() {
		return prizeName;
	}
}
