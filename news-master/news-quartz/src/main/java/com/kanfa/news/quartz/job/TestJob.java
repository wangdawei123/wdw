package com.kanfa.news.quartz.job;

import com.kanfa.news.quartz.feign.IActivityCouponServiceFeign;
import com.kanfa.news.quartz.service.IJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 实现序列化接口、防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
 */
public class TestJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IJobService jobService;

    @Autowired
    private IActivityCouponServiceFeign activityCouponServiceFeign;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //注入jobService 执行相关业务操作
        System.out.println(jobService);
        System.out.println("context = [" + activityCouponServiceFeign + "]");
        System.out.println("任务执行成功");
    }
}