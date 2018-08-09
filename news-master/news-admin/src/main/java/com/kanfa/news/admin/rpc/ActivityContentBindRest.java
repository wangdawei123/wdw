package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ActivityContentBind;
import com.kanfa.news.admin.entity.Content;
import com.kanfa.news.admin.feign.IActivityContentBindServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityContentBindPageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chen
 * on 2018/4/18 14:46
 */
@RestController
@RequestMapping("activityContentBind")
public class ActivityContentBindRest {

    @Autowired
    private IActivityContentBindServiceFeign activityContentBindServiceFeign;


    /**
     * 政法先锋 关联内容
     * @param
     * @return
     */
    @RequestMapping(value = "/getActivityContentBindPage", method = RequestMethod.POST)
    public TableResultResponse<ActivityContentBindPageInfo> getActivityContentBindPage(@RequestBody ActivityContentBindPageInfo entity){
        TableResultResponse<ActivityContentBindPageInfo> list = activityContentBindServiceFeign.getActivityContentBindPage(entity);
        return list;
    }

    /**
     * 政法先锋 关联内容 按标题搜索啊
     * @param
     * @return
     */
    @RequestMapping(value = "/getSearchPage", method = RequestMethod.GET)
    public TableResultResponse<Content> getSearchPage(@RequestParam(name = "page") Integer page,
                                                      @RequestParam(name = "limit") Integer limit,
                                                      @RequestParam(name = "activityId") Integer activityId,
                                                      @RequestParam(name = "title") String title){

        TableResultResponse<Content> searchPage = activityContentBindServiceFeign.getSearchPage(page, limit, activityId, title);
        return searchPage;
    }


    /**
     * 政法先锋 关联内容 新增
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<ActivityContentBind> add(@RequestBody ActivityContentBind entity) {
        Integer maxOrderNum = activityContentBindServiceFeign.getMaxOrderNum(entity.getActivityId());
        if (maxOrderNum == null){
            maxOrderNum = 1;
        }
        //文章
        entity.setType(2);
        entity.setOrderNumber(maxOrderNum+1);
        return  activityContentBindServiceFeign.add(entity);
    }

    /**
     * 政法先锋 关联内容 解除关联
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ObjectRestResponse delete(@RequestParam("id")Integer id) {
        ActivityContentBind activityContentBind = new ActivityContentBind();
        activityContentBind.setId(id);
        activityContentBind.setIsDelete(1);
        return activityContentBindServiceFeign.update(id, activityContentBind);
    }


}
