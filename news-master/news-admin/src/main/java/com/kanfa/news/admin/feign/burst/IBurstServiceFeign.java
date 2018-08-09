package com.kanfa.news.admin.feign.burst;

import com.kanfa.news.admin.entity.Burst;
import com.kanfa.news.admin.entity.BurstResource;
import com.kanfa.news.admin.entity.Channel;
import com.kanfa.news.admin.entity.burst.BurstType;
import com.kanfa.news.admin.vo.burst.BurstInfo;
import com.kanfa.news.admin.vo.burst.BurstTypeInfo;
import com.kanfa.news.admin.vo.burst.BurstUpdateInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/2/24 15:35
 */
@FeignClient(name = "service-provider-news")
public interface IBurstServiceFeign {

    /**
     * 根据id查询频道
     * @param id
     * @return
     */
    @RequestMapping(value = "/burst/{id}",method = RequestMethod.GET)
    ObjectRestResponse<Channel> get(@PathVariable("id") int id);

    @RequestMapping(value = "/burstType",method = RequestMethod.POST)
    ObjectRestResponse<BurstType> addBurstType(@RequestBody BurstType entity);

    @RequestMapping(value = "/burstType/{id}",method = RequestMethod.PUT)
    ObjectRestResponse<BurstType> udpateBurstType(@PathVariable("id") Integer id,@RequestBody BurstType entity);

    @RequestMapping(value = "/burstType/burstTypePage",method = RequestMethod.GET)
    TableResultResponse<BurstTypeInfo> burstTypePage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/burst/burstPage",method = RequestMethod.GET)
    TableResultResponse<BurstInfo> burstPage(@RequestParam Map<String, Object> params);

    @RequestMapping(value = "/burst/getBurstInfoById",method = RequestMethod.GET)
    ObjectRestResponse<BurstInfo> getBurstInfoById(@RequestParam("id") Integer id);

    /**
     * 更新爆料
     * @param burstUpdateInfo
     * @return
     */
    @RequestMapping(value = "/burst/updateBurst",method = RequestMethod.POST)
    ObjectRestResponse<BurstUpdateInfo> updateBurst(@RequestBody BurstUpdateInfo burstUpdateInfo);

    /**
     * 删除爆料资源
     * @param id
     * @param burstResource
     * @return
     */
    @RequestMapping(value = "/burstResource/{id}",method = RequestMethod.PUT)
    ObjectRestResponse updateBurstResource(@PathVariable("id") Integer id,@RequestBody BurstResource burstResource);

    /**
     * 更新爆料
     * @param id
     * @param burst
     * @return
     */
    @RequestMapping(value = "/burst/{id}",method = RequestMethod.PUT)
    ObjectRestResponse update(@PathVariable("id") Integer id, @RequestBody Burst burst);

    /**
     * 查询单个
     * @param entity
     * @return
     */
    @RequestMapping(value = "/burstType/getBurstType",method = RequestMethod.POST)
    List<BurstType> getBurstType(@RequestBody BurstType entity);

    /**
     * 查询类型集合
     * @param burstType
     * @return
     */
    @RequestMapping(value = "/burstType/burstTypeListAll",method = RequestMethod.POST)
    ObjectRestResponse<List<BurstType>> burstTypeListAll(@RequestBody BurstType burstType);
}
