package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 配置表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-19 18:15:53
 */
@Table(name = "kf_setting")
public class Setting implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //配置项名称
    @Id
    private String name;
	
	    //配置项值
    @Column(name = "value")
    private String value;
	
	    //配置项描述
    @Column(name = "desc")
    private String desc;
	

	/**
	 * 设置：配置项名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：配置项名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：配置项值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：配置项值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：配置项描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：配置项描述
	 */
	public String getDesc() {
		return desc;
	}
}
