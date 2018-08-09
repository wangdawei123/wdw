package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "service-provider-news")
public interface IFavServiceFeign {
    @RequestMapping(value = "/fav/add", method = RequestMethod.POST)
    AppObjectResponse add(@RequestParam(value = "uid") Integer uid,
                          @RequestParam(value = "cid") Integer cid,
                          @RequestParam(value = "type", defaultValue = "0") Integer type);


    /**
     *  按类型获取我的收藏
     */
    @RequestMapping(value = "/fav/getFavList", method = RequestMethod.POST)
    AppObjectResponse getFavList(@RequestParam("page") Integer page,
                                 @RequestParam("pcount") Integer pcount,
                                 @RequestParam("uid") Integer uid,
                                 @RequestParam("type") Integer type);


    /**
     *  文章取消收藏
     */
    @RequestMapping(value = "/fav/del", method = RequestMethod.POST)
    AppObjectResponse delFav(@RequestParam(value = "uid") Integer uid,
                             @RequestParam(value = "cid") Integer cid,
                             @RequestParam(value = "type") Integer type);
}
