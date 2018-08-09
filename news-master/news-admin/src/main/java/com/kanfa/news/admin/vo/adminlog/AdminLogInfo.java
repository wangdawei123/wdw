package com.kanfa.news.admin.vo.adminlog;

import java.io.Serializable;
import java.util.Date;


/**
 * 日志
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-03 11:36:11
 */
public class AdminLogInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //操作说明
    private String action;
	
	    //操作的url
    private String actionUrl;
	
	    //操作的
    private String ip;
	
	    //创建人
    private String createUser;
	
	    //创建时间
    private Integer createTime;

    //时间
    private Date time;

	    //文章类型
    private String typeName;

	    //文章类型
    private Integer type;

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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
