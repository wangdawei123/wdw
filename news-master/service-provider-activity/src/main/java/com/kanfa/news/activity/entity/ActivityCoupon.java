package com.kanfa.news.activity.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 活动--优惠券表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 11:33:20
 */
@Table(name = "kf_activity_coupon")
public class ActivityCoupon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //活动ID
    @Column(name = "activity_id")
    private Integer activityId;
	
	    //商家名称
    @Column(name = "merchant")
    private String merchant;
	
	    //券码名称
    @Column(name = "name")
    private String name;
	
	    //使用规则及描述
    @Column(name = "content")
    private String content;
	
	    //优惠券有效开始时间
    @Column(name = "start_time")
    private Date startTime;
	
	    //优惠券有效结束时间
    @Column(name = "end_time")
    private Date endTime;
	
	    //券码
    @Column(name = "code")
    private String code;
	
	    //领取形式  0:自动发送 1:手动
    @Column(name = "send_type")
    private Integer sendType;
	
	    //领取条件(用户对象)  0:新用户 1
    @Column(name = "accept_object")
    private Integer acceptObject;
	
	    //参与渠道id列表,用逗号隔开
    @Column(name = "extend_channel")
    private String extendChannel;
	
	    //状态 0:已创建 1:已领取 -1:已过期
    @Column(name = "stat")
    private Integer stat;
	
	    //是否已删除
    @Column(name = "deleted")
    private Integer deleted;
	
	    //创建人
    @Column(name = "created_uid")
    private Integer createdUid;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	

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
	 * 设置：商家名称
	 */
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}
	/**
	 * 获取：商家名称
	 */
	public String getMerchant() {
		return merchant;
	}
	/**
	 * 设置：券码名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：券码名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：使用规则及描述
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：使用规则及描述
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：优惠券有效开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：优惠券有效开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：优惠券有效结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：优惠券有效结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：券码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：券码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：领取形式  0:自动发送 1:手动
	 */
	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}
	/**
	 * 获取：领取形式  0:自动发送 1:手动
	 */
	public Integer getSendType() {
		return sendType;
	}
	/**
	 * 设置：领取条件(用户对象)  0:新用户 1
	 */
	public void setAcceptObject(Integer acceptObject) {
		this.acceptObject = acceptObject;
	}
	/**
	 * 获取：领取条件(用户对象)  0:新用户 1
	 */
	public Integer getAcceptObject() {
		return acceptObject;
	}
	/**
	 * 设置：参与渠道id列表,用逗号隔开
	 */
	public void setExtendChannel(String extendChannel) {
		this.extendChannel = extendChannel;
	}
	/**
	 * 获取：参与渠道id列表,用逗号隔开
	 */
	public String getExtendChannel() {
		return extendChannel;
	}
	/**
	 * 设置：状态 0:已创建 1:已领取 -1:已过期
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态 0:已创建 1:已领取 -1:已过期
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
	 * 设置：创建人
	 */
	public void setCreatedUid(Integer createdUid) {
		this.createdUid = createdUid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreatedUid() {
		return createdUid;
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
}
