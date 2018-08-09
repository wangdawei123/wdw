package com.kanfa.news.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 频道与内容关系表
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-07 11:14:06
 */
@Table(name = "xm_channel_content")
public class XmChannelContent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //频道ID
    @Column(name = "chan_id")
    private Integer chanId;
	
	    //内容ID
    @Column(name = "cid")
    private Integer cid;
	
	    //置顶，0：不置顶，数字：置顶时的时间戳
    @Column(name = "top")
    private Integer top;
	
	    //发布状态。1：发布，0：未发布
    @Column(name = "pub")
    private Integer pub;
	
	    //发布时间
    @Column(name = "pub_time")
    private Date pubTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "stat")
    private Integer stat;
	
	    //排序值
    @Column(name = "order")
    private Integer order;
	
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
    @Column(name = "check")
    private Integer check;
	
	    //操作时间(app分页用)
    @Column(name = "sort_time")
    private Integer sortTime;
	
	    //
    @Column(name = "type")
    private Integer type;
	
	    //
    @Column(name = "cate")
    private Integer cate;
	
	    //审核人
    @Column(name = "check_uid")
    private Integer checkUid;
	

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
	public void setChanId(Integer chanId) {
		this.chanId = chanId;
	}
	/**
	 * 获取：频道ID
	 */
	public Integer getChanId() {
		return chanId;
	}
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
	public void setPub(Integer pub) {
		this.pub = pub;
	}
	/**
	 * 获取：发布状态。1：发布，0：未发布
	 */
	public Integer getPub() {
		return pub;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getPubTime() {
		return pubTime;
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
	public void setCheck(Integer check) {
		this.check = check;
	}
	/**
	 * 获取：审核状态，0未审核，1审核通过，2审核不通过
	 */
	public Integer getCheck() {
		return check;
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
	 * 设置：
	 */
	public void setCate(Integer cate) {
		this.cate = cate;
	}
	/**
	 * 获取：
	 */
	public Integer getCate() {
		return cate;
	}
	/**
	 * 设置：审核人
	 */
	public void setCheckUid(Integer checkUid) {
		this.checkUid = checkUid;
	}
	/**
	 * 获取：审核人
	 */
	public Integer getCheckUid() {
		return checkUid;
	}
}
