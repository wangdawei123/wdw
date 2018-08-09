package com.kanfa.news.admin.vo.appuser;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 前台用户
 * 
 * @author wdw
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 16:29:52
 */
public class AppUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //
    private String nickname;
	
	    //手机号
    private String phone;
	
	    //头像
    private String image;
	
	    //简介
    private String introduction;
	
	    //性别，m：男，f：女，空字符：未知
    private String gender;
	
	    //收到的未读评论数
    private Integer commentNum;
	
	    //创建时间
    private Date createTime;
	
	    //状态，1：正常，0：删除
    private Integer isDelete;
	
	    //用户密码
    private String password;
	
	    //加盐加密
    private String saltEncrypt;
	
	    //1:屏蔽0:不屏蔽
    private Integer isBlock;
	
	    //修改人id
    private Integer updateId;

	    //
    private Date updateTime;

    private String createUser;

	//第三方平台openid，例如微信openid，微博uid
	private String qqId;
	private String weChatId;
	private String weiboId;

	private Integer platform;
	//用户操作
	private List<UserBindLogInfo> userBindLogInfoList;




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
	 * 设置：
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：头像
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：头像
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：简介
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * 获取：简介
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * 设置：性别，m：男，f：女，空字符：未知
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别，m：男，f：女，空字符：未知
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：收到的未读评论数
	 */
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	/**
	 * 获取：收到的未读评论数
	 */
	public Integer getCommentNum() {
		return commentNum;
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
	 * 设置：用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：用户密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：加盐加密
	 */
	public void setSaltEncrypt(String saltEncrypt) {
		this.saltEncrypt = saltEncrypt;
	}
	/**
	 * 获取：加盐加密
	 */
	public String getSaltEncrypt() {
		return saltEncrypt;
	}
	/**
	 * 设置：1:屏蔽0:不屏蔽
	 */
	public void setIsBlock(Integer isBlock) {
		this.isBlock = isBlock;
	}
	/**
	 * 获取：1:屏蔽0:不屏蔽
	 */
	public Integer getIsBlock() {
		return isBlock;
	}
	/**
	 * 设置：修改人id
	 */
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：修改人id
	 */
	public Integer getUpdateId() {
		return updateId;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getQqId() {
		return qqId;
	}

	public void setQqId(String qqId) {
		this.qqId = qqId;
	}

	public String getWeChatId() {
		return weChatId;
	}

	public void setWeChatId(String weChatId) {
		this.weChatId = weChatId;
	}

	public String getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(String weiboId) {
		this.weiboId = weiboId;
	}

	public List<UserBindLogInfo> getUserBindLogInfoList() {
		return userBindLogInfoList;
	}

	public void setUserBindLogInfoList(List<UserBindLogInfo> userBindLogInfoList) {
		this.userBindLogInfoList = userBindLogInfoList;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
}
