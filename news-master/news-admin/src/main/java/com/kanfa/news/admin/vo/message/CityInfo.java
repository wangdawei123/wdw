package com.kanfa.news.admin.vo.message;

import java.io.Serializable;


/**
 * 城市
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-10 10:28:18
 */
public class CityInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //省份id
    private Integer provinceId;
	
	    //市名
    private String name;

	//极光分组id
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
