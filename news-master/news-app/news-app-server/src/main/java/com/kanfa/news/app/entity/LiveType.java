package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 直播类型表
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-26 13:26:20
 */
@Table(name = "kf_live_type")
public class LiveType implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //直播类型名称
    @Column(name = "name")
    private String name;
	
	    //是否上线(1:上线；0:下线)
    @Column(name = "is_publish")
    private Integer isPublish;
	
	    //类型状态
    @Column(name = "status")
    private Integer status;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建日期
    @Column(name = "create_time")
    private Date createTime;
	
	    //最后编辑人
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //最后更新日期
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
	 * 设置：直播类型名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：直播类型名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：是否上线(1:上线；0:下线)
	 */
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	/**
	 * 获取：是否上线(1:上线；0:下线)
	 */
	public Integer getIsPublish() {
		return isPublish;
	}
	/**
	 * 设置：类型状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：类型状态
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：最后编辑人
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：最后编辑人
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：最后更新日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
