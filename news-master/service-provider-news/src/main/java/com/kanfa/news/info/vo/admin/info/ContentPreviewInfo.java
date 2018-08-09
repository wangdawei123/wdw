package com.kanfa.news.info.vo.admin.info;

import javax.persistence.Table;
import java.io.Serializable;


/**
 * 预览表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-29 17:05:11
 */
@Table(name = "kf_content_preview")
public class ContentPreviewInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //xm_admin_user，id
    private Integer uid;
	
	    //预览json数据
    private String json;
	
	    //创建时间
    private Integer createTime;
	
	    //类型(对应content表中type)
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
	 * 设置：xm_admin_user，id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：xm_admin_user，id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：预览json数据
	 */
	public void setJson(String json) {
		this.json = json;
	}
	/**
	 * 获取：预览json数据
	 */
	public String getJson() {
		return json;
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
	 * 设置：类型(对应content表中type)
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型(对应content表中type)
	 */
	public Integer getType() {
		return type;
	}
}
