package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ActivityBlueSky;
import com.kanfa.news.admin.feign.IActivityBlueSkyServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyAddInfo;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyPageInfo;
import com.kanfa.news.admin.vo.activity.ActivityBlueSkyShow;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/17 14:29
 */

@RestController
@RequestMapping("activityBlueSky")
public class ActivityBlueSkyRest {

    @Autowired
    private IActivityBlueSkyServiceFeign activityBlueSkyServiceFeign;

    /**
     *  新增 青春微普法大赛的候选人
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/insertActivityBlueSky", method = RequestMethod.POST)
    public ObjectRestResponse insertActivityBlueSky(@RequestBody ActivityBlueSkyAddInfo entity) {
        if (StringUtils.isEmpty(entity.getName())){
            String msg="学习名称不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getDescription())){
            String msg ="宣言不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getIntroduction())){
            String msg ="简介不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getHeadimage())){
            String msg ="头像不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getImageList()==null){
            String msg ="图集不能为空";
            return getObjectRestResponse(msg);
        }
        if (entity.getUrl()==null){
            entity.setUrl("");
        }
        if (entity.getDuration()==null){
            entity.setDuration("");
        }
        if (entity.getVideoimage()==null){
            entity.setVideoimage("");
        }
        entity.setCreateTime(new Date());
        activityBlueSkyServiceFeign.insertActivityBlueSky(entity);
        String msg ="新增候选人成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     *  得到 青春微普法大赛的候选人
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getOneActivityBlueSky", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityBlueSkyShow> getOneActivityBlueSky(@RequestParam("id")Integer id){
        ActivityBlueSkyAddInfo oneActivityBlueSky = activityBlueSkyServiceFeign.getOneActivityBlueSky(id);
        ActivityBlueSkyShow activityBlueSkyShow = new ActivityBlueSkyShow();
        BeanUtils.copyProperties(oneActivityBlueSky,activityBlueSkyShow);
        ObjectRestResponse<ActivityBlueSkyShow> activityBlueSkyShowObjectRestResponse = new ObjectRestResponse<>();
        activityBlueSkyShowObjectRestResponse.setData(activityBlueSkyShow);
        return activityBlueSkyShowObjectRestResponse;
    }

    /**
     *  编辑 青春微普法大赛的候选人
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateActivityBlueSky", method = RequestMethod.POST)
    public ObjectRestResponse<String> updateActivityBlueSky(@RequestBody ActivityBlueSkyAddInfo entity){
        entity.setUpdateTime(new Date());
        activityBlueSkyServiceFeign.updateActivityBlueSky(entity);
        String msg ="更新候选人成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     *  删除 青春微普法大赛的候选人
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse delete(@PathVariable("id")Integer id){
        ActivityBlueSky activityBlueSky = new ActivityBlueSky();
        activityBlueSky.setId(id);
        activityBlueSky.setIsDelete(0);
        activityBlueSky.setUpdateTime(new Date());
        return activityBlueSkyServiceFeign.update(id,activityBlueSky);
    }

    /**
     * 青春微普法大赛的候选人 分页及查询
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityBlueSkyPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityBlueSkyPageInfo> getActivityBlueSkyPage(@RequestBody ActivityBlueSkyPageInfo entity){
        TableResultResponse<ActivityBlueSkyPageInfo> list = activityBlueSkyServiceFeign.getActivityBlueSkyPage(entity);
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
