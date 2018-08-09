package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 内容附表-专题。一对多
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 16:03:25
 */
@Table(name = "kf_content_special")
public class ContentSpecial implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //内容ID
    @Column(name = "content_id")
    private Integer contentId;
	
	    //关联的内容ID
    @Column(name = "target_cid")
    private Integer targetCid;
	
	    //发布状态。1：发布，0：未发布
    @Column(name = "is_publish")
    private Integer isPublish;
	
	    //发布时间
    @Column(name = "publish_time")
    private Date publishTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //排序
    @Column(name = "order_number")
    private Integer orderNumber;
	
	    //专题目录id
    @Column(name = "catalog_id")
    private Integer catalogId;
	

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
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：内容ID
	 */
	public Integer getContentId() {
		return contentId;
	}
	/**
	 * 设置：关联的内容ID
	 */
	public void setTargetCid(Integer targetCid) {
		this.targetCid = targetCid;
	}
	/**
	 * 获取：关联的内容ID
	 */
	public Integer getTargetCid() {
		return targetCid;
	}
	/**
	 * 设置：发布状态。1：发布，0：未发布
	 */
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	/**
	 * 获取：发布状态。1：发布，0：未发布
	 */
	public Integer getIsPublish() {
		return isPublish;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPublishTime() {
		return publishTime;
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
	 * 设置：排序
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
	/**
	 * 设置：专题目录id
	 */
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	/**
	 * 获取：专题目录id
	 */
	public Integer getCatalogId() {
		return catalogId;
	}
}
