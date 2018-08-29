package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.LiveVideoBind;
import com.kanfa.news.admin.feign.ILiveVideoBindServiceFeign;
import com.kanfa.news.admin.vo.live.LiveVideoBindInfo;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/28 13:57
 */
@RestController
@RequestMapping("liveVideoBind")
public class LiveVideoBindRest {
    @Autowired
    private ILiveVideoBindServiceFeign liveVideoBindServiceFeign;

    @RequestMapping(value = "/getBind", method = RequestMethod.GET)
    public ObjectRestResponse<List<LiveVideoBindInfo>> getBind(@RequestParam("liveId") Integer liveId){
        List<LiveVideoBindInfo> allBind = liveVideoBindServiceFeign.getBind(liveId);
        ObjectRestResponse<List<LiveVideoBindInfo>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(allBind);
        return listObjectRestResponse;
    }


    /**
     * 分页显示按标题搜索关联视频
     * @param page,limit,liveSpecialId,title
     * @return
     */
    @RequestMapping(value = "/getSearchPage", method = RequestMethod.GET)
    public TableResultResponse<LiveVideoBindInfo> getPage(@RequestParam Integer page,
                                                            @RequestParam Integer limit,
                                                            @RequestParam Integer fromType,
                                                            @RequestParam Integer liveId,
                                                            @RequestParam String title) {
        return liveVideoBindServiceFeign.getSearchPage(page, limit,fromType,liveId,title);
    }


    /**
     * 新增直播专题关联内容
     * @param entity
     * @return
     */

    @RequestMapping(value = "/addLiveVideoBind", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> addLiveVideoBind(@RequestBody LiveVideoBind entity) {
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        liveVideoBindServiceFeign.addLiveVideoBind(entity);
        String mes = "新增成功";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(mes);
        return  stringObjectRestResponse;
    }

    /**
     * 删除直播专题关联内容
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ObjectRestResponse<LiveVideoBind> remove(@RequestParam("cid")Integer cid,
                                                    @RequestParam("bindId")Integer bindId) {
        Integer liveVideoBindId = liveVideoBindServiceFeign.getLiveVideoBindId(cid,bindId);
        return liveVideoBindServiceFeign.delete(liveVideoBindId);
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortLiveVideoBind", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Boolean> sortLiveVideoBind( @RequestBody Map<String, Object> params) {
        return liveVideoBindServiceFeign.batchSort(params);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }


}
