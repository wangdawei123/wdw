package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.entity.KpiTypeConfig;
import com.kanfa.news.admin.entity.Live;
import com.kanfa.news.admin.feign.ICorpUserServiceFeign;
import com.kanfa.news.admin.feign.ILiveServiceFeign;
import com.kanfa.news.admin.vo.live.*;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chen
 * on 2018/3/24 14:49
 */
@RestController
@RequestMapping("live")
public class LiveRest {
    @Autowired
    private ILiveServiceFeign liveServiceFeign;
    @Autowired
    private ICorpUserServiceFeign corpUserServiceFeign;

    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    public TableResultResponse<LiveInfo> getPage(@RequestBody LiveInfo entity) {
        entity.setPage(1);
        entity.setLimit(10);
        TableResultResponse<LiveInfo> list=liveServiceFeign.getLivePage(entity);
        return list;
    }


    /**
     * 新增直播
     * @param
     * @return
     */
    @RequestMapping(value = "/addOneLive", method = RequestMethod.POST)
    public ObjectRestResponse<LiveAddInfo> addOneLive(@RequestBody LiveAddInfo entity) {
        if (StringUtils.isEmpty(entity.getTitle())){
            String msg= "内容标题不能为空";
            return getObjectRestResponse(msg);
        }else if (entity.getSourceId()== null){
            String msg = "来源作者Id不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getPreviewUrl())){
            String msg = "预告视频url不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getCoverImg())){
            String msg = "封面图片的url不能为空";
            return getObjectRestResponse(msg);
        }else if (StringUtils.isEmpty(entity.getLiveContent())){
            String msg = "直播内容不能为空";
            return getObjectRestResponse(msg);
        }

        entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setCreateTime(new Date());

        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveServiceFeign.addOneLive(entity);
    }

    /**
     * 查找直播的详情
     * @param liveId
     * @return
     */
    @RequestMapping(value = "/getOneLive", method = RequestMethod.GET)
    public ObjectRestResponse<LiveShow> getVideoContent(@RequestParam Integer liveId) {
        LiveAddInfo oneLive = liveServiceFeign.getOneLive(liveId);
        LiveShow liveShow = new LiveShow();
        BeanUtils.copyProperties(oneLive,liveShow);
        ObjectRestResponse<LiveShow> liveShowObjectRestResponse = new ObjectRestResponse<>();
        liveShowObjectRestResponse.setData(liveShow);
        return liveShowObjectRestResponse;
    }


    /**
     * 编辑直播
     * @param
     * @return
     */
    @RequestMapping(value = "/updateLiveInfo", method = RequestMethod.POST)
    public ObjectRestResponse<LiveAddInfo> updateLiveInfo(@RequestBody LiveAddInfo entity) {
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setUpdateTime(new Date());
        return liveServiceFeign.updateLiveAddInfo(entity);
    }

    /**
     * 直播列表的分页和搜索
     * @param
     * @return
     */
    @RequestMapping(value = "/getLiveSearchPage", method = RequestMethod.POST)
    public TableResultResponse<LivePageInfo> getLiveSearchPage(@RequestBody LivePageInfo entity) {
        TableResultResponse<LivePageInfo> list=liveServiceFeign.getLiveSearchPage(entity);
        return list;
    }

    /**
     * 上线
     * @param
     * @return
     */
    @RequestMapping(value = "/onPublish/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse onPublicLive(@PathVariable("id")Integer id){
        Live live = new Live();
        live.setIsPublish(1);
        live.setId(id);
        live.setUpdateTime(new Date());
        live.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveServiceFeign.update(id, live);
    }

    /**
     * 下线
     * @param
     * @return
     */
    @RequestMapping(value = "/cancelPublish/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse cancelPublishLive(@PathVariable("id")Integer id){
        Live live = new Live();
        live.setIsPublish(0);
        live.setId(id);
        live.setUpdateTime(new Date());
        live.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveServiceFeign.update(id, live);
    }

    /**
     * 删除
     * @param
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.PUT)
    public  ObjectRestResponse deleteLive(@PathVariable("id")Integer id){
        Live live = new Live();
        live.setIsDelete(1);
        live.setId(id);
        live.setUpdateTime(new Date());
        live.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return liveServiceFeign.update(id, live);
    }


    /**
     * 得到直播的Id title
     * @param liveId
     * @return
     */
    @RequestMapping(value = "/selectLiveIdName", method = RequestMethod.GET)
    public ObjectRestResponse<LiveIdTitle> selectLiveIdName(@RequestParam Integer liveId) {
        Integer id = liveId;
        ObjectRestResponse<Live> liveObjectRestResponse = liveServiceFeign.get(id);
        Live data = liveObjectRestResponse.getData();
        LiveIdTitle liveIdTitle = new LiveIdTitle();
        BeanUtils.copyProperties(data,liveIdTitle);
        ObjectRestResponse<LiveIdTitle> liveIdTitleObjectRestResponse = new ObjectRestResponse<>();
        liveIdTitleObjectRestResponse.setData(liveIdTitle);
        return liveIdTitleObjectRestResponse;
    }

    /**
     * 得到工作员工的Id title
     * @param
     * @return
     */
    @RequestMapping(value = "/getIdNameCorpUser", method = RequestMethod.GET)
    public ObjectRestResponse<List<CorpUserIdName>> getIdNameCorpUser() {
        List<CorpUser> reporterForContent = corpUserServiceFeign.getReporterForContent();
        List<CorpUserIdName> corpUserIdNames = new ArrayList<CorpUserIdName>();
        for (CorpUser corpUser:reporterForContent) {
            CorpUserIdName corpUserIdName = new CorpUserIdName();
            BeanUtils.copyProperties(corpUser,corpUserIdName);
            corpUserIdNames.add(corpUserIdName);
        }
        ObjectRestResponse<List<CorpUserIdName>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(corpUserIdNames);
        return listObjectRestResponse;
    }

    /**
     * 得到工作类型的Id title
     * @param
     * @return
     */
    @RequestMapping(value = "/getIdNameWokeType",method = RequestMethod.GET)
    public ObjectRestResponse<List<KpiTypeConfigIdName>> selectWorkTypeForLive(){
        List<KpiTypeConfig> kpiTypeConfigs = liveServiceFeign.selectWorkTypeForLive();
        ArrayList<KpiTypeConfigIdName> kpiTypeConfigIdNames = new ArrayList<>();
        for (KpiTypeConfig kpiTypeConfig:kpiTypeConfigs) {
            KpiTypeConfigIdName kpiTypeConfigIdName = new KpiTypeConfigIdName();
            BeanUtils.copyProperties(kpiTypeConfig,kpiTypeConfigIdName);
            kpiTypeConfigIdNames.add(kpiTypeConfigIdName);
        }
        ObjectRestResponse<List<KpiTypeConfigIdName>> listObjectRestResponse = new ObjectRestResponse<>();
        listObjectRestResponse.setData(kpiTypeConfigIdNames);
        return listObjectRestResponse;
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }

}
