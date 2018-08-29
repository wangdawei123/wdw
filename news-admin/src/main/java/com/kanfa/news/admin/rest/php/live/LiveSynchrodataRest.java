package com.kanfa.news.admin.rest.php.live;

import com.kanfa.news.admin.feign.ILiveServiceFeign;
import com.kanfa.news.admin.vo.live.LiveAddInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.data.client.feign.IliveSynDataServiceFeign;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Chen
 * on 2018/8/7 15:57
 */
@RestController
@RequestMapping("/liveSynchrodata")
public class LiveSynchrodataRest {
    @Autowired
    private IliveSynDataServiceFeign liveSynDataServiceFeign;
    @Autowired
    private ILiveServiceFeign liveServiceFeign;

    /**
     * 添加直播
     * @param id
     * @return
     */
    @RequestMapping(value = "/addlive", method = RequestMethod.GET)
    public ObjectRestResponse addlive(@RequestParam("id") Integer id) {
        com.kanfa.news.data.common.vo.live.LiveAddInfo liveDetail = liveSynDataServiceFeign.getLiveDetail(id);
        LiveAddInfo liveAddInfo = new LiveAddInfo();
        BeanUtils.copyProperties(liveDetail, liveAddInfo);
        liveServiceFeign.addOneLive(liveAddInfo);
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        String msg = "插入live成功";
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }

    /**
     * 编辑直播
     * @param id
     * @return
     */
    @RequestMapping(value = "/updatelive", method = RequestMethod.GET)
    public ObjectRestResponse updatelive(@RequestParam("id") Integer id) {
        com.kanfa.news.data.common.vo.live.LiveAddInfo liveDetail = liveSynDataServiceFeign.getLiveDetail(id);
        LiveAddInfo liveAddInfo = new LiveAddInfo();
        BeanUtils.copyProperties(liveDetail, liveAddInfo);
        liveServiceFeign.updateLiveAddInfo(liveAddInfo);
        ObjectRestResponse<String> stringObjectRestResponse = new ObjectRestResponse<>();
        String msg = "更新live成功";
        stringObjectRestResponse.setData(msg);
        return stringObjectRestResponse;
    }



}
