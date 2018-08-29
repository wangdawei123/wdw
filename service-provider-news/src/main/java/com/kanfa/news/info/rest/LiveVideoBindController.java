package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.LiveVideoBindBiz;
import com.kanfa.news.info.entity.LiveVideoBind;
import com.kanfa.news.info.vo.admin.live.LiveVideoBindInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/liveVideoBind")
public class LiveVideoBindController extends BaseController<LiveVideoBindBiz,LiveVideoBind> {
    @Autowired
    private LiveVideoBindBiz liveVideoBindBiz;


    /**
     * 得到直播关联内容
     * @param liveId
     * @return
     */
    @RequestMapping(value = "/getAllBind",method = RequestMethod.GET)
    public List<LiveVideoBindInfo> getAllBind(@RequestParam("liveId") Integer liveId){
        List<LiveVideoBindInfo> allBind = liveVideoBindBiz.findAllBind(liveId);
        return allBind;
    }

    /**
     * 关联内容的搜索
     * @param page,limit,fromType,liveId,title
     * @return
     */
    @RequestMapping(value = "/getSearchPage",method = RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<LiveVideoBindInfo> getSearchPage(@RequestParam Integer page,
                                                                  @RequestParam Integer limit,
                                                                  @RequestParam Integer fromType,
                                                                  @RequestParam(name = "liveId") Integer liveId,
                                                                  @RequestParam String title){
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return liveVideoBindBiz.getSearchPage(query,fromType,liveId,title);
    }

//    @RequestMapping(value = "/getMaxSort",method = RequestMethod.GET )
//    public Integer getMaxSort(){
//        Integer maxSort = liveVideoBindBiz.getMaxSort();
//        return maxSort;
//    }

    @RequestMapping(value = "/getLiveVideoBindId",method = RequestMethod.GET)
    public Integer getLiveVideoBindId(@RequestParam("cid")Integer cid,
                                      @RequestParam("bindId") Integer bindId){
        LiveVideoBind liveVideoBind = new LiveVideoBind();
        liveVideoBind.setBindId(bindId);
        liveVideoBind.setCid(cid);
        LiveVideoBind liveVideoBind1 = liveVideoBindBiz.selectOne(liveVideoBind);
        return liveVideoBind1.getId();

    }



    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getLiveVideoBind",method = RequestMethod.GET)
    public List<LiveVideoBind> getLiveVideoBind(@RequestParam("ids") List<Integer> ids, @RequestParam("contentId") Integer contentId){
        return liveVideoBindBiz.getLiveVideoBind(ids,contentId);
    }

    /**
     * 排序
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
        List<LiveVideoBind> oldList = liveVideoBindBiz.getLiveVideoBind(oldIds,contentId );
        //新的
        List<LiveVideoBind> newList = liveVideoBindBiz.getLiveVideoBind(newIds, contentId);
        List<Integer> sort = new ArrayList<>(oldList.size());
        if (oldList != null && oldList.size() > 0) {
            for (LiveVideoBind liveVideoBind : oldList) {
                sort.add(liveVideoBind.getSort());
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            LiveVideoBind liveVideoBind = newList.get(i);
            liveVideoBind.setId(newList.get(i).getId());
            liveVideoBind.setSort(sort.get(i));
        }
        List<LiveVideoBind> liveVideoBinds = newList.subList(count, newList.size());
        ObjectRestResponse<Boolean> restResponse = new ObjectRestResponse<>();
        if (liveVideoBinds != null && liveVideoBinds.size() > 0) {
            Integer succNum = liveVideoBindBiz.batchUpdate(liveVideoBinds);
            if (succNum <= 0) {
                restResponse.setData(true);
                restResponse.setMessage("排序失败");
            }
        }
        restResponse.setRel(false);
        return restResponse;
    }

    /**
     * 新增绑定
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addLiveVideoBind",method = RequestMethod.POST)
    public void addLiveVideoBind(@RequestBody LiveVideoBind entity){
        Integer maxSort = liveVideoBindBiz.getMaxSort();
        if (maxSort==null){
            entity.setSort(1);
        }else {
            entity.setSort(maxSort+1);
        }
        //设置创建时间
        Long l = System.currentTimeMillis() / 1000;
        Integer time = l.intValue();
        entity.setCreateTime(time);
        entity.setSorttime(time);
        liveVideoBindBiz.insertSelective(entity);
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(500);
        return objectRestResponse;
    }

}