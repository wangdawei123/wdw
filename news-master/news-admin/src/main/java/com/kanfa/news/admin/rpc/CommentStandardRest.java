package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.CommentStandard;
import com.kanfa.news.admin.feign.ICommentStandardServiceFeign;
import com.kanfa.news.admin.vo.comment.CommentStandardInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/3/24 19:05
 */
@RestController
@RequestMapping("commentStandard")
public class CommentStandardRest extends BaseRPC {

    @Autowired
    private ICommentStandardServiceFeign commentStandardServiceFeign;


    /**
     * 添加标准评论
     * @param commentStandard
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse<CommentStandard> add(@RequestBody CommentStandard commentStandard) {
        if(StringUtils.isEmpty(commentStandard.getContent())){
            return getObjectRestResponse("请填写评论内容");
        }
        if(StringUtils.isEmpty(commentStandard.getChannels())){
            return getObjectRestResponse("请输入绑定频道");
        }
        commentStandard.setCreateUid(Integer.valueOf(getCurrentUserId()));
        commentStandard.setCreateTime(new Date());
        commentStandard.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        return commentStandardServiceFeign.addCommentStandard(commentStandard);
    }

    /**
     * 获取标准评论
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<CommentStandardInfo> get(@PathVariable("id") Integer id) {
        if(id == null){
            return getObjectRestResponse("评论id不能为空");
        }
        ObjectRestResponse<CommentStandardInfo> commentStandard = commentStandardServiceFeign.getCommentStandard(id);
        CommentStandardInfo data = commentStandard.getData();
        data.setCreateTime(null);
        data.setCreateUid(null);
        String channels = data.getChannels();
        data.setChannelArray(channels.split(","));
        commentStandard.setData(data);
        return commentStandard;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<CommentStandard> update(@PathVariable("id") Integer id,@RequestBody CommentStandard commentStandard) {
        if(StringUtils.isEmpty(commentStandard.getContent())){
            return getObjectRestResponse("请填写评论内容");
        }
        if(StringUtils.isEmpty(commentStandard.getChannels())){
            return getObjectRestResponse("请输入绑定频道");
        }
        commentStandard.setId(id);
        return commentStandardServiceFeign.updateCommentStandard(id,commentStandard);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCommentStandard/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<CommentStandard> deleteCommentStandard(@PathVariable("id") Integer id) {
        if(id==null){
            return getObjectRestResponse("评论id不能为空");
        }
        CommentStandard commentStandard = new CommentStandard();
        commentStandard.setId(id);
        commentStandard.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
        return commentStandardServiceFeign.updateCommentStandard(id,commentStandard);
    }

    /**
     * 标准评论根据content
     * @param params
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public TableResultResponse<CommentStandardInfo> getPage(@RequestParam Map<String,Object> params) {
        if(params.get("page")==null||params.get("limit")==null){
            TableResultResponse<CommentStandardInfo> tableResultResponse=new TableResultResponse<>();
            tableResultResponse.setStatus(506);
            tableResultResponse.setMessage("分页信息不能空");
            return tableResultResponse;
        }
        return commentStandardServiceFeign.getPageByTitle(params);
    }



    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setStatus(506);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }
}
