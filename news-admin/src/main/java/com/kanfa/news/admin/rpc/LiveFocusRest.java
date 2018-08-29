package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.LiveFocus;
import com.kanfa.news.admin.feign.ILiveFocusServiceFeign;
import com.kanfa.news.admin.vo.live.LiveFocusInfo;
import com.kanfa.news.admin.vo.live.LiveFocusShow;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/3/24 11:57
 */
@RestController
@RequestMapping("liveFocus")
public class LiveFocusRest {
    @Autowired
    private ILiveFocusServiceFeign  liveFocusServiceFeign;

    /**
     * 直播焦点图列表的分页查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    public TableResultResponse<LiveFocusInfo> getPage(@RequestBody LiveFocusInfo entity) {
        TableResultResponse<LiveFocusInfo> list=liveFocusServiceFeign.getPage(entity);
        return list;
    }

    /**
     * 新增
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<LiveFocus> add(@RequestBody LiveFocus entity) {
        entity.setCreateTime(new Date());
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveFocusServiceFeign.add(entity);

    }

    /**
     * 得到直播焦点图列表的信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<LiveFocusShow> get(@PathVariable("id") Integer id) {
        ObjectRestResponse<LiveFocus> liveFocusObjectRestResponse = liveFocusServiceFeign.get(id);
        LiveFocus data = liveFocusObjectRestResponse.getData();
        LiveFocusShow liveFocusShow = new LiveFocusShow();
        BeanUtils.copyProperties(data,liveFocusShow);
        ObjectRestResponse<LiveFocusShow> liveFocusShowObjectRestResponse = new ObjectRestResponse<>();
        liveFocusShowObjectRestResponse.setData(liveFocusShow);
        return liveFocusShowObjectRestResponse;
    }
    /**
     * 编辑直播焦点图列表的信息
     * @param
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<LiveFocus> update(@PathVariable("id") Integer id, @RequestBody LiveFocus entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        ObjectRestResponse<LiveFocus> update = liveFocusServiceFeign.update(id, entity);
        return update;
    }

    /**
     * 取消发布
     * @param
     * @return
     */
    @RequestMapping(value = "/cancelPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse cancelPublish(@PathVariable("id") Integer id) {
        LiveFocus liveFocus = new LiveFocus();
        liveFocus.setPub(0);
        liveFocus.setId(id);
        liveFocus.setUpdateTime(new Date());
        liveFocus.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveFocusServiceFeign.update(id, liveFocus);
    }

    /**
     * 发布
     * @param
     * @return
     */
    @RequestMapping(value = "/onPublish/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse onPublish(@PathVariable("id") Integer id) {
        LiveFocus liveFocus = new LiveFocus();
        liveFocus.setPub(1);
        liveFocus.setId(id);
        liveFocus.setUpdateTime(new Date());
        liveFocus.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveFocusServiceFeign.update(id, liveFocus);
    }


    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping(value = "/moreDeletes", method = RequestMethod.POST)
    public ObjectRestResponse delete(@RequestBody Map<String, List<Integer>> params) {
        List<Integer> ids = params.get("ids");
        int count = 0;
        if (ids.size()>0) {
            for (Integer id : ids) {
                LiveFocus liveFocus = new LiveFocus();
                liveFocus.setStat(NewsEnumsConsts.ContentOfContentState.DELETE.key());
                liveFocus.setId(id);
                liveFocus.setUpdateTime(new Date());
                liveFocus.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
                liveFocusServiceFeign.update(id, liveFocus);
                count++;
            }
        }else {
                String msg = "请选择要删除的ids不能为空";
                return getObjectRestResponse(msg);
            }
            String msg = "删除" + count + "个";
            return getObjectRestResponse(msg);
        }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }


}
