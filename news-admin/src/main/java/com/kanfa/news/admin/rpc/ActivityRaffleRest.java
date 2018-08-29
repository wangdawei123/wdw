package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ActivityRaffle;
import com.kanfa.news.admin.feign.IActivityRaffleServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityRafflePageInfo;
import com.kanfa.news.admin.vo.activity.ActivityRaffleShow;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * @author Jezy
 */
@RestController
@RequestMapping("activityRaffle")
public class ActivityRaffleRest {
    @Autowired
    private IActivityRaffleServiceFeign activityRaffleServiceFeign;

    /**
     * 新增抽奖活动
     * @param entity
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ObjectRestResponse<ActivityRaffle> add(@RequestBody ActivityRaffle entity) {
        if (StringUtils.isEmpty(entity.getTitle())&&entity.getTitle().length()<=50){
            String msg="标题不能为空且不能超过50个字符";
            return getObjectRestResponse(msg);
        }else if (entity.getEndTime() == null|| entity.getStartTime()==null){
            String msg ="开始/结束时间不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getBuoy())){
            String msg ="浮标不能为空";
            return getObjectRestResponse(msg);
        }
        if (entity.getStatus()==null) {
            entity.setStatus(0);
        }
        if (entity.getRule()==null){
            entity.setRule("");
        }
        entity.setCreatedUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        return activityRaffleServiceFeign.add(entity);
    }


    /**
     * 抽奖活动  得到详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getOne",method = RequestMethod.GET)
    public ObjectRestResponse<ActivityRaffleShow> getOne(@RequestParam("id")Integer id){
        ObjectRestResponse<ActivityRaffle> activityRaffleObjectRestResponse = activityRaffleServiceFeign.get(id);
        ActivityRaffle data = activityRaffleObjectRestResponse.getData();
        ActivityRaffleShow activityRaffleShow = new ActivityRaffleShow();
        BeanUtils.copyProperties(data,activityRaffleShow);
        ObjectRestResponse<ActivityRaffleShow> activityRaffleShowObjectRestResponse = new ObjectRestResponse<>();
        activityRaffleShowObjectRestResponse.setData(activityRaffleShow);
        return activityRaffleShowObjectRestResponse;
    }

    /**
     * 抽奖活动  编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ActivityRaffle> update(@PathVariable("id") Integer id, @RequestBody ActivityRaffle entity) {
        entity.setId(id);
        return activityRaffleServiceFeign.update(id, entity);
    }

    /**
     * 抽奖活动  删除
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityRaffle> delete(@RequestParam("id")Integer id){
        ActivityRaffle activityRaffle = new ActivityRaffle();
        activityRaffle.setId(id);
        activityRaffle.setIsDelete(1);
        return activityRaffleServiceFeign.update(id, activityRaffle);
    }

    /**
     * 抽奖活动的分页及搜索
     * @return
     */
    @RequestMapping(value = "/getActivityRafflePage", method = RequestMethod.POST)
    public TableResultResponse<ActivityRafflePageInfo> getActivityRafflePage(@RequestBody ActivityRafflePageInfo entity){
        TableResultResponse<ActivityRafflePageInfo> list = activityRaffleServiceFeign.getPioneerActivityPage(entity);
        return list;
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }


}
