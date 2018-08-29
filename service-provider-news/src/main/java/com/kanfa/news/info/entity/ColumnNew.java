package com.kanfa.news.info.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 栏目表
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-04-25 15:17:43
 */
@Table(name = "kf_column_new")
public class ColumnNew implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //栏目名称
    @Column(name = "name")
    private String name;
	
	    //栏目描述
    @Column(name = "description")
    private String description;
	
	    //栏目背景图
    @Column(name = "image")
    private String image;
	
	    //是否隐藏(0:不隐藏;1:隐藏)
    @Column(name = "hide")
    private Integer hide;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //创建者id
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //更新时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //更新者id
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //删除(0:正常;1:删除)
    @Column(name = "is_delete")
    private Integer isDelete;
	

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
	 * 设置：栏目名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：栏目名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：栏目描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：栏目描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：栏目背景图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：栏目背景图
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：是否隐藏(0:不隐藏;1:隐藏)
	 */
	public void setHide(Integer hide) {
		this.hide = hide;
	}
	/**
	 * 获取：是否隐藏(0:不隐藏;1:隐藏)
	 */
	public Integer getHide() {
		return hide;
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
	 * 设置：创建者id
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建者id
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：更新者id
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：更新者id
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：删除(0:正常;1:删除)
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：删除(0:正常;1:删除)
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
}
