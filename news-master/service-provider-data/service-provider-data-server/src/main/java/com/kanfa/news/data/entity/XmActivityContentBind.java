package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 19:46:01
 */
@Table(name = "xm_activity_content_bind")
public class XmActivityContentBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //关联的文章id
    @Column(name = "cid")
    private Integer cid;
	
	    //内容类型
    @Column(name = "type")
    private Integer type;
	
	    //活动id
    @Column(name = "activity_id")
    private Integer activityId;
	
	    //置顶
    @Column(name = "top")
    private Integer top;
	
	    //排序值
    @Column(name = "order")
    private Integer order;
	
	    //1删除0正常
    @Column(name = "deleted")
    private Integer deleted;
	

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
	 * 设置：内容类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：内容类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：活动id
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动id
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：置顶
	 */
	public void setTop(Integer top) {
		this.top = top;
	}
	/**
	 * 获取：置顶
	 */
	public Integer getTop() {
		return top;
	}
	/**
	 * 设置：排序值
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：排序值
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * 设置：1删除0正常
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：1删除0正常
	 */
	public Integer getDeleted() {
		return deleted;
	}
}
