package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
@Table(name = "kf_video_tag_bind")
public class VideoTagBind implements Serializable {
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
