package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.IPcadvServiceFeign;
import com.kanfa.news.admin.vo.adv.AdvOnlineNumInfo;
import com.kanfa.news.admin.vo.pcadv.PcadvInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.DateHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pcadv")
public class PcadvRest extends BaseRPC {

    @Autowired
    private IPcadvServiceFeign pcadvServiceFeign;

    /**
     * 添加pc广告
     * @param pcadvInfo
     * @return
     */
    @RequestMapping(value = "/addPcAdv", method = RequestMethod.POST)
    public ObjectRestResponse<PcadvInfo> addPcAdv(@RequestBody PcadvInfo pcadvInfo) {
        if(StringUtils.isEmpty(pcadvInfo.getName())){
            return getObjectRestResponse("广告名称不能为空");
        }
        if(StringUtils.isEmpty(pcadvInfo.getDescription())){
            return getObjectRestResponse("广告描述不能为空");
        }
        if(StringUtils.isEmpty(pcadvInfo.getUrl())){
            return getObjectRestResponse("广告链接不能为空");
        }
        if(pcadvInfo.getStartTime()==null || pcadvInfo.getEndTime()==null || DateHandler.isAfterForDay(pcadvInfo.getStartTime(),pcadvInfo.getEndTime())){
            return getObjectRestResponse("广告的上线时间的不能为空，同时开始不能大于结束");
        }
        if(StringUtils.isEmpty(pcadvInfo.getTitle())){
            return getObjectRestResponse("广告标题不能为空");
        }
        if(pcadvInfo.getTypes()==null || pcadvInfo.getTypes().size()<=0){
            return getObjectRestResponse("至少选择一个广告位置");
        }
        pcadvInfo.setCreateTime(new Date());
        pcadvInfo.setCreateUid(Integer.valueOf(getCurrentUserId()));
        pcadvInfo.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        return pcadvServiceFeign.addPcAdv(pcadvInfo);
    }

    /**
     * 分页
     * @param param
     * @return
     */
    @RequestMapping(value = "/getPcAdvPage", method = RequestMethod.GET)
    public TableResultResponse<PcadvInfo> getPcAdvPage(@RequestParam Map<String,Object> param) {
        if(param.get("page")==null){
            param.put("page",1);
        }
        if(param.get("limit")==null){
            param.put("limit",10);
        }
        param.put("isDelete",1);
        return pcadvServiceFeign.getPcAdvPage(param);
    }

    /**
     * 单个查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPcAdv", method = RequestMethod.GET)
    public ObjectRestResponse<PcadvInfo> getPcAdv(@RequestParam Integer id) {
        return pcadvServiceFeign.getPcAdv(id);
    }

    /**
     * 保存更新
     * @param pcadvInfo
     * @return
     */
    @RequestMapping(value = "/upatePcAdv", method = RequestMethod.PUT)
    public ObjectRestResponse<PcadvInfo> upatePcAdv(@RequestBody PcadvInfo pcadvInfo) {
        if(pcadvInfo.getId()==null){
            return getObjectRestResponse("id不能为空");
        }
        return pcadvServiceFeign.upatePcAdv(pcadvInfo.getId(),pcadvInfo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deletePcAdv/{id}", method = RequestMethod.DELETE)
    public ObjectRestResponse<PcadvInfo> deletePcAdv(@PathVariable("id") Integer id) {
        return pcadvServiceFeign.deletePcAdv(id);
    }

    /**
     * 统计上下线
     * @return
     */
    @RequestMapping(value = "/getOnlineNumPcAdv", method = RequestMethod.GET)
    public ObjectRestResponse<AdvOnlineNumInfo> getOnlineNumPcAdv() {
        return pcadvServiceFeign.getOnlineNumPcAdv();
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortPcadv", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse sortPcadv( @RequestBody Map<String, Object> params) {
        return pcadvServiceFeign.sortPcadv(params);
    }

    private ObjectRestResponse<PcadvInfo> getObjectRestResponse(String msg) {
        ObjectRestResponse<PcadvInfo> objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}