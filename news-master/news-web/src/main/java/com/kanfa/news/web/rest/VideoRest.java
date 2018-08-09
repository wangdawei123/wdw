package com.kanfa.news.web.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.feign.IContentServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Chen
 * on 2018/6/18 14:41
 */

@Controller
@RequestMapping("/web/video")
public class VideoRest {

    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    protected HttpServletRequest request;


    /**
     * 视频详情页h5分享页接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/getVideoDetail",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse getVideoDetail(@RequestParam("id")Integer id){
        return  contentServiceFeign.getVideoDetail(id);
    }


    /**
     * 视频专辑h5分享页接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/getVideoAlbum",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse getVideoAlbum(@RequestParam("id")Integer id){
        return contentServiceFeign.getVideoAlbum(id);
    }







}
