package com.kanfa.news.admin.rpc;


import com.kanfa.news.admin.entity.LiveType;
import com.kanfa.news.admin.feign.ILiveTypeServiceFeign;
import com.kanfa.news.admin.vo.live.LiveTypeIdName;
import com.kanfa.news.admin.vo.live.LiveTypeInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("liveType")
public class LiveTypeRest extends BaseRPC{
    @Autowired
    private ILiveTypeServiceFeign liveTypeServiceFeign;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<LiveType> add(@RequestBody LiveType liveType) {
        liveType.setCreateTime(new Date());
        liveType.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveTypeServiceFeign.add(liveType);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<LiveType> get(@PathVariable("id") Integer id) {
        return liveTypeServiceFeign.get(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<LiveType> updateLiveType(@PathVariable("id") Integer id, @RequestBody LiveType liveType) {
        liveType.setId(id);
        liveType.setUpdateTime(new Date());
        liveType.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveTypeServiceFeign.update(id, liveType);

    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public TableResultResponse<LiveTypeInfo> get(@RequestParam Map<String, Object> params) {
        return liveTypeServiceFeign.list(params);
    }

    @RequestMapping(value = "/cancelPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse cancelPublishLiveType(@PathVariable("id") Integer id) {
        LiveType liveType = new LiveType();
        liveType.setIsPublish(0);
        liveType.setId(id);
        liveType.setUpdateTime(new Date());
        liveType.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveTypeServiceFeign.update(id, liveType);
    }

    @RequestMapping(value = "/publish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse publishLiveType(@PathVariable("id") Integer id) {
        LiveType liveType = new LiveType();
        liveType.setIsPublish(1);
        liveType.setId(id);
        liveType.setUpdateTime(new Date());
        liveType.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveTypeServiceFeign.update(id, liveType);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<LiveType> removeLiveType(@PathVariable("id") int id) {
        return liveTypeServiceFeign.remove(id);
    }


    @RequestMapping(value = "/getLiveTypeIdName", method = RequestMethod.GET)
    public ObjectRestResponse<List<LiveTypeIdName>> getLiveTypeIdName(){
        List<LiveType> all = liveTypeServiceFeign.all();
        ArrayList<LiveTypeIdName> liveTypeIdNames = new ArrayList<>();
        for (LiveType liveType:all) {
            LiveTypeIdName liveTypeIdName = new LiveTypeIdName();
            BeanUtils.copyProperties(liveType,liveTypeIdName);
            liveTypeIdNames.add(liveTypeIdName);
        }
        ObjectRestResponse<List<LiveTypeIdName>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(liveTypeIdNames);
        return listObjectRestResponse;
    }
}
