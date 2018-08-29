package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ContentBroadcastBind;
import com.kanfa.news.admin.feign.IContentBroadcastBindServiceFeign;
import com.kanfa.news.admin.vo.video.ContentBroadcastBindInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/23 16:00
 */

@RestController
@RequestMapping("contentBroadcastBind")
public class ContentBroadcastBindRest {

    @Autowired
    private IContentBroadcastBindServiceFeign contentBroadcastBindServiceFeign;

    /**
     * 分页显示关联视频
     * @param page,limit,contentId
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<ContentBroadcastBindInfo> getPage(@RequestParam Integer page,
                                                                 @RequestParam Integer limit,
                                                                 @RequestParam Integer contentId) {

        return contentBroadcastBindServiceFeign.getPage(page, limit, contentId);
    }

    /**
     * 搜索显示关联视频
     * @param page,limit,contentId
     * @return
     */
    @RequestMapping(value = "/getSearchPage", method = RequestMethod.GET)
    public TableResultResponse<ContentBroadcastBindInfo> getSearchPage( @RequestParam Integer page,
                                                                        @RequestParam Integer limit,
                                                                        @RequestParam Integer contentId,
                                                                        @RequestParam String  title ) {

        return contentBroadcastBindServiceFeign.getSearchPage(page, limit, contentId,title);
    }

    /**
     * 新增视频列表的关联内容
     * @param entity
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<ContentBroadcastBind> add(@RequestBody ContentBroadcastBind entity) {
        Integer maxOrderNumber = contentBroadcastBindServiceFeign.getMaxOrderNumber();
        if (maxOrderNumber==null){
            entity.setOrderNumber(1);
        }else{
            entity.setOrderNumber(maxOrderNumber+1);
        }
        return contentBroadcastBindServiceFeign.add(entity);
    }

    /**
     * 删除视频列表的关联内容
     * @param
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ObjectRestResponse deleteBind(@RequestParam("bindId") Integer bindId,
                                         @RequestParam("contentId")Integer contentId) {
        contentBroadcastBindServiceFeign.deleteBind(bindId,contentId);
        String msg= "已经删除";
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        stringObjectRestResponse.setData(msg);
        stringObjectRestResponse.setStatus(200);
        stringObjectRestResponse.setRel(false);
        return stringObjectRestResponse;
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortBindContent", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Boolean> sortBindContent( @RequestBody Map<String, Object> params) {
        return contentBroadcastBindServiceFeign.sortBindContent(params);
    }


}
