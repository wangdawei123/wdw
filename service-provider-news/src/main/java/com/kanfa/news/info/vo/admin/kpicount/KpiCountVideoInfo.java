package com.kanfa.news.info.vo.admin.kpicount;

import java.io.Serializable;

/**
 * 视频工作统计列表
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-20 11:56:05
 */
public class KpiCountVideoInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String deptName;
    private String jobName;
    private String originalVideo;
    private String originalLater;
    private String animationDesing;
    private String reviewVideo;
    private String reprintVideo;
    private String dubbing;
    private String animationScript;
    private String other;

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

    public String getOriginalVideo() {
        return originalVideo;
    }

    public void setOriginalVideo(String originalVideo) {
        this.originalVideo = originalVideo;
    }

    public String getOriginalLater() {
        return originalLater;
    }

    public void setOriginalLater(String originalLater) {
        this.originalLater = originalLater;
    }

    public String getAnimationDesing() {
        return animationDesing;
    }

    public void setAnimationDesing(String animationDesing) {
        this.animationDesing = animationDesing;
    }

    public String getReviewVideo() {
        return reviewVideo;
    }

    public void setReviewVideo(String reviewVideo) {
        this.reviewVideo = reviewVideo;
    }

    public String getReprintVideo() {
        return reprintVideo;
    }

    public void setReprintVideo(String reprintVideo) {
        this.reprintVideo = reprintVideo;
    }

    public String getDubbing() {
        return dubbing;
    }

    public void setDubbing(String dubbing) {
        this.dubbing = dubbing;
    }

    public String getAnimationScript() {
        return animationScript;
    }

    public void setAnimationScript(String animationScript) {
        this.animationScript = animationScript;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}


