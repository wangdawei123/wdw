package com.kanfa.news.quartz.job;

import com.kanfa.news.quartz.feign.TaskJobFeign;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/22 15:50
 */
public class DayPvPlayJob implements Job, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TaskJobFeign taskJobFeign;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        taskJobFeign.dayPvPlay();
    }
}
