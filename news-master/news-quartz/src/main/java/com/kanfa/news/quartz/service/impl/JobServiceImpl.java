package com.kanfa.news.quartz.service.impl;

import com.kanfa.news.quartz.dynamicquery.DynamicQuery;
import com.kanfa.news.quartz.entity.QuartzEntity;
import com.kanfa.news.quartz.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("jobService")
public class JobServiceImpl implements IJobService {

    @Autowired
    private DynamicQuery dynamicQuery;

    @Override
    public List<QuartzEntity> listQuartzEntity(QuartzEntity quartz,
                                               Integer pageNo, Integer pageSize) {
        StringBuffer nativeSql = new StringBuffer();
        nativeSql.append("SELECT job.JOB_NAME as jobName,job.JOB_GROUP as jobGroup,job.DESCRIPTION as description,job.JOB_CLASS_NAME as jobClassName,");
        nativeSql.append("cron.CRON_EXPRESSION as cronExpression,tri.TRIGGER_NAME as triggerName,tri.TRIGGER_STATE as triggerState,");
        nativeSql.append("job.JOB_NAME as oldJobName,job.JOB_GROUP as oldJobGroup ");
        nativeSql.append("FROM qrtz_job_details AS job LEFT JOIN qrtz_triggers AS tri ON job.JOB_NAME = tri.JOB_NAME ");
        nativeSql.append("LEFT JOIN qrtz_cron_triggers AS cron ON cron.TRIGGER_NAME = tri.TRIGGER_NAME ");
//       nativeSql.append("SELECT");
//       nativeSql.append("   job.JOB_NAME AS jobName,");
//       nativeSql.append("   job.JOB_GROUP AS jobGroup,");
//       nativeSql.append("   job.DESCRIPTION AS description,");
//        nativeSql.append("  job.JOB_CLASS_NAME AS jobClassName,");
//        nativeSql.append("  cron.CRON_EXPRESSION AS cronExpression,");
//        nativeSql.append("  tri.TRIGGER_NAME AS triggerName,");
//        nativeSql.append("  tri.TRIGGER_STATE AS triggerState,");
//        nativeSql.append("  job.JOB_NAME AS oldJobName,");
//        nativeSql.append("  job.JOB_GROUP AS oldJobGroup");
//        nativeSql.append("FROM" );
//        nativeSql.append("  qrtz_job_details AS job" );
//        nativeSql.append("LEFT JOIN qrtz_triggers AS tri ON job.JOB_NAME = tri.JOB_NAME");
//        nativeSql.append("LEFT JOIN qrtz_cron_triggers AS cron ON cron.TRIGGER_NAME = tri.TRIGGER_NAME");
        nativeSql.append("WHERE tri.TRIGGER_TYPE = 'CRON'");
        nativeSql.append(" AND tri.JOB_GROUP = job.JOB_GROUP");
        nativeSql.append(" AND cron.TRIGGER_GROUP = tri.TRIGGER_GROUP");

        Object[] params = new Object[]{};
        //加入JobName搜索其他条件自行实现
        if (!StringUtils.isEmpty(quartz.getJobName())) {
            nativeSql.append(" AND job.JOB_NAME like CONCAT('%',?,'%')");
            params = new Object[]{quartz.getJobName()};
        }
        nativeSql.append(" group by jobName,jobGroup");
        return dynamicQuery.nativeQueryListModel(QuartzEntity.class, nativeSql.toString(), params);
    }

    @Override
    public Long listQuartzEntity(QuartzEntity quartz) {
        StringBuffer nativeSql = new StringBuffer();
        nativeSql.append("SELECT COUNT(*)");
        nativeSql.append("FROM qrtz_job_details AS job LEFT JOIN qrtz_triggers AS tri ON job.JOB_NAME = tri.JOB_NAME ");
        nativeSql.append("LEFT JOIN qrtz_cron_triggers AS cron ON cron.TRIGGER_NAME = tri.TRIGGER_NAME ");
        nativeSql.append("WHERE tri.TRIGGER_TYPE = 'CRON'");
        return dynamicQuery.nativeQueryCount(nativeSql.toString(), new Object[]{});
    }
}
