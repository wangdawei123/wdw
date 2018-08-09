package com.kanfa.news.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 内容频道卡片类型绑定表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:28:32
 */
@Table(name = "xm_channel_content_card")
public class XmChannelContentCard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //频道id
    @Column(name = "chan_id")
    private Integer chanId;
	
	    //内容id
    @Column(name = "cid")
    private Integer cid;
	
	    //0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
    @Column(name = "card")
    private Integer card;
	
	    //图片地址
    @Column(name = "image")
    private String image;
	

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
	 * 设置：频道id
	 */
	public void setChanId(Integer chanId) {
		this.chanId = chanId;
	}
	/**
	 * 获取：频道id
	 */
	public Integer getChanId() {
		return chanId;
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
	 * 设置：0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
	 */
	public void setCard(Integer card) {
		this.card = card;
	}
	/**
	 * 获取：0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
	 */
	public Integer getCard() {
		return card;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImage() {
		return image;
	}
}
