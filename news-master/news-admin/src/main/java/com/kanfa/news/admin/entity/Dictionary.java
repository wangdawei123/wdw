package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 
 * 
 * @author Jiayunwei
 * @email jiayunwei@kanfanews.com
 * @date 2018-04-26 15:57:37
 */
@Table(name = "kf_dictionary")
public class Dictionary implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //
    @Column(name = "contentType")
    private String contenttype;
	

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
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	/**
	 * 获取：
	 */
	public String getContenttype() {
		return contenttype;
	}
}
