package com.kanfa.news.admin.rest;

import com.kanfa.news.admin.biz.CorpUserBiz;
import com.kanfa.news.admin.entity.CorpJob;
import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.common.constant.news.CorpDeptEnum;
import com.kanfa.news.common.constant.news.CorpJobEnum;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("corpUser")
public class CorpUserController extends BaseController<CorpUserBiz, CorpUser> {
    @Override
    public ObjectRestResponse<CorpUser> add(@RequestBody CorpUser corpJob) {
        Assert.hasText(corpJob.getName(), CorpJobEnum.NAME_NOT_NULL.value());
        CorpUser corpUserSearch = new CorpUser();
        corpUserSearch.setName(corpJob.getName());
        Assert.isTrue(!(this.baseBiz.selectCount(corpUserSearch) > 0), CorpDeptEnum.NAME_EXISTENCE.value());
        corpJob.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        corpJob.setCreateTime(new Date());
        corpJob.setStatus(CorpJobEnum.CORP_JOB_STA_NORMAL.key());
        return super.add(corpJob);
    }

    @GetMapping("/search")
    public TableResultResponse<Map<String, Object>> page(@RequestParam Map<String, Object> params) {
        return this.baseBiz.getPage(params);
    }

    @Override
    public ObjectRestResponse<CorpUser> update(@RequestBody CorpUser corpJob) {
        corpJob.setStatus(CorpJobEnum.CORP_JOB_STA_NORMAL.key());
        corpJob.setUpdateTime(new Date());
        corpJob.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return super.update(corpJob);
    }

    @Override
    public ObjectRestResponse<CorpUser> remove(@PathVariable int id) {
        CorpUser corpJob = new CorpUser();
        corpJob.setId(id);
        corpJob.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        corpJob.setUpdateTime(new Date());
        corpJob.setStatus(CorpJobEnum.CORP_JOB_STA_DELETED.key());
        return super.update(corpJob);
    }
}