package com.kanfa.news.admin.vo.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kanfa.news.admin.vo.channel.ContentInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/5 15:58
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContentArticleInfo implements Serializable{

    private Integer id;


    //标题
    private String title;

    //长标题
    private String longTitle;

    //导语
    private String introduction;

    //图片路径
    private String image;

    //创建时间
    private Date createTime;

    //添加人
    private String createUser;

    //文章详情
    private String contentDetail;
    //视频库资源id
    private Integer did;

    //推荐集合
    private List<ContentInfo> broadList;

    public List<ContentInfo> getBroadList() {
        return broadList;
    }

    public void setBroadList(List<ContentInfo> broadList) {
        this.broadList = broadList;
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

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
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


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

}
