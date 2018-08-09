package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ChannelContent;
import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.vo.channel.ContentInfo;
import com.kanfa.news.admin.vo.content.AtlasInfo;
import com.kanfa.news.admin.vo.kpi.KpiCountContentInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 图集
 * @author Jiqc
 * @date 2018/3/18 17:59
 */
@RestController
@RequestMapping("/atlas")
public class AtlasRest extends BaseRPC{

    @Autowired
    private IContentServiceFeign contentServiceFeign;

    /**
     * 添加图集
     * @param atlasInfo
     * @return
     */
    @RequestMapping(value = "/addContentAtlas", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> addContentAtlas(@RequestBody AtlasInfo atlasInfo) {
        ContentInfo entity=new ContentInfo();
        BeanUtils.copyProperties(atlasInfo,entity);
        if (StringUtils.isEmpty(entity.getTitle())){
            String msg="图集标题不能为空";
            return getObjectRestResponse(msg);
        }else if(entity.getTagNameList()==null || entity.getTagNameList().size()<=0 || entity.getTagNameList().size()>3){
            String msg="图集标签不能为空且不多于三个";
            return getObjectRestResponse(msg);
        }else if(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key().equals(entity.getCategory())&&(entity.getAppChannelIdList()==null || entity.getAppChannelIdList().size()<=0)){
            String msg="图集频道不能为空";
            return getObjectRestResponse(msg);
        }else if(entity.getSourceType()==null){
            String msg="图集来源类型不能为空";
            return getObjectRestResponse(msg);
        }else if (atlasInfo.getImageInfoList() == null || atlasInfo.getImageInfoList().size()<3){//图片集判断
            return getObjectRestResponse("图集的图片不能为空或少于三张");
        }
        if(entity.getTitle().length()>60){
            return getObjectRestResponse("标题限60个字符");
        }
        if (entity.getDescType().equals(NewsEnumsConsts.ContentImageOfDescType.Yes.key())){
            if(StringUtils.isEmpty(entity.getIntroduction())){
                return getObjectRestResponse("请填写导语");
            }
        }else {
            List list=new ArrayList(atlasInfo.getImageInfoList().size());
            atlasInfo.getImageInfoList().forEach(e->list.add(e.getDesc().trim()));
            if(list.contains(null) || list.contains("")){
                return getObjectRestResponse("图集的图片描述不能为空");
            }
        }
        //是否是保存草稿 显示状态，0审核列不显示，1审核列表显示
        if(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey().equals(entity.getIsCheck())){
            //是否为审核 0未审核
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.OTHER.key());
        }else{
            entity.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey());
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        }
        if(NewsEnumsConsts.ContentOfSourceType.Original.getKey().equals(atlasInfo.getSourceType())){
            List<KpiCountContentInfo> kpiCountContentInfoList = atlasInfo.getKpiCountContentInfoList();
            if(kpiCountContentInfoList!=null && kpiCountContentInfoList.size()>0){
                int size = kpiCountContentInfoList.size();
                List<Integer> ids=new ArrayList<>(size);
                kpiCountContentInfoList.forEach(e->ids.add(e.getUid()));
                Set<Integer> set = new HashSet<>(ids);
                if(size != set.size()){
                    return getObjectRestResponse("来源记者不能重复添加");
                }
            }else{
                return getObjectRestResponse("来源记者不能为空");
            }
        }
        //初始化频道属性值
        List<Integer> appChannelIdList = entity.getAppChannelIdList();
        appChannelIdList.addAll(entity.getPcChannelIdList());
        entity.setChannelIdList(appChannelIdList);
        entity.setCardType(NewsEnumsConsts.ChannelContentOfCardType.GroupImg.key());
        entity.setCreateTime(new Date());
        //entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        entity.setContentType(NewsEnumsConsts.ContentOfContentType.Atlas.getKey());
        entity.setContentState(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
        entity.setIp(ClientUtil.getClientIp(request));
        if(entity.getCreateUid()==null){
            entity.setCreateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        }
        if(entity.getIsLegal()==null){
            entity.setIsLegal(0);
        }
        if(entity.getRecommendWeight()==null){
            entity.setRecommendWeight(60);
        }
        return contentServiceFeign.addContentAtlas(entity);
    }

    /**
     * 更新图集内容
     * @param atlasInfo
     * @return
     */
    @RequestMapping(value = "/updateContentAtlas", method = RequestMethod.PUT)
    public ObjectRestResponse<ContentInfo> updateContentAtlas(@RequestBody AtlasInfo atlasInfo) {
        ContentInfo entity=new ContentInfo();
        BeanUtils.copyProperties(atlasInfo,entity);
        if(entity.getId()==null){
            String msg="内容ID不能为空";
            return getObjectRestResponse(msg);
        }
        //是否是保存草稿 显示状态，0审核列不显示，1审核列表显示
        if(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey().equals(entity.getIsCheck())){
            //是否为审核 0未审核
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.OTHER.key());
        }else{
            entity.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey());
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        }
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

    /**
     * 查询内容专题
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ContentInfo> getContentAtlas(@PathVariable("id") Integer id) {
        if(id == null){
            String msg="内容未找到";
            return getObjectRestResponse(msg);
        }
        return contentServiceFeign.getContentAtlas(id);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
