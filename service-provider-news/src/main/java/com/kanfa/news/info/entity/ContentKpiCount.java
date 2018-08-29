package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Jiayunwei
 * @email jiayunwei@kanfanews.com
 * @date 2018-08-01 15:44:25
 */
@Table(name = "kf_content_kpi_count")
public class ContentKpiCount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //主键
    @Id
    private Integer id;
	
	    //内容ID
    @Column(name = "cid")
    private Integer cid;
	
	    //关联视频ID
    @Column(name = "did")
    private Integer did;
	
	    //标题
    @Column(name = "title")
    private String title;
	
	    //第一次审核日期
    @Column(name = "create_date")
    private Integer createDate;
	
	    //创建人ID
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建人名字
    @Column(name = "create_name")
    private String createName;
	
	    //第一次审核时间
    @Column(name = "first_check_time")
    private Date firstCheckTime;
	
	    //类型，2文章，3图集，4视频，22直播
    @Column(name = "type")
    private Integer type;
	
	    //来源
    @Column(name = "source_type")
    private Integer sourceType;
	
	    //内容类型，纯文，文图，图集，视频，直播
    @Column(name = "content_type")
    private String contentType;
	
	    //相关工作人员及工作，json数据
    @Column(name = "works")
    private String works;
	
	    //图集图片张数
    @Column(name = "img_num")
    private Integer imgNum;
	
	    //截止第一次审核次日24点app内PV
    @Column(name = "app_pv")
    private Integer appPv;
	
	    //截止第一次审核次日24点app内uv
    @Column(name = "app_uv")
    private Integer appUv;
	
	    //截止第一次审核次日24点总PV
    @Column(name = "all_pv")
    private Integer allPv;
	
	    //截止第一次审核次日24点总UV
    @Column(name = "all_uv")
    private Integer allUv;
	
	    //工种数
    @Column(name = "worktype_num")
    private Integer worktypeNum;
	
	    //总工作人数
    @Column(name = "workuser_num")
    private Integer workuserNum;
	

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：内容ID
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：内容ID
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：关联视频ID
	 */
	public void setDid(Integer did) {
		this.did = did;
	}
	/**
	 * 获取：关联视频ID
	 */
	public Integer getDid() {
		return did;
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
	 * 设置：第一次审核日期
	 */
	public void setCreateDate(Integer createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：第一次审核日期
	 */
	public Integer getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：创建人ID
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建人ID
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：创建人名字
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	/**
	 * 获取：创建人名字
	 */
	public String getCreateName() {
		return createName;
	}
	/**
	 * 设置：第一次审核时间
	 */
	public void setFirstCheckTime(Date firstCheckTime) {
		this.firstCheckTime = firstCheckTime;
	}
	/**
	 * 获取：第一次审核时间
	 */
	public Date getFirstCheckTime() {
		return firstCheckTime;
	}
	/**
	 * 设置：类型，2文章，3图集，4视频，22直播
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型，2文章，3图集，4视频，22直播
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：来源
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * 获取：来源
	 */
	public Integer getSourceType() {
		return sourceType;
	}
	/**
	 * 设置：内容类型，纯文，文图，图集，视频，直播
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * 获取：内容类型，纯文，文图，图集，视频，直播
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * 设置：相关工作人员及工作，json数据
	 */
	public void setWorks(String works) {
		this.works = works;
	}
	/**
	 * 获取：相关工作人员及工作，json数据
	 */
	public String getWorks() {
		return works;
	}
	/**
	 * 设置：图集图片张数
	 */
	public void setImgNum(Integer imgNum) {
		this.imgNum = imgNum;
	}
	/**
	 * 获取：图集图片张数
	 */
	public Integer getImgNum() {
		return imgNum;
	}
	/**
	 * 设置：截止第一次审核次日24点app内PV
	 */
	public void setAppPv(Integer appPv) {
		this.appPv = appPv;
	}
	/**
	 * 获取：截止第一次审核次日24点app内PV
	 */
	public Integer getAppPv() {
		return appPv;
	}
	/**
	 * 设置：截止第一次审核次日24点app内uv
	 */
	public void setAppUv(Integer appUv) {
		this.appUv = appUv;
	}
	/**
	 * 获取：截止第一次审核次日24点app内uv
	 */
	public Integer getAppUv() {
		return appUv;
	}
	/**
	 * 设置：截止第一次审核次日24点总PV
	 */
	public void setAllPv(Integer allPv) {
		this.allPv = allPv;
	}
	/**
	 * 获取：截止第一次审核次日24点总PV
	 */
	public Integer getAllPv() {
		return allPv;
	}
	/**
	 * 设置：截止第一次审核次日24点总UV
	 */
	public void setAllUv(Integer allUv) {
		this.allUv = allUv;
	}
	/**
	 * 获取：截止第一次审核次日24点总UV
	 */
	public Integer getAllUv() {
		return allUv;
	}
	/**
	 * 设置：工种数
	 */
	public void setWorktypeNum(Integer worktypeNum) {
		this.worktypeNum = worktypeNum;
	}
	/**
	 * 获取：工种数
	 */
	public Integer getWorktypeNum() {
		return worktypeNum;
	}
	/**
	 * 设置：总工作人数
	 */
	public void setWorkuserNum(Integer workuserNum) {
		this.workuserNum = workuserNum;
	}
	/**
	 * 获取：总工作人数
	 */
	public Integer getWorkuserNum() {
		return workuserNum;
	}
}
