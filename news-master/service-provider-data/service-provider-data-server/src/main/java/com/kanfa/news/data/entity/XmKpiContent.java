package com.kanfa.news.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 内容绩效表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:28:32
 */
@Table(name = "xm_kpi_content")
public class XmKpiContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //内容id
    @Column(name = "cid")
    private Integer cid;
	
	    //员工id
    @Column(name = "uid")
    private Integer uid;
	
	    //员工姓名
    @Column(name = "name")
    private String name;
	
	    //类型:w文字p图片v视频
    @Column(name = "type")
    private String type;
	
	    //权重
    @Column(name = "weight")
    private BigDecimal weight;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //更新时间
    @Column(name = "update_time")
    private Date updateTime;
	
	    //1 通过  0 表示机动组且article_type=1时没达到600字。
    @Column(name = "is_num_pass")
    private Integer isNumPass;
	
	    //文章类型： 1 纯文本 2 文本加图片 3 图集 4 文本+图片+视频
    @Column(name = "article_type")
    private Integer articleType;
	
	    //岗位ID
    @Column(name = "job_id")
    private Integer jobId;
	
	    //部门id
    @Column(name = "dept_id")
    private Integer deptId;
	
	    //第一次审核或者上线时间 
    @Column(name = "check_time")
    private Date checkTime;
	
	    //0 未上线  1上线或者审核通过
    @Column(name = "stat")
    private Integer stat;
	
	    //若有视频   存此视频的id
    @Column(name = "vid")
    private Integer vid;
	

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
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：内容id
	 */
	public Integer getCid() {
		return cid;
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
	 * 设置：文章类型： 1 纯文本 2 文本加图片 3 图集 4 文本+图片+视频
	 */
	public void setArticleType(Integer articleType) {
		this.articleType = articleType;
	}
	/**
	 * 获取：文章类型： 1 纯文本 2 文本加图片 3 图集 4 文本+图片+视频
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
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门id
	 */
	public Integer getDeptId() {
		return deptId;
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
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：0 未上线  1上线或者审核通过
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：若有视频   存此视频的id
	 */
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	/**
	 * 获取：若有视频   存此视频的id
	 */
	public Integer getVid() {
		return vid;
	}
}
