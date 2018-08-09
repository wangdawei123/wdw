package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 极光关系关联表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-07-02 14:18:30
 */
@Table(name = "kf_user_device")
public class UserDevice implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //用户id
    @Column(name = "uid")
    private Integer uid;
	
	    //1:android,2:ios,3:winphone
    @Column(name = "type")
    private Integer type;
	
	    //设备号
    @Column(name = "device_token")
    private String deviceToken;
	
	    //设备状态
    @Column(name = "status")
    private Integer status;
	
	    //创建日期
    @Column(name = "create_date")
    private Integer createDate;
	
	    //更新时间
    @Column(name = "update_date")
    private Integer updateDate;
	

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
	 * 设置：用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：1:android,2:ios,3:winphone
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1:android,2:ios,3:winphone
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：设备号
	 */
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	/**
	 * 获取：设备号
	 */
	public String getDeviceToken() {
		return deviceToken;
	}
	/**
	 * 设置：设备状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：设备状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateDate(Integer createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建日期
	 */
	public Integer getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(Integer updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Integer getUpdateDate() {
		return updateDate;
	}
}
