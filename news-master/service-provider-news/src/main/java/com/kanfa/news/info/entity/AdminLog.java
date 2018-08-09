package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-03 11:36:11
 */
@Table(name = "kf_admin_log")
public class AdminLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //操作说明
    @Column(name = "action")
    private String action;
	
	    //操作的url
    @Column(name = "action_url")
    private String actionUrl;
	
	    //操作的
    @Column(name = "ip")
    private String ip;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建时间
    @Column(name = "create_time")
    private Integer createTime;
	
	    //文章类型
    @Column(name = "type")
    private Integer type;
	
	    //
    @Column(name = "content_id")
    private Integer contentId;
	

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
	 * 设置：操作说明
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * 获取：操作说明
	 */
	public String getAction() {
		return action;
	}
	/**
	 * 设置：操作的url
	 */
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	/**
	 * 获取：操作的url
	 */
	public String getActionUrl() {
		return actionUrl;
	}
	/**
	 * 设置：操作的
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：操作的
	 */
	public String getIp() {
		return ip;
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
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Integer getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：文章类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：文章类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：
	 */
	public Integer getContentId() {
		return contentId;
	}
}
