package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.Content;
import com.kanfa.news.admin.entity.ContentBroadcast;
import com.kanfa.news.admin.feign.IContentBroadcastServiceFeign;
import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.vo.live.ContentBroadcastInfo;
import com.kanfa.news.admin.vo.live.ContentBroadcastShow;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/3/28 17:43
 */

@RestController
@RequestMapping("contentBroadcast")
public class ContentBroadcastRest {

    @Autowired
    private IContentBroadcastServiceFeign contentBroadcastServiceFeign;
    @Autowired
    private IContentServiceFeign contentServiceFeign;
    /**
     * aliyun的分页和搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    public TableResultResponse<ContentBroadcastInfo> getPage(@RequestBody ContentBroadcastInfo entity) {
        TableResultResponse<ContentBroadcastInfo> conentBroadcasts=contentBroadcastServiceFeign.getPage(entity);
        return conentBroadcasts;
    }

    /**
     * 新增aliyun
     * @param
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<ContentBroadcast> add(@RequestBody ContentBroadcast entity) {
        entity.setCreateTime(new Date());
        entity.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return contentBroadcastServiceFeign.add(entity);
    }
    /**
     * 修改aliyun
     * @param
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ContentBroadcast> update(@PathVariable("id") Integer id, @RequestBody ContentBroadcast entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return contentBroadcastServiceFeign.update(id, entity);
    }
    /**
     * 得到aliyun信息
     * @param
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ContentBroadcastShow> get(@PathVariable("id") Integer id) {
        ObjectRestResponse<ContentBroadcast> contentBroadcastObjectRestResponse = contentBroadcastServiceFeign.get(id);
        ContentBroadcast data = contentBroadcastObjectRestResponse.getData();
        ContentBroadcastShow contentBroadcastShow = new ContentBroadcastShow();
        BeanUtils.copyProperties(data,contentBroadcastShow);
        ObjectRestResponse<ContentBroadcastShow> contentBroadcastShowObjectRestResponse = new ObjectRestResponse<>();
        contentBroadcastShowObjectRestResponse.setData(contentBroadcastShow);
        return contentBroadcastShowObjectRestResponse;
    }

    /**
     * 删除aliyun信息
     * @param
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse delete(@PathVariable("id") Integer id) {
        ContentBroadcast contentBroadcast = new ContentBroadcast();
        contentBroadcast.setId(id);
        contentBroadcast.setIsDelete(0);
        contentBroadcast.setUpdateTime(new Date());
        contentBroadcast.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return contentBroadcastServiceFeign.update(id, contentBroadcast);
    }

    /**
     * 上线
     * @param
     * @return
     */
    @RequestMapping(value = "/onPublish/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse onPublic(@PathVariable("id")Integer id){
        ObjectRestResponse<ContentBroadcast> contentBroadcastObjectRestResponse = contentBroadcastServiceFeign.get(id);
        ContentBroadcast contentBroadcast = contentBroadcastObjectRestResponse.getData();
        Integer cid = contentBroadcast.getCid();
        if (cid==null){
            String msg= "没有关联的content信息";
            return getObjectRestResponse(msg);
        }
        ObjectRestResponse<Content> contentObjectRestResponse = contentServiceFeign.get(cid);
        Content data = contentObjectRestResponse.getData();
        //0待审核，1审核通过，2审核不通过 0,2下线 1上线
        data.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
        contentServiceFeign.update(cid,data);
        contentBroadcast.setUpdateTime(new Date());
        contentBroadcast.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return contentBroadcastServiceFeign.update(id, contentBroadcast);
    }

    /**
     * 下线
     * @param
     * @return
     */
    @RequestMapping(value = "/cancelPublish/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse cancelPublish(@PathVariable("id")Integer id){
        ObjectRestResponse<ContentBroadcast> contentBroadcastObjectRestResponse = contentBroadcastServiceFeign.get(id);
        ContentBroadcast contentBroadcast = contentBroadcastObjectRestResponse.getData();
        Integer cid = contentBroadcast.getCid();
        if (cid==null){
            String msg= "没有关联的content信息";
            return getObjectRestResponse(msg);
        }
        ObjectRestResponse<Content> contentObjectRestResponse = contentServiceFeign.get(cid);
        Content data = contentObjectRestResponse.getData();
        //0待审核，1审核通过，2审核不通过 0,2下线 1上线
        data.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.CHECKFAIL.key());
        contentServiceFeign.update(cid,data);
        contentBroadcast.setUpdateTime(new Date());
        contentBroadcast.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return contentBroadcastServiceFeign.update(id, contentBroadcast);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }

}
