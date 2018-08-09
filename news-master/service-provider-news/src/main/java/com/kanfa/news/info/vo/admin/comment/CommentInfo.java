package com.kanfa.news.info.vo.admin.comment;

import java.io.Serializable;
import java.util.Date;


/**
 * 评论表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:01:59
 */
public class CommentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;

	//所属的内容ID
	private Integer cid;

	//0:文章 1：圈子评论
	private Integer type;

	//父级评论ID
	private Integer pid;

	//父评论的创建者UID
	private Integer pUid;

	//评论内容
	private String content;
	private String createDevid;

	//点赞数
	private Integer loves;

	//评论状态，1：正常，0：删除
	private Integer stat;

	//是否已审核，1：已审核，0：未审核
	private Integer ops;

	//是否包含敏感词，1：是，0：否
	private Integer sens;

	//创建时间
	private Date createTime;
	private String create_time;

	//创建者UID
	private Integer createUid;

	private Integer READ;

	//创建者ip
	private Integer createIp;

	//创建者名称，create_uid是否为空决定了这里是会员名还是游客名
	private String createUser;

	//评论内容id
	private Integer commentId;

	//评论内容id
	private Integer page;

	private Integer limit;
	private Integer pcount;
	//0-全部，1-今天，2-本周，3-本月
	private Integer timeType;

	//搜索类型
	private Integer keywordType;

	private String keyword;

	//liveId直播id
	private Integer liveid;

	private String ip;

	//文章标题
	private String title;

	//创建时间
	private Date beginTime;
	//创建时间
	private Date endTime;

	public Integer getREAD() {
		return READ;
	}

	public void setREAD(Integer READ) {
		this.READ = READ;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Integer getSens() {
		return sens;
	}

	public void setSens(Integer sens) {
		this.sens = sens;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getOps() {
		return ops;
	}

	public void setOps(Integer ops) {
		this.ops = ops;
	}

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCreateIp() {
		return createIp;
	}

	public void setCreateIp(Integer createIp) {
		this.createIp = createIp;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public Integer getKeywordType() {
		return keywordType;
	}

	public void setKeywordType(Integer keywordType) {
		this.keywordType = keywordType;
	}

	public Integer getLiveid() {
		return liveid;
	}

	public void setLiveid(Integer liveid) {
		this.liveid = liveid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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

	public Integer getpUid() {
		return pUid;
	}

	public void setpUid(Integer pUid) {
		this.pUid = pUid;
	}

	public String getCreateDevid() {
		return createDevid;
	}

	public void setCreateDevid(String createDevid) {
		this.createDevid = createDevid;
	}

	public Integer getPcount() {
		return pcount;
	}

	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}
}
