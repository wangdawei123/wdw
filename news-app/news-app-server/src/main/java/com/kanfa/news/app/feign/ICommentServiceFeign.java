package com.kanfa.news.app.feign;

import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jezy
 */
@FeignClient(name = "service-provider-news")
public interface ICommentServiceFeign {
    @RequestMapping(value = "/comment/getDiscussion", method = RequestMethod.POST)
    @ResponseBody
    ObjectRestResponse getDiscussion(@RequestParam(value = "roomId") String roomId);

    /**
     * App--用户删除一条评论
     */
    @RequestMapping(value = "/comment/delOne", method = RequestMethod.POST)
    @ResponseBody
    AppObjectResponse delOne(@RequestParam("id") Integer id,
                             @RequestParam("userName") String userName,
                             @RequestParam("uid") Integer uid);


    /**
     * app -- 删除我的回答
     * @param id
     * @return
     */
    @RequestMapping(value = "/delMypost" , method = RequestMethod.GET)
    AppObjectResponse delMypost(@RequestParam("id") Integer id, @RequestParam("uid") Integer uid) ;

    /**
     * 增加评论
     */
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    AppObjectResponse add(@RequestParam("sessionid") String sessionid,
                          @RequestParam(value = "cid") Integer cid,
                          @RequestParam(value = "replyId") Integer replyId,
                          @RequestParam(value = "content") String content,
                          @RequestParam(value = "devID") String devID,
                          @RequestParam(value = "type") Integer type);


    /**
     * 获取一个内容的评论
     */
    @RequestMapping(value = "/comment/getList", method = RequestMethod.POST)
    AppObjectResponse getList(@RequestParam(value = "cid") Integer cid,
                              @RequestParam(value = "uid") Integer uid,
                              @RequestParam(value = "type") Integer type,
                              @RequestParam(value = "page") Integer page,
                              @RequestParam(value = "pcount") Integer pcount);


    /**
     * App--我的评论
     */
    @RequestMapping(value = "/comment/unread", method = RequestMethod.POST)
    AppObjectResponse unread(@RequestParam(value = "page") Integer page,
                             @RequestParam(value = "pcount") Integer pcount,
                             @RequestParam(value = "uid") Integer uid,
                             //1:默认为回复评论 2：自己发布的评论
                             @RequestParam(value = "type") Integer type);


    /**
     *  文章取消收藏
     */
    @RequestMapping(value = "/fav/del", method = RequestMethod.POST)
    AppObjectResponse delFav(@RequestParam(value = "uid") Integer uid,
                             @RequestParam(value = "cid") Integer cid,
                             @RequestParam(value = "type") Integer type);

}
