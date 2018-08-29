package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 活动表
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-10 17:01:20
 */
@Table(name = "kf_activity")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //标题
    @Column(name = "title")
    private String title;
	
	    //banner图
    @Column(name = "image")
    private String image;
	
	    //开始时间
    @Column(name = "start_time")
    private Date startTime;
	
	    //结束时间
    @Column(name = "end_time")
    private Date endTime;
	
	    //活动类型 1:投票   3、志在蓝天  4、政法先锋
    @Column(name = "type")
    private Integer type;
	
	    //活动状态
    @Column(name = "status")
    private Integer status;
	
	    //访问量
    @Column(name = "page_view")
    private Integer pageView;
	
	    //是否删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //创建人
    @Column(name = "created_uid")
    private Integer createdUid;
	
	    //创建时间
    @Column(name = "created_time")
    private Date createdTime;
	
	    //活动?规则详情
    @Column(name = "rule")
    private String rule;

    @Column(name = "dayrule")
    private String dayRule;

    public String getDayRule() {
        return dayRule;
    }

    public void setDayRule(String dayRule) {
        this.dayRule = dayRule;
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
	 * 设置：banner图
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：banner图
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：活动类型 1:投票   3、志在蓝天  4、政法先锋
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：活动类型 1:投票   3、志在蓝天  4、政法先锋
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：活动状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：活动状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：访问量
	 */
	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}
	/**
	 * 获取：访问量
	 */
	public Integer getPageView() {
		return pageView;
	}
	/**
	 * 设置：是否删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreatedUid(Integer createdUid) {
		this.createdUid = createdUid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreatedUid() {
		return createdUid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * 设置：活动?规则详情
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	/**
	 * 获取：活动?规则详情
	 */
	public String getRule() {
		return rule;
	}
}
