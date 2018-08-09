package com.kanfa.news.user.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-03 18:18:36
 */
@Table(name = "kf_experts")
public class Experts implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //专家名
    @Column(name = "name")
    private String name;
	
	    //1:男 2：母
    @Column(name = "endge")
    private Integer endge;
	
	    //手机号
    @Column(name = "phone")
    private String phone;
	
	    //创建日期
    @Column(name = "create_time")
    private Date createTime;
	
	    //专家介绍
    @Column(name = "commte")
    private String commte;
	
	    //
    @Column(name = "stat")
    private Integer stat;
	
	    //专家头像
    @Column(name = "image")
    private String image;
	

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
	 * 设置：专家名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：专家名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：1:男 2：母
	 */
	public void setEndge(Integer endge) {
		this.endge = endge;
	}
	/**
	 * 获取：1:男 2：母
	 */
	public Integer getEndge() {
		return endge;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
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
	 * 设置：专家介绍
	 */
	public void setCommte(String commte) {
		this.commte = commte;
	}
	/**
	 * 获取：专家介绍
	 */
	public String getCommte() {
		return commte;
	}
	/**
	 * 设置：
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：专家头像
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：专家头像
	 */
	public String getImage() {
		return image;
	}
}
