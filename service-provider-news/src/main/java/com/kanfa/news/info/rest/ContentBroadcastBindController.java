package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.ContentBroadcastBindBiz;
import com.kanfa.news.info.entity.ContentBroadcastBind;
import com.kanfa.news.info.vo.admin.video.ContentBroadcastBindInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("contentBroadcastBind")
public class ContentBroadcastBindController extends BaseController<ContentBroadcastBindBiz,ContentBroadcastBind> {

    @Autowired
    private ContentBroadcastBindBiz contentBroadcastBindBiz;

    /**
     * 解除绑定
     * @param entity
     * @return
     */
    @RequestMapping(value = "/unBindContent",method = RequestMethod.POST)
    ObjectRestResponse<ContentBroadcastBind> unBindContent(@RequestBody ContentBroadcastBind entity){
        contentBroadcastBindBiz.delete(entity);
        return new ObjectRestResponse<ContentBroadcastBind>();
    }

    /**
     * 查询绑定
     * @param contentBroadcastBind
     * @return
     */
    @RequestMapping(value = "/getContentBind",method = RequestMethod.POST)
    List<ContentBroadcastBindInfo> getContentBind(@RequestBody ContentBroadcastBind contentBroadcastBind){
        return contentBroadcastBindBiz.getContentBind(contentBroadcastBind);
    }

    @RequestMapping(value = "/getPage",method = RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<ContentBroadcastBindInfo> getPage(@RequestParam Integer page,
                                                                 @RequestParam Integer limit,
                                                                 @RequestParam Integer contentId){
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return contentBroadcastBindBiz.getPage(query,contentId);
    }



    @RequestMapping(value = "/getSearchPage",method = RequestMethod.GET )
    @ResponseBody
    public TableResultResponse<ContentBroadcastBindInfo> getSearchPage(@RequestParam Integer page,
                                                                  @RequestParam Integer limit,
                                                                  @RequestParam(name = "contentId") Integer contentId,
                                                                  @RequestParam(name = "title") String title){
        Query query = new Query(new HashMap<>(16));
        query.setLimit(limit);
        query.setPage(page);
        return contentBroadcastBindBiz.getSearchPage(query,contentId,title);
    }

    /**
     * 绑定分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/getContentBindPage",method = RequestMethod.POST)
    public TableResultResponse<ContentBroadcastBindInfo> getContentBindPage(@RequestParam Map<String, Object> params){
        return contentBroadcastBindBiz.getContentBindPage(params);
    }
    @RequestMapping(value = "/getMaxOrderNumber",method = RequestMethod.GET )
    @ResponseBody
    public  Integer getMaxOrderNumber(){
        return contentBroadcastBindBiz.getMaxOrderNumber();
    }

    /**
     * 删除视频下的关联内容
     * @param bindId
     * @return
     */
    @RequestMapping(value = "/deleteBind",method = RequestMethod.GET )
    public void delete(@RequestParam(name = "bindId")Integer bindId,@RequestParam("contentId")Integer contentId){
        ContentBroadcastBind contentBroadcastBind = new ContentBroadcastBind();
        contentBroadcastBind.setBindId(bindId);
        contentBroadcastBind.setContentId(contentId);
        contentBroadcastBindBiz.delete(contentBroadcastBind);
    }

    /**
     * 查询绑定内容
     * @param ids
     * @param contentId
     * @return
     */
    @RequestMapping(value = "/getBroadcastBind",method = RequestMethod.GET)
    public List<ContentBroadcastBind> getBroadcastBind(@RequestParam("ids") List<Integer> ids, @RequestParam("contentId") Integer contentId){
        return contentBroadcastBindBiz.getBroadcastBind(ids,contentId);
    }

    /**
     * 批量更新
     * @param contentBroadcastBindList
     * @return
     */
    @RequestMapping(value = "/batchUpdate",method = RequestMethod.POST)
    public Integer batchUpdate(@RequestBody List<ContentBroadcastBind> contentBroadcastBindList){
        return contentBroadcastBindBiz.batchUpdate(contentBroadcastBindList);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    @Override
    public ObjectRestResponse<ContentBroadcastBind> add(@RequestBody  ContentBroadcastBind contentBroadcastBind) {
        Integer maxOrderNumber = contentBroadcastBindBiz.getMaxOrderNumber();
        if(maxOrderNumber!=null){
            contentBroadcastBind.setOrderNumber(maxOrderNumber+1);
        }else {
            contentBroadcastBind.setOrderNumber(1);
        }
        return super.add(contentBroadcastBind);
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortBindContent",method = RequestMethod.POST)
    public ObjectRestResponse<Boolean> sortBindContent(@RequestBody Map<String, Object> params){
        List<Integer> newIds = (List<Integer>) params.get("newIds");
        List<Integer> oldIds = (List<Integer>) params.get("oldIds");
        if(params.get("contentId")==null || !isInteger(params.get("contentId").toString())){
            return getObjectRestResponse("请传入正确的源内容id");
        }
        Integer contentId = Integer.valueOf(params.get("contentId").toString());
        if (newIds == null || newIds.size() <= 0 || oldIds == null || oldIds.size() <= 0) {
            return getObjectRestResponse("请传入新旧id集合");
        }
        int count = newIds.size();
        for (int i = 0; i < oldIds.size(); i++) {
            if (!(oldIds.get(i).equals(newIds.get(i)))) {
                count = i;
                break;
            }
        }
        List<ContentBroadcastBind> oldList = contentBroadcastBindBiz.getBroadcastBind(oldIds, contentId);
        //新的
        List<ContentBroadcastBind> newList = contentBroadcastBindBiz.getBroadcastBind(newIds, contentId);
        List<Integer> sort = new ArrayList<>(oldList.size());
        if (oldList != null && oldList.size() > 0) {
            for (ContentBroadcastBind contentBroadcastBind : oldList) {
                sort.add(contentBroadcastBind.getOrderNumber());
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            ContentBroadcastBind contentBroadcastBind = newList.get(i);
            contentBroadcastBind.setId(newList.get(i).getId());
            contentBroadcastBind.setOrderNumber(sort.get(i));
        }
        List<ContentBroadcastBind> contentBroadcastBindList = newList.subList(count, newList.size());
        ObjectRestResponse<Boolean> restResponse = new ObjectRestResponse<>();
        if (contentBroadcastBindList != null && contentBroadcastBindList.size() > 0) {
            Integer succNum = contentBroadcastBindBiz.batchUpdate(contentBroadcastBindList);
            if (succNum <= 0) {
                restResponse.setData(true);
                restResponse.setMessage("排序失败");
            }
        }
        restResponse.setRel(false);
        return restResponse;
    }

    /**
     * 文章绑定更新
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batchBindAndDelete",method = RequestMethod.POST)
    public ObjectRestResponse batchBindAndDelete(@RequestBody List<Integer> ids, @RequestParam("contentId") Integer contentId){
        contentBroadcastBindBiz.batchBindAndDelete(ids,contentId);
        return new ObjectRestResponse();
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setStatus(506);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}