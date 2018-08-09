package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-18 14:17:51
 */
@Table(name = "kf_activity_content_bind")
public class ActivityContentBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //关联的文章id
    @Column(name = "content_id")
    private Integer contentId;
	
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
    @Column(name = "order_number")
    private Integer orderNumber;
	
	    //1删除0正常
    @Column(name = "is_delete")
    private Integer isDelete;
	

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
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：关联的文章id
	 */
	public Integer getContentId() {
		return contentId;
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
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：排序值
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
	/**
	 * 设置：1删除0正常
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：1删除0正常
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
}
