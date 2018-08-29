package com.kanfa.news.admin.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 内容帮顶频道中间表
 * 
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 17:06:36
 */
@Table(name = "kf_channel_content")
public class ChannelContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //频道ID
    @Column(name = "channel_id")
    private Integer channelId;
	
	    //内容ID
    @Column(name = "content_id")
    private Integer contentId;
	
	    //置顶，0：不置顶，数字：置顶时的时间戳
    @Column(name = "top")
    private Integer top;
	
	    //发布状态。1：发布，0：未发布
    @Column(name = "is_publish")
    private Integer isPublish;
	
	    //发布时间
    @Column(name = "publish_time")
    private Date publishTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "is_delete")
    private Integer isDelete;
	
	    //排序值
    @Column(name = "order_number")
    private Integer orderNumber;
	
	    //0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动
    @Column(name = "card_type")
    private Integer cardType;
	
	    //推荐权重
    @Column(name = "recommend_weight")
    private Integer recommendWeight;
	
	    //内容来源.1：content表，2：live表
    @Column(name = "from_type")
    private Integer fromType;
	
	    //审核状态，0未审核，1审核通过，2审核不通过
    @Column(name = "check_status")
    private Integer checkStatus;
	
	    //
    @Column(name = "type")
    private Integer type;
	
	    //操作时间(app分页用)
    @Column(name = "sort_time")
    private Integer sortTime;
	

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
	 * 设置：频道ID
	 */
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取：频道ID
	 */
	public Integer getChannelId() {
		return channelId;
	}
	/**
	 * 设置：内容ID
	 */
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	/**
	 * 获取：内容ID
	 */
	public Integer getContentId() {
		return contentId;
	}
	/**
	 * 设置：置顶，0：不置顶，数字：置顶时的时间戳
	 */
	public void setTop(Integer top) {
		this.top = top;
	}
	/**
	 * 获取：置顶，0：不置顶，数字：置顶时的时间戳
	 */
	public Integer getTop() {
		return top;
	}
	/**
	 * 设置：发布状态。1：发布，0：未发布
	 */
	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}
	/**
	 * 获取：发布状态。1：发布，0：未发布
	 */
	public Integer getIsPublish() {
		return isPublish;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPublishTime() {
		return publishTime;
	}
	/**
	 * 设置：状态，1：正常，0：删除
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	/**
	 * 设置：排序值
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：排序值
	 */
	public Integer getOrderNumber() {
		return orderNumber;
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
	 * 设置：推荐权重
	 */
	public void setRecommendWeight(Integer recommendWeight) {
		this.recommendWeight = recommendWeight;
	}
	/**
	 * 获取：推荐权重
	 */
	public Integer getRecommendWeight() {
		return recommendWeight;
	}
	/**
	 * 设置：内容来源.1：content表，2：live表
	 */
	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}
	/**
	 * 获取：内容来源.1：content表，2：live表
	 */
	public Integer getFromType() {
		return fromType;
	}
	/**
	 * 设置：审核状态，0未审核，1审核通过，2审核不通过
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取：审核状态，0未审核，1审核通过，2审核不通过
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}
	/**
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：操作时间(app分页用)
	 */
	public void setSortTime(Integer sortTime) {
		this.sortTime = sortTime;
	}
	/**
	 * 获取：操作时间(app分页用)
	 */
	public Integer getSortTime() {
		return sortTime;
	}
}
