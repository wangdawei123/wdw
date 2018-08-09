package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 后台账号表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:02:21
 */
@Table(name = "kf_admin_user")
public class AdminUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //角色名。当角色为root时，拥有所有权限
    @Column(name = "role")
    private String role;
	
	    //用户名
    @Column(name = "name")
    private String name;
	
	    //真实姓名
    @Column(name = "realname")
    private String realname;
	
	    //昵称，用于前台显示
    @Column(name = "nickname")
    private String nickname;
	
	    //头像
    @Column(name = "image")
    private String image;
	
	    //密码(md5)
    @Column(name = "pwd")
    private String pwd;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "stat")
    private Integer stat;
	
	    //前台用户id
    @Column(name = "front_uid")
    private Integer frontUid;
	

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
	 * 设置：角色名。当角色为root时，拥有所有权限
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * 获取：角色名。当角色为root时，拥有所有权限
	 */
	public String getRole() {
		return role;
	}
	/**
	 * 设置：用户名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：用户名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：昵称，用于前台显示
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称，用于前台显示
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：头像
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：头像
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：密码(md5)
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * 获取：密码(md5)
	 */
	public String getPwd() {
		return pwd;
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
	 * 设置：状态，1：正常，0：删除
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：前台用户id
	 */
	public void setFrontUid(Integer frontUid) {
		this.frontUid = frontUid;
	}
	/**
	 * 获取：前台用户id
	 */
	public Integer getFrontUid() {
		return frontUid;
	}
}
