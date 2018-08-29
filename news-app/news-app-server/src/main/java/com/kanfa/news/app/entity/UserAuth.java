package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 前台用户第三方授权信息
 * 
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 16:29:52
 */
@Table(name = "kf_user_auth")
public class UserAuth implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //用户id
    @Column(name = "uid")
    private Integer uid;
	
	    //第三方平台代码，1：微博；2：微信；3：QQ
    @Column(name = "platform")
    private Integer platform;
	
	    //第三方平台openid，例如微信openid，微博uid
    @Column(name = "openid")
    private String openid;
	
	    //unionid
    @Column(name = "unionid")
    private String unionid;
	
	    //access_token
    @Column(name = "access_token")
    private String accessToken;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //1 正常 0不正常
    @Column(name = "status")
    private Integer status;
	
	    //公众号openid
    @Column(name = "gzopenid")
    private String gzopenid;
	

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
	 * 设置：用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：第三方平台代码，1：微博；2：微信；3：QQ
	 */
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	/**
	 * 获取：第三方平台代码，1：微博；2：微信；3：QQ
	 */
	public Integer getPlatform() {
		return platform;
	}
	/**
	 * 设置：第三方平台openid，例如微信openid，微博uid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：第三方平台openid，例如微信openid，微博uid
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：unionid
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	/**
	 * 获取：unionid
	 */
	public String getUnionid() {
		return unionid;
	}
	/**
	 * 设置：access_token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * 获取：access_token
	 */
	public String getAccessToken() {
		return accessToken;
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
	 * 设置：1 正常 0不正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1 正常 0不正常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：公众号openid
	 */
	public void setGzopenid(String gzopenid) {
		this.gzopenid = gzopenid;
	}
	/**
	 * 获取：公众号openid
	 */
	public String getGzopenid() {
		return gzopenid;
	}
}
