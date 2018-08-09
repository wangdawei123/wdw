package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.StringHelper;
import com.kanfa.news.info.biz.AppUserBiz;
import com.kanfa.news.info.biz.CommentBiz;
import com.kanfa.news.info.biz.ProblemBiz;
import com.kanfa.news.info.biz.UserBiz;
import com.kanfa.news.info.entity.AppUser;
import com.kanfa.news.info.entity.Comment;
import com.kanfa.news.info.entity.Problem;
import com.kanfa.news.info.entity.UserAuth;
import com.kanfa.news.info.mapper.CommentMapper;
import com.kanfa.news.info.vo.admin.comment.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProblemController extends BaseController<ProblemBiz,Problem> {
    @Autowired
    private ProblemBiz problemBiz;
    @Autowired
    private AppUserBiz userBiz;


    /**
     * app -- 删除我的回答
     * @param id
     * @return
     */
    @RequestMapping(value = "/delMypost" , method = RequestMethod.GET)
    public AppObjectResponse delMypost(@RequestParam("id") Integer id ,@RequestParam("uid") Integer uid) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
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

    /**
     * 更新用户信息
     */
    @RequestMapping(value = "/updateUserInfo" , method = RequestMethod.POST)
    public AppObjectResponse updateUserInfo(@RequestParam("uid") Integer uid , @RequestParam("nickname") String nickname, @RequestParam("gender") String gender) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        // 清除空白字符
        String nameString = StringHelper.trimString(nickname);
        AppUser user = userBiz.findByNickname(nameString);
        if(user != null && !user.getId().equals(uid)){
            response.setStatus(AppCommonMessageEnum.APP_USERNAME_IS_CHANGED.key());
            response.setMessage(AppCommonMessageEnum.APP_USERNAME_IS_CHANGED.value());
            return response;
        }
        if(StringHelper.chk_cn_str(nameString).equals(LiveCommonEnum.CONSTANT_TWO) && nameString.length() > LiveCommonEnum.CONSTANT_EIGHT){
            response.setStatus(AppCommonMessageEnum.APP_USERNAME_TOO_LONG.key());
            response.setMessage(AppCommonMessageEnum.APP_USERNAME_TOO_LONG.value());
            return response;
        }
        if(StringHelper.chk_cn_str(nameString).equals(LiveCommonEnum.CONSTANT_ONE) && nameString.length() > LiveCommonEnum.CONSTANT_TWENTY_TWO){
            response.setStatus(AppCommonMessageEnum.APP_USERNAME_TOO_LONG.key());
            response.setMessage(AppCommonMessageEnum.APP_USERNAME_TOO_LONG.value());
            return response;
        }
        if(StringHelper.chk_cn_str(nameString).equals(LiveCommonEnum.CONSTANT_ZERO) && nameString.length() > LiveCommonEnum.CONSTANT_SEXTEEN){
            response.setStatus(AppCommonMessageEnum.APP_USERNAME_TOO_LONG.key());
            response.setMessage(AppCommonMessageEnum.APP_USERNAME_TOO_LONG.value());
            return response;
        }
        Integer r = userBiz.updateone(uid ,nameString ,gender);
        if(r < LiveCommonEnum.CONSTANT_ONE){
            response.setData(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
            response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            return response;
        }
        return response;
    }


}