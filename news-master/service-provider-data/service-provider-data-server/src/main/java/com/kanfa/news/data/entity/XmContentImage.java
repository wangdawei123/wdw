package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 内容附表-图集
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:28:32
 */
@Table(name = "xm_content_image")
public class XmContentImage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //内容ID
    @Id
    private Integer cid;
	
	    //是否使用导语来做图片介绍，1：是，0：否
    @Column(name = "desc_type")
    private Integer descType;
	
	    //图集的图片数目
    @Column(name = "num")
    private Integer num;
	
	    //状态，1：正常，0：删除
    @Column(name = "stat")
    private Integer stat;
	

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
	 * 设置：是否使用导语来做图片介绍，1：是，0：否
	 */
	public void setDescType(Integer descType) {
		this.descType = descType;
	}
	/**
	 * 获取：是否使用导语来做图片介绍，1：是，0：否
	 */
	public Integer getDescType() {
		return descType;
	}
	/**
	 * 设置：图集的图片数目
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：图集的图片数目
	 */
	public Integer getNum() {
		return num;
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
}
