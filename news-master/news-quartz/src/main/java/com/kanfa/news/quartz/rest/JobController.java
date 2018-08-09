package com.kanfa.news.quartz.rest;


import com.kanfa.news.quartz.entity.QuartzEntity;
import com.kanfa.news.quartz.entity.Result;
import com.kanfa.news.quartz.service.IJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    private final static Logger LOGGER = LoggerFactory.getLogger(JobController.class);


    @Autowired
    private Scheduler scheduler;
    @Autowired
    private IJobService jobService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @PostMapping("/add")
    public Result save(QuartzEntity quartz) {
        LOGGER.info("新增任务");
        try {
            //如果是修改  展示旧的 任务
            if (quartz.getOldJobGroup() != null) {
                JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
                scheduler.deleteJob(key);
            }
            Class cls = Class.forName(quartz.getJobClassName());
            cls.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                    quartz.getJobGroup())
                    .withDescription(quartz.getDescription()).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup()).withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
            scheduler.pauseJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
        } catch(ObjectAlreadyExistsException e){
            return Result.error(500,quartz.getJobName()+"任务在"+quartz.getJobGroup()+"分组中已经存在");
        } catch(ClassNotFoundException e){
            return Result.error(500,"执行类没有找到");
        }
        catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }

    @GetMapping("/list")
    public Result list(QuartzEntity quartz) {
        LOGGER.info("任务列表");
        List<QuartzEntity> list = jobService.listQuartzEntity(quartz, 0, 0);
        return Result.ok(list);
    }

    @PostMapping("/trigger")
    public Result trigger(QuartzEntity quartz) {
        try {
            JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }

    /*@PostMapping("/pause")
    public  Result pause(QuartzEntity quartz,HttpServletResponse response) {
        LOGGER.info("停止任务");
        try {
            JobKey key = new JobKey(quartz.getJobName(),quartz.getJobGroup());
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }
    @PostMapping("/resume")
    public  Result resume(QuartzEntity quartz,HttpServletResponse response) {
        LOGGER.info("恢复任务");
        try {
            JobKey key = new JobKey(quartz.getJobName(),quartz.getJobGroup());
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }*/

    @PostMapping("/remove")
    public Result remove(QuartzEntity quartz) {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
            // 停止触发器  
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器  
            scheduler.unscheduleJob(triggerKey);
            // 删除任务  
            scheduler.deleteJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }
//NORMAL:正常状态 PAUSED：暂停状态 COMPLETE：触发器完成
    @PostMapping("/resume")
    public Result resume(QuartzEntity quartz) {
        try {
            JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.resumeJob(jobKey);
            scheduler.triggerJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }
    @PostMapping("/pause")
    public Result pause(QuartzEntity quartz) {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            JobKey jobKey = JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup());
            scheduler.pauseJob(jobKey);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
        return Result.ok();
    }
}
