package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 评论敏感字符
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-28 13:30:45
 */
public class CommentBadwords implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    private Integer id;
	
	    //敏感字，用半角逗号隔开
    private String sensitiveWord;
	
	    //非法字符，用半角逗号隔开
    private String illegalWord;
	

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
	 * 设置：敏感字，用半角逗号隔开
	 */
	public void setSensitiveWord(String sensitiveWord) {
		this.sensitiveWord = sensitiveWord;
	}
	/**
	 * 获取：敏感字，用半角逗号隔开
	 */
	public String getSensitiveWord() {
		return sensitiveWord;
	}
	/**
	 * 设置：非法字符，用半角逗号隔开
	 */
	public void setIllegalWord(String illegalWord) {
		this.illegalWord = illegalWord;
	}
	/**
	 * 获取：非法字符，用半角逗号隔开
	 */
	public String getIllegalWord() {
		return illegalWord;
	}
}
