package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-12 13:40:41
 */
@Table(name = "kf_city")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //省份id
    @Column(name = "province_id")
    private Integer provinceId;
	
	    //市名
    @Column(name = "name")
    private String name;
	
	    //极光分组id
    @Column(name = "group_id")
    private String groupId;
	

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
	 * 设置：省份id
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省份id
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：市名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：市名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：极光分组id
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * 获取：极光分组id
	 */
	public String getGroupId() {
		return groupId;
	}
}
