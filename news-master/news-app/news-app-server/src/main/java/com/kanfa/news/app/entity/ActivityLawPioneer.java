package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-26 17:22:05
 */
@Table(name = "kf_activity_law_pioneer")
public class ActivityLawPioneer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //政法活动id
    @Column(name = "activity_law_id")
    private Integer activityLawId;
	
	    //关联的文章id
    @Column(name = "cid")
    private Integer cid;
	
	    //标题
    @Column(name = "title")
    private String title;
	
	    //图标
    @Column(name = "icon")
    private String icon;
	
	    //背景图
    @Column(name = "background_image")
    private String backgroundImage;
	
	    //简介
    @Column(name = "description")
    private String description;
	
	    //创建日期
    @Column(name = "create_time")
    private Date createTime;

	    //创建人
    @Column(name = "create_uid")
    private String createUid;
	
	    //候选人状态   1 正常  0  删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //排序值
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
	 * 设置：政法活动id
	 */
	public void setActivityLawId(Integer activityLawId) {
		this.activityLawId = activityLawId;
	}
	/**
	 * 获取：政法活动id
	 */
	public Integer getActivityLawId() {
		return activityLawId;
	}
	/**
	 * 设置：关联的文章id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：关联的文章id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：图标
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：背景图
	 */
	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	/**
	 * 获取：背景图
	 */
	public String getBackgroundImage() {
		return backgroundImage;
	}
	/**
	 * 设置：简介
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：简介
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	/**
	 * 设置：候选人状态   1 正常  0  删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：候选人状态   1 正常  0  删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：排序值
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序值
	 */
	public Integer getSort() {
		return sort;
	}
}
