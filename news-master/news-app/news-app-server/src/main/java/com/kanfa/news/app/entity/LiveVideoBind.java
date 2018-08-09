package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 直播关联内容表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-28 10:48:58
 */
@Table(name = "kf_live_video_bind")
public class LiveVideoBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //绑定ID
    @Id
    private Integer id;
	
	    //内容ID
    @Column(name = "cid")
    private Integer cid;
	
	    //绑定的视频或者直播ID
    @Column(name = "bind_id")
    private Integer bindId;
	
	    //内容来源。1：content表视频，2：live表直播
    @Column(name = "from_type")
    private Integer fromType;
	
	    //排序值
    @Column(name = "sort")
    private Integer sort;
	
	    //排序时间
    @Column(name = "sorttime")
    private Integer sorttime;
	
	    //创建时间
    @Column(name = "create_time")
    private Integer createTime;
	
	    //更新时间
    @Column(name = "update_time")
    private Integer updateTime;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //更新人
    @Column(name = "update_uid")
    private Integer updateUid;
	

	/**
	 * 设置：绑定ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：绑定ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：内容ID
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：内容ID
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：绑定的视频或者直播ID
	 */
	public void setBindId(Integer bindId) {
		this.bindId = bindId;
	}
	/**
	 * 获取：绑定的视频或者直播ID
	 */
	public Integer getBindId() {
		return bindId;
	}
	/**
	 * 设置：内容来源。1：content表视频，2：live表直播
	 */
	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}
	/**
	 * 获取：内容来源。1：content表视频，2：live表直播
	 */
	public Integer getFromType() {
		return fromType;
	}
	/**
	 * 设置：排序值
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序值
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：排序时间
	 */
	public void setSorttime(Integer sorttime) {
		this.sorttime = sorttime;
	}
	/**
	 * 获取：排序时间
	 */
	public Integer getSorttime() {
		return sorttime;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Integer getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Integer getUpdateTime() {
		return updateTime;
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
	 * 设置：更新人
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：更新人
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
}
