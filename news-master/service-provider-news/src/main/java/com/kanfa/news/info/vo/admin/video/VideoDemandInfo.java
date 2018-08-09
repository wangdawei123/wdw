package com.kanfa.news.info.vo.admin.video;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/15 13:36
 */
public class VideoDemandInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    //视频标题
    private String title;
    //上传人
    private String createUser;
    //create_time 推荐时间
    private Date createTime;
    //status 文件的状态 视频库状态  1：上传失败 2：上传完成 3：转码中 4：转码完成
    private Integer status;

    //视频库的url
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String url;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private String duration;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //中文的文件状态
    private String statusCh;

    public String getStatusCh() {
        return statusCh;
    }

    public void setStatusCh(String statusCh) {
        this.statusCh = statusCh;
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

    public String getCreateUser() {
        return StringUtils.isEmpty(createUser) ? "" : createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
