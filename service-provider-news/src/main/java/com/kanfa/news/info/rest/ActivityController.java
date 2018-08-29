package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.ActivityBiz;
import com.kanfa.news.info.biz.ActivitySkyBiz;
import com.kanfa.news.info.biz.ActivityVoteBiz;
import com.kanfa.news.info.biz.ActivityVoteContentBiz;
import com.kanfa.news.info.entity.Activity;
import com.kanfa.news.info.entity.ActivitySky;
import com.kanfa.news.info.entity.ActivityVote;
import com.kanfa.news.info.entity.ActivityVoteContent;
import com.kanfa.news.info.vo.admin.activity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("activity")
public class ActivityController extends BaseController<ActivityBiz,Activity> {

    @Autowired
    private ActivityBiz activityBiz;
    @Autowired
    private ActivityVoteBiz activityVoteBiz;
    @Autowired
    private ActivityVoteContentBiz activityVoteContentBiz;
    @Autowired
    private ActivitySkyBiz activitySkyBiz;



    /**
     * 新增建言/投票活动
     * @param
     * @return
     */
    @RequestMapping(value = "/insertVoteActivity",method = RequestMethod.POST)
    public void insertVoteActivity(@RequestBody VoteActivityAddInfo entity){
        activityBiz.insertVoteActivity(entity);
    }

    /**
     * 得到 建言/投票活动详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneVoteActivity",method = RequestMethod.GET)
    public VoteActivityAddInfo getOneVoteActivity(@RequestParam("id")Integer id){
        VoteActivityAddInfo oneVoteActivity = activityBiz.getOneVoteActivity(id);
        return oneVoteActivity;
    }

    /**
     * 编辑 建言/投票活动
     * @param
     * @return
     */
    @RequestMapping(value = "/updateVoteActivity",method = RequestMethod.POST)
    public  void updateVoteActivity(@RequestBody VoteActivityAddInfo entity){
        VoteActivityAddInfo oneVoteActivity = activityBiz.getOneVoteActivity(entity.getId());
        Activity activity = new Activity();
        activity.setId(oneVoteActivity.getId());
        BeanUtils.copyProperties(entity,activity);
        activityBiz.updateSelectiveById(activity);
        //更新ActivityVote
        ActivityVote activityVote = new ActivityVote();
        BeanUtils.copyProperties(entity,activityVote);
        activityVote.setActivityId(entity.getId());
        activityVote.setId(oneVoteActivity.getActivityVoteId());
        activityVoteBiz.updateSelectiveById(activityVote);
        //更新kf_activity_vote_content中content
        ActivityVoteContent activityVoteContent = new ActivityVoteContent();
        BeanUtils.copyProperties(entity,activityVoteContent);
        activityVoteContent.setId(oneVoteActivity.getActivityVoteContentId());
        activityVoteContent.setActivityVoteId(oneVoteActivity.getActivityVoteId());
        activityVoteContentBiz.updateSelectiveById(activityVoteContent);
    }

    /**
     * 建言/投票活动分页信息及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getVoteActivityPage", method = RequestMethod.POST)
    public TableResultResponse<VoteActivityInfo> getVoteActivityPage(@RequestBody VoteActivityInfo entity) {
        return activityBiz.getVoteActivityPage(entity);
    }

    /**
     * 优惠券活动分页信息及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getCouponsActivityPage", method = RequestMethod.POST)
    public TableResultResponse<CouponsActivityPageInfo> getCouponsActivityPage(@RequestBody CouponsActivityPageInfo entity) {
        return activityBiz.getCouponsActivityPage(entity);
    }

    /**
     * 新增 青春微普法大赛活动
     * @param
     * @return
     */
    @RequestMapping(value = "/addYouthActivity",method = RequestMethod.POST)
    public void addYouthActivity(@RequestBody YouthActivityAddInfo entity){
        activityBiz.addYouthActivity(entity);
    }

    /**
     * 青春微普法大赛活动 得到详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneYouthActivity",method = RequestMethod.GET)
    public YouthActivityAddInfo getOneYouthActivity(@RequestParam("id")Integer id){
        YouthActivityAddInfo oneYouthActivity = activityBiz.getOneYouthActivity(id);
        return oneYouthActivity;
    }


    /**
     * 青春微普法大赛活动 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/updateYouthActivity",method = RequestMethod.POST)
    public  void updateYouthActivity(@RequestBody YouthActivityAddInfo entity){
        YouthActivityAddInfo oneYouthActivity = activityBiz.getOneYouthActivity(entity.getId());
        Activity activity = new Activity();
        activity.setId(oneYouthActivity.getId());
        BeanUtils.copyProperties(entity,activity);
        activityBiz.updateSelectiveById(activity);
        //更新ActivityVote
        ActivitySky activitySky = new ActivitySky();
        activitySky.setActivityId(activity.getId());
        activitySky.setId(oneYouthActivity.getActivitySkyId());
        activitySky.setVotePeriod(entity.getVotePeriod());
        activitySky.setNeedLogin(entity.getNeedLogin());
        activitySkyBiz.updateSelectiveById(activitySky);
    }

    /**
     * 青春微普法大赛活动 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getYouthActivityPage", method = RequestMethod.POST)
    public TableResultResponse<YouthActivityPageInfo> getYouthActivityPage(@RequestBody YouthActivityPageInfo entity) {
        return activityBiz.getYouthActivityPage(entity);
    }

    /**
     * 政法先锋活动 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getPioneerActivityPage", method = RequestMethod.POST)
    public TableResultResponse<PioneerActivityPageInfo> getPioneerActivityPage(@RequestBody PioneerActivityPageInfo entity) {
        return activityBiz.getPioneerActivityPage(entity);
    }


}