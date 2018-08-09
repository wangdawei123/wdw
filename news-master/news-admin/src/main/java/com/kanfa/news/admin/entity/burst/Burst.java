package com.kanfa.news.admin.entity.burst;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 新闻爆料表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
@Table(name = "kf_burst")
public class Burst implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //爆料人昵称
    @Column(name = "user_name")
    private String userName;
	
	    //用户id
    @Column(name = "user_id")
    private Integer userId;
	
	    //爆料类型(取自news_type表)
    @Column(name = "burst_type_id")
    private Integer burstTypeId;
	
	    //事发地点
    @Column(name = "address")
    private String address;
	
	    //经纬度坐标 lat,lon
    @Column(name = "point")
    private String point;
	
	    //新闻简述
    @Column(name = "remark")
    private String remark;
	
	    //详情描述
    @Column(name = "content")
    private String content;
	
	    //补充描述
    @Column(name = "add_desc")
    private String addDesc;
	
	    //该绑定的内容数量
    @Column(name = "bind_num")
    private Integer bindNum;
	
	    //新闻爆料状态(1:未受理;2:完成;3:拒绝)
    @Column(name = "status")
    private Integer status;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //更新时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //后台操作用户id
    @Column(name = "admin_id")
    private Integer adminId;
	

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
	 * 设置：爆料人昵称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：爆料人昵称
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：爆料类型(取自news_type表)
	 */
	public void setBurstTypeId(Integer burstTypeId) {
		this.burstTypeId = burstTypeId;
	}
	/**
	 * 获取：爆料类型(取自news_type表)
	 */
	public Integer getBurstTypeId() {
		return burstTypeId;
	}
	/**
	 * 设置：事发地点
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：事发地点
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：经纬度坐标 lat,lon
	 */
	public void setPoint(String point) {
		this.point = point;
	}
	/**
	 * 获取：经纬度坐标 lat,lon
	 */
	public String getPoint() {
		return point;
	}
	/**
	 * 设置：新闻简述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：新闻简述
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：详情描述
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：详情描述
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：补充描述
	 */
	public void setAddDesc(String addDesc) {
		this.addDesc = addDesc;
	}
	/**
	 * 获取：补充描述
	 */
	public String getAddDesc() {
		return addDesc;
	}
	/**
	 * 设置：该绑定的内容数量
	 */
	public void setBindNum(Integer bindNum) {
		this.bindNum = bindNum;
	}
	/**
	 * 获取：该绑定的内容数量
	 */
	public Integer getBindNum() {
		return bindNum;
	}
	/**
	 * 设置：新闻爆料状态(1:未受理;2:完成;3:拒绝)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：新闻爆料状态(1:未受理;2:完成;3:拒绝)
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：后台操作用户id
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	/**
	 * 获取：后台操作用户id
	 */
	public Integer getAdminId() {
		return adminId;
	}
}
