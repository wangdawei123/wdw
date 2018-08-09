package com.kanfa.news.admin.vo.activity;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/23 15:31
 */
public class ActivityCouponShow {
    private Integer id;
    //商家名称 merchant_name
    private String merchantName;
    //券码名称 name
    private String name;
    //开始时间 start_time
    private Date startTime;
    //结束时间 end_time
    private Date endTime;
    //状态 0:已创建 1:已领取 -1:已过期  coupon_status
    private Integer couponStatus;
    //卷码 code
    private String code;
    //领取形式 0:自动发送 1:手动 send_type
    private Integer sendType;
    //领取条件 0:新用户 1 老用户 accept_object
    private Integer acceptObject;
    //参与渠道 参与渠道id列表,用逗号隔开 extend_channel
    private String extendChannel;
    //使用规则 content
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public Integer getAcceptObject() {
        return acceptObject;
    }

    public void setAcceptObject(Integer acceptObject) {
        this.acceptObject = acceptObject;
    }

    public String getExtendChannel() {
        return extendChannel;
    }

    public void setExtendChannel(String extendChannel) {
        this.extendChannel = extendChannel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
