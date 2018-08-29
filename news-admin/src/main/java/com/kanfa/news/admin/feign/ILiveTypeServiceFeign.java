package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.LiveType;
import com.kanfa.news.admin.feign.fallback.LiveTypeServiceFallBack;
import com.kanfa.news.admin.vo.live.LiveTypeInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-news", fallback = LiveTypeServiceFallBack.class)
public interface ILiveTypeServiceFeign {
    /**
     * 新增直播类型
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/liveType", method = RequestMethod.POST)
    ObjectRestResponse<LiveType> add(@RequestBody LiveType entity);

    /**
     * 编辑直播类型
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/liveType/{id}", method = RequestMethod.GET)
    @ResponseBody
    ObjectRestResponse<LiveType> get(@PathVariable("id") int id);

    /**
     * 保存直播类型
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/liveType/{id}", method = RequestMethod.PUT)
    @ResponseBody
    ObjectRestResponse<LiveType> update(@PathVariable("id") Integer id, @RequestBody LiveType entity);

    /**
     * 分页查询直播类型
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/liveType/getPage")
    TableResultResponse<LiveTypeInfo> list(@RequestParam Map<String, Object> params);

    /**
     * 删除直播类型
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/liveType/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    ObjectRestResponse<LiveType> remove(@PathVariable("id") int id);



    @RequestMapping(value = "/liveType/all", method = RequestMethod.GET)
    @ResponseBody
    List<LiveType> all();


}
