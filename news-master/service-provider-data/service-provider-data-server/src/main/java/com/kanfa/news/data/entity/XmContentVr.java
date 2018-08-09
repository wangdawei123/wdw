package com.kanfa.news.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 内容附表-VR
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-07 14:55:51
 */
@Table(name = "xm_content_vr")
public class XmContentVr implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //内容ID
    @Id
    private Integer cid;
	
	    //视频URL
    @Column(name = "url")
    private String url;
	
	    //视频时长
    @Column(name = "duration")
    private String duration;
	
	    //状态，1：正常，0：删除
    @Column(name = "stat")
    private Integer stat;
	
	    //视频库id
    @Column(name = "did")
    private Integer did;
	

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
	 * 设置：视频URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：视频URL
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：视频时长
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * 获取：视频时长
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * 设置：状态，1：正常，0：删除
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：视频库id
	 */
	public void setDid(Integer did) {
		this.did = did;
	}
	/**
	 * 获取：视频库id
	 */
	public Integer getDid() {
		return did;
	}
}
