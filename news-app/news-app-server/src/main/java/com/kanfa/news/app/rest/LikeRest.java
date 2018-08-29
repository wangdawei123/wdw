package com.kanfa.news.app.rest;

import com.kanfa.news.app.vo.user.PlatformUser;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 视频,VR，直播统计点赞接口
 *
 * @author wdw
 */
@RestController
@RequestMapping("like")
public class LikeRest extends BaseRest {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 统计点赞| 取消点赞 的次数
     * @param type
     * @param content_id
     * @param is_like
     * @param sessionid
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<PlatformUser> index(@RequestParam("type") Integer type,
                                                  @RequestParam("content_id") Integer content_id,
                                                  @RequestParam("is_like") Integer is_like,
                                                  @RequestParam(value = "sessionid",required = false) Integer sessionid) {
        AppObjectResponse result = new AppObjectResponse();
        Integer uid = BaseContextHandler.getUserID() != null ? Integer.valueOf(BaseContextHandler.getUserID()) : null;
        Integer isLike =is_like;
        Integer contentId = content_id;
        if (isLike == 1) {
            if (uid == null) {
                // 保存指定type所有视频|vr的点赞总数
                redisTemplate.opsForHash().increment("like_total_" + type, contentId, 1);
                return result;
            }
            // 保存用户所有点赞过的的视频id
            redisTemplate.opsForSet().add("like_user_"+uid+"_type_"+type,contentId);
            // 保存某个视频的点赞用户列表
            redisTemplate.opsForSet().add("like_type_"+type+"_users_"+contentId,uid);
            // 保存指定type所有视频|vr的点赞总数
            redisTemplate.opsForHash().increment("like_total_" + type, contentId, 1);
        }
        //取消点赞
        if (isLike == 0) {
            if (uid == null) {
                // 保存指定type所有视频|vr的点赞总数
                redisTemplate.opsForHash().increment("like_total_" + type, contentId, -1);
                return result;
            }
            // 取消保存用户所有点赞过的的视频id
            redisTemplate.opsForSet().remove("like_user_"+uid+"_type_"+type,contentId);
            // 取消保存某个视频的点赞用户列表
            redisTemplate.opsForSet().remove("like_type_"+type+"_users_"+contentId,uid);
            // 保存指定type所有视频|vr的点赞总数
            redisTemplate.opsForHash().increment("like_total_" + type, contentId, -1);
        }
        return result;
    }


}
