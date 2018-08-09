package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:49:21
 */
@Table(name = "xm_demand")
public class XmDemand implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //媒体id
    @Column(name = "medid")
    private String medid;
	
	    //视频名称
    @Column(name = "title")
    private String title;
	
	    //视频创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //视频库状态  1：上传失败 2：上传完成 3：转码中 4：转码完成
    @Column(name = "status")
    private String status;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //转码地址
    @Column(name = "url")
    private String url;
	
	    //文件名
    @Column(name = "name")
    private String name;
	
	    //1：正常 0：失败
    @Column(name = "stat")
    private Integer stat;
	
	    //运行id
    @Column(name = "runid")
    private String runid;
	
	    //时长
    @Column(name = "duration")
    private String duration;
	
	    //视频md5值
    @Column(name = "videomd")
    private String videomd;
	
	    //视频类型(废弃)
    @Column(name = "type_id")
    private Integer typeId;
	
	    //视频类型 １转载视频　２普通视频　３栏目视频　４动画视频
    @Column(name = "type")
    private Integer type;
	
	    //来源类型 0转载 1原创
    @Column(name = "source_type")
    private Integer sourceType;
	

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
	 * 设置：媒体id
	 */
	public void setMedid(String medid) {
		this.medid = medid;
	}
	/**
	 * 获取：媒体id
	 */
	public String getMedid() {
		return medid;
	}
	/**
	 * 设置：视频名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：视频名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：视频创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：视频创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：视频库状态  1：上传失败 2：上传完成 3：转码中 4：转码完成
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：视频库状态  1：上传失败 2：上传完成 3：转码中 4：转码完成
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：转码地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：转码地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：文件名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：文件名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：1：正常 0：失败
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：1：正常 0：失败
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：运行id
	 */
	public void setRunid(String runid) {
		this.runid = runid;
	}
	/**
	 * 获取：运行id
	 */
	public String getRunid() {
		return runid;
	}
	/**
	 * 设置：时长
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	/**
	 * 获取：时长
	 */
	public String getDuration() {
		return duration;
	}
	/**
	 * 设置：视频md5值
	 */
	public void setVideomd(String videomd) {
		this.videomd = videomd;
	}
	/**
	 * 获取：视频md5值
	 */
	public String getVideomd() {
		return videomd;
	}
	/**
	 * 设置：视频类型(废弃)
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：视频类型(废弃)
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * 设置：视频类型 １转载视频　２普通视频　３栏目视频　４动画视频
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：视频类型 １转载视频　２普通视频　３栏目视频　４动画视频
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：来源类型 0转载 1原创
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * 获取：来源类型 0转载 1原创
	 */
	public Integer getSourceType() {
		return sourceType;
	}
}
