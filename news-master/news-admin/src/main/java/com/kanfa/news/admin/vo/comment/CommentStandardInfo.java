package com.kanfa.news.admin.vo.comment;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-23 20:01:17
 */
public class CommentStandardInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //评论内容
    private String content;

	private Date createTime;

	//创建者UID
	private Integer createUid;

	//频道id
	private String channels;

	private String[] channelArray;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
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

	public String getChannels() {
		return channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	public String[] getChannelArray() {
		return channelArray;
	}

	public void setChannelArray(String[] channelArray) {
		this.channelArray = channelArray;
	}
}
