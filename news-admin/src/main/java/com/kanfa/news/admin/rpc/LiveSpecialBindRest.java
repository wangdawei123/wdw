package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.LiveSpecialBind;
import com.kanfa.news.admin.feign.ILiveSpecialBindServiceFeign;
import com.kanfa.news.admin.vo.live.LiveSpecialBindInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/18 14:44
 */
@RestController
@RequestMapping("liveSpecialBind")
public class LiveSpecialBindRest {
    @Autowired
    private ILiveSpecialBindServiceFeign liveSpecialBindServiceFeign;

    /**
     * 分页显示关联视频
     * @param page,limit,liveSpecialId
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<LiveSpecialBindInfo> getPage(@RequestParam Integer page,
                                                            @RequestParam Integer limit,
                                                            @RequestParam Integer liveSpecialId) {

        return liveSpecialBindServiceFeign.getPage(page, limit, liveSpecialId);
    }

    /**
     * 分页显示按标题搜索关联视频
     * @param page,limit,liveSpecialId,title
     * @return
     */
    @RequestMapping(value = "/getContentPage", method = RequestMethod.GET)
    public TableResultResponse<LiveSpecialBindInfo> getPage(@RequestParam Integer page,
                                                           @RequestParam Integer limit,
                                                           @RequestParam Integer liveSpecialId,
                                                           @RequestParam String title) {
        return liveSpecialBindServiceFeign.getPage(page, limit,liveSpecialId, title);
    }

    /**
     * 新增直播专题关联内容
     * @param entity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<LiveSpecialBind> add(@RequestBody LiveSpecialBind entity) {
        Integer liveSpecialId = entity.getLiveSpecialId();
        Integer maxSort = liveSpecialBindServiceFeign.getMaxSort(liveSpecialId);
        if (maxSort==null){
            maxSort=1;
            entity.setSort(maxSort);
        }
        entity.setSort(maxSort+1);
        return liveSpecialBindServiceFeign.add(entity);
    }

    /**
     * 删除直播专题关联内容
     * @param liveId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public  ObjectRestResponse remove(@RequestParam Integer liveId) {
        if ( liveId!= null){
            liveSpecialBindServiceFeign.delete(liveId);
            String msg= "已经删除";
            return getObjectRestResponse(msg);
        }
        String msg= "liveId为空";
        return getObjectRestResponse(msg);
    }


    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortLiveSpecialBind", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Boolean> sortLiveSpecialBind( @RequestBody Map<String, Object> params) {
        return liveSpecialBindServiceFeign.batchSort(params);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }

}
