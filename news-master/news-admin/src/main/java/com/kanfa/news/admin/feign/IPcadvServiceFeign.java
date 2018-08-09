package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.vo.adv.AdvOnlineNumInfo;
import com.kanfa.news.admin.vo.pcadv.PcadvInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@FeignClient(name = "service-provider-news")
public interface IPcadvServiceFeign {

    /**
     * 新增pc广告
     * @param pcadvInfo
     * @return
     */
    @RequestMapping(value = "/pcadv/addPcAdv",method = RequestMethod.POST)
    ObjectRestResponse<PcadvInfo> addPcAdv(@RequestBody PcadvInfo pcadvInfo);

    /**
     * 分页
     * @param param
     * @return
     */
    @RequestMapping(value = "/pcadv/getAdvPage",method = RequestMethod.GET)
    TableResultResponse<PcadvInfo> getPcAdvPage(@RequestParam Map<String, Object> param);

    /**
     * 单个查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/pcadv/getPcAdv",method = RequestMethod.GET)
    ObjectRestResponse<PcadvInfo> getPcAdv(@RequestParam("id") Integer id);

    /**
     * 更新保存
     * @param id
     * @param pcadvInfo
     * @return
     */
    @RequestMapping(value = "/pcadv/upatePcAdv/{id}",method = RequestMethod.PUT)
    ObjectRestResponse<PcadvInfo> upatePcAdv(@PathVariable("id") Integer id, @RequestBody PcadvInfo pcadvInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/pcadv/deletePcAdv/{id}",method = RequestMethod.DELETE)
    ObjectRestResponse<PcadvInfo> deletePcAdv(@PathVariable("id") Integer id);

    /**
     * 统计上下线
     * @return
     */
    @RequestMapping(value = "/pcadv/getOnlineNumPcAdv",method = RequestMethod.GET)
    ObjectRestResponse<AdvOnlineNumInfo> getOnlineNumPcAdv();

    /**
     * ids查询所有
     * @param oldIds
     * @return
     */
    @RequestMapping(value = "/pcadv/getPcadvInfoByIds",method = RequestMethod.GET)
    List<PcadvInfo> getPcadvInfoByIds(@RequestParam("oldIds") List<Integer> oldIds);

    /**
     * 批量更新
     * @param pcadvInfoList
     * @return
     */
    @RequestMapping(value = "/pcadv/batchUpdate",method = RequestMethod.POST)
    Integer batchUpdate(@RequestBody List<PcadvInfo> pcadvInfoList);

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/pcadv/sortPcadv",method = RequestMethod.POST)
    ObjectRestResponse sortPcadv(@RequestBody Map<String, Object> params);
}
