package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-10 15:16:07
 */
@Table(name = "kf_region")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //省份
    @Column(name = "name")
    private String name;
	

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
	 * 设置：省份
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：省份
	 */
	public String getName() {
		return name;
	}
}
