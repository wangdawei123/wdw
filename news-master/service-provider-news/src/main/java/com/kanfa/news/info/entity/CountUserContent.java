package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 人员发布内容表
 * 
 * @author Jiayunwei
 * @email jiayunwei@kanfanews.com
 * @date 2018-07-30 17:25:16
 */
@Table(name = "kf_count_user_content")
public class CountUserContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //统计日期
    @Column(name = "count_date")
    private Integer countDate;
	
	    //小编id
    @Column(name = "uid")
    private Integer uid;
	
	    //小编名字
    @Column(name = "edit_name")
    private String editName;
	
	    //内容id
    @Column(name = "cid")
    private Integer cid;
	
	    //纯文
    @Column(name = "pure_text")
    private Integer pureText;
	
	    //图文
    @Column(name = "article")
    private Integer article;
	
	    //图集
    @Column(name = "images")
    private Integer images;
	
	    //视频
    @Column(name = "video")
    private Integer video;
	
	    //直播
    @Column(name = "live")
    private Integer live;
	
	    //是否原创
    @Column(name = "is_original")
    private Integer isOriginal;
	
	    //是否值班
    @Column(name = "is_work")
    private Integer isWork;
	

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
	 * 设置：统计日期
	 */
	public void setCountDate(Integer countDate) {
		this.countDate = countDate;
	}
	/**
	 * 获取：统计日期
	 */
	public Integer getCountDate() {
		return countDate;
	}
	/**
	 * 设置：小编id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：小编id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：小编名字
	 */
	public void setEditName(String editName) {
		this.editName = editName;
	}
	/**
	 * 获取：小编名字
	 */
	public String getEditName() {
		return editName;
	}
	/**
	 * 设置：内容id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：内容id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：纯文
	 */
	public void setPureText(Integer pureText) {
		this.pureText = pureText;
	}
	/**
	 * 获取：纯文
	 */
	public Integer getPureText() {
		return pureText;
	}
	/**
	 * 设置：图文
	 */
	public void setArticle(Integer article) {
		this.article = article;
	}
	/**
	 * 获取：图文
	 */
	public Integer getArticle() {
		return article;
	}
	/**
	 * 设置：图集
	 */
	public void setImages(Integer images) {
		this.images = images;
	}
	/**
	 * 获取：图集
	 */
	public Integer getImages() {
		return images;
	}
	/**
	 * 设置：视频
	 */
	public void setVideo(Integer video) {
		this.video = video;
	}
	/**
	 * 获取：视频
	 */
	public Integer getVideo() {
		return video;
	}
	/**
	 * 设置：直播
	 */
	public void setLive(Integer live) {
		this.live = live;
	}
	/**
	 * 获取：直播
	 */
	public Integer getLive() {
		return live;
	}
	/**
	 * 设置：是否原创
	 */
	public void setIsOriginal(Integer isOriginal) {
		this.isOriginal = isOriginal;
	}
	/**
	 * 获取：是否原创
	 */
	public Integer getIsOriginal() {
		return isOriginal;
	}
	/**
	 * 设置：是否值班
	 */
	public void setIsWork(Integer isWork) {
		this.isWork = isWork;
	}
	/**
	 * 获取：是否值班
	 */
	public Integer getIsWork() {
		return isWork;
	}
}
