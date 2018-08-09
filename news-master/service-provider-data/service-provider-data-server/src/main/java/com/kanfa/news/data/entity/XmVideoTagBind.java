package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 标签与视频关联表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:44:16
 */
@Table(name = "xm_video_tag_bind")
public class XmVideoTagBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //标签id
    @Column(name = "video_tag_id")
    private Integer videoTagId;
	
	    //视频id
    @Column(name = "video_id")
    private Integer videoId;
	

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
	 * 设置：标签id
	 */
	public void setVideoTagId(Integer videoTagId) {
		this.videoTagId = videoTagId;
	}
	/**
	 * 获取：标签id
	 */
	public Integer getVideoTagId() {
		return videoTagId;
	}
	/**
	 * 设置：视频id
	 */
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	/**
	 * 获取：视频id
	 */
	public Integer getVideoId() {
		return videoId;
	}
}
