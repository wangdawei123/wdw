package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 专题-目录表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 15:36:06
 */
@Table(name = "kf_special_catalog")
public class SpecialCatalog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //专题id
    @Column(name = "cid")
    private Integer cid;
	
	    //目录标题
    @Column(name = "title")
    private String title;
	
	    //标题排序
    @Column(name = "order_number")
    private Integer orderNumber;
	
	    //状态(0:已删除；1:正常)
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //修改时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //更新人
    @Column(name = "update_uid")
    private Integer updateUid;
	

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
	 * 设置：专题id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：专题id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：目录标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：目录标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：标题排序
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：标题排序
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
	/**
	 * 设置：状态(0:已删除；1:正常)
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：状态(0:已删除；1:正常)
	 */
	public Integer getIsDelete() {
		return isDelete;
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
	 * 设置：创建人
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：更新人
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
}
