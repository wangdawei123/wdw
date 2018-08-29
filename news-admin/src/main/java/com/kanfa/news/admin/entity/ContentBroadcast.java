package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 节目直播表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 13:47:27
 */
@Table(name = "kf_content_broadcast")
public class ContentBroadcast implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //kf_content.id
    @Column(name = "cid")
    private Integer cid;
	
	    //1:预告,2:直播,3:回顾
    @Column(name = "broadcast_status")
    private Integer broadcastStatus;
	
	    //
    @Column(name = "pre_time")
    private Date preTime;
	
	    //视频图
    @Column(name = "video_img")
    private String videoImg;
	
	    //
    @Column(name = "encrypt")
    private Integer encrypt;
	
	    //预告流url
    @Column(name = "preview_url")
    private String previewUrl;
	
	    //直播流url
    @Column(name = "live_url")
    private String liveUrl;
	
	    //点播流url
    @Column(name = "review_url")
    private String reviewUrl;
	
	    //参与数
    @Column(name = "partner_num")
    private Integer partnerNum;
	
	    //刷新时间
    @Column(name = "refresh_count")
    private Integer refreshCount;
	
	    //
    @Column(name = "create_time")
    private Date createTime;
	
	    //创建者
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //
    @Column(name = "update_time")
    private Date updateTime;
	
	    //更新者
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //0:删除,1:未删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //关联直播的gid
    @Column(name = "live_id")
    private String liveId;
	
	    //直播回放时长
    @Column(name = "replay_duration")
    private String replayDuration;
	
	    //开始时间
    @Column(name = "start_time")
    private Integer startTime;
	
	    //直播来源渠道(1:北京时间;2:aliyun)
    @Column(name = "live_source_channel")
    private Integer liveSourceChannel;
	
	    //aliyun直播Appname
    @Column(name = "appname")
    private String appname;
	
	    //aliyun直播流名字
    @Column(name = "streamsname")
    private String streamsname;
	
	    //直播认证手机号
    @Column(name = "broadcast_phone")
    private Long broadcastPhone;
	
	    //直播地址
    @Column(name = "live_address")
    private String liveAddress;
	
	    //标题
    @Column(name = "title")
    private String title;
	

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
	 * 设置：kf_content.id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：kf_content.id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：1:预告,2:直播,3:回顾
	 */
	public void setBroadcastStatus(Integer broadcastStatus) {
		this.broadcastStatus = broadcastStatus;
	}
	/**
	 * 获取：1:预告,2:直播,3:回顾
	 */
	public Integer getBroadcastStatus() {
		return broadcastStatus;
	}
	/**
	 * 设置：
	 */
	public void setPreTime(Date preTime) {
		this.preTime = preTime;
	}
	/**
	 * 获取：
	 */
	public Date getPreTime() {
		return preTime;
	}
	/**
	 * 设置：视频图
	 */
	public void setVideoImg(String videoImg) {
		this.videoImg = videoImg;
	}
	/**
	 * 获取：视频图
	 */
	public String getVideoImg() {
		return videoImg;
	}
	/**
	 * 设置：
	 */
	public void setEncrypt(Integer encrypt) {
		this.encrypt = encrypt;
	}
	/**
	 * 获取：
	 */
	public Integer getEncrypt() {
		return encrypt;
	}
	/**
	 * 设置：预告流url
	 */
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	/**
	 * 获取：预告流url
	 */
	public String getPreviewUrl() {
		return previewUrl;
	}
	/**
	 * 设置：直播流url
	 */
	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}
	/**
	 * 获取：直播流url
	 */
	public String getLiveUrl() {
		return liveUrl;
	}
	/**
	 * 设置：点播流url
	 */
	public void setReviewUrl(String reviewUrl) {
		this.reviewUrl = reviewUrl;
	}
	/**
	 * 获取：点播流url
	 */
	public String getReviewUrl() {
		return reviewUrl;
	}
	/**
	 * 设置：参与数
	 */
	public void setPartnerNum(Integer partnerNum) {
		this.partnerNum = partnerNum;
	}
	/**
	 * 获取：参与数
	 */
	public Integer getPartnerNum() {
		return partnerNum;
	}
	/**
	 * 设置：刷新时间
	 */
	public void setRefreshCount(Integer refreshCount) {
		this.refreshCount = refreshCount;
	}
	/**
	 * 获取：刷新时间
	 */
	public Integer getRefreshCount() {
		return refreshCount;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建者
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：更新者
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：更新者
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：0:删除,1:未删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：0:删除,1:未删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：关联直播的gid
	 */
	public void setLiveId(String liveId) {
		this.liveId = liveId;
	}
	/**
	 * 获取：关联直播的gid
	 */
	public String getLiveId() {
		return liveId;
	}
	/**
	 * 设置：直播回放时长
	 */
	public void setReplayDuration(String replayDuration) {
		this.replayDuration = replayDuration;
	}
	/**
	 * 获取：直播回放时长
	 */
	public String getReplayDuration() {
		return replayDuration;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Integer getStartTime() {
		return startTime;
	}
	/**
	 * 设置：直播来源渠道(1:北京时间;2:aliyun)
	 */
	public void setLiveSourceChannel(Integer liveSourceChannel) {
		this.liveSourceChannel = liveSourceChannel;
	}
	/**
	 * 获取：直播来源渠道(1:北京时间;2:aliyun)
	 */
	public Integer getLiveSourceChannel() {
		return liveSourceChannel;
	}
	/**
	 * 设置：aliyun直播Appname
	 */
	public void setAppname(String appname) {
		this.appname = appname;
	}
	/**
	 * 获取：aliyun直播Appname
	 */
	public String getAppname() {
		return appname;
	}
	/**
	 * 设置：aliyun直播流名字
	 */
	public void setStreamsname(String streamsname) {
		this.streamsname = streamsname;
	}
	/**
	 * 获取：aliyun直播流名字
	 */
	public String getStreamsname() {
		return streamsname;
	}
	/**
	 * 设置：直播认证手机号
	 */
	public void setBroadcastPhone(Long broadcastPhone) {
		this.broadcastPhone = broadcastPhone;
	}
	/**
	 * 获取：直播认证手机号
	 */
	public Long getBroadcastPhone() {
		return broadcastPhone;
	}
	/**
	 * 设置：直播地址
	 */
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	/**
	 * 获取：直播地址
	 */
	public String getLiveAddress() {
		return liveAddress;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
}
