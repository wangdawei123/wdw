package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 图集的图片表。一对多
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-01 18:51:03
 */
@Table(name = "kf_content_image_group")
public class ContentImageGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //内容ID
    @Column(name = "cid")
    private Integer cid;
	
	    //图片描述
    @Column(name = "desc")
    private String desc;
	
	    //图片URL。同样可以根据其获取缩略图URl
    @Column(name = "image")
    private String image;
	
	    //排序值，越大的值越靠前
    @Column(name = "order")
    private Integer order;
	

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
	 * 设置：图片描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：图片描述
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：图片URL。同样可以根据其获取缩略图URl
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片URL。同样可以根据其获取缩略图URl
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：排序值，越大的值越靠前
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：排序值，越大的值越靠前
	 */
	public Integer getOrder() {
		return order;
	}
}
