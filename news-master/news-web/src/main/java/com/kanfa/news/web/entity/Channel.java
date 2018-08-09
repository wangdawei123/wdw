package com.kanfa.news.web.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 频道表
 *
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-02-11 16:52:18
 */
public class Channel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	//频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
	private Integer category;

	//排序值，序号越大越往后
	private Integer orderNumber;

	//频道名
	private String name;

	//描述
	private String description;

	//是否是头条，1：是，0：否
	private Integer isHeadline;

	//是否是始终显示，不可隐藏的，1：是，0：否
	private Integer isFixed;

	//置顶
	private Integer isTop;

	//是否为默认频道，默认频道在第一次用户登录时会显示。1：是，0：否
	private Integer isDefault;

	//发布状态。1：发布，0：未发布
	private Integer isPublish;

	//发布时间
	private Date publishTime;

	//创建者
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


	/**
	 * 设置：id  频道id 22生活首页不可删除 不可编辑
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id  频道id 22生活首页不可删除 不可编辑
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}
	/**
	 * 获取：频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
	 */
	public Integer getCategory() {
		return category;
	}
	/**
	 * 设置：排序值，序号越大越往后
	 */
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：排序值，序号越大越往后
	 */
	public Integer getOrderNumber() {
		return orderNumber;
	}
	/**
	 * 设置：频道名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：频道名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：是否是头条，1：是，0：否
	 */
	public void setIsHeadline(Integer isHeadline) {
		this.isHeadline = isHeadline;
	}
	/**
	 * 获取：是否是头条，1：是，0：否
	 */
	public Integer getIsHeadline() {
		return isHeadline;
	}
	/**
	 * 设置：是否是始终显示，不可隐藏的，1：是，0：否
	 */
	public void setIsFixed(Integer isFixed) {
		this.isFixed = isFixed;
	}
	/**
	 * 获取：是否是始终显示，不可隐藏的，1：是，0：否
	 */
	public Integer getIsFixed() {
		return isFixed;
	}
	/**
	 * 设置：置顶
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	/**
	 * 获取：置顶
	 */
	public Integer getIsTop() {
		return isTop;
	}
	/**
	 * 设置：是否为默认频道，默认频道在第一次用户登录时会显示。1：是，0：否
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取：是否为默认频道，默认频道在第一次用户登录时会显示。1：是，0：否
	 */
	public Integer getIsDefault() {
		return isDefault;
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
	 * 设置：创建者
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建者
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：状态，1：正常，0：删除
	 */
	public void setChannelStatus(Integer channelStatus) {
		this.channelStatus = channelStatus;
	}
	/**
	 * 获取：状态，1：正常，0：删除
	 */
	public Integer getChannelStatus() {
		return channelStatus;
	}
	/**
	 * 设置：远端id
	 */
	public void setPromoteId(Integer promoteId) {
		this.promoteId = promoteId;
	}
	/**
	 * 获取：远端id
	 */
	public Integer getPromoteId() {
		return promoteId;
	}
	/**
	 * 设置：
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * 获取：
	 */
	public String getImage() {
		return image;
	}
	/**
	 * 设置：最后编辑
	 */
	public void setEditUid(String editUid) {
		this.editUid = editUid;
	}
	/**
	 * 获取：最后编辑
	 */
	public String getEditUid() {
		return editUid;
	}
	/**
	 * 设置：商铺展示
	 */
	public void setShopShow(String shopShow) {
		this.shopShow = shopShow;
	}
	/**
	 * 获取：商铺展示
	 */
	public String getShopShow() {
		return shopShow;
	}
	/**
	 * 设置：1攻略展示
	 */
	public void setStrategyShow(String strategyShow) {
		this.strategyShow = strategyShow;
	}
	/**
	 * 获取：1攻略展示
	 */
	public String getStrategyShow() {
		return strategyShow;
	}
	/**
	 * 设置：攻略名
	 */
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	/**
	 * 获取：攻略名
	 */
	public String getStrategyName() {
		return strategyName;
	}
	/**
	 * 设置：店铺名
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * 获取：店铺名
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * 设置：评论量初始值
	 */
	public void setCommentInitNum(Integer commentInitNum) {
		this.commentInitNum = commentInitNum;
	}
	/**
	 * 获取：评论量初始值
	 */
	public Integer getCommentInitNum() {
		return commentInitNum;
	}
	/**
	 * 设置：评论量显示阈值
	 */
	public void setCommentThreshold(Integer commentThreshold) {
		this.commentThreshold = commentThreshold;
	}
	/**
	 * 获取：评论量显示阈值
	 */
	public Integer getCommentThreshold() {
		return commentThreshold;
	}
	/**
	 * 设置：评论量显示规则
	 */
	public void setCommentShowRule(String commentShowRule) {
		this.commentShowRule = commentShowRule;
	}
	/**
	 * 获取：评论量显示规则
	 */
	public String getCommentShowRule() {
		return commentShowRule;
	}
	/**
	 * 设置：阅读量初始值
	 */
	public void setViewInitNum(Integer viewInitNum) {
		this.viewInitNum = viewInitNum;
	}
	/**
	 * 获取：阅读量初始值
	 */
	public Integer getViewInitNum() {
		return viewInitNum;
	}
	/**
	 * 设置：阅读量显示阈值
	 */
	public void setViewThreshold(Integer viewThreshold) {
		this.viewThreshold = viewThreshold;
	}
	/**
	 * 获取：阅读量显示阈值
	 */
	public Integer getViewThreshold() {
		return viewThreshold;
	}
	/**
	 * 设置：阅读量显示规则
	 */
	public void setViewShowRule(String viewShowRule) {
		this.viewShowRule = viewShowRule;
	}
	/**
	 * 获取：阅读量显示规则
	 */
	public String getViewShowRule() {
		return viewShowRule;
	}
	/**
	 * 设置：操作时间
	 */
	public void setSortTime(Integer sortTime) {
		this.sortTime = sortTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Integer getSortTime() {
		return sortTime;
	}
	/**
	 * 设置：热门标签显示阈值
	 */
	public void setHotThreshold(Integer hotThreshold) {
		this.hotThreshold = hotThreshold;
	}
	/**
	 * 获取：热门标签显示阈值
	 */
	public Integer getHotThreshold() {
		return hotThreshold;
	}
	/**
	 * 设置：置顶数量
	 */
	public void setTopNum(Integer topNum) {
		this.topNum = topNum;
	}
	/**
	 * 获取：置顶数量
	 */
	public Integer getTopNum() {
		return topNum;
	}
}
