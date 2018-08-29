package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 广告
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-05-30 18:51:24
 */
@Table(name = "kf_pcadv")
public class Pcadv implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //名称
    @Column(name = "name")
    private String name;
	
	    //上线时间
    @Column(name = "start_time")
    private Date startTime;
	
	    //下线时间
    @Column(name = "end_time")
    private Date endTime;
	
	    //标题
    @Column(name = "title")
    private String title;
	
	    //大缩略图
    @Column(name = "image")
    private String image;
	
	    //url
    @Column(name = "url")
    private String url;
	
	    //描述
    @Column(name = "description")
    private String description;
	
	    //PV
    @Column(name = "views")
    private Integer views;
	
	    //创建者
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //广告排序,值越大越靠前
    @Column(name = "order_number")
    private Integer orderNumber;
	

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：上线时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：上线时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：下线时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：下线时间
	 */
	public Date getEndTime() {
		return endTime;
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
	 * 设置：大缩略图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：大缩略图
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：PV
	 */
	public void setViews(Integer views) {
		this.views = views;
	}
	/**
	 * 获取：PV
	 */
	public Integer getViews() {
		return views;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建者
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
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
	 * 设置：广告排序,值越大越靠前
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：广告排序,值越大越靠前
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
}
