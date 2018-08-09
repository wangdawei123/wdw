package com.kanfa.news.activity.vo.info;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-23 14:35:04
 */
public class ActivityAwardResultInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //名称
    private String title;
	
	    //
    private Date startTime;
	
	    //
    private Date endTime;
	
	    //是否需要登录  1需要  0 不需要
    private Integer needLogin;
	
	    //状态  1、开启  0关闭
    private Integer status;
	
	    //活动图标
    private String icon;
	
	    //浮标
    private String buoy;
	
	    //
    private String image;
	
	    //
    private Integer createdUid;
	
	    //
    private Date createTime;
	
	    //规则
    private String rule;
	
	    //是否删除  1删除  0 正常
    private Integer isDelete;

    private Integer pid ;
    private Date prizeTime ;
    private Integer prizeId ;
    private Integer uid ;
    private Integer prizeType ;
    private String prizeName ;


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}

	public Date getPrizeTime() {
		return prizeTime;
	}

	public void setPrizeTime(Date prizeTime) {
		this.prizeTime = prizeTime;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

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
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：是否需要登录  1需要  0 不需要
	 */
	public void setNeedLogin(Integer needLogin) {
		this.needLogin = needLogin;
	}
	/**
	 * 获取：是否需要登录  1需要  0 不需要
	 */
	public Integer getNeedLogin() {
		return needLogin;
	}
	/**
	 * 设置：状态  1、开启  0关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态  1、开启  0关闭
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：活动图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：活动图标
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：浮标
	 */
	public void setBuoy(String buoy) {
		this.buoy = buoy;
	}
	/**
	 * 获取：浮标
	 */
	public String getBuoy() {
		return buoy;
	}
	/**
	 * 设置：
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：
	 */
	public void setCreatedUid(Integer createdUid) {
		this.createdUid = createdUid;
	}
	/**
	 * 获取：
	 */
	public Integer getCreatedUid() {
		return createdUid;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：规则
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	/**
	 * 获取：规则
	 */
	public String getRule() {
		return rule;
	}
	/**
	 * 设置：是否删除  1删除  0 正常
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否删除  1删除  0 正常
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
}
