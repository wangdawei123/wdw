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
@Table(name = "kf_video_album_bind")
public class VideoAlbumBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //专辑id
    @Column(name = "video_album_id")
    private Integer videoAlbumId;
	
	    //视频id
    @Column(name = "video_id")
    private Integer videoId;
	
	    //排序值,降序排序
    @Column(name = "sort")
    private Integer sort;
	

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
	 * 设置：专辑id
	 */
	public void setVideoAlbumId(Integer videoAlbumId) {
		this.videoAlbumId = videoAlbumId;
	}
	/**
	 * 获取：专辑id
	 */
	public Integer getVideoAlbumId() {
		return videoAlbumId;
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
	/**
	 * 设置：排序值,降序排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序值,降序排序
	 */
	public Integer getSort() {
		return sort;
	}
}
