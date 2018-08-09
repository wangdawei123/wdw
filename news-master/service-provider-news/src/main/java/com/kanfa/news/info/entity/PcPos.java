package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-05-30 18:51:24
 */
@Table(name = "kf_pc_pos")
public class PcPos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //
    @Column(name = "pc_id")
    private Integer pcId;
	
	    //
    @Column(name = "type")
    private Integer type;
	

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
	 * 设置：
	 */
	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}
	/**
	 * 获取：
	 */
	public Integer getPcId() {
		return pcId;
	}
	/**
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
}
