package com.kanfa.news.info.mongodb.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/14 17:37
 */
@Document(collection = "count_video_view")
public class CountVideoView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private ObjectId id;
    private  Integer vid;
    private  Integer live_id;
    private  Integer pv;
    private  Integer uv;
    private  Integer app_pv_in;
    private  Integer app_pv_out;
    private  Integer app_video_in;
    private  Integer app_video_out;

    private Integer count_date;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getLive_id() {
        return live_id;
    }

    public void setLive_id(Integer live_id) {
        this.live_id = live_id;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getCount_date() {
        return count_date;
    }

    public void setCount_date(Integer count_date) {
        this.count_date = count_date;
    }

    public Integer getApp_pv_in() {
        return app_pv_in;
    }

    public void setApp_pv_in(Integer app_pv_in) {
        this.app_pv_in = app_pv_in;
    }

    public Integer getApp_pv_out() {
        return app_pv_out;
    }

    public void setApp_pv_out(Integer app_pv_out) {
        this.app_pv_out = app_pv_out;
    }

    public Integer getApp_video_in() {
        return app_video_in;
    }

    public void setApp_video_in(Integer app_video_in) {
        this.app_video_in = app_video_in;
    }

    public Integer getApp_video_out() {
        return app_video_out;
    }

    public void setApp_video_out(Integer app_video_out) {
        this.app_video_out = app_video_out;
    }
}
