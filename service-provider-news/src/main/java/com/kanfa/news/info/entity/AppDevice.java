package com.kanfa.news.info.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * app设备表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-04-04 17:59:08
 */
@Table(name = "kf_app_device")
public class AppDevice implements Serializable {
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
    @Column(name = "device_id")
    private String deviceId;
	
	    //idfa
    @Column(name = "idfa")
    private String idfa;
	
	    //设备状态
    @Column(name = "status")
    private Integer status;
	
	    //创建日期
    @Column(name = "create_date")
    private Integer createDate;
	
	    //更新时间
    @Column(name = "update_time")
    private Integer updateTime;
	
	    //版本号
    @Column(name = "version")
    private String version;
	

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
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备号
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：idfa
	 */
	public void setIdfa(String idfa) {
		this.idfa = idfa;
	}
	/**
	 * 获取：idfa
	 */
	public String getIdfa() {
		return idfa;
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
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：版本号
	 */
	public String getVersion() {
		return version;
	}
}
