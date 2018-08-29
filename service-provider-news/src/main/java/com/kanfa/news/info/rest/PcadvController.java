package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.PcPosBiz;
import com.kanfa.news.info.biz.PcadvBiz;
import com.kanfa.news.info.entity.PcPos;
import com.kanfa.news.info.entity.Pcadv;
import com.kanfa.news.info.vo.admin.adv.AdvOnlineNumInfo;
import com.kanfa.news.info.vo.admin.pcadv.PcadvInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pcadv")
public class PcadvController extends BaseController<PcadvBiz,Pcadv> {

    @Autowired
    private PcadvBiz pcadvBiz;
    @Autowired
    private PcPosBiz pcPosBiz;

    /**
     * 新增pc广告
     * @param pcadvInfo
     * @return
     */
    @RequestMapping(value = "/addPcAdv",method = RequestMethod.POST)
    public ObjectRestResponse<PcadvInfo> addPcAdv(@RequestBody PcadvInfo pcadvInfo){
        pcadvInfo=pcadvBiz.addPcAdv(pcadvInfo);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(pcadvInfo);
        return objectRestResponse;
    }

    /**
     * 分页
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAdvPage",method = RequestMethod.GET)
    public TableResultResponse<PcadvInfo> getPcAdvPage(@RequestParam Map<String, Object> param){
        return pcadvBiz.getPcAdvPage(param);
    }

    /**
     * 单个查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPcAdv",method = RequestMethod.GET)
    public ObjectRestResponse<PcadvInfo> getPcAdv(@RequestParam("id") Integer id){
        Pcadv pcadv = pcadvBiz.selectById(id);
        if(pcadv!=null){
            PcadvInfo pcadvInfo=new PcadvInfo();
            BeanUtils.copyProperties(pcadv,pcadvInfo);
            pcadvInfo.setCreateTime(null);
            pcadvInfo.setCreateUid(null);
            pcadvInfo.setOrderNumber(null);
            pcadvInfo.setViews(null);
            pcadvInfo.setIsDelete(null);
            PcPos pcPos = new PcPos();
            pcPos.setPcId(id);
            List<PcPos> pcPosList = pcPosBiz.selectList(pcPos);
            if(pcPosList!=null && pcPosList.size()>0){
                List<Integer> types=new ArrayList<>(4);
                for (PcPos pos : pcPosList) {
                    types.add(pos.getType());
                }
                pcadvInfo.setTypes(types);
            }
            ObjectRestResponse objectRestResponse = new ObjectRestResponse();
            objectRestResponse.setData(pcadvInfo);
            return objectRestResponse;
        }
        return new ObjectRestResponse<>();
    }

    /**
     * 更新保存
     * @param pcadvInfo
     * @return
     */
    @RequestMapping(value = "/upatePcAdv/{id}",method = RequestMethod.PUT)
    public ObjectRestResponse<PcadvInfo> upatePcAdv(@RequestBody PcadvInfo pcadvInfo){
        pcadvInfo = pcadvBiz.upatePcAdv(pcadvInfo);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(pcadvInfo);
        return objectRestResponse;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deletePcAdv/{id}",method = RequestMethod.DELETE)
    public ObjectRestResponse<PcadvInfo> deletePcAdv(@PathVariable("id") Integer id){
        pcadvBiz.deletePcAdv(id);
        return new ObjectRestResponse<>();
    }

    /**
     * 统计上下线
     * @return
     */
    @RequestMapping(value = "/getOnlineNumPcAdv",method = RequestMethod.GET)
    public ObjectRestResponse<AdvOnlineNumInfo> getOnlineNumPcAdv(){
        AdvOnlineNumInfo advOnlineNumInfo = pcadvBiz.getOnlineNumPcAdv();
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setData(advOnlineNumInfo);
        return objectRestResponse;
    }

    /**
     * ids查询所有
     * @param oldIds
     * @return
     */
    @RequestMapping(value = "/getPcadvInfoByIds",method = RequestMethod.GET)
    public List<Pcadv> getPcadvInfoByIds(@RequestParam("oldIds") List<Integer> oldIds){
        return pcadvBiz.getPcadvInfoByIds(oldIds);
    }

    /**
     * 批量更新
     * @param pcadvInfoList
     * @return
     */
    @RequestMapping(value = "/batchUpdate",method = RequestMethod.POST)
    public Integer batchUpdate(@RequestBody List<Pcadv> pcadvInfoList){
        return pcadvBiz.batchUpdate(pcadvInfoList);
    }

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/sortPcadv",method = RequestMethod.POST)
    public ObjectRestResponse sortPcadv(@RequestBody Map<String, Object> params){
        List<Integer> newIds = (List<Integer>) params.get("newIds");
        List<Integer> oldIds = (List<Integer>) params.get("oldIds");
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
        List<Pcadv> oldList = pcadvBiz.getPcadvInfoByIds(oldIds);
        //新的
        List<Pcadv> newList = pcadvBiz.getPcadvInfoByIds(newIds);
        List<Integer> sort = new ArrayList<>(oldList.size());
        if (oldList != null && oldList.size() > 0) {
            for (Pcadv pcadvInfo : oldList) {
                sort.add(pcadvInfo.getOrderNumber());
            }
        }
        for (int i = 0; i < newList.size(); i++) {
            Pcadv channelContent = newList.get(i);
            channelContent.setId(newList.get(i).getId());
            channelContent.setOrderNumber(sort.get(i));
        }
        List<Pcadv> pcadvInfoList = newList.subList(count, newList.size());
        ObjectRestResponse<Boolean> restResponse = new ObjectRestResponse<>();
        if (pcadvInfoList != null && pcadvInfoList.size() > 0) {
            Integer succNum = pcadvBiz.batchUpdate(pcadvInfoList);
            if (succNum <= 0) {
                restResponse.setData(true);
                restResponse.setMessage("排序失败");
            }
        }
        restResponse.setRel(false);
        return restResponse;
    }

    private ObjectRestResponse<PcadvInfo> getObjectRestResponse(String msg) {
        ObjectRestResponse<PcadvInfo> objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}