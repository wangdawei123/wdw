package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 广告
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-05-30 13:54:21
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-05-30 11:04:20
 */
@Table(name = "kf_adv")
public class Adv implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //名称
    @Column(name = "name")
    private String name;
	
	    //上线时间
    @Column(name = "start_time")
    private Date startTime;
	
	    //下线时间
    @Column(name = "end_time")
    private Date endTime;
	
	    //广告类型，1：图文，2：文字，3：APP下载
    @Column(name = "type")
    private Integer type;
	
	    //内容类型,1:url,2:资讯，3：专题，4：生活，5：投票，6：活动
    @Column(name = "content_type")
    private Integer contentType;
	
	    //标题
    @Column(name = "title")
    private String title;
	
	    //大缩略图
    @Column(name = "image")
    private String image;
	
	    //url
    @Column(name = "url")
    private String url;
	
	    //描述
    @Column(name = "description")
    private String description;
	
	    //APP名称
    @Column(name = "app_name")
    private String appName;
	
	    //app安卓版的包名
    @Column(name = "app_android")
    private String appAndroid;
	
	    //PV
    @Column(name = "views")
    private Integer views;
	
	    //创建者
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //启动页时间
    @Column(name = "keep")
    private Integer keep;
	
	    //广告排序,值越大越靠前
    @Column(name = "order_number")
    private Integer orderNumber;
	

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：上线时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：上线时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：下线时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：下线时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：广告类型，1：图文，2：文字，3：APP下载
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：广告类型，1：图文，2：文字，3：APP下载
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：内容类型,1:url,2:资讯，3：专题，4：生活，5：投票，6：活动
	 */
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	/**
	 * 获取：内容类型,1:url,2:资讯，3：专题，4：生活，5：投票，6：活动
	 */
	public Integer getContentType() {
		return contentType;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：大缩略图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：大缩略图
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：APP名称
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
	/**
	 * 获取：APP名称
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * 设置：app安卓版的包名
	 */
	public void setAppAndroid(String appAndroid) {
		this.appAndroid = appAndroid;
	}
	/**
	 * 获取：app安卓版的包名
	 */
	public String getAppAndroid() {
		return appAndroid;
	}
	/**
	 * 设置：PV
	 */
	public void setViews(Integer views) {
		this.views = views;
	}
	/**
	 * 获取：PV
	 */
	public Integer getViews() {
		return views;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建者
	 */
	public Integer getCreateUid() {
		return createUid;
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
	 * 设置：启动页时间
	 */
	public void setKeep(Integer keep) {
		this.keep = keep;
	}
	/**
	 * 获取：启动页时间
	 */
	public Integer getKeep() {
		return keep;
	}
	/**
	 * 设置：广告排序,值越大越靠前
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：广告排序,值越大越靠前
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
}
