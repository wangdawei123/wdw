package com.kanfa.news.app.vo.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 */
public class AppUser {
    private int id;
    private Date createTime;
    private String gender;
    private String image;
    private String nickname;
    private String isDelete;
    private String sessionid;

    //手机号
    private String phone;

    //简介
    private String introduction;

    //收到的未读评论数
    private Integer commentNum;

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

    private Map<String, Object> qq;
    private Map<String, Object> weibo;
    private Map<String, Object> weixin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Map<String, Object> getQq() {
        return qq;
    }

    public void setQq(Map<String, Object> qq) {
        this.qq = qq;
    }

    public Map<String, Object> getWeibo() {
        return weibo;
    }

    public void setWeibo(Map<String, Object> weibo) {
        this.weibo = weibo;
    }

    public Map<String, Object> getWeixin() {
        return weixin;
    }

    public void setWeixin(Map<String, Object> weixin) {
        this.weixin = weixin;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSaltEncrypt() {
        return saltEncrypt;
    }

    public void setSaltEncrypt(String saltEncrypt) {
        this.saltEncrypt = saltEncrypt;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
