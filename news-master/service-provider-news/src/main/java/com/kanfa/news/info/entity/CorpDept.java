package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 公司部门表
 * 
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-18 12:03:22
 */
@Table(name = "kf_corp_dept")
public class CorpDept implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //组织名称
    @Column(name = "name")
    private String name;
	
	    //1:一级部门 2:二级部门
    @Column(name = "level")
    private Integer level;
	
	    //父类id
    @Column(name = "parent_id")
    private Integer parentId;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建日期
    @Column(name = "create_time")
    private Date createTime;
	
	    //最后编辑人
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //最后更新日期
    @Column(name = "update_time")
    private Date updateTime;
	
	    //状态 ： 1  正常  2  停用
    @Column(name = "stat")
    private Integer stat;
	

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
	 * 设置：组织名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：组织名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：1:一级部门 2:二级部门
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：1:一级部门 2:二级部门
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：父类id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父类id
	 */
	public Integer getParentId() {
		return parentId;
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
	/**
	 * 设置：最后编辑人
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：最后编辑人
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：最后更新日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：状态 ： 1  正常  2  停用
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态 ： 1  正常  2  停用
	 */
	public Integer getStat() {
		return stat;
	}
}
