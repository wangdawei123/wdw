package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 话题帖子内容
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-02 14:55:56
 */
@Table(name = "kf_problem")
public class Problem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //资讯文章id
    @Column(name = "cid")
    private Integer cid;
	
	    //问题
    @Column(name = "content")
    private String content;
	
	    //回复问题
    @Column(name = "reason")
    private String reason;
	
	    //1:回复了,0：未回复
    @Column(name = "ops")
    private Integer ops;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //回复人
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //回复时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //状态
    @Column(name = "stat")
    private Integer stat;
	
	    //是否已读 0:未读 1:已读
    @Column(name = "is_read")
    private Integer isRead;
	

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
	 * 设置：资讯文章id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：资讯文章id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：问题
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：问题
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：回复问题
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：回复问题
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * 设置：1:回复了,0：未回复
	 */
	public void setOps(Integer ops) {
		this.ops = ops;
	}
	/**
	 * 获取：1:回复了,0：未回复
	 */
	public Integer getOps() {
		return ops;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateUid() {
		return createUid;
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
	 * 设置：回复人
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：回复人
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：回复时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：回复时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：状态
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：是否已读 0:未读 1:已读
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取：是否已读 0:未读 1:已读
	 */
	public Integer getIsRead() {
		return isRead;
	}
}
