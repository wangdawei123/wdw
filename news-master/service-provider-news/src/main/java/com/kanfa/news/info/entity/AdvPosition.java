package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 广告位置
 * 
<<<<<<< HEAD
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-05-30 13:54:20
=======
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-05-30 11:04:20
>>>>>>> fc0846d88caa6f570b8873eb5c3d39561a730d8a
 */
@Table(name = "kf_adv_position")
public class AdvPosition implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //广告ID
    @Column(name = "advertisement_id")
    private Integer advertisementId;
	
	    //频道ID
    @Column(name = "channel_id")
    private Integer channelId;
	
	    //位置类型，1：普通，2：正文
    @Column(name = "type")
    private Integer type;
	
	    //排序值，越大的排序越靠前
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
	 * 设置：广告ID
	 */
	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}
	/**
	 * 获取：广告ID
	 */
	public Integer getAdvertisementId() {
		return advertisementId;
	}
	/**
	 * 设置：频道ID
	 */
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取：频道ID
	 */
	public Integer getChannelId() {
		return channelId;
	}
	/**
	 * 设置：位置类型，1：普通，2：正文
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：位置类型，1：普通，2：正文
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：排序值，越大的排序越靠前
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：排序值，越大的排序越靠前
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
}
