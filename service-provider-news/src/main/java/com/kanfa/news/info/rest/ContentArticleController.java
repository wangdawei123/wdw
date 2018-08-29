package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.biz.CommentBiz;
import com.kanfa.news.info.biz.ContentArticleBiz;
import com.kanfa.news.info.biz.ContentBiz;
import com.kanfa.news.info.entity.ContentArticle;
import com.kanfa.news.info.vo.admin.comment.CommentInfo;
import com.kanfa.news.info.vo.admin.info.ContentArticleInfo;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("contentArticle")
public class ContentArticleController extends BaseController<ContentArticleBiz,ContentArticle> {

    @Autowired
    private ContentArticleBiz contentArticleBiz;
    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private CommentBiz commentBiz;

    @RequestMapping(value = "/getContentArticle",method = RequestMethod.POST)
    public ObjectRestResponse<ContentArticleInfo> getContentArticle(@RequestBody ContentArticleInfo contentArticleInfo){
        //内容信息，详情，评论信息
        ContentInfo contentArticle = contentBiz.getContentArticle(contentArticleInfo.getId(),false);
        BeanUtils.copyProperties(contentArticle,contentArticleInfo);
        //推荐内容
        ContentInfo entity=new ContentInfo();
        entity.setId(contentArticle.getId());
        entity.setContentState(1);
        entity.setCheckStatus(1);
        List<ContentInfo> broadList = contentBiz.getBroadContent(entity);
        contentArticleInfo.setBroadList(broadList);
        //评论分页查询
        Map<String,Object> map=new HashMap<>();
        map.put("contentId",contentArticleInfo.getId());
        map.put("page",1);
        map.put("limit",5);
        TableResultResponse<CommentInfo> pageComment=commentBiz.getPage(map);
        contentArticleInfo.setPageComment(pageComment);
        ObjectRestResponse<ContentArticleInfo> resp=new ObjectRestResponse<>();
        resp.setData(contentArticleInfo);
        return resp;
    }
}