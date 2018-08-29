package com.kanfa.news.admin.vo.kpicount;

import java.io.Serializable;

/**
 * 直播工作统计列表
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiCountLiveInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String deptName;
    private String jobName;
    private Integer columnLive;
    private Integer ordinaryLive;
    private Integer allCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getColumnLive() {
        return columnLive;
    }

    public void setColumnLive(Integer columnLive) {
        this.columnLive = columnLive;
    }

    public Integer getOrdinaryLive() {
        return ordinaryLive;
    }

    public void setOrdinaryLive(Integer ordinaryLive) {
        this.ordinaryLive = ordinaryLive;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
}


