package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityVotePeopleBiz;
import com.kanfa.news.info.biz.ActivityVotePeopleImageBiz;
import com.kanfa.news.info.entity.ActivityVotePeople;
import com.kanfa.news.info.entity.ActivityVotePeopleImage;
import com.kanfa.news.info.vo.admin.activity.ActivityVotePeopleAddInfo;
import com.kanfa.news.info.vo.admin.activity.ActivityVotePeoplePageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("activityVotePeople")
public class ActivityVotePeopleController extends BaseController<ActivityVotePeopleBiz,ActivityVotePeople> {

    @Autowired
    private ActivityVotePeopleBiz activityVotePeopleBiz;
    @Autowired
    private ActivityVotePeopleImageBiz activityVotePeopleImageBiz;

    /**
     * 建言/投票活动中的候选人分页信息及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getVoteActivityPeoplePage", method = RequestMethod.POST)
    public TableResultResponse<ActivityVotePeoplePageInfo> getVoteActivityPeoplePage(@RequestBody ActivityVotePeoplePageInfo entity) {
        return activityVotePeopleBiz.getVoteActivityPeoplePage(entity);
    }

    /**
     * 建言/投票活动中的候选人 新增
     * @param
     * @return
     */
    @RequestMapping(value = "/voteActivityPeopleAdd", method = RequestMethod.POST)
    public void voteActivityPeopleAdd (@RequestBody ActivityVotePeopleAddInfo entity){
        activityVotePeopleBiz.activityVotePeopleAdd(entity);
    }

    /**
     * 得到 建言/投票活动中的候选人信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneActivityVotePeople",method = RequestMethod.GET)
    public ActivityVotePeopleAddInfo getOneActivityVotePeople(@RequestParam("id")Integer id){
        return activityVotePeopleBiz.getOneActivityVotePeople(id);
    }


    /**
     * 编辑  建言/投票活动中的候选人
     * @param
     * @return
     */
    @RequestMapping(value = "/updateActivityVotePeople",method = RequestMethod.POST)
    public void updateActivityVotePeople(@RequestBody ActivityVotePeopleAddInfo entity){
        ActivityVotePeopleAddInfo oneActivityVotePeople = activityVotePeopleBiz.getOneActivityVotePeople(entity.getId());
        //更新activityVotePeople
        ActivityVotePeople activityVotePeople = new ActivityVotePeople();
        activityVotePeople.setId(oneActivityVotePeople.getId());
        BeanUtils.copyProperties(entity,activityVotePeople);
        activityVotePeople.setUpdateTime(new Date());
        activityVotePeopleBiz.updateSelectiveById(activityVotePeople);
        //更新ActivityVotePeopleImage
        ActivityVotePeopleImage activityVotePeopleImage = new ActivityVotePeopleImage();
        activityVotePeopleImage.setActivityVotePeopleId(oneActivityVotePeople.getId());
        //表中数据
        List<ActivityVotePeopleImage> activityVotePeopleImages = activityVotePeopleImageBiz.selectList(activityVotePeopleImage);
        for (ActivityVotePeopleImage activityVotePeopleImage1:activityVotePeopleImages){
            //已删除
            activityVotePeopleImage1.setStatus(0);
            activityVotePeopleImageBiz.updateSelectiveById(activityVotePeopleImage1);
        }
        //新增候选人图片 kf_activity_vote_people_image
        activityVotePeopleBiz.activityVotePeopleImageAdd(entity);
    }

}