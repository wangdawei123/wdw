package com.kanfa.news.info.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 视频栏目表
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-15 10:50:52
 */
@Table(name = "kf_video_column")
public class VideoColumn implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //栏目标题
    @Column(name = "title")
    private String title;
	
	    //栏目简介
    @Column(name = "summary")
    private String summary;
	
	    //栏目内容
    @Column(name = "content")
    private String content;
	
	    //封面图片
    @Column(name = "cover_img")
    private String coverImg;
	
	    //栏目状态 0:下线 1:上线
    @Column(name = "pub")
    private Integer pub;
	
	    //是否已删除 1:未删除 0:已删除
    @Column(name = "stat")
    private Integer stat;
	
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
	
	    //栏目排序
    @Column(name = "column_order")
    private Integer columnOrder;
	
	    //是否首推
    @Column(name = "is_top")
    private Integer isTop;
	

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
	 * 设置：栏目标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：栏目标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：栏目简介
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 获取：栏目简介
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * 设置：栏目内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：栏目内容
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
	 * 设置：栏目状态 0:下线 1:上线
	 */
	public void setPub(Integer pub) {
		this.pub = pub;
	}
	/**
	 * 获取：栏目状态 0:下线 1:上线
	 */
	public Integer getPub() {
		return pub;
	}
	/**
	 * 设置：是否已删除 1:未删除 0:已删除
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：是否已删除 1:未删除 0:已删除
	 */
	public Integer getStat() {
		return stat;
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
	 * 设置：栏目排序
	 */
	public void setColumnOrder(Integer columnOrder) {
		this.columnOrder = columnOrder;
	}
	/**
	 * 获取：栏目排序
	 */
	public Integer getColumnOrder() {
		return columnOrder;
	}
	/**
	 * 设置：是否首推
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	/**
	 * 获取：是否首推
	 */
	public Integer getIsTop() {
		return isTop;
	}
}
