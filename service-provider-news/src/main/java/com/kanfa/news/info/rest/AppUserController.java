package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.DateHandler;
import com.kanfa.news.info.biz.AppUserBiz;
import com.kanfa.news.info.entity.AppUser;
import com.kanfa.news.info.mongodb.entity.UserBindLogInfo;
import com.kanfa.news.info.mongodb.mapper.UserBindLogDao;
import com.kanfa.news.info.vo.admin.appuser.AppUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("appUser")
public class AppUserController extends BaseController<AppUserBiz,AppUser> {

    @Autowired
    private AppUserBiz appUserBiz;
    @Autowired
    private UserBindLogDao userBindLogDao;

    /**
     * 查询前端用户分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/getPage",method = RequestMethod.GET)
    public TableResultResponse<AppUserInfo> getPage(@RequestParam Map<String, Object> params){
        return appUserBiz.getPage(params);
    }

    /**
     * 查询单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAppUser/{id}",method = RequestMethod.GET)
    public ObjectRestResponse<AppUserInfo> getAppUser(@PathVariable("id") Integer id){
        //个人基本信息
        //关联授权信息
        AppUserInfo appUserInfo=appUserBiz.getAppUser(id);
        //日志信息 用户和管理员操作
        List<UserBindLogInfo> userBindLogInfoList = userBindLogDao.getUserLogByUid(id);
        if(userBindLogInfoList!=null && userBindLogInfoList.size()>0){
            for (UserBindLogInfo userBindLogInfo : userBindLogInfoList) {
                try {
                    userBindLogInfo.setCreateTime(DateHandler.longToDate(userBindLogInfo.getTime(),"yyyy-MM-dd HH:mm:ss"));
                    userBindLogInfo.setTime(null);
                    userBindLogInfo.set_id(null);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        UserBindLogInfo userBindLog = new UserBindLogInfo();
        userBindLog.setUid(id);
        userBindLog.setType(0);
        userBindLog.setCreateTime(appUserInfo.getCreateTime());
        userBindLogInfoList.add(0,userBindLog);
        appUserInfo.setUserBindLogInfoList(userBindLogInfoList);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(appUserInfo);
        return objectRestResponse;
    }

    /**
     * 统计
     * @return
     */
    @RequestMapping(value = "/countAppUser",method = RequestMethod.GET)
    public ObjectRestResponse<Map<String,Integer>> countAppUser(){
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(appUserBiz.countAppUser());
        return objectRestResponse;
    }

}