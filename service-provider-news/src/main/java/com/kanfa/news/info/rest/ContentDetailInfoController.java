package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.AdminUserBiz;
import com.kanfa.news.info.biz.ContentPreviewBiz;
import com.kanfa.news.info.entity.AdminUser;
import com.kanfa.news.info.entity.ContentPreview;
import com.kanfa.news.info.mapper.AdminUserMapper;
import com.kanfa.news.info.mongodb.biz.ContentDetailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/10 10:02
 */
@RestController
@RequestMapping("contentDetailInfo")
public class ContentDetailInfoController extends BaseController<AdminUserBiz,AdminUser> {

    @Autowired
    private ContentDetailInfoService  contentDetailInfoService;
    @Autowired
    private AdminUserBiz adminUserBiz;

    private  final static Integer STATUSOK = 200;
    /**
     * 状态 1 为可用，0为删除
     */
    private  final static  Integer ENABLE = 1;

    /**
     * 发稿查询
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param uid
     * @param ascDesc
     * @param columnName
     * @return
     */
    @RequestMapping(value = "/getList")
    public Object getList(@RequestParam Integer page,
                                    @RequestParam Integer limit,
                                    @RequestParam String startDate,
                                    @RequestParam String endDate,
                                    @RequestParam String pvEndDate,
                                    @RequestParam(required=false) Integer uid,
                                    @RequestParam(required=false) Integer ascDesc,
                                    @RequestParam(required=false) String columnName){
        AdminUser adminUser = new AdminUser();
        adminUser.setStat(ENABLE);
        List<AdminUser> userList = adminUserBiz.selectList(adminUser);
        Map map = new HashMap(2);
        map.put("status",STATUSOK);
        map.put("data",contentDetailInfoService.getList(page,limit,uid,startDate,endDate,pvEndDate,ascDesc,columnName,userList));
        return map;
    }

    /**
     * 发稿明细
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param uid
     * @param ascDesc
     * @param columnName
     * @return
     */
    @RequestMapping(value = "/getDetailList")
    public Object getDetailList(@RequestParam Integer page,
                                    @RequestParam Integer limit,
                                    @RequestParam String startDate,
                                    @RequestParam String endDate,
                                    @RequestParam String pvEndDate,
                                    @RequestParam(required=false) Integer uid,
                                    @RequestParam(required=false,defaultValue = "1") Integer ascDesc,
                                    @RequestParam(required=false) String columnName,
                                    @RequestParam(required=false) Integer type,
                                    @RequestParam(required=false) Integer sourceType,
                                    @RequestParam(required=false) String title){
        Map map = new HashMap(2);
        map.put("status",STATUSOK);
        map.put("data",contentDetailInfoService.getDetailList(page,limit,uid,startDate,endDate,pvEndDate,ascDesc,columnName,type,sourceType,title));
        return map;
    }
}