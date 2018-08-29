package com.kanfa.news.search.client.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 内容绩效表
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 17:42:20
 */
public class KpiContentInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //内容id
    private Integer contentId;
	
	    //员工id
    private Integer uid;
	
	    //员工姓名
    private String name;
	
	    //类型:w文字p图片v视频
    private String type;
	
	    //权重
    private BigDecimal weight;
	
	    //创建时间
    private Date createTime;
	
	    //更新时间
    private Date updateTime;
	
	    //1 通过  0 表示机动组且article_type=1时没达到600字。
    private Integer isNumPass;
	
	    //文章类型： 1 纯文本 2 文本加图片 3 图集 4 文本+图片+视频 5 文字+视频　６资讯列表里新增视频
    private Integer articleType;
	
	    //岗位ID
    private Integer jobId;
	
	    //部门id
    private Integer departmentId;
	
	    //第一次审核或者上线时间 
    private Date checkTime;
	
	    //0 未上线  1上线或者审核通过
    private Integer isPublish;
	
	    //若有视频   存此视频的id
    private Integer videoId;
	

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
	 * 设置：内容id
	 */
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：内容id
	 */
	public Integer getContentId() {
		return contentId;
	}
	/**
	 * 设置：员工id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：员工id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：员工姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：员工姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：类型:w文字p图片v视频
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型:w文字p图片v视频
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：权重
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	/**
	 * 获取：权重
	 */
	public BigDecimal getWeight() {
		return weight;
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
	 * 设置：1 通过  0 表示机动组且article_type=1时没达到600字。
	 */
	public void setIsNumPass(Integer isNumPass) {
		this.isNumPass = isNumPass;
	}
	/**
	 * 获取：1 通过  0 表示机动组且article_type=1时没达到600字。
	 */
	public Integer getIsNumPass() {
		return isNumPass;
	}
	/**
	 * 设置：文章类型： 1 纯文本 2 文本加图片 3 图集 4 文本+图片+视频 5 文字+视频　６资讯列表里新增视频
	 */
	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}
	/**
	 * 获取：文章类型： 1 纯文本 2 文本加图片 3 图集 4 文本+图片+视频 5 文字+视频　６资讯列表里新增视频
	 */
	public Integer getArticleType() {
		return articleType;
	}
	/**
	 * 设置：岗位ID
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	/**
	 * 获取：岗位ID
	 */
	public Integer getJobId() {
		return jobId;
	}
	/**
	 * 设置：部门id
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取：部门id
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置：第一次审核或者上线时间 
	 */
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * 获取：第一次审核或者上线时间 
	 */
	public Date getCheckTime() {
		return checkTime;
	}
	/**
	 * 设置：0 未上线  1上线或者审核通过
	 */
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	/**
	 * 获取：0 未上线  1上线或者审核通过
	 */
	public Integer getIsPublish() {
		return isPublish;
	}
	/**
	 * 设置：若有视频   存此视频的id
	 */
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	/**
	 * 获取：若有视频   存此视频的id
	 */
	public Integer getVideoId() {
		return videoId;
	}
}
