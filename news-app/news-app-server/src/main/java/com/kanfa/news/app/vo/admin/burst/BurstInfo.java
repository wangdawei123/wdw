package com.kanfa.news.app.vo.admin.burst;

import com.kanfa.news.app.vo.admin.info.ContentInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/20 18:24
 */
public class BurstInfo implements Serializable{

    private Integer id;

    //爆料人昵称
    private String userName;

    //用户id
    private Integer userId;

    //事发地点
    private String address;

    //新闻简述
    private String remark;

    //该绑定的内容数量
    private Integer bindNum;

    //新闻爆料状态(1:未受理;2:完成;3:拒绝)
    private Integer status;

    //创建时间
    private Date createTime;

    //手机
    private String phone;

    //类型名称
    private String typeName;

    //类型id
    private Integer typeId;

    //经纬度
    private String point;

    //详情描述
    private String content;

    //补充描述
    private String addDesc;

    private List<ContentInfo> bindContent;

    //爆料资源信息内容
    private List<BurstResourceInfo> listRsrContent;

    //爆料补充的资源信息
    private List<BurstResourceInfo> listRsrAddInfo;

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

    public List<BurstResourceInfo> getListRsrContent() {
        return listRsrContent;
    }

    public void setListRsrContent(List<BurstResourceInfo> listRsrContent) {
        this.listRsrContent = listRsrContent;
    }

    public List<BurstResourceInfo> getListRsrAddInfo() {
        return listRsrAddInfo;
    }

    public void setListRsrAddInfo(List<BurstResourceInfo> listRsrAddInfo) {
        this.listRsrAddInfo = listRsrAddInfo;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddDesc() {
        return addDesc;
    }

    public void setAddDesc(String addDesc) {
        this.addDesc = addDesc;
    }

    public List<ContentInfo> getBindContent() {
        return bindContent;
    }

    public void setBindContent(List<ContentInfo> bindContent) {
        this.bindContent = bindContent;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBindNum() {
        return bindNum;
    }

    public void setBindNum(Integer bindNum) {
        this.bindNum = bindNum;
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
}
