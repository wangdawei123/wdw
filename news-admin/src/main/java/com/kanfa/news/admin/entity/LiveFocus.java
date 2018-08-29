package com.kanfa.news.admin.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 直播焦点图表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-24 11:23:12
 */
@Table(name = "kf_live_focus")
public class LiveFocus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //焦点图标题
    @Column(name = "title")
    private String title;
	
	    //直播类型id,0代表是直播首页
    @Column(name = "live_type_id")
    private Integer liveTypeId;
	
	    //图片地址
    @Column(name = "image")
    private String image;
	
	    //0:下线 1:上线
    @Column(name = "pub")
    private Integer pub;
	
	    //排序
    @Column(name = "sort")
    private Integer sort;
	
	    //跳转链接
    @Column(name = "url")
    private String url;
	
	    //要跳转到的直播id
    @Column(name = "live_id")
    private Integer liveId;
	
	    //1、站内内容 2、url 3、app 4、URL不跳转
    @Column(name = "jump")
    private Integer jump;
	
	    //状态(0:已删除;1:正常)
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
	 * 设置：焦点图标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：焦点图标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：直播类型id,0代表是直播首页
	 */
	public void setLiveTypeId(Integer liveTypeId) {
		this.liveTypeId = liveTypeId;
	}
	/**
	 * 获取：直播类型id,0代表是直播首页
	 */
	public Integer getLiveTypeId() {
		return liveTypeId;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：0:下线 1:上线
	 */
	public void setPub(Integer pub) {
		this.pub = pub;
	}
	/**
	 * 获取：0:下线 1:上线
	 */
	public Integer getPub() {
		return pub;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：跳转链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：跳转链接
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：要跳转到的直播id
	 */
	public void setLiveId(Integer liveId) {
		this.liveId = liveId;
	}
	/**
	 * 获取：要跳转到的直播id
	 */
	public Integer getLiveId() {
		return liveId;
	}
	/**
	 * 设置：1、站内内容 2、url 3、app 4、URL不跳转
	 */
	public void setJump(Integer jump) {
		this.jump = jump;
	}
	/**
	 * 获取：1、站内内容 2、url 3、app 4、URL不跳转
	 */
	public Integer getJump() {
		return jump;
	}
	/**
	 * 设置：状态(0:已删除;1:正常)
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态(0:已删除;1:正常)
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
}
