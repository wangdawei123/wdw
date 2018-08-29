package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 新闻爆料图片&视频表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
@Table(name = "kf_burst_resource")
public class BurstResource implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //新闻id
    @Column(name = "burst_id")
    private Integer burstId;
	
	    //类型(1:图片;2:视频;3:文件)
    @Column(name = "type")
    private Integer type;
	
	    //视频/文件 title
    @Column(name = "title")
    private String title;
	
	    //上传地址
    @Column(name = "url")
    private String url;
	
	    //来源(1:用户;2:小编)
    @Column(name = "burst_source")
    private Integer burstSource;
	
	    //上传文件的md5值
    @Column(name = "videomd")
    private String videomd;
	
	    //上传状态(1:上传失败;2:上传成功)
    @Column(name = "upload_status")
    private Integer uploadStatus;
	
	    //是否正常，1:正常 0:删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //upload_stat 更新时间
    @Column(name = "update_time")
    private Date updateTime;
	

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
	 * 设置：新闻id
	 */
	public void setBurstId(Integer burstId) {
		this.burstId = burstId;
	}
	/**
	 * 获取：新闻id
	 */
	public Integer getBurstId() {
		return burstId;
	}
	/**
	 * 设置：类型(1:图片;2:视频;3:文件)
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型(1:图片;2:视频;3:文件)
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：视频/文件 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：视频/文件 title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：上传地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：上传地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：来源(1:用户;2:小编)
	 */
	public void setBurstSource(Integer burstSource) {
		this.burstSource = burstSource;
	}
	/**
	 * 获取：来源(1:用户;2:小编)
	 */
	public Integer getBurstSource() {
		return burstSource;
	}
	/**
	 * 设置：上传文件的md5值
	 */
	public void setVideomd(String videomd) {
		this.videomd = videomd;
	}
	/**
	 * 获取：上传文件的md5值
	 */
	public String getVideomd() {
		return videomd;
	}
	/**
	 * 设置：上传状态(1:上传失败;2:上传成功)
	 */
	public void setUploadStatus(Integer uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	/**
	 * 获取：上传状态(1:上传失败;2:上传成功)
	 */
	public Integer getUploadStatus() {
		return uploadStatus;
	}
	/**
	 * 设置：是否正常，1:正常 0:删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否正常，1:正常 0:删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：upload_stat 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：upload_stat 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
