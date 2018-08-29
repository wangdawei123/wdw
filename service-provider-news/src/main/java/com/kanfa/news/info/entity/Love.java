package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 喜欢表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-06-14 10:54:39
 */
@Table(name = "kf_love")
public class Love implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //所属的内容ID
    @Id
    private Integer cid;
	
	    //创建者UID或sessionid
    @Column(name = "uuid")
    private String uuid;
	
	    //设备id
    @Column(name = "dev_id")
    private String devId;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	

	/**
	 * 设置：所属的内容ID
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：所属的内容ID
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：创建者UID或sessionid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 获取：创建者UID或sessionid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * 设置：设备id
	 */
	public void setDevId(String devId) {
		this.devId = devId;
	}
	/**
	 * 获取：设备id
	 */
	public String getDevId() {
		return devId;
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
}
