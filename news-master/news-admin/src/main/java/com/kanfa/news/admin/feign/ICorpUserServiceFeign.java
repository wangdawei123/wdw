package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.vo.channel.CorpUserAndDeptInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/14 13:35
 */
@FeignClient(name = "service-provider-news")
public interface ICorpUserServiceFeign {

    @RequestMapping(value = "/corpUser/all",method = RequestMethod.GET)
    List<CorpUser> getReporterForContent();

    @RequestMapping(value = "/corpUser/getCorpUserIdName",method = RequestMethod.GET)
    List<CorpUser> getCorpUserIdName();

    /**
     * 查询部门和员工
     * @return
     */
    @RequestMapping(value = "/corpUser/getReporterAndDept",method = RequestMethod.GET)
    CorpUserAndDeptInfo getReporterAndDept();
}
