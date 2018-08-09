package com.kanfa.news.activity.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 内容附表-视频
 * 
 * @author ffy
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-14 18:05:14
 */
@Table(name = "kf_content_video")
public class ContentVideo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //内容ID
    @Id
    private Integer cid;
	
	    //视频底图
    @Column(name = "image")
    private String image;
	
	    //视频URL
    @Column(name = "url")
    private String url;
	
	    //视频时长
    @Column(name = "duration")
    private String duration;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //视频库id
    @Column(name = "did")
    private Integer did;
	
	    //来源
    @Column(name = "source")
    private String source;
	
	    //来源id
    @Column(name = "source_id")
    private Integer sourceId;
	

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
	 * 设置：视频底图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：视频底图
	 */
	public String getImage() {
		return image;
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
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getIsDelete() {
		return isDelete;
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
	/**
	 * 设置：来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：来源id
	 */
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	/**
	 * 获取：来源id
	 */
	public Integer getSourceId() {
		return sourceId;
	}
}
