package com.kanfa.news.info.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 频道表
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 18:45:00
 */
@Table(name = "kf_channel")
public class Channel  implements Serializable {
	private static final long serialVersionUID = 1L;

	//id  频道id 22生活首页不可删除 不可编辑
	@Id
	private Integer id;

	//频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
	@Column(name = "category")
	private Integer category;

	//排序值，序号越大越往后
	@Column(name = "order_number")
	private Integer orderNumber;

	//频道名
	@Column(name = "name")
	private String name;

	//描述
	@Column(name = "description")
	private String description;

	//是否是头条，1：是，0：否
	@Column(name = "is_headline")
	private Integer isHeadline;

	//是否是始终显示，不可隐藏的，1：是，0：否
	@Column(name = "is_fixed")
	private Integer isFixed;

	//置顶
	@Column(name = "is_top")
	private Integer isTop;

	//是否为默认频道，默认频道在第一次用户登录时会显示。1：是，0：否
	@Column(name = "is_default")
	private Integer isDefault;

	//发布状态。1：发布，0：未发布
	@Column(name = "is_publish")
	private Integer isPublish;

	//发布时间
	@Column(name = "publish_time")
	private Date publishTime;

	//创建者
	@Column(name = "create_uid")
	private Integer createUid;

	//创建时间
	@Column(name = "create_time")
	private Date createTime;

	//状态，1：正常，0：删除
	@Column(name = "channel_status")
	private Integer channelStatus;

	//远端id
	@Column(name = "promote_id")
	private Integer promoteId;

	//
	@Column(name = "url")
	private String url;

	//
	@Column(name = "image")
	private String image;

	//最后编辑
	@Column(name = "edit_uid")
	private String editUid;

	//商铺展示
	@Column(name = "shop_show")
	private String shopShow;

	//1攻略展示
	@Column(name = "strategy_show")
	private String strategyShow;

	//攻略名
	@Column(name = "strategy_name")
	private String strategyName;

	//店铺名
	@Column(name = "shop_name")
	private String shopName;

	//评论量初始值
	@Column(name = "comment_init_num")
	private Integer commentInitNum;

	//评论量显示阈值
	@Column(name = "comment_threshold")
	private Integer commentThreshold;

	//评论量显示规则
	@Column(name = "comment_show_rule")
	private String commentShowRule;

	//阅读量初始值
	@Column(name = "view_init_num")
	private Integer viewInitNum;

	//阅读量显示阈值
	@Column(name = "view_threshold")
	private Integer viewThreshold;

	//阅读量显示规则
	@Column(name = "view_show_rule")
	private String viewShowRule;

	//操作时间
	@Column(name = "sort_time")
	private Integer sortTime;

	//热门标签显示阈值
	@Column(name = "hot_threshold")
	private Integer hotThreshold;

	//置顶数量
	@Column(name = "top_num")
	private Integer topNum;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsHeadline() {
		return isHeadline;
	}

	public void setIsHeadline(Integer isHeadline) {
		this.isHeadline = isHeadline;
	}

	public Integer getIsFixed() {
		return isFixed;
	}

	public void setIsFixed(Integer isFixed) {
		this.isFixed = isFixed;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(Integer channelStatus) {
		this.channelStatus = channelStatus;
	}

	public Integer getPromoteId() {
		return promoteId;
	}

	public void setPromoteId(Integer promoteId) {
		this.promoteId = promoteId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEditUid() {
		return editUid;
	}

	public void setEditUid(String editUid) {
		this.editUid = editUid;
	}

	public String getShopShow() {
		return shopShow;
	}

	public void setShopShow(String shopShow) {
		this.shopShow = shopShow;
	}

	public String getStrategyShow() {
		return strategyShow;
	}

	public void setStrategyShow(String strategyShow) {
		this.strategyShow = strategyShow;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getCommentInitNum() {
		return commentInitNum;
	}

	public void setCommentInitNum(Integer commentInitNum) {
		this.commentInitNum = commentInitNum;
	}

	public Integer getCommentThreshold() {
		return commentThreshold;
	}

	public void setCommentThreshold(Integer commentThreshold) {
		this.commentThreshold = commentThreshold;
	}

	public String getCommentShowRule() {
		return commentShowRule;
	}

	public void setCommentShowRule(String commentShowRule) {
		this.commentShowRule = commentShowRule;
	}

	public Integer getViewInitNum() {
		return viewInitNum;
	}

	public void setViewInitNum(Integer viewInitNum) {
		this.viewInitNum = viewInitNum;
	}

	public Integer getViewThreshold() {
		return viewThreshold;
	}

	public void setViewThreshold(Integer viewThreshold) {
		this.viewThreshold = viewThreshold;
	}

	public String getViewShowRule() {
		return viewShowRule;
	}

	public void setViewShowRule(String viewShowRule) {
		this.viewShowRule = viewShowRule;
	}

	public Integer getSortTime() {
		return sortTime;
	}

	public void setSortTime(Integer sortTime) {
		this.sortTime = sortTime;
	}

	public Integer getHotThreshold() {
		return hotThreshold;
	}

	public void setHotThreshold(Integer hotThreshold) {
		this.hotThreshold = hotThreshold;
	}

	public Integer getTopNum() {
		return topNum;
	}

	public void setTopNum(Integer topNum) {
		this.topNum = topNum;
	}
}
