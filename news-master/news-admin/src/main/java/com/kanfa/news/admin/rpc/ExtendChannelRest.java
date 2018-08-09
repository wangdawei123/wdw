package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ExtendChannel;
import com.kanfa.news.admin.feign.IExtendChannelServiceFeign;
import com.kanfa.news.admin.vo.activity.ExtendChannelPageInfo;
import com.kanfa.news.admin.vo.activity.GetOneExtendChannel;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen
 * on 2018/4/16 11:50
 */
@RestController
@RequestMapping("extendChannel")
public class ExtendChannelRest {

    @Autowired
    private IExtendChannelServiceFeign extendChannelServiceFeign;

    /**
     * 推广渠道的分页及查询
     * @return
     */
    @RequestMapping(value = "/getExtendChannelPage", method = RequestMethod.POST)
    public TableResultResponse<ExtendChannelPageInfo> getExtendChannelPage(@RequestBody ExtendChannelPageInfo entity){
        TableResultResponse<ExtendChannelPageInfo> list = extendChannelServiceFeign.getExtendChannelPage(entity);
        return list;
    }

    /**
     * 推广渠道 得到详情
     * @return
     */
    @RequestMapping(value = "/getOneExtendChannel", method = RequestMethod.GET)
    public ObjectRestResponse<GetOneExtendChannel> getOneExtendChannel(@RequestParam("id")Integer id){
        ObjectRestResponse<ExtendChannel> extendChannelObjectRestResponse = extendChannelServiceFeign.get(id);
        ExtendChannel data = extendChannelObjectRestResponse.getData();
        GetOneExtendChannel getOneExtendChannel = new GetOneExtendChannel();
        BeanUtils.copyProperties(data,getOneExtendChannel);
        ObjectRestResponse<GetOneExtendChannel> getOneExtendChannelObjectRestResponse = new ObjectRestResponse<>();
        getOneExtendChannelObjectRestResponse.setData(getOneExtendChannel);
        return getOneExtendChannelObjectRestResponse;
    }

    /**
     * 推广渠道 编辑
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ExtendChannel> update(@PathVariable("id") Integer id, @RequestBody ExtendChannel entity) {
        entity.setId(id);
        return extendChannelServiceFeign.update(id, entity);
    }

    /**
     * 推广渠道 新增
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<ExtendChannel> add(@RequestBody ExtendChannel entity) {
        if (StringUtils.isEmpty(entity.getName())){
            String msg="渠道名称不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getChannelNum())){
            String msg="渠道号不能为空";
            return getObjectRestResponse(msg);
        }
        entity.setCreatedUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());
        return extendChannelServiceFeign.add(entity);
    }

    /**
     * 推广渠道 删除
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ObjectRestResponse delete(@RequestParam("id")Integer id) {
        ExtendChannel extendChannel = new ExtendChannel();
        extendChannel.setId(id);
        //已删除 1
        extendChannel.setIsDelete(1);
        return extendChannelServiceFeign.update(id, extendChannel);
    }

    /**
     * 推广渠道 批量删除
     * @return
     */
    @RequestMapping(value = "/moreDeletes", method = RequestMethod.POST)
    public ObjectRestResponse moreDeletes(@RequestBody Map<String, List<Integer>> params) {
        List<Integer> ids = params.get("ids");
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        int count = 0;
        if (ids.size() > 0) {
            for (Integer id : ids) {
                ExtendChannel extendChannel = new ExtendChannel();
                extendChannel.setId(id);
                //已删除 1
                extendChannel.setIsDelete(1);
                extendChannelServiceFeign.update(id, extendChannel);
                count++;
            }
        } else {
            String msg = "请选择要删除的id不能为空";
            stringObjectRestResponse.setData(msg);
            return stringObjectRestResponse;
        }
        String msg = "删除" + count + "个";
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    @RequestMapping(value = "/extendChannelIdNames", method = RequestMethod.GET)
    public ObjectRestResponse<List<ExtendChannel>> extendChannelIdName(){
        List<ExtendChannel> extendChannels = extendChannelServiceFeign.extendChannelIdNames();
        ObjectRestResponse<List<ExtendChannel>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(extendChannels);
        return listObjectRestResponse;
    }



    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
