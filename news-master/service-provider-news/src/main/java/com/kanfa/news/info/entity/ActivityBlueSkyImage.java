package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 11:23:12
 */
@Table(name = "kf_activity_blue_sky_image")
public class ActivityBlueSkyImage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //
    @Column(name = "blue_sky_people_id")
    private Integer blueSkyPeopleId;
	
	    //图片
    @Column(name = "image")
    private String image;
	
	    //图片状态：1、正常  0、失效
    @Column(name = "status")
    private Integer status;
	
	    //图片排序
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
	 * 设置：
	 */
	public void setBlueSkyPeopleId(Integer blueSkyPeopleId) {
		this.blueSkyPeopleId = blueSkyPeopleId;
	}
	/**
	 * 获取：
	 */
	public Integer getBlueSkyPeopleId() {
		return blueSkyPeopleId;
	}
	/**
	 * 设置：图片
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：图片状态：1、正常  0、失效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：图片状态：1、正常  0、失效
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：图片排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：图片排序
	 */
	public Integer getSort() {
		return sort;
	}
}
