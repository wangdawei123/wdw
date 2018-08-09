package com.kanfa.news.activity.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 内容附表-文章
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 17:06:36
 */
@Table(name = "kf_content_article")
public class ContentArticle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //内容ID
    @Id
    private Integer cid;
	
	    //来源
    @Column(name = "source")
    private String source;
	
	    //文章内容
    @Column(name = "content")
    private String content;
	
	    //视频
    @Column(name = "video")
    private String video;
	
	    //视频底图
    @Column(name = "video_img")
    private String videoImg;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //视频库id
    @Column(name = "did")
    private Integer did;
	
	    //文章作者
    @Column(name = "author")
    private String author;
	

	/**
	 * 设置：内容ID
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：内容ID
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：文章内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：文章内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：视频
	 */
	public void setVideo(String video) {
		this.video = video;
	}
	/**
	 * 获取：视频
	 */
	public String getVideo() {
		return video;
	}
	/**
	 * 设置：视频底图
	 */
	public void setVideoImg(String videoImg) {
		this.videoImg = videoImg;
	}
	/**
	 * 获取：视频底图
	 */
	public String getVideoImg() {
		return videoImg;
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
	/**
	 * 设置：文章作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：文章作者
	 */
	public String getAuthor() {
		return author;
	}
}
