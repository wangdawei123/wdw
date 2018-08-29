package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-03 16:37:55
 */
@Table(name = "kf_kpi_type_config")
public class KpiTypeConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //类型名称
    @Column(name = "name")
    private String name;
	
	    //工作内容对应的工作数量
    @Column(name = "work_num")
    private Integer workNum;
	
	    //pv最小量
    @Column(name = "pv_limit")
    private Integer pvLimit;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //更新时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //最后编辑人
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //1视频工作类型２直播工作类型３文章展示类型４文章工作类型
    @Column(name = "config_type")
    private Integer configType;
	
	    //1 正常 0 删除
    @Column(name = "stat")
    private Integer stat;
	
	    //类型说明
    @Column(name = "remark")
    private String remark;
	
	    //列表是否显示 0不显示 1显示
    @Column(name = "is_show")
    private Integer isShow;
	
	    //父id
    @Column(name = "pid")
    private Integer pid;
	
	    //直播：1 栏目直播　２普通直播　视频１转载视频　２普通视频　３栏目视频　４动画视频
    @Column(name = "relation_type")
    private Integer relationType;
	

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
	 * 设置：类型名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：类型名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：工作内容对应的工作数量
	 */
	public void setWorkNum(Integer workNum) {
		this.workNum = workNum;
	}
	/**
	 * 获取：工作内容对应的工作数量
	 */
	public Integer getWorkNum() {
		return workNum;
	}
	/**
	 * 设置：pv最小量
	 */
	public void setPvLimit(Integer pvLimit) {
		this.pvLimit = pvLimit;
	}
	/**
	 * 获取：pv最小量
	 */
	public Integer getPvLimit() {
		return pvLimit;
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
	 * 设置：1视频工作类型２直播工作类型３文章展示类型４文章工作类型
	 */
	public void setConfigType(Integer configType) {
		this.configType = configType;
	}
	/**
	 * 获取：1视频工作类型２直播工作类型３文章展示类型４文章工作类型
	 */
	public Integer getConfigType() {
		return configType;
	}
	/**
	 * 设置：1 正常 0 删除
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：1 正常 0 删除
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：类型说明
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：类型说明
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：列表是否显示 0不显示 1显示
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	/**
	 * 获取：列表是否显示 0不显示 1显示
	 */
	public Integer getIsShow() {
		return isShow;
	}
	/**
	 * 设置：父id
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父id
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * 设置：直播：1 栏目直播　２普通直播　视频１转载视频　２普通视频　３栏目视频　４动画视频
	 */
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}
	/**
	 * 获取：直播：1 栏目直播　２普通直播　视频１转载视频　２普通视频　３栏目视频　４动画视频
	 */
	public Integer getRelationType() {
		return relationType;
	}
}
