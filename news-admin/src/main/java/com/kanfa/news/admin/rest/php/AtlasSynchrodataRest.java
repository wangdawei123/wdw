package com.kanfa.news.admin.rest.php;

import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import com.kanfa.news.data.client.feign.IAtlasSynDataServiceFeign;
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
@RequestMapping("/atlasSynchrodataRest")
public class AtlasSynchrodataRest extends BaseRPC{

    @Autowired
    private IAtlasSynDataServiceFeign atlasSynDataServiceFeign;
    @Autowired
    private IContentServiceFeign contentServiceFeign;

    /**
     * 添加文章
     * @param id
     * @return
     */
    @RequestMapping(value = "/addContentAtlas", method = RequestMethod.GET)
    public ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> addContentAtlas(@RequestParam("id") Integer id) {
        if (null==id){
            String msg="id不能为空";
            return getObjectRestResponse(msg);
        }
        com.kanfa.news.admin.vo.channel.ContentInfo entity=new com.kanfa.news.admin.vo.channel.ContentInfo();
        ObjectRestResponse<ContentInfo> contentInfo = atlasSynDataServiceFeign.getContentAtlas(id);
        ContentInfo data = contentInfo.getData();
        BeanUtils.copyProperties(data,entity);
        return contentServiceFeign.addContent(entity);
    }

    /**
     * 更新内容
     *
     * @return
     */
    @RequestMapping(value = "/updateContentAtlas", method = RequestMethod.POST)
    public ObjectRestResponse<com.kanfa.news.admin.vo.channel.ContentInfo> updateContentAtlas(@RequestParam("id") Integer id) {
        if (null == id) {
            String msg = "内容ID不能为空";
            return getObjectRestResponse(msg);
        }
        com.kanfa.news.admin.vo.channel.ContentInfo entity=new com.kanfa.news.admin.vo.channel.ContentInfo();
        ObjectRestResponse<ContentInfo> contentInfo = atlasSynDataServiceFeign.getContentAtlas(id);
        ContentInfo data = contentInfo.getData();
        BeanUtils.copyProperties(data,entity);
        entity.setCardType(NewsEnumsConsts.ChannelContentOfCardType.GroupImg.key());
        List<Integer> appChannelIdList = entity.getAppChannelIdList();
        appChannelIdList.addAll(entity.getPcChannelIdList());
        entity.setChannelIdList(appChannelIdList);
        //图集标识
        entity.setContentType(NewsEnumsConsts.ContentOfContentType.Atlas.getKey());
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setIp(ClientUtil.getClientIp(request));
        return contentServiceFeign.updateContentAtlas(entity);
    }


    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
