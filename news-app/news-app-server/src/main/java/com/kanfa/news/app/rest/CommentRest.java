package com.kanfa.news.app.rest;

import com.kanfa.news.app.biz.*;
import com.kanfa.news.app.config.RequestConfiguration;
import com.kanfa.news.app.entity.AppUser;
import com.kanfa.news.app.entity.Discussion;
import com.kanfa.news.app.feign.ICommentServiceFeign;
import com.kanfa.news.app.mapper.AppUserMapper;
import com.kanfa.news.app.mapper.DiscussionMapper;
import com.kanfa.news.app.vo.admin.comment.CommentConstant;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("comment")
public class CommentRest extends BaseRest {
    @Autowired
    private CommentBiz commentBiz;
    @Autowired
    private SettingBiz settingBiz;
    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private DiscussionMapper discussionMapper;
    @Autowired
    private LiveBiz liveBiz;
    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private RequestConfiguration requestConfiguration;
    @Autowired
    private ProblemBiz problemBiz;

    @RequestMapping(value = "/getDiscussion", method = RequestMethod.POST)
    public ObjectRestResponse getDiscussion(@RequestParam(value = "room_id") String roomId) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        String redisKey = "Discussion_" + roomId;
        Object object = redisTemplate.opsForValue().get("redisKey");
        Map dataMap = new HashMap(16);
        if (object == null) {
            Example example = new Example(Discussion.class);
            int limit = Math.abs(new Random().nextInt() % 11) + 10;
            example.createCriteria().andCondition("limit " + limit);
            List<Discussion> discussionList = discussionMapper.selectByRowBounds(null, new RowBounds(0, limit));
            redisTemplate.opsForValue().set(redisKey, discussionList, 3600L, TimeUnit.SECONDS);
            dataMap.put("discussion", discussionList);
        } else {
            dataMap.put("discussion", object);
        }
        objectRestResponse.setData(dataMap);
        return objectRestResponse;
    }

    /**
     * 增加评论
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AppObjectResponse add(@RequestParam(value = "cid") Integer cid,
                                 @RequestParam(value = "replyId", required = false) Integer replyId,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam(value = "devID") String devID,
                                 @RequestParam("sessionid") String sessionid,
                                 @RequestParam(value = "type") Integer type) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if (cid == null) {
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_ILLEGALITY.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_ILLEGALITY.value());
            return response;
        } else if (content == null) {
            response.setStatus(AppCommonMessageEnum.APP_COMMENT_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_COMMENT_MISSING.value());
            return response;
        }

        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        Map<String ,Object> map = new HashMap<>(5);
        String ip = request.getHeader(requestConfiguration.getIpAddr());
        System.out.println("rest request.getHeader(requestConfiguration.getIpAddr()) = " + ip);
        AppUser appUser = appUserMapper.selectByPrimaryKey(o);
        Integer uid = appUser.getId();
        String nickName = appUser.getNickname();
        if(o == null){
            String prefix = settingBiz.selectByname("comment_tourist_prefix");
            //设置一个1000-10000的随机数
            Integer random = (int)Math.random()*9001+1000;
            long l = System.currentTimeMillis() + random;
            String s = String.valueOf(l);
            //取最后8个字符
            String substring = s.substring(s.length()-8 ,s.length());
            nickName = prefix+ substring;
        }
        if( null != uid){
            nickName = null;
        }else if(nickName == null || devID == null){
            response.setStatus(AppCommonMessageEnum.APP_DEVID_ERROR.key());
            response.setMessage(AppCommonMessageEnum.APP_DEVID_ERROR.value());
            return response;
        }
        //检查是否含有敏感词
        Map<String, Object> badWords = commentBiz.checkBadwords(content);
        if((Boolean) badWords.get("bads")){
            response.setStatus(AppCommonMessageEnum.APP_HAS_BADWORDS.key());
            response.setMessage(AppCommonMessageEnum.APP_HAS_BADWORDS.value());
            return response;
        }
        //检查是否在黑名单中
        Boolean result = commentBiz.checkBlacklist(uid ,devID);
        if(result){
            response.setStatus(AppCommonMessageEnum.APP_FORBID_COMMENT.key());
            response.setMessage(AppCommonMessageEnum.APP_FORBID_COMMENT.value());
            return response;
        }

        Integer id = commentBiz.add(uid ,nickName ,devID ,cid ,replyId ,content ,type ,(Boolean) badWords.get("bads"),request);
        if(null == id){
            response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
            response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            return response;
        }
        String msg = null;
        if(cid.equals(LiveCommonEnum.EXTENSION_LIVE_ID)){
            msg = "评论成功！";
        }else {
            String value = settingBiz.selectByname("comment_ops");
            if((LiveCommonEnum.OPS_TRUE+"").equals(value)){
                msg = "评论成功！";
            }else{
                msg = "评论成功！";
            }
        }
        //推送给被评论者
        if(type.equals(LiveCommonEnum.NEWS_COMMENT)){
            //更新评论数
            contentBiz.updateCommentCount(cid ,null);
        }else if(type.equals(LiveCommonEnum.REVELATION_COMMENT)){

        }else if(type.equals(LiveCommonEnum.LIVE_COMMENT)){
            liveBiz.updateCommentCount(cid ,null);
            msg = "评论成功！";
        }
        map.put("id" ,id);
        map.put("content" ,badWords.get("content"));
        map.put("msg" ,msg);
        response.setData(map);
        return response;
    }

    /**
     * 获取一个内容的评论
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public AppObjectResponse getList(@RequestParam(value = "cid") Integer cid,
                                     @RequestParam(value = "uid", required = false,defaultValue = "0") Integer uid,
                                     @RequestParam(value = "type") Integer type,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "pcount", defaultValue = "10") Integer pcount) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if (cid == null) {
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_ILLEGALITY.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_ILLEGALITY.value());
            return response;
        }
        Map<String ,Object> map = new HashMap<>(5);
        //由于获取某文章内容的评论，需要显示所有用户的评论，不能用uid限制条件，所以uid传参为null
        List<Map<String, Object>> comments = commentBiz.selectComment(cid, page, pcount, null, type, uid);
        if(comments == null){
            map.put("comment",new ArrayList<>());
            map.put("total",0);
        }else{
            map.put("comment",comments);
            map.put("total",comments.size());
        }
        response.setData(map);
        return response;
    }


    /**
     * App--我的评论
     */
    @RequestMapping(value = "/unread", method = RequestMethod.POST)
    public AppObjectResponse unread(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "pcount", defaultValue = "8") Integer pcount,
                                    @RequestParam("sessionid") String sessionid,
                                    //1:默认为回复评论 2：自己发布的评论
                                    @RequestParam(value = "type", defaultValue = "1") Integer type) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        if (o == null) {
            //用户未登陆
            response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
            response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
            return response;
        } else {
            Integer uid = (Integer) o;
            Map<String ,Object> map = new HashMap<>(5);
            if(type.equals(LiveCommonEnum.COMMENT_PUBLISH)){
                //获取自己的评论 type = 2
                List<Map<String, Object>> listByCreator = commentBiz.getListByCreator(uid, page, pcount);
                map.put("comment",listByCreator);
            }else{
                //获取回复的评论 type = 1
                List<Map<String, Object>> listByReceiver = commentBiz.getListByReceiver(uid, page, pcount);
                map.put("comment",listByReceiver);
            }
            commentBiz.updateread(uid);
            response.setData(map);
            return response;
        }
    }


    /**
     * App--用户--删除一条评论
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public AppObjectResponse delOne(@RequestParam("id") Integer id,
                                    @RequestParam("username") String userName,
                                    @RequestParam("sessionid") String sessionid) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if (id == null || userName == null) {
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        }
        if (sessionid == null) {
            response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
            response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
            return response;
        } else {
            Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
            if (o == null) {
                //用户未登陆
                response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
                response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
                return response;
            } else {
                Integer uid = (Integer) o;
                Integer i = commentBiz.delOne(uid,userName,id);
                if(i.equals(CommentConstant.COMMENT_NOT_EXIST)){
                    //要删除的评论不存在
                    response.setStatus(AppCommonMessageEnum.APP_COMMENT_NOT_EXIST.key());
                    response.setMessage(AppCommonMessageEnum.APP_COMMENT_NOT_EXIST.value());
                }else if(i.equals(CommentConstant.DEL_COMMENT_ERROR)){
                    //操作过程出现错误
                    response.setStatus(AppCommonMessageEnum.APP_DEL_COMMENT_ERROR.key());
                    response.setMessage(AppCommonMessageEnum.APP_DEL_COMMENT_ERROR.value());
                }else if(!i.equals(CommentConstant.SECCESS)){
                    //删除未成功
                    response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
                    response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
                }else if(i.equals(CommentConstant.SECCESS)){
                    response.setStatus(AppCommonMessageEnum.APP_DEL_COMMENT_SUCCESS.key());
                    response.setMessage(AppCommonMessageEnum.APP_DEL_COMMENT_SUCCESS.value());
                }
                response.setData(null);
                return response;
            }
        }
    }


    /**
     * 删除我的回答
     *
     * @param id        回答的id
     * @param sessionid
     * @return
     */
    @RequestMapping(value = "/delMypost", method = RequestMethod.POST)
    public AppObjectResponse delMypost(@RequestParam("id") Integer id, @RequestParam("sessionid") String sessionid) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        if (o == null) {
            //用户未登陆
            response.setStatus(AppCommonMessageEnum.USER_NOT_LOGIN.key());
            response.setMessage(AppCommonMessageEnum.USER_NOT_LOGIN.value());
            return response;
        } else {
            Integer uid = (Integer) o;
            Integer i = problemBiz.delMyProblem(uid, id);
            if(i != 1){
                //删除未成功
                response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
                response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            }else if(i == 1){
                response.setStatus(AppCommonMessageEnum.APP_DEL_COMMENT_SUCCESS.key());
                response.setMessage(AppCommonMessageEnum.APP_DEL_COMMENT_SUCCESS.value());
            }
            return response;
        }
    }

}
