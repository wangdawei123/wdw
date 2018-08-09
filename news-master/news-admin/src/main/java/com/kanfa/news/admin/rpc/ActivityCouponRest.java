package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Activity;
import com.kanfa.news.admin.entity.ActivityCoupon;
import com.kanfa.news.admin.feign.IActivityCouponServiceFeign;
import com.kanfa.news.admin.feign.IActivityServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityCouponInfo;
import com.kanfa.news.admin.vo.activity.ActivityCouponShow;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/4/23 14:06
 */
@RestController
@RequestMapping("activityCoupon")
public class ActivityCouponRest {

    @Autowired
    private IActivityCouponServiceFeign activityCouponServiceFeign;
    @Autowired
    private IActivityServiceFeign activityServiceFeign;
    /**
     * 新增 优惠券
     * @param
     * @return
     */
    @RequestMapping(value = "/insertActivityCoupon",method = RequestMethod.POST)
    public ObjectRestResponse insertActivityCoupon(@RequestBody  ActivityCouponInfo entity){
        if (StringUtils.isEmpty(entity.getMerchantName())){
            String msg="商家名称不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getName())){
            String msg="券码名称不能为空";
            return getObjectRestResponse(msg);
        }else if(entity.getCodes() == null){
            String msg="参与渠道不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getName())){
            String msg="券码名称不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getContent())){
            String msg="使用规则不能为空";
            return getObjectRestResponse(msg);
        }
        ObjectRestResponse<Activity> activityObjectRestResponse = activityServiceFeign.get(entity.getActivityId());
        Activity data = activityObjectRestResponse.getData();
        Date startTime = data.getStartTime();
        Date endTime = data.getEndTime();
        if (entity.getStartTime().getTime() < startTime.getTime() || entity.getStartTime().getTime() > endTime.getTime()){
            String msg="可用时间必须在优惠券活动时间范围内";
            return getObjectRestResponse(msg);
        }
        if (entity.getEndTime().getTime() < startTime.getTime() || entity.getEndTime().getTime() > endTime.getTime()){
            String msg="可用时间必须在优惠券活动时间范围内";
            return getObjectRestResponse(msg);
        }
        entity.setCreatedUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        activityCouponServiceFeign.insertActivityCoupon(entity);
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        String msg = "新增优惠券成功";
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 得到详情 优惠券
     * @param
     * @return
     */
    @RequestMapping(value = "/getOne",method = RequestMethod.GET)
    public ObjectRestResponse<ActivityCouponShow> getOne(@RequestParam("id")Integer id){
        ObjectRestResponse<ActivityCoupon> activityCouponObjectRestResponse = activityCouponServiceFeign.get(id);
        ActivityCoupon data = activityCouponObjectRestResponse.getData();
        ActivityCouponShow activityCouponShow = new ActivityCouponShow();
        BeanUtils.copyProperties(data,activityCouponShow);
        ObjectRestResponse<ActivityCouponShow> activityCouponShowObjectRestResponse = new ObjectRestResponse<>();
        activityCouponShowObjectRestResponse.setData(activityCouponShow);
        return activityCouponShowObjectRestResponse;
    }

    /**
     * 编辑 优惠券
     * @param
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ActivityCoupon> update(@PathVariable("id") Integer id, @RequestBody ActivityCoupon entity) {
        entity.setId(id);
        return activityCouponServiceFeign.update(id, entity);
    }

    /**
     * 删除 优惠券
     * @param
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ObjectRestResponse<ActivityCoupon> delete(@RequestParam("id")Integer id){
        ActivityCoupon activityCoupon = new ActivityCoupon();
        activityCoupon.setId(id);
        activityCoupon.setIsDelete(1);
        return activityCouponServiceFeign.update(id, activityCoupon);
    }

    /**
     * 批量删除 优惠券
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
                ActivityCoupon activityCoupon = new ActivityCoupon();
                activityCoupon.setId(id);
                activityCoupon.setIsDelete(1);
                activityCouponServiceFeign.update(id, activityCoupon);
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
     * 优惠券 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityCouponPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityCouponInfo> getActivityCouponPage(@RequestBody ActivityCouponInfo entity){
        return  activityCouponServiceFeign.getActivityCouponPage(entity);
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
