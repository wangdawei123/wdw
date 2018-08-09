package com.kanfa.news.web.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.entity.Content;
import com.kanfa.news.web.feign.IContentServiceFeign;
import com.kanfa.news.web.vo.comment.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 评论
 * @author jiqc
 */
@Controller
@RequestMapping("/comment")
public class CommentRest {

    @Autowired
    private IContentServiceFeign contentServiceFeign;


    /**
     * 异步请求评论分页
     * @param commentInfo
     * @return
     */
    @RequestMapping(value = "/ajaxGetList",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse ajaxGetList(CommentInfo commentInfo) {
        if(commentInfo.getCid()==null){
            getObjectRestResponse("文章id不能为空",null);
        }
        commentInfo.setpUid(commentInfo.getUid());
        ObjectRestResponse<Content> contentObjectRestResponse = contentServiceFeign.get(commentInfo.getCid());
        if(contentObjectRestResponse.getData()==null){
            getObjectRestResponse("文章不存在","web/redirect/test");
        }
        if(commentInfo.getPage()==null){
            commentInfo.setPage(1);
        }
        if(commentInfo.getPcount()==null){
            commentInfo.setPcount(5);
        }
        commentInfo.setPcount(commentInfo.getPage()*commentInfo.getPcount());
        commentInfo.setPage(1);
        return contentServiceFeign.getCommentList(commentInfo);
    }

    private ObjectRestResponse getObjectRestResponse(String msg,Object data) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setData(data);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
