package com.kanfa.news.web.vo.comment;

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
public class CommentInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer cid;
	private Integer page;
	private Integer pcount;
	private Integer uid;
	private Integer pUid;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPcount() {
		return pcount;
	}

	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getpUid() {
		return pUid;
	}

	public void setpUid(Integer pUid) {
		this.pUid = pUid;
	}
}
