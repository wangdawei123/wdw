package com.kanfa.news.admin.vo.message;

import java.io.Serializable;
import java.util.List;


/**
 * 省份
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-18 16:26:12
 */
public class ProvinceInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //
    private String name;

	private List<CityInfo> cityList;
	
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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}

	public List<CityInfo> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityInfo> cityList) {
		this.cityList = cityList;
	}
}
