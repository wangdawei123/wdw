package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityLawPioneerBiz;
import com.kanfa.news.info.entity.ActivityLawPioneer;
import com.kanfa.news.info.vo.admin.activity.ActivityLawPioneerPageInfo;
import com.kanfa.news.info.vo.admin.info.ActivityLawPioneerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activityLawPioneer")
public class ActivityLawPioneerController extends BaseController<ActivityLawPioneerBiz,ActivityLawPioneer> {

    @Autowired
    private ActivityLawPioneerBiz activityLawPioneerBiz;

    /**
     * 查询政法先锋机构或个人
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public ObjectRestResponse<List<ActivityLawPioneerInfo>> getList(@RequestBody ActivityLawPioneerInfo entity){
        ObjectRestResponse<List<ActivityLawPioneerInfo>> objectRestResponse=new ObjectRestResponse<>();
        objectRestResponse.setData(activityLawPioneerBiz.getList(entity));
        return objectRestResponse;
    }


    /**
     * 政法先锋-->机构或个人
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getActivityLawPioneerPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityLawPioneerPageInfo> getActivityLawPioneerPage(@RequestBody ActivityLawPioneerPageInfo entity) {
        return activityLawPioneerBiz.getActivityLawPioneerPage(entity);
    }



    @RequestMapping(value = "/getMaxSort",method = RequestMethod.GET)
    public Integer getMaxSort(@RequestParam("activityLawId")Integer activityLawId){
        return  activityLawPioneerBiz.getMaxSort(activityLawId);
    }





}