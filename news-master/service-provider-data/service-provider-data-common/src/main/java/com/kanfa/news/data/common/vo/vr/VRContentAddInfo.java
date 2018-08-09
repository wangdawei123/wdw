package com.kanfa.news.data.common.vo.vr;


import com.kanfa.news.data.common.vo.kpiCount.KpiCountContentInfo;
import com.kanfa.news.data.common.vo.video.VideoDemand;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Chen
 * on 2018/3/29 17:00
 */
public class VRContentAddInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer contentState;

    public Integer getContentState() {
        return contentState;
    }

    public void setContentState(Integer contentState) {
        this.contentState = contentState;
    }

    //标题
    private String title;
    //app频道集合
    private List<Integer> appChannelList;


    public List<Integer> getAppChannelList() {
        return appChannelList;
    }

    public void setAppChannelList(List<Integer> appChannelList) {
        this.appChannelList = appChannelList;
    }

    //vr频道集合
    private List<Integer>  vrChannelList;
    //VR作者 需要uid weight remarks
    private List<KpiCountContentInfo> kpiCountList;
    //法制类Vr  is_legal 1 是法制类 0 不是法制类',
    private Integer isLegal;
    //导语 introduction
    private String introduction;
    //缩略图 image
    private String image;
    //视频地址的Id
    private VideoDemand videoDemand;
    //videoDuration  视频时长
    private String videoDuration;



    //内容的数据类型，1：专题，2：文章，3：图集，4：视频，9:直播，11VR，12问答,13活动
    private Integer contentType;


    //1资讯（APP首页），2APP视频，3APPVR，4PC资讯
    private Integer category;

    //创建人
    private Integer createUid;
    private Date createTime;

    //修改人
    private Integer updateUid;
    private Date updateTime;

    private Integer CheckStatus;

    private Integer isCheck;

    public Integer getCheckStatus() {
        return CheckStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        CheckStatus = checkStatus;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getVrChannelList() {
        return vrChannelList;
    }

    public void setVrChannelList(List<Integer> vrChannelList) {
        this.vrChannelList = vrChannelList;
    }

    public List<KpiCountContentInfo> getKpiCountList() {
        return kpiCountList;
    }

    public void setKpiCountList(List<KpiCountContentInfo> kpiCountList) {
        this.kpiCountList = kpiCountList;
    }

    public Integer getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(Integer isLegal) {
        this.isLegal = isLegal;
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

    public VideoDemand getVideoDemand() {
        return videoDemand;
    }

    public void setVideoDemand(VideoDemand videoDemand) {
        this.videoDemand = videoDemand;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }
}
