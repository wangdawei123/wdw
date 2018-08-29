package com.kanfa.news.admin.rest;

import com.kanfa.news.admin.biz.CorpDeptBiz;
import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.common.constant.news.CorpDeptEnum;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("dept")
public class CorpDeptController extends BaseController<CorpDeptBiz, CorpDept> {

    @Override
    public ObjectRestResponse<CorpDept> add(@RequestBody CorpDept corpDept) {
        Assert.hasText(corpDept.getName(), CorpDeptEnum.NAME_NOT_NULL.value());
        CorpDept corpDeptSearch = new CorpDept();
        corpDeptSearch.setName(corpDept.getName());
        Assert.isTrue(!(this.baseBiz.selectCount(corpDeptSearch) > 0), CorpDeptEnum.NAME_EXISTENCE.value());
        corpDept.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        corpDept.setCreateTime(new Date());
        corpDept.setStat(CorpDeptEnum.CORP_DEPT_STA_NORMAL.key());
        return super.add(corpDept);
    }

    /**
     * 获取Level为1的部门数据
     *
     * @return
     */
    @GetMapping("/getLevel")
    public TableResultResponse<CorpDept> getLevel(@RequestParam(required = false, defaultValue = "1") Integer level) {
        Map paramMap = new HashMap(16);
        paramMap.put("level", Integer.valueOf(level));
        paramMap.put("limit", "200");
        return super.list(paramMap);
    }

    @Override
    public ObjectRestResponse<CorpDept> update(@RequestBody CorpDept corpDept) {
        corpDept.setUpdateTime(new Date());
        corpDept.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return super.update(corpDept);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public TableResultResponse<Map<String, Object>> page(@RequestParam Map<String, Object> params) {
        return this.baseBiz.getPage(params);
    }

}
