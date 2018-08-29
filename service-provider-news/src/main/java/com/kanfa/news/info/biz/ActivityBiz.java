package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.ActivitySky;
import com.kanfa.news.info.entity.ActivityVote;
import com.kanfa.news.info.entity.ActivityVoteContent;
import com.kanfa.news.info.mapper.ActivitySkyMapper;
import com.kanfa.news.info.mapper.ActivityVoteContentMapper;
import com.kanfa.news.info.mapper.ActivityVoteMapper;
import com.kanfa.news.info.vo.admin.activity.*;
import com.kanfa.news.info.vo.admin.live.LiveInfo;
import com.kanfa.news.info.vo.admin.live.LivePageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.Activity;
import com.kanfa.news.info.mapper.ActivityMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 活动表
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-10 17:01:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityBiz extends BaseBiz<ActivityMapper,Activity> {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityVoteMapper activityVoteMapper;
    @Autowired
    private ActivityVoteContentMapper activityVoteContentMapper;
    @Autowired
    private ActivitySkyMapper activitySkyMapper;

    /**
     * 新增建言/投票活动
     * @param
     * @return
     */
    public void insertVoteActivity(VoteActivityAddInfo entity){
        //未删除
        entity.setIsDelete(0);
        ////活动类型type 1投票 3、志在蓝天  4、政法先锋
        entity.setType(1);
        //新增活动
        Activity activity = new Activity();
        BeanUtils.copyProperties(entity,activity);
        activityMapper.addVoteActivity(activity);
        entity.setActivityId(activity.getId());
        //绑定activityVote
        ActivityVote activityVote = new ActivityVote();
        BeanUtils.copyProperties(entity,activityVote);
        activityVoteMapper.insertActivityVote(activityVote);
        //绑定content活动详情
        ActivityVoteContent activityVoteContent = new ActivityVoteContent();
        activityVoteContent.setActivityVoteId(activityVote.getId());
        activityVoteContent.setContent(entity.getContent());
        activityVoteContentMapper.insertSelective(activityVoteContent);
    }

    /**
     * 得到新增建言/投票活动的详情
     * @param
     * @return
     */
    public VoteActivityAddInfo getOneVoteActivity(Integer id){
        VoteActivityAddInfo voteActivityAddInfo = new VoteActivityAddInfo();
        //得到Activity
        Activity activity = activityMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(activity,voteActivityAddInfo);
        //得到ActivityVote
        ActivityVote activityVote = new ActivityVote();
        activityVote.setActivityId(id);
        ActivityVote activityVote1 = activityVoteMapper.selectOne(activityVote);
        BeanUtils.copyProperties(activityVote1,voteActivityAddInfo);
        voteActivityAddInfo.setActivityVoteId(activityVote1.getId());
        //得到kf_activity_vote_content中content
        ActivityVoteContent activityVoteContent = new ActivityVoteContent();
        activityVoteContent.setActivityVoteId(activityVote1.getId());
        ActivityVoteContent activityVoteContent1 = activityVoteContentMapper.selectOne(activityVoteContent);
        BeanUtils.copyProperties(activityVoteContent1,voteActivityAddInfo);
        voteActivityAddInfo.setActivityVoteContentId(activityVoteContent1.getId());
        voteActivityAddInfo.setId(id);
        return voteActivityAddInfo;
    }


    /**
     * 新增建言/投票活动的分页显示
     * @param
     * @return
     */
    public TableResultResponse<VoteActivityInfo> getVoteActivityPage(VoteActivityInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //建言/投票活动
        entity.setType(1);
        entity.setIsDelete(0);
        List<VoteActivityInfo> list = activityMapper.getVoteActivityPage(entity);
        return new TableResultResponse<VoteActivityInfo>(result.getTotal(),list);
    }


    /**
     * 优惠券活动的分页显示
     * @param
     * @return
     */
    public TableResultResponse<CouponsActivityPageInfo> getCouponsActivityPage(CouponsActivityPageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //优惠券活动
        entity.setType(2);
        entity.setIsDelete(0);
        List<CouponsActivityPageInfo> list = activityMapper.getCouponsActivityPage(entity);
        return new TableResultResponse<CouponsActivityPageInfo>(result.getTotal(),list);
    }

    /**
     * 青春微普法大赛活动 新增
     * @param
     * @return
     */
    public void addYouthActivity(YouthActivityAddInfo entity){
        //设置为 青春微普法大赛类型
        entity.setType(5);
        entity.setIsDelete(0);
        //新增 青春微普法活动
        Activity activity = new Activity();
        BeanUtils.copyProperties(entity,activity);
        activityMapper.addVoteActivity(activity);
        //绑定 activitySky
        ActivitySky activitySky = new ActivitySky();
        activitySky.setActivityId(activity.getId());
        activitySky.setNeedLogin(entity.getNeedLogin());
        activitySky.setStatus(entity.getStatus());
        activitySky.setVotePeriod(entity.getVotePeriod());
        activitySkyMapper.insertSelective(activitySky);

    }


    /**
     * 青春微普法大赛活动 得到详情
     * @param
     * @return
     */
    public  YouthActivityAddInfo getOneYouthActivity(Integer id){
        YouthActivityAddInfo youthActivityAddInfo = new YouthActivityAddInfo();
        //得到 活动的信息
        Activity activity = activityMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(activity,youthActivityAddInfo);
        //得到 activitySky 的信息
        ActivitySky activitySky = new ActivitySky();
        activitySky.setActivityId(id);
        ActivitySky activitySky1 = activitySkyMapper.selectOne(activitySky);
        youthActivityAddInfo.setNeedLogin(activitySky1.getNeedLogin());
        youthActivityAddInfo.setVotePeriod(activitySky1.getVotePeriod());
        youthActivityAddInfo.setActivitySkyId(activitySky1.getId());
        return youthActivityAddInfo;

    }


    /**
     * 青春微普法大赛活动 分页及搜索
     * @param
     * @return
     */
    public TableResultResponse<YouthActivityPageInfo> getYouthActivityPage(YouthActivityPageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //青春微普法大赛活动
        entity.setType(5);
        entity.setIsDelete(0);
        List<YouthActivityPageInfo> list = activityMapper.getYouthActivityPage(entity);
        return new TableResultResponse<YouthActivityPageInfo>(result.getTotal(),list);
    }

    /**
     * 政法先锋活动 分页及搜索
     * @param
     * @return
     */
    public TableResultResponse<PioneerActivityPageInfo> getPioneerActivityPage(PioneerActivityPageInfo entity){
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //政法先锋
        entity.setType(4);
        entity.setIsDelete(0);
        List<PioneerActivityPageInfo> pioneerActivityPage = activityMapper.getPioneerActivityPage(entity);
        return new TableResultResponse<PioneerActivityPageInfo>(result.getTotal(),pioneerActivityPage);
    }




}