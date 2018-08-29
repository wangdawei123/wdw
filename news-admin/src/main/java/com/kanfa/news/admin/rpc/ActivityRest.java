package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Activity;
import com.kanfa.news.admin.feign.IActivityServiceFeign;
import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.vo.activity.*;
import com.kanfa.news.admin.vo.content.ActivityInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 活动
 * @author Jiqc
 * @date 2018/3/18 17:59
 */
@RestController
@RequestMapping("/activity")
public class ActivityRest extends BaseRPC{

    @Autowired
    private IContentServiceFeign contentServiceFeign;

    @Autowired
    private IActivityServiceFeign activityServiceFeign;

    /**
     * 添加活动
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addContentActivity", method = RequestMethod.POST)
    public ObjectRestResponse<ActivityInfo> addContentActivity(@RequestBody ActivityInfo entity) {
        if (StringUtils.isEmpty(entity.getTitle())){
            String msg="活动标题不能为空";
            return getObjectRestResponse(msg);
        }else if(entity.getAppChannelIdList()==null || entity.getAppChannelIdList().size()<=0){
            String msg="活动频道不能为空";
            return getObjectRestResponse(msg);
        }else if(entity.getImage()==null){
            String msg="活动缩略图不能为空";
            return getObjectRestResponse(msg);
        } else if(entity.getCardType()==null){
            String msg="活动不能为空";
            return getObjectRestResponse(msg);
        }
        //活动card为h5空链接不能为空
        //13:投票活动
        if(entity.getCardType().equals(13)){
            if(StringUtils.isEmpty(entity.getCustomUrl())){
                return getObjectRestResponse("外链接不能为空");
            }
            entity.setContentType(NewsEnumsConsts.ContentOfContentType.Activity.getKey());
        }else {
            entity.setContentType(NewsEnumsConsts.ContentOfContentType.PoliticalAndLaw.getKey());
        }
        //是否是保存草稿 显示状态，0审核列不显示，1审核列表显示
        if(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey().equals(entity.getIsCheck())){
            //是否为审核 0未审核
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.OTHER.key());
        }else{
            entity.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey());
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        }
        if(entity.getTitle().length()>60){
            return getObjectRestResponse("标题限60个字符");
        }
        //初始化频道属性值
        entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        entity.setCreateTime(new Date());
        entity.setContentState(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        entity.setIp(ClientUtil.getClientIp(request));
        if(entity.getCreateUid()==null){
            entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        }
        if(entity.getIsLegal()==null){
            entity.setIsLegal(0);
        }
        if(entity.getRecommendWeight()==null){
            entity.setRecommendWeight(60);
        }
        return contentServiceFeign.addContentActivity(entity);
    }

    /**
     * 更新活动内容
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateContentActivity", method = RequestMethod.PUT)
    public ObjectRestResponse<ActivityInfo> updateContentActivity(@RequestBody ActivityInfo entity) {
        if(entity.getId()==null){
            String msg="内容ID不能为空";
            return getObjectRestResponse(msg);
        }
        //8：外链广告,9:直播，10：并列四图,11:投票活动
        if(NewsEnumsConsts.ChannelContentOfCardType.OutUrl.key().equals(entity.getCardType())){
            if(StringUtils.isEmpty(entity.getCustomUrl())){
                return getObjectRestResponse("外链接不能为空");
            }
            entity.setContentType(NewsEnumsConsts.ContentOfContentType.Activity.getKey());
        }else {
            entity.setContentType(NewsEnumsConsts.ContentOfContentType.PoliticalAndLaw.getKey());
        }
        //是否是保存草稿 显示状态，0审核列不显示，1审核列表显示
        if(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey().equals(entity.getIsCheck())){
            //是否为审核 0未审核
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.OTHER.key());
        }else{
            entity.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey());
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        }
        //活动标识
        entity.setContentType(NewsEnumsConsts.ContentOfContentType.Activity.getKey());
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setIp(ClientUtil.getClientIp(request));
        return contentServiceFeign.updateContentActivity(entity);
    }

    @RequestMapping(value = "/getContentActivity/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ActivityInfo> getContentActivity(@PathVariable("id") Integer id) {
        if(id == null){
            String msg="内容未找到";
            return getObjectRestResponse(msg);
        }
        return contentServiceFeign.getContentActivity(id);
    }


    /**
     * 新增建言/投票活动
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/insertVoteActivity", method = RequestMethod.POST)
    public ObjectRestResponse insertVoteActivity(@RequestBody VoteActivityAddInfo entity) {
        if (StringUtils.isEmpty(entity.getTitle())){
            String msg="标题不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getEndTime() == null|| entity.getStartTime()==null){
            String msg ="开始/结束时间不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getContent() == null){
            String msg ="活动详情不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreatedUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreatedTime(new Date());
        activityServiceFeign.insertVoteActivity(entity);
        String msg ="新增新增建言/投票活动成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     *  得到 建言/投票活动详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneVoteActivity", method = RequestMethod.GET)
    public ObjectRestResponse<VoteActivityAddShow> getOneVoteActivity(@RequestParam("id")Integer id){
        VoteActivityAddInfo oneVoteActivity = activityServiceFeign.getOneVoteActivity(id);
        VoteActivityAddShow voteActivityAddShow = new VoteActivityAddShow();
        BeanUtils.copyProperties(oneVoteActivity,voteActivityAddShow);
        ObjectRestResponse<VoteActivityAddShow> voteActivityAddShowObjectRestResponse = new ObjectRestResponse<>();
        voteActivityAddShowObjectRestResponse.setData(voteActivityAddShow);
        return voteActivityAddShowObjectRestResponse;
    }


    /**
     * 编辑 建言/投票活动
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateVoteActivity", method = RequestMethod.POST)
    public ObjectRestResponse updateVoteActivity(@RequestBody VoteActivityAddInfo entity) {
        if (StringUtils.isEmpty(entity.getTitle())){
            String msg="标题不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getEndTime() == null|| entity.getStartTime()==null){
            String msg ="开始/结束时间不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getContent() == null){
            String msg ="活动详情不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreatedUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreatedTime(new Date());
        activityServiceFeign.updateVoteActivity(entity);
        String msg ="编辑建言/投票活动成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 建言/投票活动 分页及搜索
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getVoteActivityPage", method = RequestMethod.POST)
    public TableResultResponse<VoteActivityInfo> getVoteActivityPage(@RequestBody VoteActivityInfo entity){
        TableResultResponse<VoteActivityInfo> voteActivityPage = activityServiceFeign.getVoteActivityPage(entity);
        return voteActivityPage;
    }

    /**
     * 建言/投票活动 删除
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse delete(@PathVariable("id") Integer id) {
        Activity activity = new Activity();
        activity.setId(id);
        activity.setIsDelete(1);
        return activityServiceFeign.update(id, activity);
    }

    /**
     * 优惠券活动 新增
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<Activity> add(@RequestBody Activity entity) {
        //初始化属性
        entity.setIsDelete(0);
        entity.setCreatedUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreatedTime(new Date());
        //优惠卷活动
        entity.setType(2);
        return activityServiceFeign.add(entity);
    }

    /**
     * 优惠券活动 分页显示及搜索
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getCouponsActivityPage", method = RequestMethod.POST)
    public TableResultResponse<CouponsActivityPageInfo> getCouponsActivityPage(@RequestBody CouponsActivityPageInfo entity){
        TableResultResponse<CouponsActivityPageInfo> voteActivityPage = activityServiceFeign.getCouponsActivityPage(entity);
        return voteActivityPage;
    }


    /**
     * 优惠券活动 得到详情
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<CouponsActivityShow> get(@PathVariable("id") Integer id) {
        ObjectRestResponse<Activity> activityObjectRestResponse = activityServiceFeign.get(id);
        Activity data = activityObjectRestResponse.getData();
        CouponsActivityShow couponsActivityShow = new CouponsActivityShow();
        BeanUtils.copyProperties(data,couponsActivityShow);
        ObjectRestResponse<CouponsActivityShow> couponsActivityShowObjectRestResponse = new ObjectRestResponse<>();
        couponsActivityShowObjectRestResponse.setData(couponsActivityShow);
        return couponsActivityShowObjectRestResponse;
    }

    /**
     * 优惠券活动 编辑
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateCouponsActivity", method = RequestMethod.POST)
    public ObjectRestResponse<Activity> updateCouponsActivity(@RequestBody Activity entity){
        Activity activity = new Activity();
        BeanUtils.copyProperties(entity,activity);
        ObjectRestResponse<Activity> update = activityServiceFeign.update(entity.getId(), activity);
        return update;
    }



    /**
     * 新增 青春微普法大赛活动
     * @param
     * @return
     */
    @RequestMapping(value = "/addYouthActivity", method = RequestMethod.POST)
    public ObjectRestResponse addYouthActivity(@RequestBody YouthActivityAddInfo entity) {
        if (StringUtils.isEmpty(entity.getTitle())){
            String msg="标题不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getEndTime() == null|| entity.getStartTime()==null){
            String msg ="开始/结束时间不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreatedUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreatedTime(new Date());
        activityServiceFeign.addYouthActivity(entity);
        String msg ="新增青春微普法大赛活动成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }


    /**
     * 青春微普法大赛活动 得到详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneYouthActivity", method = RequestMethod.GET)
    public ObjectRestResponse<YouthActivityAddShow> getOneYouthActivity(@RequestParam("id")Integer id){
        YouthActivityAddInfo oneYouthActivity = activityServiceFeign.getOneYouthActivity(id);
        YouthActivityAddShow youthActivityAddShow = new YouthActivityAddShow();
        BeanUtils.copyProperties(oneYouthActivity,youthActivityAddShow);
        ObjectRestResponse<YouthActivityAddShow> voteActivityAddShowObjectRestResponse = new ObjectRestResponse<>();
        voteActivityAddShowObjectRestResponse.setData(youthActivityAddShow);
        return voteActivityAddShowObjectRestResponse;
    }
    /**
     * 青春微普法大赛活动 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/updateYouthActivity", method = RequestMethod.POST)
    public ObjectRestResponse updateYouthActivity(@RequestBody YouthActivityAddInfo entity) {
        if (StringUtils.isEmpty(entity.getTitle())){
            String msg="标题不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getEndTime() == null|| entity.getStartTime()==null){
            String msg ="开始/结束时间不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreatedUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreatedTime(new Date());
        activityServiceFeign.updateYouthActivity(entity);
        String msg ="编辑青春微普法大赛活动成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }


    /**
     * 青春微普法大赛活动 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getYouthActivityPage", method = RequestMethod.POST)
    public TableResultResponse<YouthActivityPageInfo> getYouthActivityPage(@RequestBody YouthActivityPageInfo entity){
        TableResultResponse<YouthActivityPageInfo> youthActivityPageInfoTableResultResponse = activityServiceFeign.getYouthActivityPage(entity);
        return youthActivityPageInfoTableResultResponse;
    }

    /**
     * 政法先锋活动 分页及搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getPioneerActivityPage", method = RequestMethod.POST)
    public TableResultResponse<PioneerActivityPageInfo> getPioneerActivityPage(@RequestBody PioneerActivityPageInfo entity){
        TableResultResponse<PioneerActivityPageInfo> list = activityServiceFeign.getPioneerActivityPage(entity);
        return list;
    }

    /**
     * 政法先锋活动 得到详情
     * @param
     * @return
     */
    @RequestMapping(value = "/getOnePioneerActivity",method = RequestMethod.GET)
    public ObjectRestResponse<PioneerActivityShow> getOnePioneerActivity(@RequestParam("id")Integer id){
        ObjectRestResponse<Activity> activityObjectRestResponse = activityServiceFeign.get(id);
        Activity data = activityObjectRestResponse.getData();
        PioneerActivityShow pioneerActivityShow = new PioneerActivityShow();
        BeanUtils.copyProperties(data,pioneerActivityShow);
        ObjectRestResponse<PioneerActivityShow> pioneerActivityShowObjectRestResponse = new ObjectRestResponse<>();
        pioneerActivityShowObjectRestResponse.setData(pioneerActivityShow);
        return pioneerActivityShowObjectRestResponse;
    }

    /**
     * 政法先锋活动 编辑
     * @param
     * @return
     */
    @RequestMapping(value = "/updatePioneerActivity", method = RequestMethod.POST)
    public ObjectRestResponse<Activity> updatePioneerActivity(@RequestBody Activity entity){
        Activity activity = new Activity();
        BeanUtils.copyProperties(entity,activity);
        ObjectRestResponse<Activity> update = activityServiceFeign.update(entity.getId(), activity);
        return update;
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }

}
