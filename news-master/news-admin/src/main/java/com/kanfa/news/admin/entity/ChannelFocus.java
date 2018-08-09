package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 频道焦点图
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-07 09:57:46
 */
@Table(name = "kf_channel_focus")
public class ChannelFocus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //频道id,kf_channel.id
    @Column(name = "channel_id")
    private Integer channelId;
	
	    //焦点图标题
    @Column(name = "title")
    private String title;
	
	    //内容ID，可选值
    @Column(name = "cid")
    private Integer cid;
	
	    //cid的类型，与kf_content的type相同（冗余字段）
    @Column(name = "type")
    private Integer type;
	
	    //链接
    @Column(name = "url")
    private String url;
	
	    //图片URL
    @Column(name = "image")
    private String image;
	
	    //排序
    @Column(name = "order_number")
    private Integer orderNumber;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //更新时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //编辑人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //
    @Column(name = "is_publish")
    private Integer isPublish;
	
	    //圈子帖子id
    @Column(name = "post_id")
    private Integer postId;
	
	    //
    @Column(name = "name")
    private String name;
	
	    //全景图预加载图片
    @Column(name = "preload_image")
    private String preloadImage;
	
	    //是否是全景图
    @Column(name = "fullshot")
    private Integer fullshot;
	
	    //1、站内内容 2、url 3、app 4、URL不跳转(全景图)
    @Column(name = "jump")
    private Integer jump;
	

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
	 * 设置：频道id,kf_channel.id
	 */
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取：频道id,kf_channel.id
	 */
	public Integer getChannelId() {
		return channelId;
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
	 * 设置：内容ID，可选值
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：内容ID，可选值
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：cid的类型，与kf_content的type相同（冗余字段）
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：cid的类型，与kf_content的type相同（冗余字段）
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：链接
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：图片URL
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片URL
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNumber() {
		return orderNumber;
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
	 * 设置：更新时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：编辑人
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：编辑人
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：
	 */
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	/**
	 * 获取：
	 */
	public Integer getIsPublish() {
		return isPublish;
	}
	/**
	 * 设置：圈子帖子id
	 */
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	/**
	 * 获取：圈子帖子id
	 */
	public Integer getPostId() {
		return postId;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：全景图预加载图片
	 */
	public void setPreloadImage(String preloadImage) {
		this.preloadImage = preloadImage;
	}
	/**
	 * 获取：全景图预加载图片
	 */
	public String getPreloadImage() {
		return preloadImage;
	}
	/**
	 * 设置：是否是全景图
	 */
	public void setFullshot(Integer fullshot) {
		this.fullshot = fullshot;
	}
	/**
	 * 获取：是否是全景图
	 */
	public Integer getFullshot() {
		return fullshot;
	}
	/**
	 * 设置：1、站内内容 2、url 3、app 4、URL不跳转(全景图)
	 */
	public void setJump(Integer jump) {
		this.jump = jump;
	}
	/**
	 * 获取：1、站内内容 2、url 3、app 4、URL不跳转(全景图)
	 */
	public Integer getJump() {
		return jump;
	}
}
