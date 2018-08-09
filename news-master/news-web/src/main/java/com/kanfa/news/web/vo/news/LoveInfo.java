package com.kanfa.news.web.vo.news;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 喜欢表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-06-14 10:54:39
 */
public class LoveInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //所属的内容ID
    private Integer cid;
	    //创建者UID或sessionid
    private String uuid;
	    //设备id
    private String devId;
	    //创建时间
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
