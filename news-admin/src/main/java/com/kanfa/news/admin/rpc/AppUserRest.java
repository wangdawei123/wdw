package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.IAppUserServiceFeign;
import com.kanfa.news.admin.vo.appuser.AppUserInfo;
import com.kanfa.news.admin.vo.appuser.FeedbackInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-21 8:15
 */
@RestController
@RequestMapping("appUser")
public class AppUserRest extends BaseRPC{
    @Autowired
    private IAppUserServiceFeign appUserServiceFeign;

    /**
     * 分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public TableResultResponse<AppUserInfo> page(@RequestParam Map<String,Object> params){
        if(params.get("page")==null || params.get("limit")==null){
            return getObjectRestResponse("分页信息不能为空");
        }
        //        params.put("isBlock",0);
        params.put("isDelete",1);
        return appUserServiceFeign.getPage(params);
    }

    /**
     * 屏蔽与恢复
     * @param id
     * @param isBlock
     * @return
     */
    @RequestMapping(value = "/blockAppUser", method = RequestMethod.PUT)
    public ObjectRestResponse<AppUserInfo> blockAppUser(@RequestParam Integer id,@RequestParam Integer isBlock){
        if(id==null || isBlock==null){
            return getObjectResponse("id及屏蔽参数不能为空");
        }
        AppUserInfo appUserInfo=new AppUserInfo();
        appUserInfo.setId(id);
        appUserInfo.setIsBlock(isBlock);
        appUserInfo.setUpdateId(Integer.valueOf(getCurrentUserId()));
        appUserInfo.setUpdateTime(new Date());
        return appUserServiceFeign.blockAppUser(id,appUserInfo);
    }

    /**
     * 用户统计
     * @return
     */
    @RequestMapping(value = "/countAppUser", method = RequestMethod.GET)
    public ObjectRestResponse<Map<String,Integer>> countAppUser(){
        return appUserServiceFeign.countAppUser();
    }

    /**
     * 查看
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAppUser/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<AppUserInfo> getAppUser(@PathVariable("id") Integer id){
        if(id==null){
            return getObjectResponse("id不能为空");
        }
        return appUserServiceFeign.getAppUser(id);
    }

    /**
     * 意见反馈
     * @param params
     * @return
     */
    @RequestMapping(value = "/getFeedbackPage", method = RequestMethod.GET)
    public TableResultResponse<FeedbackInfo> getFeedbackPage(@RequestParam Map<String,Object> params){
        if(params.get("page")==null || params.get("limit")==null){
            return getObjectRestResponse("分页信息不能为空");
        }
        return appUserServiceFeign.getFeedbackPage(params);
    }

    /**
     * 批量删除反馈
     * @param params
     * @return
     */
    @RequestMapping(value = "/batchDeleteFeedback", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> batchDeleteFeedback(@RequestBody Map<String,List<Integer>> params){
        if(params.get("ids")==null || params.get("ids").size()<=0){
            return getObjectResponse("ids不能为空");
        }
        return appUserServiceFeign.batchDeleteFeedback(params);
    }

    /**
     * 删除意见反馈
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteFeedback/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<FeedbackInfo> deleteFeedback(@PathVariable("id") Integer id){
        if(id==null){
            return getObjectResponse("id不能为空");
        }
        return appUserServiceFeign.deleteFeedback(id);
    }

    private TableResultResponse getObjectRestResponse(String msg) {
        TableResultResponse objectRestResponse = new TableResultResponse();
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
    private ObjectRestResponse getObjectResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setMessage(msg);
        objectRestResponse.setRel(true);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }

}
