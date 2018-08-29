package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 栏目和内容关联表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-07-30 16:34:09
 */
@Table(name = "kf_video_column_bind")
public class VideoColumnBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //栏目id
    @Column(name = "video_column_id")
    private Integer videoColumnId;
	
	    //视频id
    @Column(name = "cid")
    private Integer cid;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建日期
    @Column(name = "create_time")
    private Date createTime;
	
	    //最后编辑人
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //最后更新日期
    @Column(name = "update_time")
    private Integer updateTime;
	
	    //内容来源，1,content 2,Live表
    @Column(name = "from_type")
    private Integer fromType;
	
	    //内容标题
    @Column(name = "title")
    private String title;
	
	    //是否审核，1审核通过，0没通过审核
    @Column(name = "is_check")
    private Integer isCheck;
	
	    //是否在线，1在线，0下线
    @Column(name = "pub")
    private Integer pub;
	

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
	 * 设置：栏目id
	 */
	public void setVideoColumnId(Integer videoColumnId) {
		this.videoColumnId = videoColumnId;
	}
	/**
	 * 获取：栏目id
	 */
	public Integer getVideoColumnId() {
		return videoColumnId;
	}
	/**
	 * 设置：视频id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：视频id
	 */
	public Integer getCid() {
		return cid;
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
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：最后编辑人
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：最后编辑人
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：最后更新日期
	 */
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新日期
	 */
	public Integer getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：内容来源，1,content 2,Live表
	 */
	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}
	/**
	 * 获取：内容来源，1,content 2,Live表
	 */
	public Integer getFromType() {
		return fromType;
	}
	/**
	 * 设置：内容标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：内容标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：是否审核，1审核通过，0没通过审核
	 */
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	/**
	 * 获取：是否审核，1审核通过，0没通过审核
	 */
	public Integer getIsCheck() {
		return isCheck;
	}
	/**
	 * 设置：是否在线，1在线，0下线
	 */
	public void setPub(Integer pub) {
		this.pub = pub;
	}
	/**
	 * 获取：是否在线，1在线，0下线
	 */
	public Integer getPub() {
		return pub;
	}
}
