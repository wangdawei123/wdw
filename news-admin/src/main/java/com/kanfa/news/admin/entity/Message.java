package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 后台发送消息
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-09 19:43:53
 */
@Table(name = "kf_message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //目标类型 1 咨询 2 专题 3 生活 4 论坛 5直播
    @Column(name = "target_type")
    private Integer targetType;
	
	    //标题
    @Column(name = "title")
    private String title;
	
	    //消息内容
    @Column(name = "content")
    private String content;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //安卓推送错误码，0：成功
    @Column(name = "ad_code")
    private String adCode;
	
	    //IOS推送错误码，0：成功
    @Column(name = "ios_code")
    private String iosCode;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //错误消息 目前只有极光有
    @Column(name = "errmsg")
    private String errmsg;
	
	    //状态，1：友盟，2：极光
    @Column(name = "type")
    private Integer type;
	

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
	 * 设置：目标类型 1 咨询 2 专题 3 生活 4 论坛 5直播
	 */
	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}
	/**
	 * 获取：目标类型 1 咨询 2 专题 3 生活 4 论坛 5直播
	 */
	public Integer getTargetType() {
		return targetType;
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
	 * 设置：消息内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：消息内容
	 */
	public String getContent() {
		return content;
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
	 * 设置：安卓推送错误码，0：成功
	 */
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
	/**
	 * 获取：安卓推送错误码，0：成功
	 */
	public String getAdCode() {
		return adCode;
	}
	/**
	 * 设置：IOS推送错误码，0：成功
	 */
	public void setIosCode(String iosCode) {
		this.iosCode = iosCode;
	}
	/**
	 * 获取：IOS推送错误码，0：成功
	 */
	public String getIosCode() {
		return iosCode;
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
	 * 设置：错误消息 目前只有极光有
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	/**
	 * 获取：错误消息 目前只有极光有
	 */
	public String getErrmsg() {
		return errmsg;
	}
	/**
	 * 设置：状态，1：友盟，2：极光
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：状态，1：友盟，2：极光
	 */
	public Integer getType() {
		return type;
	}
}
