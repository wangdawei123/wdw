package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityBlueSkyBiz;
import com.kanfa.news.info.biz.ActivityBlueSkyImageBiz;
import com.kanfa.news.info.entity.ActivityBlueSky;
import com.kanfa.news.info.entity.ActivityBlueSkyImage;
import com.kanfa.news.info.vo.admin.activity.ActivityBlueSkyAddInfo;
import com.kanfa.news.info.vo.admin.activity.ActivityBlueSkyPageInfo;
import com.kanfa.news.info.vo.admin.activity.VoteActivityAddInfo;
import com.kanfa.news.info.vo.admin.activity.VoteActivityInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activityBlueSky")
public class ActivityBlueSkyController extends BaseController<ActivityBlueSkyBiz,ActivityBlueSky> {

    @Autowired
    private ActivityBlueSkyBiz activityBlueSkyBiz;
    @Autowired
    private ActivityBlueSkyImageBiz activityBlueSkyImageBiz;

    /**
     * 新增 青春微普法大赛的候选人
     * @param
     * @return
     */
    @RequestMapping(value = "/insertActivityBlueSky",method = RequestMethod.POST)
    public void insertActivityBlueSky(@RequestBody ActivityBlueSkyAddInfo entity){
        activityBlueSkyBiz.insertActivityBlueSky(entity);
    }

    /**
     * 得到 青春微普法大赛的候选人
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneActivityBlueSky",method = RequestMethod.GET)
    public ActivityBlueSkyAddInfo getOneActivityBlueSky(@RequestParam("id")Integer id){
        return  activityBlueSkyBiz.getOneActivityBlueSky(id);
    }

    /**
     * 编辑 青春微普法大赛的候选人
     * @param
     * @return
     */
    @RequestMapping(value = "/updateActivityBlueSky",method = RequestMethod.POST)
    public void updateActivityBlueSky(@RequestBody ActivityBlueSkyAddInfo entity){
        ActivityBlueSkyAddInfo oneActivityBlueSky = activityBlueSkyBiz.getOneActivityBlueSky(entity.getId());
        ActivityBlueSky activityBlueSky = new ActivityBlueSky();
        activityBlueSky.setId(entity.getId());
        activityBlueSky.setActivityId(oneActivityBlueSky.getActivityId());
        BeanUtils.copyProperties(entity,activityBlueSky);
        activityBlueSkyBiz.updateSelectiveById(activityBlueSky);
        ActivityBlueSkyImage activityBlueSkyImage = new ActivityBlueSkyImage();
        activityBlueSkyImage.setBlueSkyPeopleId(entity.getId());
        List<ActivityBlueSkyImage> activityBlueSkyImages = activityBlueSkyImageBiz.selectList(activityBlueSkyImage);
        for (ActivityBlueSkyImage skyImage:activityBlueSkyImages){
            skyImage.setStatus(0);
            activityBlueSkyImageBiz.updateSelectiveById(skyImage);
        }
        //绑定activityBlueSkyImage
        activityBlueSkyBiz.bindactivityBlueSkyImage(entity);
    }


    /**
     * 青春微普法大赛的候选人 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityBlueSkyPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityBlueSkyPageInfo> getActivityBlueSkyPage(@RequestBody ActivityBlueSkyPageInfo entity) {
        return activityBlueSkyBiz.getActivityBlueSkyPage(entity);
    }


    }