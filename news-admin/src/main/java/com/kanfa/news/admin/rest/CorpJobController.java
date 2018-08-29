package com.kanfa.news.admin.rest;

import com.kanfa.news.admin.biz.CorpJobBiz;
import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.admin.entity.CorpJob;
import com.kanfa.news.common.constant.news.CorpDeptEnum;
import com.kanfa.news.common.constant.news.CorpJobEnum;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import zipkin2.Call;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("corpJob")
public class CorpJobController extends BaseController<CorpJobBiz, CorpJob> {
    @Override
    public ObjectRestResponse<CorpJob> add(@RequestBody CorpJob corpJob) {
        Assert.hasText(corpJob.getName(), CorpJobEnum.NAME_NOT_NULL.value());
        CorpJob corpJobSearch = new CorpJob();
        corpJobSearch.setName(corpJob.getName());
        Assert.isTrue(!(this.baseBiz.selectCount(corpJobSearch) > 0), CorpDeptEnum.NAME_EXISTENCE.value());
        corpJob.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        corpJob.setCreateTime(new Date());
        corpJob.setStat(CorpJobEnum.CORP_JOB_STA_NORMAL.key());
        return super.add(corpJob);
    }

    @Override
    public ObjectRestResponse<CorpJob> update(@RequestBody CorpJob corpJob) {
        corpJob.setStat(CorpJobEnum.CORP_JOB_STA_NORMAL.key());
        corpJob.setUpdateTime(new Date());
        corpJob.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return super.update(corpJob);
    }

    @GetMapping("/search")
    public TableResultResponse<Map<String, Object>> page(@RequestParam Map<String, Object> params) {
        return this.baseBiz.getPage(params);
    }

    @Override
    public ObjectRestResponse<CorpJob> remove(@PathVariable int id) {
        CorpJob corpJob = new CorpJob();
        corpJob.setId(id);
        corpJob.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        corpJob.setUpdateTime(new Date());
        corpJob.setStat(CorpJobEnum.CORP_JOB_STA_DELETED.key());
        return super.update(corpJob);
    }

    /**
     * 获取所有岗位信息
     *
     * @return
     */
    @GetMapping("/getAll")
    public ObjectRestResponse<CorpJob> getAll() {
        return this.baseBiz.getAll();
    }
}