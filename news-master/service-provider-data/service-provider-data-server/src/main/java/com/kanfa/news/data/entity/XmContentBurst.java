package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 新闻爆料与内容关系表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:22:46
 */
@Table(name = "xm_content_burst")
public class XmContentBurst implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //爆料id
    @Column(name = "burst_id")
    private Integer burstId;
	
	    //content表id
    @Column(name = "cid")
    private Integer cid;
	
	    //状态(同content表中check状态)
    @Column(name = "stat")
    private Integer stat;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //更新时间
    @Column(name = "update_time")
    private Date updateTime;
	

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
	 * 设置：爆料id
	 */
	public void setBurstId(Integer burstId) {
		this.burstId = burstId;
	}
	/**
	 * 获取：爆料id
	 */
	public Integer getBurstId() {
		return burstId;
	}
	/**
	 * 设置：content表id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：content表id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：状态(同content表中check状态)
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态(同content表中check状态)
	 */
	public Integer getStat() {
		return stat;
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
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
