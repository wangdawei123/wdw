package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 推广渠道
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-16 11:26:02
 */
@Table(name = "kf_extend_channel")
public class ExtendChannel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //渠道名
    @Column(name = "name")
    private String name;
	
	    //渠道号
    @Column(name = "channel_num")
    private String channelNum;
	
	    //状态
    @Column(name = "status")
    private Integer status;
	
	    //是否已删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //创建人
    @Column(name = "created_uid")
    private Integer createdUid;
	

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
	 * 设置：渠道名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：渠道名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：渠道号
	 */
	public void setChannelNum(String channelNum) {
		this.channelNum = channelNum;
	}
	/**
	 * 获取：渠道号
	 */
	public String getChannelNum() {
		return channelNum;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：是否已删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否已删除
	 */
	public Integer getIsDelete() {
		return isDelete;
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
	 * 设置：创建人
	 */
	public void setCreatedUid(Integer createdUid) {
		this.createdUid = createdUid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreatedUid() {
		return createdUid;
	}
}
