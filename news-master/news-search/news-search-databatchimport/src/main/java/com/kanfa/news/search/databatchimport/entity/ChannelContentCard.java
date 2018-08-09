package com.kanfa.news.search.databatchimport.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 内容频道卡片类型绑定表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-23 20:01:17
 */
@Table(name = "kf_channel_content_card")
public class ChannelContentCard implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //频道id
    @Column(name = "channel_id")
    private Integer channelId;
	
	    //内容id
    @Column(name = "content_id")
    private Integer contentId;
	
	    //0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
    @Column(name = "card_type")
    private Integer cardType;
	
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
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取：频道id
	 */
	public Integer getChannelId() {
		return channelId;
	}
	/**
	 * 设置：内容id
	 */
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：内容id
	 */
	public Integer getContentId() {
		return contentId;
	}
	/**
	 * 设置：0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
	 */
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	/**
	 * 获取：0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
	 */
	public Integer getCardType() {
		return cardType;
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
