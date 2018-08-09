package com.kanfa.news.web.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 意见反馈表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-24 15:48:03
 */
@Table(name = "kf_feedback")
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //用户id
    @Column(name = "uid")
    private Integer uid;
	
	    //建议
    @Column(name = "advise")
    private String advise;
	
	    //联系电话
    @Column(name = "phone")
    private String phone;
	
	    //发布时间
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
	 * 设置：用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：建议
	 */
	public void setAdvise(String advise) {
		this.advise = advise;
	}
	/**
	 * 获取：建议
	 */
	public String getAdvise() {
		return advise;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
