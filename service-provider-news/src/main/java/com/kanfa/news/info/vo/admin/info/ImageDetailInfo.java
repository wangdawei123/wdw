package com.kanfa.news.info.vo.admin.info;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/5 15:58
 */
public class ImageDetailInfo implements Serializable{

    private Integer id;
    private String title;
    //视频
    private List<Map<String,Object>> extent;
    //设备id
    private String devid;
    //用户id
    private Integer uid;
    private Integer uuid;

    private Integer sourceType;

    private List<ContentImageInfo> contentImageList;

    private Map<String, Object> signPackage;

    private Map<String,Object> share;

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

    public List<Map<String, Object>> getExtent() {
        return extent;
    }

    public void setExtent(List<Map<String, Object>> extent) {
        this.extent = extent;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public List<ContentImageInfo> getContentImageList() {
        return contentImageList;
    }

    public void setContentImageList(List<ContentImageInfo> contentImageList) {
        this.contentImageList = contentImageList;
    }

    public Map<String, Object> getSignPackage() {
        return signPackage;
    }

    public void setSignPackage(Map<String, Object> signPackage) {
        this.signPackage = signPackage;
    }

    public Map<String, Object> getShare() {
        return share;
    }

    public void setShare(Map<String, Object> share) {
        this.share = share;
    }
}
