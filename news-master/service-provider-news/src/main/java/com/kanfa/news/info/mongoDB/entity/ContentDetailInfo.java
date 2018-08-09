package com.kanfa.news.info.mongodb.entity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "content_detail_info")
public class ContentDetailInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private ObjectId id;
    private Integer cid;
    private String title;
    private Integer create_time;
    private Editor editor;
    private Integer from_id;
    private Integer source_type;
    private Integer type;
    private Integer first_check_time;
    private Check check;
    private Integer pv_total;
    private Integer app_pv;
    private Integer play_total;
    private Integer play_app;
    private Integer stat;
    private Integer live_type;
    private List<Day> day;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Integer create_time) {
        this.create_time = create_time;
    }

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Integer from_id) {
        this.from_id = from_id;
    }

    public Integer getSource_type() {
        return source_type;
    }

    public void setSource_type(Integer source_type) {
        this.source_type = source_type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFirst_check_time() {
        return first_check_time;
    }

    public void setFirst_check_time(Integer first_check_time) {
        this.first_check_time = first_check_time;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Integer getPv_total() {
        return pv_total;
    }

    public void setPv_total(Integer pv_total) {
        this.pv_total = pv_total;
    }

    public Integer getApp_pv() {
        return app_pv;
    }

    public void setApp_pv(Integer app_pv) {
        this.app_pv = app_pv;
    }

    public Integer getPlay_total() {
        return play_total;
    }

    public void setPlay_total(Integer play_total) {
        this.play_total = play_total;
    }

    public Integer getPlay_app() {
        return play_app;
    }

    public void setPlay_app(Integer play_app) {
        this.play_app = play_app;
    }

    public List<Day> getDay() {
        return day;
    }

    public void setDay(List<Day> day) {
        this.day = day;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Integer getLive_type() {
        return live_type;
    }

    public void setLive_type(Integer live_type) {
        this.live_type = live_type;
    }
}
