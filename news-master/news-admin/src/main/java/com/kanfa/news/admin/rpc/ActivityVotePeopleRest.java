package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ActivityVotePeople;
import com.kanfa.news.admin.feign.IActivityVotePeopleServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityVotePeopleAddInfo;
import com.kanfa.news.admin.vo.activity.ActivityVotePeopleAddShow;
import com.kanfa.news.admin.vo.activity.ActivityVotePeoplePageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/12 11:46
 */

@RestController
@RequestMapping("/activityVotePeople")
public class ActivityVotePeopleRest {

    @Autowired
    private IActivityVotePeopleServiceFeign activityVotePeopleServiceFeign;


    /**
     * 建言/投票活动中的候选人分页信息及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getVoteActivityPeoplePage", method = RequestMethod.POST)
    public TableResultResponse<ActivityVotePeoplePageInfo> getVoteActivityPage(@RequestBody ActivityVotePeoplePageInfo entity){
        TableResultResponse<ActivityVotePeoplePageInfo> voteActivityPeoplePagePage = activityVotePeopleServiceFeign.getVoteActivityPeoplePage(entity);
        return voteActivityPeoplePagePage;
    }

    /**
     * 建言/投票活动中的候选人 新增
     * @param
     * @return
     */
    @RequestMapping(value = "/voteActivityPeopleAdd", method = RequestMethod.POST)
    public ObjectRestResponse  voteActivityPeopleAdd(@RequestBody ActivityVotePeopleAddInfo entity){
        if (entity.getDetail()==null){
            entity.setDetail("");
        }
        if (entity.getOrderNumber()==null){
            entity.setOrderNumber(0);
        }
        if (entity.getPresetComment()==null){
            entity.setPresetComment(0);
        }
        if (entity.getPresetVote()==null){
            entity.setPresetVote(0);
        }
        activityVotePeopleServiceFeign.voteActivityPeopleAdd(entity);
        String msg ="新增候选人成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 得到建言/投票活动中的候选人信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneActivityVotePeople", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityVotePeopleAddShow> getOneActivityVotePeople(@RequestParam("id")Integer id ){
        ActivityVotePeopleAddInfo oneActivityVotePeople = activityVotePeopleServiceFeign.getOneActivityVotePeople(id);
        ActivityVotePeopleAddShow activityVotePeopleAddShow = new ActivityVotePeopleAddShow();
        BeanUtils.copyProperties(oneActivityVotePeople,activityVotePeopleAddShow);
        ObjectRestResponse<ActivityVotePeopleAddShow> activityVotePeopleAddShowObjectRestResponse = new ObjectRestResponse<>();
        activityVotePeopleAddShowObjectRestResponse.setData(activityVotePeopleAddShow);
        return activityVotePeopleAddShowObjectRestResponse;
    }

    /**
     * 建言/投票活动中的候选人 新增
     * @param
     * @return
     */
    @RequestMapping(value = "/updateActivityVotePeople", method = RequestMethod.POST)
    public ObjectRestResponse  updateActivityVotePeople(@RequestBody ActivityVotePeopleAddInfo entity){
        if (entity.getDetail()==null){
            entity.setDetail("");
        }
        if (entity.getOrderNumber()==null){
            entity.setOrderNumber(0);
        }
        if (entity.getPresetComment()==null){
            entity.setPresetComment(0);
        }
        if (entity.getPresetVote()==null){
            entity.setPresetVote(0);
        }
        activityVotePeopleServiceFeign.updateActivityVotePeople(entity);
        String msg ="编辑候选人成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse delete(@PathVariable("id")Integer id){
        ActivityVotePeople activityVotePeople = new ActivityVotePeople();
        activityVotePeople.setId(id);
        activityVotePeople.setStatus(0);
        return activityVotePeopleServiceFeign.update(id, activityVotePeople);
    }


}
