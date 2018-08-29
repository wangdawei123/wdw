package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ActivityBlueSkyComment;
import com.kanfa.news.admin.feign.IActivityBlueSkyCommentServiceFeign;
import com.kanfa.news.admin.feign.IActivityBlueSkyServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyAddInfo;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyCommentPageInfo;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyCommentShow;
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
 * on 2018/4/17 18:08
 */
@RestController
@RequestMapping("activityBlueSkyComment")
public class ActivityBlueSkyCommentRest {

    @Autowired
    private IActivityBlueSkyCommentServiceFeign activityBlueSkyCommentServiceFeign;
    @Autowired
    private IActivityBlueSkyServiceFeign activityBlueSkyServiceFeign;

    /**
     * 青春普法
     * 候选人评论列表 的分页及按审核状态搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityBlueSkyCommentPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityBlueSkyCommentPageInfo> getActivityBlueSkyCommentPage(@RequestBody ActivityBlueSkyCommentPageInfo entity){
        TableResultResponse<ActivityBlueSkyCommentPageInfo> list = activityBlueSkyCommentServiceFeign.getActivityBlueSkyCommentPage(entity);
        return list;
    }

    /**
     * 青春普法
     * 候选人评论 得到详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneActivityBlueSkyComment", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityBlueSkyCommentShow> getOneActivityBlueSkyComment(@RequestParam("id")Integer id){
        ObjectRestResponse<ActivityBlueSkyComment> activityBlueSkyCommentObjectRestResponse = activityBlueSkyCommentServiceFeign.get(id);
        ActivityBlueSkyComment data = activityBlueSkyCommentObjectRestResponse.getData();
        ActivityBlueSkyCommentShow activityBlueSkyCommentShow = new ActivityBlueSkyCommentShow();
        BeanUtils.copyProperties(data,activityBlueSkyCommentShow);
        ActivityBlueSkyAddInfo oneActivityBlueSky = activityBlueSkyServiceFeign.getOneActivityBlueSky(data.getBlueSkyPeopleId());
        activityBlueSkyCommentShow.setActivityBlueSkyName(oneActivityBlueSky.getName());
        ObjectRestResponse<ActivityBlueSkyCommentShow> activityBlueSkyCommentShowObjectRestResponse = new ObjectRestResponse<>();
        activityBlueSkyCommentShowObjectRestResponse.setData(activityBlueSkyCommentShow);
        return activityBlueSkyCommentShowObjectRestResponse;

    }


    /**
     * 青春普法
     * 候选人评论 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ActivityBlueSkyComment> update(@PathVariable("id") Integer id, @RequestBody ActivityBlueSkyComment entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        return activityBlueSkyCommentServiceFeign.update(id,entity);
    }

    /**
     * 青春普法
     * 候选人评论 删除
     * @param
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityBlueSkyComment> delete(@RequestParam("id")Integer id ) {
        ActivityBlueSkyComment activityBlueSkyComment = new ActivityBlueSkyComment();
        activityBlueSkyComment.setId(id);
        activityBlueSkyComment.setIsDelete(1);
        return activityBlueSkyCommentServiceFeign.update(id,activityBlueSkyComment);
    }


    /**
     * 青春普法
     * 候选人评论 审核通过
     * @param
     * @return
     */
    @RequestMapping(value = "throughReview", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityBlueSkyComment> throughReview(@RequestParam("id")Integer id ) {
        ActivityBlueSkyComment activityBlueSkyComment = new ActivityBlueSkyComment();
        activityBlueSkyComment.setId(id);
        activityBlueSkyComment.setStatus(2);
        return activityBlueSkyCommentServiceFeign.update(id,activityBlueSkyComment);
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
                ActivityBlueSkyComment activityBlueSkyComment = new ActivityBlueSkyComment();
                activityBlueSkyComment.setId(id);
                activityBlueSkyComment.setIsDelete(1);
                activityBlueSkyCommentServiceFeign.update(id,activityBlueSkyComment);
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
                ActivityBlueSkyComment activityBlueSkyComment = new ActivityBlueSkyComment();
                activityBlueSkyComment.setId(id);
                activityBlueSkyComment.setStatus(2);
                activityBlueSkyCommentServiceFeign.update(id,activityBlueSkyComment);
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
