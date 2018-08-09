package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 内容附表-专题。一对多
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:28:32
 */
@Table(name = "xm_content_special")
public class XmContentSpecial implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //内容ID
    @Column(name = "cid")
    private Integer cid;
	
	    //关联的内容ID
    @Column(name = "target_cid")
    private Integer targetCid;
	
	    //发布状态。1：发布，0：未发布
    @Column(name = "pub")
    private Integer pub;
	
	    //发布时间
    @Column(name = "pub_time")
    private Date pubTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "stat")
    private Integer stat;
	
	    //排序
    @Column(name = "order")
    private Integer order;
	
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
	public void setPub(Integer pub) {
		this.pub = pub;
	}
	/**
	 * 获取：发布状态。1：发布，0：未发布
	 */
	public Integer getPub() {
		return pub;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPubTime() {
		return pubTime;
	}
	/**
	 * 设置：状态，1：正常，0：删除
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：排序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrder() {
		return order;
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
