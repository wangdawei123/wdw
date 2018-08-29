package com.kanfa.news.info.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 用户对内容的收藏
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:01:31
 */
@Table(name = "kf_fav")
public class Fav implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //所属的内容ID
    @Id
    private Integer cid;
	
	    //创建者UID
    @Column(name = "uid")
    private Integer uid;
	
	    //内容的类型（冗余字段）
    @Column(name = "type")
    private Integer type;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	

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
	 * 设置：创建者UID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：创建者UID
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：内容的类型（冗余字段）
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：内容的类型（冗余字段）
	 */
	public Integer getType() {
		return type;
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
}
