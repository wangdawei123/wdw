package com.kanfa.news.app.entity;

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
 * @date 2018-02-26 18:01:42
 */
@Table(name = "kf_video_source")
public class VideoSource implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //来源id
    @Column(name = "name")
    private String name;
	
	    //添加人id
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //添加时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //修改人id
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //修改时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //头像
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
	 * 设置：来源id
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：来源id
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：添加人id
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：添加人id
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改人id
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：修改人id
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
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
}
