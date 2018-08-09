package com.kanfa.news.admin.vo.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/8 13:26
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ChannelFocusInfo implements Serializable{
    private Integer id;

    //频道id,kf_channel.id
    private Integer channelId;

    //内容Id
    private Integer cid;

    //直播id转为cid使用
    private Integer lid;

    //焦点图标题
    private String title;

    //排序
    private Integer orderNumber;

    //状态，1：正常，0：删除
    private Integer isDelete;

    //cid的类型，与kf_content的type相同（冗余字段）
    private Integer type;

    //更新时间
    private Date createTime;

    //url
    private String url;

    //image
    private String image;

    //编辑人
    private String createUser;

    //编辑人
    private String channelName;

    //
    private Integer isPublish;

    //
    private Integer jump;
    //
    private List<Object> option;

    private Integer createUid;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getJump() {
        return jump;
    }

    public void setJump(Integer jump) {
        this.jump = jump;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public List<Object> getOption() {
        return option;
    }

    public void setOption(List<Object> option) {
        this.option = option;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }
}
