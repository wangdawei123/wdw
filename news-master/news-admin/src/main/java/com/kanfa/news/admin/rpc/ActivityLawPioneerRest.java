package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ActivityLawPioneer;
import com.kanfa.news.admin.feign.IActivityLawPioneerServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityLawPioneerPageInfo;
import com.kanfa.news.admin.vo.activity.ActivityLawPioneerShow;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/18 18:07
 */
@RestController
@RequestMapping("activityLawPioneer")
public class ActivityLawPioneerRest {

    @Autowired
    private IActivityLawPioneerServiceFeign activityLawPioneerServiceFeign;

    /**
     * 政法先锋活动 政法先锋-->机构或个人分页及查询
     * @param
     * @return
     */
    @RequestMapping(value = "/getPioneerActivityPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityLawPioneerPageInfo> getPioneerActivityPage(@RequestBody ActivityLawPioneerPageInfo entity){
        TableResultResponse<ActivityLawPioneerPageInfo> list = activityLawPioneerServiceFeign.getPioneerActivityPage(entity);
        return list;
    }

    /**
     * 政法先锋活动 政法先锋-->机构 新增
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<ActivityLawPioneer> add(@RequestBody ActivityLawPioneer entity) {

        if (StringUtils.isEmpty(entity.getTitle())){
            String msg="标题不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getIcon())){
            String msg ="图标不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getDescription())){
            String msg ="简介不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getBackgroundImage())){
            String msg ="背景图不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getSort()==null){
            String msg ="排序值不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreateUid(BaseContextHandler.getUserID());
        entity.setCreateTime(new Date());
        Integer maxSort = activityLawPioneerServiceFeign.getMaxSort(entity.getActivityLawId());
        if (entity.getSort()==null){
            if (maxSort==null){
                maxSort = 0;
            }
            entity.setSort(maxSort+1);
        }
        return activityLawPioneerServiceFeign.add(entity);
    }


    /**
     * 政法先锋活动 政法先锋-->机构 得到详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityLawPioneerShow> getOne(@RequestParam("id")Integer id){
        ObjectRestResponse<ActivityLawPioneer> activityLawPioneerObjectRestResponse = activityLawPioneerServiceFeign.get(id);
        ActivityLawPioneer data = activityLawPioneerObjectRestResponse.getData();
        ActivityLawPioneerShow activityLawPioneerShow = new ActivityLawPioneerShow();
        BeanUtils.copyProperties(data,activityLawPioneerShow);
        ObjectRestResponse<ActivityLawPioneerShow> activityLawPioneerShowObjectRestResponse = new ObjectRestResponse<>();
        activityLawPioneerShowObjectRestResponse.setData(activityLawPioneerShow);
        return activityLawPioneerShowObjectRestResponse;
    }

    /**
     * 政法先锋活动 政法先锋-->机构 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ActivityLawPioneer> update(@PathVariable("id") Integer id,@RequestBody ActivityLawPioneer entity) {
        entity.setId(id);
        return activityLawPioneerServiceFeign.update(id, entity);
    }

    /**
     * 政法先锋活动 政法先锋-->机构 删除
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ObjectRestResponse delete(@RequestParam("id")Integer id){
        ActivityLawPioneer activityLawPioneer = new ActivityLawPioneer();
        activityLawPioneer.setId(id);
        activityLawPioneer.setIsDelete(0);
        return activityLawPioneerServiceFeign.update(id,activityLawPioneer);
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
