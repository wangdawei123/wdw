package com.kanfa.news.admin.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 内容附表-VR
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-29 19:11:08
 */
@Table(name = "kf_content_vr")
public class ContentVr implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //内容ID
    @Id
    private Integer contentId;
	
	    //视频URL
    @Column(name = "url")
    private String url;
	
	    //视频时长
    @Column(name = "duration")
    private String duration;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //视频库id
    @Column(name = "did")
    private Integer did;
	

	/**
	 * 设置：内容ID
	 */
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：内容ID
	 */
	public Integer getContentId() {
		return contentId;
	}
	/**
	 * 设置：视频URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：视频URL
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：视频时长
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * 获取：视频时长
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * 设置：状态，1：正常，0：删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：视频库id
	 */
	public void setDid(Integer did) {
		this.did = did;
	}
	/**
	 * 获取：视频库id
	 */
	public Integer getDid() {
		return did;
	}
}
