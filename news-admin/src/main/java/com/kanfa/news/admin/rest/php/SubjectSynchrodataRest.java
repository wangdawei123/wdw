package com.kanfa.news.admin.rest.php;

import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import com.kanfa.news.data.client.feign.IContentSynDataServiceFeign;
import com.kanfa.news.data.client.feign.ISubjectSynDataServiceFeign;
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
    private ISubjectSynDataServiceFeign subjectSynDataServiceFeign;
    @Autowired
    private IContentServiceFeign contentServiceFeign;

    /**
     * 添加文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/addContentSubject", method = RequestMethod.GET)
    public ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> addContentSubject(@RequestParam("id") Integer id) {
        if (null==id){
            String msg="id不能为空";
            return getObjectRestResponse(msg);
        }
        com.kanfa.news.admin.vo.channel.ContentInfo entity=new com.kanfa.news.admin.vo.channel.ContentInfo();
        ObjectRestResponse<ContentInfo> contentInfo = subjectSynDataServiceFeign.getContentSubject(id);
        ContentInfo data = contentInfo.getData();
        BeanUtils.copyProperties(data,entity);
        //初始化频道属性值
        List<Integer> appChannelIdList = entity.getAppChannelIdList();
        appChannelIdList.addAll(entity.getPcChannelIdList());
        entity.setChannelIdList(appChannelIdList);
        entity.setCardType(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key());//小图
        entity.setIp(ClientUtil.getClientIp(request));
        if(entity.getIsLegal()==null){
            entity.setIsLegal(0);
        }
        if(entity.getRecommendWeight()==null){
            entity.setRecommendWeight(60);
        }
        return contentServiceFeign.addContent(entity);
    }

    /**
     * 更新内容
     *
     * @return
     */
    @RequestMapping(value = "/updateContentSubject", method = RequestMethod.POST)
    public ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> updateContentSubject(@RequestParam("id") Integer id) {
        if (null==id){
            String msg="id不能为空";
            return getObjectRestResponse(msg);
        }
        com.kanfa.news.admin.vo.channel.ContentInfo entity=new com.kanfa.news.admin.vo.channel.ContentInfo();
        ObjectRestResponse<ContentInfo> contentInfo = subjectSynDataServiceFeign.getContentSubject(id);
        ContentInfo data = contentInfo.getData();
        BeanUtils.copyProperties(data,entity);
        entity.setCardType(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key());//小图
        List<Integer> appChannelIdList = entity.getAppChannelIdList();
        appChannelIdList.addAll(entity.getPcChannelIdList());
        entity.setChannelIdList(appChannelIdList);
        //专题标识
        entity.setIp(ClientUtil.getClientIp(request));
        return contentServiceFeign.updateContentSubject(entity);
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
