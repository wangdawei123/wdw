package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 用户自定义的频道表
 * 
 * @author wdw
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-14 12:01:06
 */
@Table(name = "kf_channel_custom")
public class ChannelCustom implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer uid;
	
	    //设备ID
    @Column(name = "dev_id")
    private String devId;
	
	    //频道ID，以半角逗号分隔，顺序即是用户定义的顺序
    @Column(name = "chan_ids")
    private String chanIds;
	
	    //隐藏频道ID，以半角逗号分隔，顺序即是用户定义的顺序
    @Column(name = "hide_chan_ids")
    private String hideChanIds;
	

	/**
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：设备ID
	 */
	public void setDevId(String devId) {
		this.devId = devId;
	}
	/**
	 * 获取：设备ID
	 */
	public String getDevId() {
		return devId;
	}
	/**
	 * 设置：频道ID，以半角逗号分隔，顺序即是用户定义的顺序
	 */
	public void setChanIds(String chanIds) {
		this.chanIds = chanIds;
	}
	/**
	 * 获取：频道ID，以半角逗号分隔，顺序即是用户定义的顺序
	 */
	public String getChanIds() {
		return chanIds;
	}
	/**
	 * 设置：隐藏频道ID，以半角逗号分隔，顺序即是用户定义的顺序
	 */
	public void setHideChanIds(String hideChanIds) {
		this.hideChanIds = hideChanIds;
	}
	/**
	 * 获取：隐藏频道ID，以半角逗号分隔，顺序即是用户定义的顺序
	 */
	public String getHideChanIds() {
		return hideChanIds;
	}
}
