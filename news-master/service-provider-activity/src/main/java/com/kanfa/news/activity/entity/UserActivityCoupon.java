package com.kanfa.news.activity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 用户领取的优惠券表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
@Table(name = "kf_user_activity_coupon")
public class UserActivityCoupon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //用户id
    @Column(name = "user_id")
    private Integer userId;
	
	    //用户名
    @Column(name = "user_name")
    private String userName;
	
	    //用户手机号
    @Column(name = "user_phone")
    private String userPhone;
	
	    //领取的优惠券id
    @Column(name = "activity_coupon_id")
    private Integer activityCouponId;
	
	    //领取的券码code
    @Column(name = "activity_coupon_code")
    private String activityCouponCode;
	
	    //优惠券有效开始时间
    @Column(name = "activity_coupon_start_time")
    private Date activityCouponStartTime;
	
	    //优惠券有效结束时间
    @Column(name = "activity_coupon_end_time")
    private Date activityCouponEndTime;
	
	    //商家
    @Column(name = "activity_merchant")
    private String activityMerchant;
	
	    //券码名称
    @Column(name = "coupon_code_name")
    private String couponCodeName;
	
	    //推广渠道名称
    @Column(name = "extend_channel")
    private String extendChannel;
	
	    //状态 0:已领取 1:已使用 -1:已过期
    @Column(name = "stat")
    private Integer stat;
	
	    //是否已删除
    @Column(name = "deleted")
    private Integer deleted;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //活动id
    @Column(name = "activity_id")
    private Integer activityId;
	

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
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：用户手机号
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	/**
	 * 获取：用户手机号
	 */
	public String getUserPhone() {
		return userPhone;
	}
	/**
	 * 设置：领取的优惠券id
	 */
	public void setActivityCouponId(Integer activityCouponId) {
		this.activityCouponId = activityCouponId;
	}
	/**
	 * 获取：领取的优惠券id
	 */
	public Integer getActivityCouponId() {
		return activityCouponId;
	}
	/**
	 * 设置：领取的券码code
	 */
	public void setActivityCouponCode(String activityCouponCode) {
		this.activityCouponCode = activityCouponCode;
	}
	/**
	 * 获取：领取的券码code
	 */
	public String getActivityCouponCode() {
		return activityCouponCode;
	}
	/**
	 * 设置：优惠券有效开始时间
	 */
	public void setActivityCouponStartTime(Date activityCouponStartTime) {
		this.activityCouponStartTime = activityCouponStartTime;
	}
	/**
	 * 获取：优惠券有效开始时间
	 */
	public Date getActivityCouponStartTime() {
		return activityCouponStartTime;
	}
	/**
	 * 设置：优惠券有效结束时间
	 */
	public void setActivityCouponEndTime(Date activityCouponEndTime) {
		this.activityCouponEndTime = activityCouponEndTime;
	}
	/**
	 * 获取：优惠券有效结束时间
	 */
	public Date getActivityCouponEndTime() {
		return activityCouponEndTime;
	}
	/**
	 * 设置：商家
	 */
	public void setActivityMerchant(String activityMerchant) {
		this.activityMerchant = activityMerchant;
	}
	/**
	 * 获取：商家
	 */
	public String getActivityMerchant() {
		return activityMerchant;
	}
	/**
	 * 设置：券码名称
	 */
	public void setCouponCodeName(String couponCodeName) {
		this.couponCodeName = couponCodeName;
	}
	/**
	 * 获取：券码名称
	 */
	public String getCouponCodeName() {
		return couponCodeName;
	}
	/**
	 * 设置：推广渠道名称
	 */
	public void setExtendChannel(String extendChannel) {
		this.extendChannel = extendChannel;
	}
	/**
	 * 获取：推广渠道名称
	 */
	public String getExtendChannel() {
		return extendChannel;
	}
	/**
	 * 设置：状态 0:已领取 1:已使用 -1:已过期
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态 0:已领取 1:已使用 -1:已过期
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：是否已删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否已删除
	 */
	public Integer getDeleted() {
		return deleted;
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
}
