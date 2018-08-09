package com.kanfa.news.admin.feign;

import com.kanfa.news.admin.entity.Message;
import com.kanfa.news.admin.vo.message.CityInfo;
import com.kanfa.news.admin.vo.message.MessageInfo;
import com.kanfa.news.admin.vo.message.ProvinceInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/5 17:28
 */
@FeignClient(name = "service-provider-news")
public interface IMessageServiceFeign {

    /**
     * 查询省份信息
     * @return
     */
    @RequestMapping(value = "/region/getProvince",method = RequestMethod.GET)
    List<ProvinceInfo> getProvince();

    /**
     * 查询城市信息
     * @return
     */
    @RequestMapping(value = "/city/getCity",method = RequestMethod.GET)
    List<CityInfo> getCity(@RequestParam("provinceId") Integer provinceId);

    /**
     * 查询单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/city/{id}",method = RequestMethod.GET)
    ObjectRestResponse<CityInfo> getCityOne(@PathVariable("id") Integer id);

    /**
     * 添加推送
     * @param message
     * @return
     */
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    ObjectRestResponse<Message> addPushMessage(@RequestBody Message message);

    /**
     * 搜索所有消息分页
     * @return
     */
    @RequestMapping(value = "/message/getMessagePage",method = RequestMethod.GET)
    TableResultResponse<MessageInfo> getMessagePage(@RequestParam Map<String, Object> params);

    /**
     * 查询单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/message/{id}",method = RequestMethod.GET)
    ObjectRestResponse<MessageInfo> getMessageOne(@PathVariable("id") Integer id);

}
