package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.ActivityVotePeople;
import com.kanfa.news.app.entity.ActivityVotePeopleImage;
import com.kanfa.news.app.mapper.ActivityVotePeopleImageMapper;
import com.kanfa.news.app.mapper.ActivityVotePeopleMapper;
import com.kanfa.news.app.vo.admin.activity.ActivityVotePeopleAddInfo;
import com.kanfa.news.app.vo.admin.activity.ActivityVotePeoplePageInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 活动--投票--投票人信息表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-04-12 11:24:51
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityVotePeopleBiz extends BaseBiz<ActivityVotePeopleMapper,ActivityVotePeople> {

    @Autowired
    private ActivityVotePeopleMapper activityVotePeopleMapper;
    @Autowired
    private ActivityVotePeopleImageMapper activityVotePeopleImageMapper;


    /**
     * 建言/投票活动中的候选人分页信息及搜索
     * @param
     * @return
     */
    public TableResultResponse<ActivityVotePeoplePageInfo> getVoteActivityPeoplePage(ActivityVotePeoplePageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //设置筛选条件
        entity.setStatus(1);
       List<ActivityVotePeoplePageInfo> list = activityVotePeopleMapper.getActivityVotePeoplePage(entity);
       return new TableResultResponse<ActivityVotePeoplePageInfo>(result.getTotal(),list);
    }


    /**
     * 建言/投票活动中的候选人 新增
     * @param
     * @return
     */
    public void activityVotePeopleAdd(ActivityVotePeopleAddInfo entity){
        //新增候选人信息 kf_activity_vote_people
        entity.setCreateTime(new Date());
        ActivityVotePeople activityVotePeople = new ActivityVotePeople();
        BeanUtils.copyProperties(entity,activityVotePeople);
        activityVotePeopleMapper.ActivityVotePeopleAdd(activityVotePeople);
        Integer activityVotePeopleId = activityVotePeople.getId();
        //新增候选人图片 kf_activity_vote_people_image
        if (entity.getImage()!= null && entity.getImage().size()<=10){
            for (String image:entity.getImage()){
                ActivityVotePeopleImage activityVotePeopleImage = new ActivityVotePeopleImage();
                activityVotePeopleImage.setActivityVotePeopleId(activityVotePeopleId);
                Integer integer = activityVotePeopleImageMapper.selectMaxSortById(activityVotePeopleId);
                activityVotePeopleImage.setSort(integer+1);
                activityVotePeopleImage.setImage(image);
                activityVotePeopleImageMapper.insertSelective(activityVotePeopleImage);
            }

        }

    }

    /**
     * 得到建言/投票活动中的候选人详细信息
     * @param
     * @return
     */
    public ActivityVotePeopleAddInfo getOneActivityVotePeople(Integer id){
        ActivityVotePeopleAddInfo activityVotePeopleAddInfo = new ActivityVotePeopleAddInfo();
        //得到候选人的详细信息
        ActivityVotePeople activityVotePeople = activityVotePeopleMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(activityVotePeople,activityVotePeopleAddInfo);
        activityVotePeopleAddInfo.setActivityVoteId(activityVotePeople.getActivityVoteId());
        Integer activityVotePeopleId = activityVotePeople.getId();
        //得到候选人图片
        List<String> imageList = activityVotePeopleImageMapper.getImageList(activityVotePeopleId);
        activityVotePeopleAddInfo.setImage(imageList);
        return activityVotePeopleAddInfo ;
    }


    /**
     * 建言/投票活动中的候选人 新增图片
     * @param
     * @return
     */
    public void activityVotePeopleImageAdd(ActivityVotePeopleAddInfo entity){
        //新增候选人图片 kf_activity_vote_people_image
        if (entity.getImage()!= null&& entity.getImage().size()<=10){
            for (String image:entity.getImage()){
                ActivityVotePeopleImage activityVotePeopleImage = new ActivityVotePeopleImage();
                activityVotePeopleImage.setActivityVotePeopleId(entity.getId());
                Integer integer = activityVotePeopleImageMapper.selectMaxSortById(entity.getId());
                if (integer==null) {
                    activityVotePeopleImage.setSort(1);
                }
                activityVotePeopleImage.setImage(image);
                activityVotePeopleImageMapper.insertSelective(activityVotePeopleImage);
            }
        }
    }


}