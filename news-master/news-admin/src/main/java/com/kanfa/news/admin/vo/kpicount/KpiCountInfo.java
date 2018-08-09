package com.kanfa.news.admin.vo.kpicount;

import java.io.Serializable;

/**
 * 机动即时部、全国采访部记者工作统计列表
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiCountInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer uid;
    private String name;
    private String deptName;
    private String jobName;
    private Float w;
    private Float wp;
    private Float wpv;
    private Float t;
    private Float video;
    private Float other;
    private String eightPv;
    private Integer typeId;

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Float getW() {
        return w;
    }

    public void setW(Float w) {
        this.w = w;
    }

    public Float getWp() {
        return wp;
    }

    public void setWp(Float wp) {
        this.wp = wp;
    }

    public Float getWpv() {
        return wpv;
    }

    public void setWpv(Float wpv) {
        this.wpv = wpv;
    }

    public Float getT() {
        return t;
    }

    public void setT(Float t) {
        this.t = t;
    }

    public Float getVideo() {
        return video;
    }

    public void setVideo(Float video) {
        this.video = video;
    }

    public Float getOther() {
        return other;
    }

    public void setOther(Float other) {
        this.other = other;
    }

    public String getEightPv() {
        return eightPv;
    }

    public void setEightPv(String eightPv) {
        this.eightPv = eightPv;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}


