package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 专题与直播关联表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 15:43:19
 */
@Table(name = "kf_live_special_bind")
public class LiveSpecialBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //专题id
    @Column(name = "live_special_id")
    private Integer liveSpecialId;
	
	    //直播id
    @Column(name = "live_id")
    private Integer liveId;
	
	    //排序值,降序排序
    @Column(name = "sort")
    private Integer sort;
	

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
	 * 设置：专题id
	 */
	public void setLiveSpecialId(Integer liveSpecialId) {
		this.liveSpecialId = liveSpecialId;
	}
	/**
	 * 获取：专题id
	 */
	public Integer getLiveSpecialId() {
		return liveSpecialId;
	}
	/**
	 * 设置：直播id
	 */
	public void setLiveId(Integer liveId) {
		this.liveId = liveId;
	}
	/**
	 * 获取：直播id
	 */
	public Integer getLiveId() {
		return liveId;
	}
	/**
	 * 设置：排序值,降序排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序值,降序排序
	 */
	public Integer getSort() {
		return sort;
	}
}
