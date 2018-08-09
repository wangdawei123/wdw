package com.kanfa.news.info.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * 
 * @author ffy
 * @email fengfangyan@kanfanews.com
 * @date 2018-03-14 11:46:04
 */
@Table(name = "kf_kpi_count")
public class KpiCount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //员工id
    @Column(name = "uid")
    private Integer uid;
	
	    //内容的数据类型，2:cid 4:vid  9:live_id
    @Column(name = "type")
    private Integer type;
	
	    //对应的type
    @Column(name = "type_id")
    private Integer typeId;
	
	    //员工姓名
    @Column(name = "name")
    private String name;
	
	    //文章类型： 1 纯文本 2 文字加图片 3 文字+视频4 文本+图片+视频 5 图集６资讯列表里新增视频
    @Column(name = "article_type")
    private Integer articleType;
	
	    //文章类型： 16 纯文本 17 图片 18视频 19文字＋图片 20 文字＋视频 21图片＋视频22 文＋图＋视频23图集。对应配置表
    @Column(name = "work_type")
    private Integer workType;
	
	    //权重
    @Column(name = "weight")
    private BigDecimal weight;
	
	    //第一次审核或者上线时间 .
    @Column(name = "first_check_time")
    private Date firstCheckTime;
	
	    //第一次审核通过的操作人id
    @Column(name = "first_check_id")
    private Integer firstCheckId;
	
	    //1 通过  0 表示机动组且article_type=1时没达到600字。
    @Column(name = "is_num_pass")
    private Integer isNumPass;
	
	    //岗位ID
    @Column(name = "job_id")
    private Integer jobId;
	
	    //部门id
    @Column(name = "department_id")
    private Integer departmentId;
	
	    //0 正常　１特殊稿件
    @Column(name = "is_special")
    private Integer isSpecial;
	
	    //pv达标时间
    @Column(name = "pv_finish_time")
    private Date pvFinishTime;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //更新时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //备注
    @Column(name = "remarks")
    private String remarks;
	

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
	 * 设置：内容的数据类型，2:cid 4:vid  9:live_id
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：内容的数据类型，2:cid 4:vid  9:live_id
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：对应的type
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：对应的type
	 */
	public Integer getTypeId() {
		return typeId;
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
	 * 设置：文章类型： 1 纯文本 2 文字加图片 3 文字+视频4 文本+图片+视频 5 图集６资讯列表里新增视频
	 */
	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}
	/**
	 * 获取：文章类型： 1 纯文本 2 文字加图片 3 文字+视频4 文本+图片+视频 5 图集６资讯列表里新增视频
	 */
	public Integer getArticleType() {
		return articleType;
	}
	/**
	 * 设置：文章类型： 16 纯文本 17 图片 18视频 19文字＋图片 20 文字＋视频 21图片＋视频22 文＋图＋视频23图集。对应配置表
	 */
	public void setWorkType(Integer workType) {
		this.workType = workType;
	}
	/**
	 * 获取：文章类型： 16 纯文本 17 图片 18视频 19文字＋图片 20 文字＋视频 21图片＋视频22 文＋图＋视频23图集。对应配置表
	 */
	public Integer getWorkType() {
		return workType;
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
	 * 设置：第一次审核或者上线时间 .
	 */
	public void setFirstCheckTime(Date firstCheckTime) {
		this.firstCheckTime = firstCheckTime;
	}
	/**
	 * 获取：第一次审核或者上线时间 .
	 */
	public Date getFirstCheckTime() {
		return firstCheckTime;
	}
	/**
	 * 设置：第一次审核通过的操作人id
	 */
	public void setFirstCheckId(Integer firstCheckId) {
		this.firstCheckId = firstCheckId;
	}
	/**
	 * 获取：第一次审核通过的操作人id
	 */
	public Integer getFirstCheckId() {
		return firstCheckId;
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
	 * 设置：岗位ID
	 */
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	/**
	 * 获取：岗位ID
	 * @param jobId
	 */
	public Integer getJobId(Integer jobId) {
		return this.jobId;
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
	 * 设置：0 正常　１特殊稿件
	 */
	public void setIsSpecial(Integer isSpecial) {
		this.isSpecial = isSpecial;
	}
	/**
	 * 获取：0 正常　１特殊稿件
	 */
	public Integer getIsSpecial() {
		return isSpecial;
	}
	/**
	 * 设置：pv达标时间
	 */
	public void setPvFinishTime(Date pvFinishTime) {
		this.pvFinishTime = pvFinishTime;
	}
	/**
	 * 获取：pv达标时间
	 */
	public Date getPvFinishTime() {
		return pvFinishTime;
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
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}
