package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.LiveSpecial;
import com.kanfa.news.admin.feign.ILiveSpecialServiceFeign;
import com.kanfa.news.admin.vo.live.LiveSpecialAccording;
import com.kanfa.news.admin.vo.live.LiveSpecialInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/16 16:31
 */

@RestController
@RequestMapping("/liveSpecial")
public class LiveSpecialRest {

    @Autowired
    private ILiveSpecialServiceFeign liveSpecialServiceFeign;


    /**
     * 分页查询直播专题
     * @param page,limit
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<LiveSpecialInfo> get(@RequestParam Integer page,
                                                    @RequestParam Integer limit) {
        return liveSpecialServiceFeign.getPage(page, limit);
    }

    /**
     * 按标题搜索
     * @param page,limit,title
     * @return
     */
    @RequestMapping(value = "/getSearchPage", method = RequestMethod.GET)
    public TableResultResponse<LiveSpecialInfo> getSearchPage(@RequestParam Integer page,
                                                              @RequestParam Integer limit,
                                                              @RequestParam String title) {
        return liveSpecialServiceFeign.getSearchPage(page, limit, title);
    }


    /**
     * 得到一个直播专题
     * @param id
     * @return
     */
    @RequestMapping(value = "/getliveSpecial", method = RequestMethod.GET)
    public ObjectRestResponse<LiveSpecialAccording> getliveSpecial(@RequestParam Integer id) {
        ObjectRestResponse<LiveSpecial> liveSpecialObjectRestResponse = liveSpecialServiceFeign.get(id);
        LiveSpecial data = liveSpecialObjectRestResponse.getData();
        LiveSpecialAccording liveSpecialAccording = new LiveSpecialAccording();
        BeanUtils.copyProperties(data, liveSpecialAccording);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(liveSpecialAccording);
        return objectRestResponse;
    }

    /**
     * update直播专题
     * @param id,entity
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<LiveSpecial> update(@PathVariable("id") Integer id, @RequestBody LiveSpecial entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveSpecialServiceFeign.update(id, entity);
    }


    /**
     * cancelPublish直播专题
     * @param id,entity
     * @return
     */
    @RequestMapping(value = "/cancelPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse cancelPublish(@PathVariable("id") Integer id) {
        LiveSpecial liveSpecial = new LiveSpecial();
        liveSpecial.setPub(0);
        liveSpecial.setId(id);
        liveSpecial.setUpdateTime(new Date());
        liveSpecial.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveSpecialServiceFeign.update(id, liveSpecial);
    }

    /**
     * onPublish直播专题
     * @param id,entity
     * @return
     */
    @RequestMapping(value = "/onPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse onPublish(@PathVariable("id") Integer id) {
        LiveSpecial liveSpecial = new LiveSpecial();
        liveSpecial.setPub(1);
        liveSpecial.setId(id);
        liveSpecial.setUpdateTime(new Date());
        liveSpecial.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveSpecialServiceFeign.update(id, liveSpecial);
    }

    /**
     * remove直播专题
     * @param id,entity
     * @return
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse remove(@PathVariable("id") Integer id) {
        LiveSpecial liveSpecial = new LiveSpecial();
        //是否已删除 1:未删除 0:已删除
        liveSpecial.setStat(0);
        liveSpecial.setId(id);
        liveSpecial.setUpdateTime(new Date());
        liveSpecial.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveSpecialServiceFeign.update(id, liveSpecial);
    }


    /**
     * add直播专题
     * @param entity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<LiveSpecial> add(@RequestBody LiveSpecial entity) {
        //初始化直播专题的属性
        entity.setPub(0);
        entity.setSpecialType(0);
        entity.setStat(1);
        entity.setCreateTime(new Date());
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveSpecialServiceFeign.add(entity);
    }

}
