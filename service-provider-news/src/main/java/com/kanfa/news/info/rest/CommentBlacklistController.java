package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CommentBlacklistBiz;
import com.kanfa.news.info.entity.CommentBlacklist;
import com.kanfa.news.info.vo.admin.comment.CommentBlacklistInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("commentBlacklist")
public class CommentBlacklistController extends BaseController<CommentBlacklistBiz,CommentBlacklist> {

    @Autowired
    private CommentBlacklistBiz commentBlacklistBiz;

    /**
     * 查询所有黑名单分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/getBlackUserPage",method = RequestMethod.GET)
    public TableResultResponse<CommentBlacklistInfo> getBlackUserPage(@RequestParam Map<String, Object> params){
        TableResultResponse<CommentBlacklistInfo> blackUserPage = commentBlacklistBiz.getBlackUserPage(params);
        for (CommentBlacklistInfo commentBlacklistInfo : blackUserPage.getData().getRows()) {
            if(commentBlacklistInfo.getUid()==0){
                commentBlacklistInfo.setType("游客");
                commentBlacklistInfo.setUid(null);
            }else{
                commentBlacklistInfo.setType("会员");
            }
        }
        return blackUserPage;
    }
}