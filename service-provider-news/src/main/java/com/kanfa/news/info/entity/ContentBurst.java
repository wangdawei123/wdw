package com.kanfa.news.info.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 新闻爆料与内容关系表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-22 13:46:14
 */
@Table(name = "kf_content_burst")
public class ContentBurst implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //爆料id
    @Column(name = "burst_id")
    private Integer burstId;
	
	    //content表id
    @Column(name = "content_id")
    private Integer contentId;
	
	    //状态(同content表中check状态)
    @Column(name = "status")
    private Integer status;
	
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
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：content表id
	 */
	public Integer getContentId() {
		return contentId;
	}
	/**
	 * 设置：状态(同content表中check状态)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态(同content表中check状态)
	 */
	public Integer getStatus() {
		return status;
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
