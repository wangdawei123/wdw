package com.kanfa.news.admin.rest.php;

import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import com.kanfa.news.data.client.feign.IContentSynDataServiceFeign;
import com.kanfa.news.data.common.vo.channel.ContentInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * php数据同步
 * @author Jiqc
 * @date 2018/3/18 17:59
 */
@RestController
@RequestMapping("/subjectSynchrodataRest")
public class SubjectSynchrodataRest extends BaseRPC{

    @Autowired
    private IContentSynDataServiceFeign contentSynDataServiceFeign;
    @Autowired
    private IContentServiceFeign contentServiceFeign;

    /**
     * 添加文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/addContent", method = RequestMethod.GET)
    public ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> addContent(@RequestParam("id") Integer id) {
        if (null==id){
            String msg="id不能为空";
            return getObjectRestResponse(msg);
        }
        com.kanfa.news.admin.vo.channel.ContentInfo entity=new com.kanfa.news.admin.vo.channel.ContentInfo();
        ObjectRestResponse<ContentInfo> contentInfo = contentSynDataServiceFeign.getContentInfo(id);
        ContentInfo data = contentInfo.getData();
        BeanUtils.copyProperties(data,entity);
        return contentServiceFeign.addContent(entity);
    }

    /**
     * 更新内容
     *
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> update(@RequestBody com.kanfa.news.admin.vo.channel.ContentInfo contentInfo , HttpServletRequest request) {
        //查询咨询分页，条件：类型，状态，开始结束日期，文章标题，id，创建人，频道id
        if (contentInfo.getId() == null) {
            String msg = "内容ID不能为空";
            return getObjectRestResponse(msg);
        }
        if(contentInfo.getVideoDemand()!=null){
            contentInfo.setVideo(contentInfo.getVideoDemand().getUrl());
            contentInfo.setDid(contentInfo.getVideoDemand().getId());
        }
        List<Integer> appChannelIdList = contentInfo.getAppChannelIdList();
        appChannelIdList.addAll(contentInfo.getPcChannelIdList());
        contentInfo.setChannelIdList(appChannelIdList);
        String clientIp = ClientUtil.getClientIp(request);
        contentInfo.setIp(clientIp);
        //标识文章更新
        contentInfo.setContentType(NewsEnumsConsts.ContentOfContentType.Article.getKey());
        contentInfo.setUpdateTime(new Date());
        contentInfo.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> update = contentServiceFeign.update(contentInfo);
        return update;
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
