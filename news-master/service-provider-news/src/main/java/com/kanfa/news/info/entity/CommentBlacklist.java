package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 评论的屏蔽用户
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-28 13:30:44
 */
@Table(name = "kf_comment_blacklist")
public class CommentBlacklist implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //设备ID，屏蔽非会员时，使用此值
    @Column(name = "device_id")
    private String deviceId;
	
	    //会员ID
    @Column(name = "uid")
    private Integer uid;
	
	    //被屏蔽时的用户名/游客名，仅作参考用
    @Column(name = "name")
    private String name;
	
	    //创建时间
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
	 * 设置：设备ID，屏蔽非会员时，使用此值
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备ID，屏蔽非会员时，使用此值
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * 设置：会员ID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：会员ID
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：被屏蔽时的用户名/游客名，仅作参考用
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：被屏蔽时的用户名/游客名，仅作参考用
	 */
	public String getName() {
		return name;
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
