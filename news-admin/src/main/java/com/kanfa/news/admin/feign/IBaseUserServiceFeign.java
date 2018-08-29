package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.BaseUser;
import com.kanfa.news.admin.vo.AdminUserInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/7 16:37
 */
@FeignClient(name = "service-provider-news")
public interface IBaseUserServiceFeign {

    /**
     * 所有编辑人
     * @return
     */
    @RequestMapping(value = "/baseUser/all",method = RequestMethod.GET)
    @ResponseBody
    List<BaseUser> allBaseUser();


    /**
     * 修改
     *
     * @return
     */
    @RequestMapping(value = "/baseUser/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<BaseUser> update(@PathVariable("id") Integer id, @RequestBody BaseUser entity);


    //得到一个BaseUser
    @RequestMapping(value = "/baseUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<BaseUser> get(@PathVariable("id") int id);

    /**
     * 获取编辑人（未删除）
     * @return
     */
    @RequestMapping(value = "/baseUser/getAllBaseUser",method = RequestMethod.GET)
    List<AdminUserInfo> getAllBaseUser();
}
