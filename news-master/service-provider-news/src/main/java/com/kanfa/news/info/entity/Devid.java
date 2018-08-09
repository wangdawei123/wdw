package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 设备id表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-06-04 16:03:47
 */
@Table(name = "kf_devid")
public class Devid implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //
    @Column(name = "devid")
    private String devid;
	
	    //
    @Column(name = "create_time")
    private Date createTime;
	

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
	 * 设置：
	 */
	public void setDevid(String devid) {
		this.devid = devid;
	}
	/**
	 * 获取：
	 */
	public String getDevid() {
		return devid;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
