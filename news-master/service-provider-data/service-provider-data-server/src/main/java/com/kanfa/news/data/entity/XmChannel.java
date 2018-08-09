package com.kanfa.news.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 频道表
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:36:00
 */
@Table(name = "xm_channel")
public class XmChannel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //id  频道id 22生活首页不可删除 不可编辑
    @Id
    private Integer id;
	
	    //频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
    @Column(name = "cate")
    private Integer cate;
	
	    //排序值，序号越大越往后
    @Column(name = "order")
    private Integer order;
	
	    //频道名
    @Column(name = "name")
    private String name;
	
	    //描述
    @Column(name = "desc")
    private String desc;
	
	    //是否是头条，1：是，0：否
    @Column(name = "headline")
    private Integer headline;
	
	    //是否是始终显示，不可隐藏的，1：是，0：否
    @Column(name = "fixed")
    private Integer fixed;
	
	    //置顶
    @Column(name = "top")
    private Integer top;
	
	    //是否为默认频道，默认频道在第一次用户登录时会显示。1：是，0：否
//    @Column(name = "default")
//    private Integer default;
	
	    //发布状态。1：发布，0：未发布
    @Column(name = "pub")
    private Integer pub;
	
	    //发布时间
    @Column(name = "pub_time")
    private Date pubTime;
	
	    //创建者
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //状态，1：正常，0：删除
    @Column(name = "stat")
    private Integer stat;
	
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
    @Column(name = "type")
    private String type;
	
	    //1攻略展示
    @Column(name = "types")
    private String types;
	
	    //攻略名
    @Column(name = "s_name")
    private String sName;
	
	    //店铺名
    @Column(name = "m_name")
    private String mName;
	
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
	
	    //热门标签显示阈值
    @Column(name = "hot_threshold")
    private Integer hotThreshold;
	
	    //置顶数量
    @Column(name = "top_num")
    private Integer topNum;
	
	    //视频频道是否展示关联内容(0:不展示;1:展示)
    @Column(name = "is_relation")
    private Integer isRelation;
	

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
	public void setCate(Integer cate) {
		this.cate = cate;
	}
	/**
	 * 获取：频道，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
	 */
	public Integer getCate() {
		return cate;
	}
	/**
	 * 设置：排序值，序号越大越往后
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * 获取：排序值，序号越大越往后
	 */
	public Integer getOrder() {
		return order;
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
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：描述
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：是否是头条，1：是，0：否
	 */
	public void setHeadline(Integer headline) {
		this.headline = headline;
	}
	/**
	 * 获取：是否是头条，1：是，0：否
	 */
	public Integer getHeadline() {
		return headline;
	}
	/**
	 * 设置：是否是始终显示，不可隐藏的，1：是，0：否
	 */
	public void setFixed(Integer fixed) {
		this.fixed = fixed;
	}
	/**
	 * 获取：是否是始终显示，不可隐藏的，1：是，0：否
	 */
	public Integer getFixed() {
		return fixed;
	}
	/**
	 * 设置：置顶
	 */
	public void setTop(Integer top) {
		this.top = top;
	}
	/**
	 * 获取：置顶
	 */
	public Integer getTop() {
		return top;
	}
	/**
	 * 设置：是否为默认频道，默认频道在第一次用户登录时会显示。1：是，0：否

	public void setDefault(Integer default) {
		this.default = default;
	}*/
	/**
	 * 获取：是否为默认频道，默认频道在第一次用户登录时会显示。1：是，0：否

	public Integer getDefault() {
		return default;
	}*/
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
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：商铺展示
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：1攻略展示
	 */
	public void setTypes(String types) {
		this.types = types;
	}
	/**
	 * 获取：1攻略展示
	 */
	public String getTypes() {
		return types;
	}
	/**
	 * 设置：攻略名
	 */
	public void setSName(String sName) {
		this.sName = sName;
	}
	/**
	 * 获取：攻略名
	 */
	public String getSName() {
		return sName;
	}
	/**
	 * 设置：店铺名
	 */
	public void setMName(String mName) {
		this.mName = mName;
	}
	/**
	 * 获取：店铺名
	 */
	public String getMName() {
		return mName;
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
	/**
	 * 设置：视频频道是否展示关联内容(0:不展示;1:展示)
	 */
	public void setIsRelation(Integer isRelation) {
		this.isRelation = isRelation;
	}
	/**
	 * 获取：视频频道是否展示关联内容(0:不展示;1:展示)
	 */
	public Integer getIsRelation() {
		return isRelation;
	}
}
