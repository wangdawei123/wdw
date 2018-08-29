package com.kanfa.news.admin.vo.vr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kanfa.news.admin.vo.kpi.KpiCountContentInfo;
import com.kanfa.news.admin.vo.video.VideoDemand;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chen
 * on 2018/4/1 11:00
 */
public class VRShow implements Serializable {
    private Integer id;
    //标题
    private String title;

    //app频道
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private  List<Integer> appChannelList;

    public List<Integer> getAppChannelList() {
        return appChannelList;
    }

    public void setAppChannelList(List<Integer> appChannelList) {
        this.appChannelList = appChannelList;
    }

    //vr频道集合
    private List<Integer> vrChannelList;
    //VR作者 需要uid weight remarks
    private List<KpiCountContentInfo> kpiCountList;
    //法制类Vr  is_legal 1 是法制类 0 不是法制类',
    private Integer isLegal;
    //导语 introduction
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String introduction;
    //缩略图 image
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String image;
    //视频地址的Id
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private VideoDemand videoDemand;
    //videoDuration  视频时长
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String videoDuration;

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
}
