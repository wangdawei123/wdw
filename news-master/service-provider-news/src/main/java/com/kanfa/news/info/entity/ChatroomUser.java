package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Jezy
 * @email chenjie@kanfanews.com
 * @date 2018-03-28 11:53:56
 */
@Table(name = "kf_chatroom_user")
public class ChatroomUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //ID
    @Id
    private Integer id;
	
	    //看法用户ID 或者 设备号
    @Column(name = "uid")
    private String uid;
	
	    //融云token
    @Column(name = "token")
    private String token;
	
	    //token生成时间
    @Column(name = "createTime")
    private Integer createtime;
	
	    //是否禁言 1正常 0禁言
    @Column(name = "talkStatus")
    private Integer talkstatus;
	
	    //看法用户昵称
    @Column(name = "userName")
    private String username;
	
	    //更新昵称时间
    @Column(name = "updateTime")
    private Integer updatetime;
	
	    //封禁时间
    @Column(name = "banTime")
    private Integer bantime;
	
	    //封禁周期，分钟，与融云周期保持一致
    @Column(name = "banCycle")
    private Integer bancycle;
	
	    //是否为匿名用户 0 看法用户 1匿名用户
    @Column(name = "defaultUser")
    private Integer defaultuser;
	

	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：看法用户ID 或者 设备号
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取：看法用户ID 或者 设备号
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置：融云token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：融云token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：token生成时间
	 */
	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：token生成时间
	 */
	public Integer getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：是否禁言 1正常 0禁言
	 */
	public void setTalkstatus(Integer talkstatus) {
		this.talkstatus = talkstatus;
	}
	/**
	 * 获取：是否禁言 1正常 0禁言
	 */
	public Integer getTalkstatus() {
		return talkstatus;
	}
	/**
	 * 设置：看法用户昵称
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：看法用户昵称
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：更新昵称时间
	 */
	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：更新昵称时间
	 */
	public Integer getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：封禁时间
	 */
	public void setBantime(Integer bantime) {
		this.bantime = bantime;
	}
	/**
	 * 获取：封禁时间
	 */
	public Integer getBantime() {
		return bantime;
	}
	/**
	 * 设置：封禁周期，分钟，与融云周期保持一致
	 */
	public void setBancycle(Integer bancycle) {
		this.bancycle = bancycle;
	}
	/**
	 * 获取：封禁周期，分钟，与融云周期保持一致
	 */
	public Integer getBancycle() {
		return bancycle;
	}
	/**
	 * 设置：是否为匿名用户 0 看法用户 1匿名用户
	 */
	public void setDefaultuser(Integer defaultuser) {
		this.defaultuser = defaultuser;
	}
	/**
	 * 获取：是否为匿名用户 0 看法用户 1匿名用户
	 */
	public Integer getDefaultuser() {
		return defaultuser;
	}
}
