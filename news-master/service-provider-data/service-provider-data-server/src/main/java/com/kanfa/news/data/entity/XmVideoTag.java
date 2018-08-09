package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 视频标签表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:44:16
 */
@Table(name = "xm_video_tag")
public class XmVideoTag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //标签名称
    @Column(name = "name")
    private String name;
	
	    //是否已删除 1:未删除 0:已删除
    @Column(name = "stat")
    private Integer stat;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建日期
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
	 * 设置：标签名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：标签名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：是否已删除 1:未删除 0:已删除
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：是否已删除 1:未删除 0:已删除
	 */
	public Integer getStat() {
		return stat;
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
}
