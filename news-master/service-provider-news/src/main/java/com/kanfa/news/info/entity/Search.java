package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-17 14:43:14
 */
@Table(name = "kf_search")
public class Search implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //搜索名称
    @Column(name = "name")
    private String name;
	
	    //点击数
    @Column(name = "click_num")
    private Integer clickNum;
	
	    //执行时间
    @Column(name = "imp_time")
    private Date impTime;
	
	    //添加uid
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //添加时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //修改uid
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //修改时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //0：失效  1：显示
    @Column(name = "show")
    private Integer show;
	
	    //0:删除 1:正常
    @Column(name = "stat")
    private Integer stat;
	
	    //结束时间
    @Column(name = "end_time")
    private Date endTime;
	

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
	 * 设置：搜索名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：搜索名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：点击数
	 */
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}
	/**
	 * 获取：点击数
	 */
	public Integer getClickNum() {
		return clickNum;
	}
	/**
	 * 设置：执行时间
	 */
	public void setImpTime(Date impTime) {
		this.impTime = impTime;
	}
	/**
	 * 获取：执行时间
	 */
	public Date getImpTime() {
		return impTime;
	}
	/**
	 * 设置：添加uid
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：添加uid
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
	 * 设置：修改uid
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：修改uid
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
	 * 设置：0：失效  1：显示
	 */
	public void setShow(Integer show) {
		this.show = show;
	}
	/**
	 * 获取：0：失效  1：显示
	 */
	public Integer getShow() {
		return show;
	}
	/**
	 * 设置：0:删除 1:正常
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：0:删除 1:正常
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
}
