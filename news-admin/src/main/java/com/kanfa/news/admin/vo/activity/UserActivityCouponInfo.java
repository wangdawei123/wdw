package com.kanfa.news.admin.vo.activity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/23 17:13
 */
public class UserActivityCouponInfo implements Serializable {
    private Integer id;
    //商家名称 activity_merchant
    private String activityMerchant;
    //券码名称 coupon_code_name
    private String couponCodeName;
    //券码号 activity_coupon_code
    private String activityCouponCode;
    //有效开始时间 activity_coupon_start_time
    private Date activityCouponStartTime;
    //有效结束时间 activity_coupon_end_time
    private Date activityCouponEndTime;
    //发出时间 create_time
    private Date createTime;
    //领取人 user_name
    private String userName;

    //coupon_status 状态 0:已领取 1:已使用 -1:已过期 0
    private Integer couponStatus;
    //is_delete 0 未删除 0
    private Integer isDelete;
    //activity_id
    private Integer activityId;
    //page
    private Integer page;
    //limit
    private Integer limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityMerchant() {
        return activityMerchant;
    }

    public void setActivityMerchant(String activityMerchant) {
        this.activityMerchant = activityMerchant;
    }

    public String getCouponCodeName() {
        return couponCodeName;
    }

    public void setCouponCodeName(String couponCodeName) {
        this.couponCodeName = couponCodeName;
    }

    public String getActivityCouponCode() {
        return activityCouponCode;
    }

    public void setActivityCouponCode(String activityCouponCode) {
        this.activityCouponCode = activityCouponCode;
    }

    public Date getActivityCouponStartTime() {
        return activityCouponStartTime;
    }

    public void setActivityCouponStartTime(Date activityCouponStartTime) {
        this.activityCouponStartTime = activityCouponStartTime;
    }

    public Date getActivityCouponEndTime() {
        return activityCouponEndTime;
    }

    public void setActivityCouponEndTime(Date activityCouponEndTime) {
        this.activityCouponEndTime = activityCouponEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
