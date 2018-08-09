package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.LiveSpecialBindBiz;
import com.kanfa.news.info.entity.LiveSpecialBind;
import com.kanfa.news.info.vo.admin.live.LiveSpecialBindInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("liveSpecialBind")
public class LiveSpecialBindController extends BaseController<LiveSpecialBindBiz,LiveSpecialBind> {

    @Autowired
    private LiveSpecialBindBiz liveSpecialBindBiz;

    @RequestMapping(value = "/getPage",method = RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<LiveSpecialBindInfo> getPage(@RequestParam Integer page,
                                                            @RequestParam Integer limit,
                                                            @RequestParam Integer liveSpecialId){
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return liveSpecialBindBiz.getPage(query,liveSpecialId);
    }


    @RequestMapping(value = "/getSearchPage",method = RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<LiveSpecialBindInfo> getSearchPage(@RequestParam Integer page,
                                                                   @RequestParam Integer limit,
                                                                   @RequestParam(name = "liveSpecialId") Integer liveSpecialId,
                                                                   @RequestParam String title){
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return liveSpecialBindBiz.getSearchPage(query,liveSpecialId,title);
    }

    //删除
    @RequestMapping(value = "/deleteLiveSpecialBind",method = RequestMethod.GET )
    public void delete(@RequestParam Integer liveId){
        LiveSpecialBind liveSpecialBind = new LiveSpecialBind();
        liveSpecialBind.setLiveId(liveId);
        liveSpecialBindBiz.delete(liveSpecialBind);
    }

    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getLiveSpecialBind",method = RequestMethod.GET)
    public List<LiveSpecialBind> getLiveSpecialBind(@RequestParam("ids") List<Integer> ids, @RequestParam("contentId") Integer contentId){
        return liveSpecialBindBiz.getLiveSpecialBind(ids,contentId);
    }

    /**
     * 批量更新
     * @param
     * @return
     */
    @RequestMapping(value = "/batchSort",method = RequestMethod.POST)
    public ObjectRestResponse<Boolean> batchSort(@RequestBody Map<String, Object> params){
        //查询频道原有的集合
        List<Integer> newIds = (List<Integer>) params.get("newIds");
        List<Integer> oldIds = (List<Integer>) params.get("oldIds");
        Integer contentId = (Integer) params.get("contentId");
        if (newIds == null || newIds.size() <= 0 || oldIds == null || oldIds.size() <= 0 || contentId == null) {
            return getObjectRestResponse("请传入新旧id集合和源内容id");
        }
        int count = newIds.size();
        for (int i = 0; i < oldIds.size(); i++) {
            if (!(oldIds.get(i).equals(newIds.get(i)))) {
                count = i;
                break;
            }
        }
        List<LiveSpecialBind> oldList = liveSpecialBindBiz.getLiveSpecialBind(oldIds, contentId);
        //新的
        List<LiveSpecialBind> newList = liveSpecialBindBiz.getLiveSpecialBind(newIds, contentId);
        List<Integer> sort = new ArrayList<>(oldList.size());
        if (oldList != null && oldList.size() > 0) {
            for (LiveSpecialBind liveSpecialBind : oldList) {
                sort.add(liveSpecialBind.getSort());
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            LiveSpecialBind liveSpecialBind = newList.get(i);
            liveSpecialBind.setId(newList.get(i).getId());
            liveSpecialBind.setSort(sort.get(i));
        }
        List<LiveSpecialBind> liveVideoBinds = newList.subList(count, newList.size());
        ObjectRestResponse<Boolean> restResponse = new ObjectRestResponse<>();
        if (liveVideoBinds != null && liveVideoBinds.size() > 0) {
            Integer succNum = liveSpecialBindBiz.batchUpdate(liveVideoBinds);
            if (succNum <= 0) {
                restResponse.setData(true);
                restResponse.setMessage("排序失败");
            }
        }
        restResponse.setRel(false);
        return restResponse;
    }

    @RequestMapping(value = "/getMaxSort",method = RequestMethod.GET)
   public Integer getMaxSort(@RequestParam("liveSpecialId") Integer liveSpecialId){
        return  liveSpecialBindBiz.getMaxSort(liveSpecialId);
    }



    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(500);
        return objectRestResponse;
    }

}