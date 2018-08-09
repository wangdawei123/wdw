package com.kanfa.news.app.vo.admin.comment;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/3/28 13:41
 */
public class CommentBlacklistInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;

    //设备ID，屏蔽非会员时，使用此值
    private String deviceId;

    //会员ID
    private Integer uid;

    //被屏蔽时的用户名/游客名，仅作参考用
    private String name;

    //会员，游客
    private String type;

    //创建时间
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
