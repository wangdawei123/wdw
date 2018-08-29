package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CorpDeptBiz;
import com.kanfa.news.info.biz.CorpUserBiz;
import com.kanfa.news.info.entity.CorpDept;
import com.kanfa.news.info.entity.CorpUser;
import com.kanfa.news.info.vo.admin.info.CorpUserAndDeptInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("corpUser")
public class CorpUserController extends BaseController<CorpUserBiz,CorpUser> {

    @Autowired
    private CorpDeptBiz corpDeptBiz;


    @RequestMapping(value = "/getCorpUserIdName",method = RequestMethod.GET)
    public List<CorpUser> getCorpUserIdName(){
        return  this.baseBiz.getCorpUserIdName();
    }

    /**
     * 查询部门和员工
     * @return
     */
    @RequestMapping(value = "/getReporterAndDept",method = RequestMethod.GET)
    public CorpUserAndDeptInfo getReporterAndDept(){
        CorpUser corpUser = new CorpUser();
        corpUser.setStatus(NewsEnumsConsts.CorpUserOfStatus.OnPosition.key());
        List<CorpUser> corpUsers = this.baseBiz.selectList(corpUser);
        CorpDept corpDept = new CorpDept();
        corpDept.setStat(NewsEnumsConsts.CorpDeptOfStat.Normal.key());
        List<CorpDept> corpDepts = corpDeptBiz.selectList(corpDept);
        CorpUserAndDeptInfo corpUserAndDeptInfo = new CorpUserAndDeptInfo();
        corpUserAndDeptInfo.setCorpUsers(corpUsers);
        corpUserAndDeptInfo.setCorpDepts(corpDepts);
        return corpUserAndDeptInfo;
    }

}