package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-23 20:01:17
 */
@Table(name = "kf_content_standard")
public class ContentStandard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //内容id或直播id
    @Column(name = "cid")
    private Integer cid;
	
	    //内容类型 1:原类型 2:新直播
    @Column(name = "type")
    private Integer type;
	
	    //虚拟评论内容
    @Column(name = "content")
    private String content;
	
	    //脚本运行时间戳，以分钟为单位,秒为00
    @Column(name = "run_time")
    private Integer runTime;
	
	    //创建用户id
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //
    @Column(name = "create_time")
    private Date createTime;
	
	    //0 未执行　１　执行
    @Column(name = "stat")
    private Integer stat;
	

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
	 * 设置：内容id或直播id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：内容id或直播id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：内容类型 1:原类型 2:新直播
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：内容类型 1:原类型 2:新直播
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：虚拟评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：虚拟评论内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：脚本运行时间戳，以分钟为单位,秒为00
	 */
	public void setRunTime(Integer runTime) {
		this.runTime = runTime;
	}
	/**
	 * 获取：脚本运行时间戳，以分钟为单位,秒为00
	 */
	public Integer getRunTime() {
		return runTime;
	}
	/**
	 * 设置：创建用户id
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建用户id
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：0 未执行　１　执行
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：0 未执行　１　执行
	 */
	public Integer getStat() {
		return stat;
	}
}
