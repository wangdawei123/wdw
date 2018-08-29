package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.AdminUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/7 16:37
 */
@FeignClient(name = "service-provider-news")
public interface IAdminUserServiceFeign {

    /**
     * 所有编辑人
     * @return
     */
    @RequestMapping(value = "/adminUser/all",method = RequestMethod.GET)
    @ResponseBody
    List<AdminUser> allBaseUser();

}
