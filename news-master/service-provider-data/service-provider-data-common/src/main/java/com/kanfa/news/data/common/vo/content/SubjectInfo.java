package com.kanfa.news.data.common.vo.content;

import com.kanfa.news.data.common.vo.kpi.KpiCountContentInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/29 11:12
 */
public class SubjectInfo implements Serializable {
    private Integer id;

    /**
     * 栏目，1资讯（APP首页），2APP视频，3APPVR，4PC资讯
     */
    private Integer category;

    /**
     * app频道集合
     */
    private List<Integer> appChannelIdList;
    /**
     * pc频道集合
     */
    private List<Integer> pcChannelIdList;

    /**
     * 记者统计
     */
    private List<KpiCountContentInfo> kpiCountContentInfoList;

    //标题
    private String title;

    //导语
    private String introduction;

    //图片路径
    private String image;

    //专题的封面图
    private String coverImg;

    /**
     * 标签名称集合
     */
    private List<String> tagNameList;

    //推荐权重
    private Integer recommendWeight;

    //0 在审核列表不显示　１显示
    private Integer isCheck;

    //审核状态（0待审核，1审核通过，2审核不通过）
    private Integer checkStatus;

    private Integer isLegal;

    //创建人Id
    private Integer createUid;

    /**
     * 子目录集合
     */
    private List<SubjectCatalogInfo> childCatalogList;

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getAppChannelIdList() {
        return appChannelIdList;
    }

    public void setAppChannelIdList(List<Integer> appChannelIdList) {
        this.appChannelIdList = appChannelIdList;
    }

    public List<Integer> getPcChannelIdList() {
        return pcChannelIdList;
    }

    public void setPcChannelIdList(List<Integer> pcChannelIdList) {
        this.pcChannelIdList = pcChannelIdList;
    }

    public List<KpiCountContentInfo> getKpiCountContentInfoList() {
        return kpiCountContentInfoList;
    }

    public void setKpiCountContentInfoList(List<KpiCountContentInfo> kpiCountContentInfoList) {
        this.kpiCountContentInfoList = kpiCountContentInfoList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public List<String> getTagNameList() {
        return tagNameList;
    }

    public void setTagNameList(List<String> tagNameList) {
        this.tagNameList = tagNameList;
    }

    public Integer getRecommendWeight() {
        return recommendWeight;
    }

    public void setRecommendWeight(Integer recommendWeight) {
        this.recommendWeight = recommendWeight;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(Integer isLegal) {
        this.isLegal = isLegal;
    }

    public List<SubjectCatalogInfo> getChildCatalogList() {
        return childCatalogList;
    }

    public void setChildCatalogList(List<SubjectCatalogInfo> childCatalogList) {
        this.childCatalogList = childCatalogList;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
