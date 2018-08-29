package com.kanfa.news.admin.vo.burst;

import java.io.Serializable;


/**
 * 新闻爆料图片&视频表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
public class BurstResourceInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;

	    //类型(1:图片;2:视频;3:文件)
    private Integer type;
	
	    //视频/文件 title
    private String title;
	
	    //上传地址
    private String url;

	//上传文件的md5值
	private String videomd;
	
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
	 * 设置：类型(1:图片;2:视频;3:文件)
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型(1:图片;2:视频;3:文件)
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：视频/文件 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：视频/文件 title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：上传地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：上传地址
	 */
	public String getUrl() {
		return url;
	}

	public String getVideomd() {
		return videomd;
	}

	public void setVideomd(String videomd) {
		this.videomd = videomd;
	}
}
