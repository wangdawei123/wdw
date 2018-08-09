package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 广告配置表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-04-11 10:43:33
 */
@Table(name = "kf_advertisement_config")
public class AdvertisementConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //ios,android
    @Column(name = "platform")
    private String platform;
	
	    //
    @Column(name = "channel_id")
    private Integer channelId;
	
	    //
    @Column(name = "position")
    private Integer position;
	
	    //
    @Column(name = "pid")
    private Integer pid;
	
	    //
    @Column(name = "ad_type")
    private Integer adType;
	

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
	 * 设置：ios,android
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	/**
	 * 获取：ios,android
	 */
	public String getPlatform() {
		return platform;
	}
	/**
	 * 设置：
	 */
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取：
	 */
	public Integer getChannelId() {
		return channelId;
	}
	/**
	 * 设置：
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}
	/**
	 * 获取：
	 */
	public Integer getPosition() {
		return position;
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
	 * 设置：
	 */
	public void setAdType(Integer adType) {
		this.adType = adType;
	}
	/**
	 * 获取：
	 */
	public Integer getAdType() {
		return adType;
	}
}
