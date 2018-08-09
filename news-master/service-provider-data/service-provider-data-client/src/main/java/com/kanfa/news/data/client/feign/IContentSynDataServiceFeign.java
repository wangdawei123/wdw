package com.kanfa.news.data.client.feign;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.data.common.vo.channel.ContentInfo;
import com.kanfa.news.data.common.vo.video.VideoContentInfo;
import com.kanfa.news.data.common.vo.vr.VRContentAddInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jiqc
 * @date 2018/3/5 17:28
 */
@FeignClient(value = "service-provider-data")
public interface IContentSynDataServiceFeign {

    /**
     * 根据id查询内容
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/xmContent/getContentInfo/{id}",method = RequestMethod.GET)
    ObjectRestResponse<ContentInfo> getContentInfo(@PathVariable("id") int id);

    /**
     * 得到视频详尽信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getVideoDetail",method = RequestMethod.GET)
    VideoContentInfo getVideoDetail(@RequestParam("id")Integer id);

    /**
     * 得到vr详尽信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/content/getVrDetail",method = RequestMethod.GET)
    VRContentAddInfo getVrDetail(@RequestParam("id")Integer id);

}
