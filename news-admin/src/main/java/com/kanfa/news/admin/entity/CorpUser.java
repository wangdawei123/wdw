package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 公司人员表
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-12 14:22:33
 */
@Table(name = "kf_corp_user")
public class CorpUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //用户名称
    @Column(name = "name")
    private String name;
	
	    //一级部门的id
    @Column(name = "level1_id")
    private Integer level1Id;
	
	    //二级部门id
    @Column(name = "level2_id")
    private Integer level2Id;
	
	    //岗位id
    @Column(name = "job_id")
    private Integer jobId;
	
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
	
	    //1在职 2 离职 3删除
    @Column(name = "status")
    private Integer status;
	

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
	 * 设置：用户名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：用户名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：一级部门的id
	 */
	public void setLevel1Id(Integer level1Id) {
		this.level1Id = level1Id;
	}
	/**
	 * 获取：一级部门的id
	 */
	public Integer getLevel1Id() {
		return level1Id;
	}
	/**
	 * 设置：二级部门id
	 */
	public void setLevel2Id(Integer level2Id) {
		this.level2Id = level2Id;
	}
	/**
	 * 获取：二级部门id
	 */
	public Integer getLevel2Id() {
		return level2Id;
	}
	/**
	 * 设置：岗位id
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	/**
	 * 获取：岗位id
	 */
	public Integer getJobId() {
		return jobId;
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
	 * 设置：1在职 2 离职 3删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1在职 2 离职 3删除
	 */
	public Integer getStatus() {
		return status;
	}
}
