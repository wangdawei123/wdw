package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 关联内容表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:46:08
 */
@Table(name = "kf_content_broadcast_bind")
public class ContentBroadcastBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //kf_content.id
    @Column(name = "content_id")
    private Integer contentId;
	
	    //kf_content.id
    @Column(name = "bind_id")
    private Integer bindId;
	
	    //排序值
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
	 * 设置：kf_content.id
	 */
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：kf_content.id
	 */
	public Integer getContentId() {
		return contentId;
	}
	/**
	 * 设置：kf_content.id
	 */
	public void setBindId(Integer bindId) {
		this.bindId = bindId;
	}
	/**
	 * 获取：kf_content.id
	 */
	public Integer getBindId() {
		return bindId;
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
}
