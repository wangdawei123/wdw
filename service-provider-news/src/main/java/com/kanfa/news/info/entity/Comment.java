package com.kanfa.news.info.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 评论表
 *
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-06-15 10:34:25
 */
@Table(name = "kf_comment")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	@Id
	private Integer id;

	//所属的内容ID
	@Column(name = "cid")
	private Integer cid;

	//0:文章 1：圈子评论
	@Column(name = "type")
	private Integer type;

	//父级评论ID
	@Column(name = "pid")
	private Integer pid;

	//父评论的创建者UID
	@Column(name = "p_uid")
	private Integer pUid;

	//评论内容
	@Column(name = "content")
	private String content;

	//点赞数
	@Column(name = "loves")
	private Integer loves;

	//创建时间
	@Column(name = "create_time")
	private Date createTime;

	//创建者UID
	@Column(name = "create_uid")
	private Integer createUid;

	//创建者名称，create_uid是否为空决定了这里是会员名还是游客名
	@Column(name = "create_user")
	private String createUser;

	//设备号，游客必填
	@Column(name = "create_devid")
	private String createDevid;

	//创建者IP
	@Column(name = "create_ip")
	private Integer createIp;

	//是否包含敏感词，1：是，0：否
	@Column(name = "sens")
	private Integer sens;

	//是否已审核，1：已审核，0：未审核
	@Column(name = "ops")
	private Integer ops;

	//阅读状态,0:未读,1:已读
	@Column(name = "read")
	private Integer read;

	//状态，1：正常，0：删除
	@Column(name = "stat")
	private Integer stat;

	//评论内容id
	@Column(name = "comment_id")
	private Integer commentId;

	public Integer getpUid() {
		return pUid;
	}

	public void setpUid(Integer pUid) {
		this.pUid = pUid;
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
	 * 设置：所属的内容ID
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：所属的内容ID
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：0:文章 1：圈子评论
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：0:文章 1：圈子评论
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：父级评论ID
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：父级评论ID
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * 设置：父评论的创建者UID
	 */
	public void setPUid(Integer pUid) {
		this.pUid = pUid;
	}
	/**
	 * 获取：父评论的创建者UID
	 */
	public Integer getPUid() {
		return pUid;
	}
	/**
	 * 设置：评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评论内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：点赞数
	 */
	public void setLoves(Integer loves) {
		this.loves = loves;
	}
	/**
	 * 获取：点赞数
	 */
	public Integer getLoves() {
		return loves;
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
	 * 设置：创建者UID
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建者UID
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：创建者名称，create_uid是否为空决定了这里是会员名还是游客名
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建者名称，create_uid是否为空决定了这里是会员名还是游客名
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：设备号，游客必填
	 */
	public void setCreateDevid(String createDevid) {
		this.createDevid = createDevid;
	}
	/**
	 * 获取：设备号，游客必填
	 */
	public String getCreateDevid() {
		return createDevid;
	}
	/**
	 * 设置：创建者IP
	 */
	public void setCreateIp(Integer createIp) {
		this.createIp = createIp;
	}
	/**
	 * 获取：创建者IP
	 */
	public Integer getCreateIp() {
		return createIp;
	}
	/**
	 * 设置：是否包含敏感词，1：是，0：否
	 */
	public void setSens(Integer sens) {
		this.sens = sens;
	}
	/**
	 * 获取：是否包含敏感词，1：是，0：否
	 */
	public Integer getSens() {
		return sens;
	}
	/**
	 * 设置：是否已审核，1：已审核，0：未审核
	 */
	public void setOps(Integer ops) {
		this.ops = ops;
	}
	/**
	 * 获取：是否已审核，1：已审核，0：未审核
	 */
	public Integer getOps() {
		return ops;
	}
	/**
	 * 设置：阅读状态,0:未读,1:已读
	 */
	public void setRead(Integer read) {
		this.read = read;
	}
	/**
	 * 获取：阅读状态,0:未读,1:已读
	 */
	public Integer getRead() {
		return read;
	}
	/**
	 * 设置：状态，1：正常，0：删除
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getStat() {
		return stat;
	}
	/**
	 * 设置：评论内容id
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	/**
	 * 获取：评论内容id
	 */
	public Integer getCommentId() {
		return commentId;
	}

}
