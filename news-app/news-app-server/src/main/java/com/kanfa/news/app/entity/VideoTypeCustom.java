package com.kanfa.news.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 用户自定义的视频分类表
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-16 16:18:54
 */
@Table(name = "kf_video_type_custom")
public class VideoTypeCustom implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer uid;
	
	    //设备ID
    @Column(name = "dev_id")
    private String devId;
	
	    //视频分类ID，以半角逗号分隔，顺序即是用户定义的顺序
    @Column(name = "type_ids")
    private String typeIds;
	
	    //隐藏视频分类ID，以半角逗号分隔，顺序即是用户定义的顺序
    @Column(name = "hide_type_ids")
    private String hideTypeIds;
	
	    //
    @Column(name = "create_time")
    private Date createTime;
	
	    //
    @Column(name = "update_time")
    private Date updateTime;
	

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
	 * 设置：视频分类ID，以半角逗号分隔，顺序即是用户定义的顺序
	 */
	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}
	/**
	 * 获取：视频分类ID，以半角逗号分隔，顺序即是用户定义的顺序
	 */
	public String getTypeIds() {
		return typeIds;
	}
	/**
	 * 设置：隐藏视频分类ID，以半角逗号分隔，顺序即是用户定义的顺序
	 */
	public void setHideTypeIds(String hideTypeIds) {
		this.hideTypeIds = hideTypeIds;
	}
	/**
	 * 获取：隐藏视频分类ID，以半角逗号分隔，顺序即是用户定义的顺序
	 */
	public String getHideTypeIds() {
		return hideTypeIds;
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
}
