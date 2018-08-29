package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-02-26 18:01:42
 */
@Table(name = "kf_video_album")
public class VideoAlbum implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //专辑标题
    @Column(name = "title")
    private String title;
	
	    //专辑简介
    @Column(name = "summary")
    private String summary;
	
	    //专辑内容
    @Column(name = "content")
    private String content;
	
	    //封面图片
    @Column(name = "cover_img")
    private String coverImg;
	
	    //专辑状态 0:下线 1:上线
    @Column(name = "is_publish")
    private Integer isPublish;
	
	    //是否已删除 1:未删除 0:已删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
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
    private Date updateTime;
	
	    //专辑类型 1为普通 2为推荐
    @Column(name = "album_type")
    private Integer albumType;
	
	    //专辑排序
    @Column(name = "album_order")
    private Integer albumOrder;
	

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
	 * 设置：专辑标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：专辑标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：专辑简介
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 获取：专辑简介
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * 设置：专辑内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：专辑内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：封面图片
	 */
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	/**
	 * 获取：封面图片
	 */
	public String getCoverImg() {
		return coverImg;
	}
	/**
	 * 设置：专辑状态 0:下线 1:上线
	 */
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	/**
	 * 获取：专辑状态 0:下线 1:上线
	 */
	public Integer getIsPublish() {
		return isPublish;
	}
	/**
	 * 设置：是否已删除 1:未删除 0:已删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否已删除 1:未删除 0:已删除
	 */
	public Integer getIsDelete() {
		return isDelete;
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
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：专辑类型 1为普通 2为推荐
	 */
	public void setAlbumType(Integer albumType) {
		this.albumType = albumType;
	}
	/**
	 * 获取：专辑类型 1为普通 2为推荐
	 */
	public Integer getAlbumType() {
		return albumType;
	}
	/**
	 * 设置：专辑排序
	 */
	public void setAlbumOrder(Integer albumOrder) {
		this.albumOrder = albumOrder;
	}
	/**
	 * 获取：专辑排序
	 */
	public Integer getAlbumOrder() {
		return albumOrder;
	}
}
