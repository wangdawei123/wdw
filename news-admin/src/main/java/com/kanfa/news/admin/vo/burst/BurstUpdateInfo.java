package com.kanfa.news.admin.vo.burst;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/20 18:24
 */
public class BurstUpdateInfo implements Serializable{

    private Integer id;
    //新闻爆料状态(1:未受理;2:完成;3:拒绝)
    private Integer status;
    //创建时间
    private Date createTime;
    //爆料类型(取自news_type表)
    private Integer burstTypeId;
    //补充描述
    private String addDesc;
    //更新时间
    private Date updateTime;
    //后台操作用户id
    private Integer adminId;

    //资源子集
    private List<BurstResourceInfo> burstResourceInfoList;

    //图片子集
    private List<String> burstImageList;
    //视频子集
    private List<BurstResourceInfo> burstVideoList;

    public List<String> getBurstImageList() {
        return burstImageList;
    }

    public void setBurstImageList(List<String> burstImageList) {
        this.burstImageList = burstImageList;
    }

    public List<BurstResourceInfo> getBurstVideoList() {
        return burstVideoList;
    }

    public void setBurstVideoList(List<BurstResourceInfo> burstVideoList) {
        this.burstVideoList = burstVideoList;
    }

    public List<BurstResourceInfo> getBurstResourceInfoList() {
        return burstResourceInfoList;
    }

    public void setBurstResourceInfoList(List<BurstResourceInfo> burstResourceInfoList) {
        this.burstResourceInfoList = burstResourceInfoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBurstTypeId() {
        return burstTypeId;
    }

    public void setBurstTypeId(Integer burstTypeId) {
        this.burstTypeId = burstTypeId;
    }

    public String getAddDesc() {
        return addDesc;
    }

    public void setAddDesc(String addDesc) {
        this.addDesc = addDesc;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
