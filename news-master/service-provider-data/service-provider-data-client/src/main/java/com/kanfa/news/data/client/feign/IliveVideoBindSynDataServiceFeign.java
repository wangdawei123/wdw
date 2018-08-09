package com.kanfa.news.data.client.feign;

import com.kanfa.news.data.common.vo.live.XmLiveVideoBind;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Chen
 * on 2018/8/8 11:03
 */
@FeignClient(name = "service-provider-data")
public interface IliveVideoBindSynDataServiceFeign {
    /**
     * 根据cid查询Livevideobinds集合
     * @param cid
     * @return
     */
    @RequestMapping(value = "/xmLiveVideoBind/getAllBinds",method = RequestMethod.GET)
    List<XmLiveVideoBind> getAllBinds(@RequestParam("cid")Integer cid);
}
