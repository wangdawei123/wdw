package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ActivityVotePeopleComment;
import com.kanfa.news.admin.feign.IActivityVotePeopleCommentServiceFeign;
import com.kanfa.news.admin.feign.IActivityVotePeopleServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityVotePeopleAddInfo;
import com.kanfa.news.admin.vo.activity.ActivityVotePeopleCommentPageInfo;
import com.kanfa.news.admin.vo.activity.ActivityVotePeopleCommentShow;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/4/13 14:30
 */

@RestController
@RequestMapping("/activityVotePeopleComment")
public class ActivityVotePeopleCommentRest {

    @Autowired
    private IActivityVotePeopleCommentServiceFeign activityVotePeopleCommentServiceFeign;
    @Autowired
    private IActivityVotePeopleServiceFeign activityVotePeopleServiceFeign;

    /**
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityVotePeopleCommentPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityVotePeopleCommentPageInfo> getActivityVotePeopleCommentPage(@RequestBody ActivityVotePeopleCommentPageInfo entity){
        TableResultResponse<ActivityVotePeopleCommentPageInfo> voteActivityPeoplePagePage = activityVotePeopleCommentServiceFeign.getActivityVotePeopleCommentPage(entity);
        return voteActivityPeoplePagePage;
    }


    /**
     * 候选人评论列表 的 详情展示
     * @param
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityVotePeopleCommentShow> get(@PathVariable("id") Integer id) {
        ObjectRestResponse<ActivityVotePeopleComment> activityVotePeopleCommentObjectRestResponse = activityVotePeopleCommentServiceFeign.get(id);
        ActivityVotePeopleComment data = activityVotePeopleCommentObjectRestResponse.getData();
        ActivityVotePeopleCommentShow activityVotePeopleCommentShow = new ActivityVotePeopleCommentShow();
        BeanUtils.copyProperties(data,activityVotePeopleCommentShow);
        //得到候选人的名字
        ActivityVotePeopleAddInfo oneActivityVotePeople = activityVotePeopleServiceFeign.getOneActivityVotePeople(data.getActivityVotePeopleId());
        activityVotePeopleCommentShow.setActivityVotePeopleName(oneActivityVotePeople.getName());
        ObjectRestResponse<ActivityVotePeopleCommentShow> activityVotePeopleCommentShowObjectRestResponse = new ObjectRestResponse<>();
        activityVotePeopleCommentShowObjectRestResponse.setData(activityVotePeopleCommentShow);
        return activityVotePeopleCommentShowObjectRestResponse;
    }


    /**
     * 候选人评论列表 的 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ActivityVotePeopleComment> update(@PathVariable("id") Integer id, @RequestBody ActivityVotePeopleComment entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        return activityVotePeopleCommentServiceFeign.update(id, entity);
    }


    /**
     * 候选人评论列表 的 删除
     * @param
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ActivityVotePeopleComment> delete(@PathVariable("id") Integer id, @RequestBody ActivityVotePeopleComment entity) {
        entity.setId(id);
        entity.setIsDelete(1);
        entity.setUpdateTime(new Date());
        return activityVotePeopleCommentServiceFeign.update(id, entity);
    }

    /**
     * 候选人评论列表 的 通过审核
     * @param
     * @return
     */
    @RequestMapping(value = "/throughReview/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ActivityVotePeopleComment> throughReview(@PathVariable("id") Integer id, @RequestBody ActivityVotePeopleComment entity) {
        entity.setId(id);
        entity.setStatus(2);
        entity.setUpdateTime(new Date());
        return activityVotePeopleCommentServiceFeign.update(id, entity);
    }


    /**
     * 候选人评论列表 的 批量删除
     * @param
     * @return
     */
    @RequestMapping(value = "/moreDeletes", method = RequestMethod.POST)
    public ObjectRestResponse moreDeletes(@RequestBody Map<String, List<Integer>> params) {
        List<Integer> ids = params.get("ids");
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        int count = 0;
        if (ids.size() > 0) {
            for (Integer id : ids) {
                ActivityVotePeopleComment activityVotePeopleComment = new ActivityVotePeopleComment();
                activityVotePeopleComment.setId(id);
                activityVotePeopleComment.setIsDelete(1);
                activityVotePeopleComment.setUpdateTime(new Date());
                activityVotePeopleCommentServiceFeign.update(id, activityVotePeopleComment);
                count++;
            }
        } else {
            String msg = "请选择要删除的id不能为空";
            stringObjectRestResponse.setData(msg);
            return stringObjectRestResponse;
        }
        String msg = "删除" + count + "个";
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 候选人评论列表 的 批量通过审核
     * @param
     * @return
     */

    @RequestMapping(value = "/moreReview", method = RequestMethod.POST)
    public ObjectRestResponse moreReview(@RequestBody Map<String, List<Integer>> params) {
        List<Integer> ids = params.get("ids");
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        int count = 0;
        if (ids.size() > 0) {
            for (Integer id : ids) {
                ActivityVotePeopleComment activityVotePeopleComment = new ActivityVotePeopleComment();
                activityVotePeopleComment.setId(id);
                activityVotePeopleComment.setStatus(2);
                activityVotePeopleComment.setUpdateTime(new Date());
                activityVotePeopleCommentServiceFeign.update(id, activityVotePeopleComment);
                count++;
            }
        } else {
            String msg = "请选择要通过的id不能为空";
            stringObjectRestResponse.setData(msg);
            return stringObjectRestResponse;
        }
        String msg = "审核通过" + count + "个";
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

}
