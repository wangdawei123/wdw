package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 关联内容表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:25:32
 */
@Table(name = "xm_content_broadcast_bind")
public class XmContentBroadcastBind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //xm_content.id
    @Column(name = "cid")
    private Integer cid;
	
	    //xm_content.id
    @Column(name = "bind_id")
    private Integer bindId;
	
	    //排序值
    @Column(name = "order")
    private Integer order;
	
	    //状态是否删除(0:正常;1:删除)
    @Column(name = "stat")
    private Integer stat;
	

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
	 * 设置：xm_content.id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：xm_content.id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：xm_content.id
	 */
	public void setBindId(Integer bindId) {
		this.bindId = bindId;
	}
	/**
	 * 获取：xm_content.id
	 */
	public Integer getBindId() {
		return bindId;
	}
	/**
	 * 设置：排序值
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：排序值
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * 设置：状态是否删除(0:正常;1:删除)
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态是否删除(0:正常;1:删除)
	 */
	public Integer getStat() {
		return stat;
	}
}
