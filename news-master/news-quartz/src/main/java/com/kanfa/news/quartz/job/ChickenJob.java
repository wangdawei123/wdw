package com.kanfa.news.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.Serializable;

/**
 * @author Jezy
 */
public class ChickenJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("大吉大利、今晚吃鸡-01-测试集群模式");
    }
}
