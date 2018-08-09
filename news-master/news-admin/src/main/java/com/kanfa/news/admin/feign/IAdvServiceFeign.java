package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.vo.adv.AdvInfo;
import com.kanfa.news.admin.vo.adv.AdvOnlineNumInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@FeignClient(name = "service-provider-news")
public interface IAdvServiceFeign {

    /**
     * 新增广告
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/adv/addAdv",method = RequestMethod.POST)
    ObjectRestResponse<AdvInfo> addAdv(@RequestBody AdvInfo advInfo);

    /**
     * 分页
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/adv/getAdvPage",method = RequestMethod.POST)
    TableResultResponse<AdvInfo> getAdvPage(@RequestBody AdvInfo advInfo);

    /**
     * 查询单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/adv/getAdv/{id}",method = RequestMethod.GET)
    ObjectRestResponse<AdvInfo> getAdv(@PathVariable("id") Integer id);

    /**
     * 更新保存
     * @param advInfo
     * @return
     */
    @RequestMapping(value = "/adv/updateAdv/{id}",method = RequestMethod.PUT)
    ObjectRestResponse<AdvInfo> updateAdv(@PathVariable("id") Integer id, @RequestBody AdvInfo advInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/adv/deleteAdv/{id}",method = RequestMethod.DELETE)
    ObjectRestResponse<AdvInfo> deleteAdv(@PathVariable("id") Integer id);

    /**
     * 上下线统计
     * @return
     */
    @RequestMapping(value = "/adv/getOnlineNum",method = RequestMethod.GET)
    ObjectRestResponse<AdvOnlineNumInfo> getOnlineNum();

    /**
     * 根据ids查询
     * @param oldIds
     * @return
     */
    @RequestMapping(value = "/adv/getAdvInfoByIds",method = RequestMethod.GET)
    List<AdvInfo> getAdvInfoByIds(@RequestParam("oldIds") List<Integer> oldIds);

    /**
     * 批量更新
     * @param advInfoList
     * @return
     */
    @RequestMapping(value = "/adv/batchUpdate",method = RequestMethod.POST)
    Integer batchUpdate(@RequestBody List<AdvInfo> advInfoList);

    /**
     * 拖拽排序
     * @param params
     * @return
     */
    @RequestMapping(value = "/adv/sortAdv",method = RequestMethod.POST)
    ObjectRestResponse sortAdv(@RequestBody Map<String, Object> params);
}
