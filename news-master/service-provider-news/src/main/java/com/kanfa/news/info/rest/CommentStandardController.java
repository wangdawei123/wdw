package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CommentStandardBiz;
import com.kanfa.news.info.entity.CommentStandard;
import com.kanfa.news.info.vo.admin.comment.CommentStandardInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("commentStandard")
public class CommentStandardController extends BaseController<CommentStandardBiz,CommentStandard> {
    @Autowired
    private CommentStandardBiz commentStandardBiz;

    /**
     * 分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/getPage",method = RequestMethod.POST)
    TableResultResponse<CommentStandardInfo> getPage(@RequestBody Map<String, Object> params){
        return commentStandardBiz.getPage(params);
    }

    /**
     * 分页查询根据content
     * @param params
     * @return
     */
    @RequestMapping(value = "/getPageByTitle",method = RequestMethod.GET)
    public TableResultResponse<CommentStandardInfo> getPageByTitle(@RequestParam() Map<String, Object> params){
        return commentStandardBiz.getPageByTitle(params);
    }

}