package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.vo.channel.ContentInfo;
import com.kanfa.news.admin.vo.content.ContentOfSearchBindInfo;
import com.kanfa.news.admin.vo.content.SubjectInfo;
import com.kanfa.news.admin.vo.kpi.KpiCountContentInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import com.kanfa.news.common.util.ClientUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 专题
 * @author Jiqc
 * @date 2018/3/18 17:59
 */
@RestController
@RequestMapping("/subject")
public class SubjectRest extends BaseRPC{

    @Autowired
    private IContentServiceFeign contentServiceFeign;

    /**
     * 添加专题
     * @param subjectInfo
     * @return
     */
    @RequestMapping(value = "/addContentSubject", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> addContentSubject(@RequestBody SubjectInfo subjectInfo) {
        ContentInfo entity=new ContentInfo();
        BeanUtils.copyProperties(subjectInfo,entity);
        if (StringUtils.isEmpty(entity.getTitle())){
            String msg="专题标题不能为空";
            return getObjectRestResponse(msg);
        }else if(entity.getTagNameList()==null && entity.getTagNameList().size()>0){
            String msg="专题标签不能为空";
            return getObjectRestResponse(msg);
        }else if (NewsEnumsConsts.ChannelOfCategory.INFO_APP.key().equals(entity.getCategory())&&(entity.getAppChannelIdList()==null || entity.getAppChannelIdList().size()<=0)) {
            String msg="专题频道不能为空";
            return getObjectRestResponse(msg);
        } else if(StringUtils.isEmpty(entity.getIntroduction())){
            String msg="专题导语不能为空";
            return getObjectRestResponse(msg);
        }else if(StringUtils.isEmpty(entity.getImage())){
            String msg="专题缩略图不能为空";
            return getObjectRestResponse(msg);
        }else if(StringUtils.isEmpty(entity.getCoverImg())){
            String msg="专题封面图不能为空";
            return getObjectRestResponse(msg);
        }else if(entity.getChildCatalogList()==null||entity.getChildCatalogList().size()<=0){//子目录未判断
            return getObjectRestResponse("专题子目录不能空");
        }
        //是否是保存草稿 显示状态，0审核列不显示，1审核列表显示
        if(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey().equals(entity.getIsCheck())){
            //是否为审核 0未审核
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.OTHER.key());
        }else{
            entity.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey());
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        }
        List<KpiCountContentInfo> kpiCountContentInfoList = subjectInfo.getKpiCountContentInfoList();
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
        if(entity.getTitle().length()>60){
            return getObjectRestResponse("标题限60个字符");
        }
        //初始化频道属性值
        List<Integer> appChannelIdList = entity.getAppChannelIdList();
        appChannelIdList.addAll(entity.getPcChannelIdList());
        entity.setChannelIdList(appChannelIdList);
        entity.setCardType(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key());//小图
        //entity.setCategory(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key());
        entity.setCreateTime(new Date());
        entity.setContentType(NewsEnumsConsts.ContentOfContentType.Project.getKey());
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
        return contentServiceFeign.addContentSubject(entity);
    }

    /**
     * 更新专题内容
     * @param subjectInfo
     * @return
     */
    @RequestMapping(value = "/updateContentSubject", method = RequestMethod.POST)
    public ObjectRestResponse<ContentInfo> updateContentSubject(@RequestBody SubjectInfo subjectInfo) {
        ContentInfo entity=new ContentInfo();
        BeanUtils.copyProperties(subjectInfo,entity);
        if(entity.getId()==null){
            String msg="内容ID不能为空";
            return getObjectRestResponse(msg);
        }
        List<KpiCountContentInfo> kpiCountContentInfoList = subjectInfo.getKpiCountContentInfoList();
        if(kpiCountContentInfoList!=null && kpiCountContentInfoList.size()>0){
            int size = kpiCountContentInfoList.size();
            List<Integer> ids=new ArrayList<>(size);
            kpiCountContentInfoList.forEach(e->ids.add(e.getId()));
            Set<Integer> set = new HashSet<>(ids);
            if(size != set.size()){
                return getObjectRestResponse("来源记者不能重复添加");
            }
        }else{
            return getObjectRestResponse("来源记者不能为空");
        }
        if(entity.getTitle().length()>60){
            return getObjectRestResponse("标题限60个字符");
        }
        //是否是保存草稿 显示状态，0审核列不显示，1审核列表显示
        if(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey().equals(entity.getIsCheck())){
            //是否为审核 0未审核
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.OTHER.key());
        }else{
            entity.setIsCheck(NewsEnumsConsts.ContentOfIsCheck.Noshow.getKey());
            entity.setCheckStatus(NewsEnumsConsts.ContentOfCheckStatus.WAITCHECK.key());
        }
        entity.setCardType(NewsEnumsConsts.ChannelContentOfCardType.SmallImg.key());//小图
        List<Integer> appChannelIdList = entity.getAppChannelIdList();
        appChannelIdList.addAll(entity.getPcChannelIdList());
        entity.setChannelIdList(appChannelIdList);
        //专题标识
        entity.setContentType(NewsEnumsConsts.ContentOfContentType.Project.getKey());
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        entity.setIp(ClientUtil.getClientIp(request));
        return contentServiceFeign.updateContentSubject(entity);
    }

    /**
     * 仅保存子目录 源内容id
     * @param subjectInfo
     * @return
     */
    @RequestMapping(value = "/saveSpecialCatalog", method = RequestMethod.POST)
    public ObjectRestResponse<Integer> saveSpecialCatalog(@RequestBody SubjectInfo subjectInfo) {
        if(subjectInfo.getChildCatalogList()==null && subjectInfo.getChildCatalogList().size()>0){
            String msg="子目录不能为空";
            return getObjectRestResponse(msg);
        }
        subjectInfo.setCreateUid(Integer.valueOf(getCurrentUserId()));
        return contentServiceFeign.saveSpecialCatalog(subjectInfo);
    }

    /**
     * 解除子目录绑定
     * @param cid
     * @param targetId
     * @return
     */
    @RequestMapping(value = "/deleteBind", method = RequestMethod.GET)
    public ObjectRestResponse<Integer> deleteBind(@RequestParam("cid") Integer cid,@RequestParam("targetId") Integer targetId) {
        if(cid==null || targetId==null){
            String msg="源内容id与绑定内容id不能为空";
            return getObjectRestResponse(msg);
        }
        return contentServiceFeign.deleteBind(cid,targetId);
    }

    /**
     * 删除子目录
     * @param cid
     * @param cataLogId
     * @return
     */
    @RequestMapping(value = "/deleteSpecialCatalog", method = RequestMethod.GET)
    public ObjectRestResponse<Integer> deleteSpecialCatalog(@RequestParam("cid") Integer cid,@RequestParam("cataLogId") Integer cataLogId) {
        if(cid==null || cataLogId==null){
            String msg="源内容id与子目录id不能为空";
            return getObjectRestResponse(msg);
        }
        return contentServiceFeign.deleteSpecialCatalog(cid,cataLogId);
    }

    /**
     * 查询内容专题
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ObjectRestResponse<ContentInfo> getContentSubject(@PathVariable("id") Integer id) {
        if(id == null){
            String msg="内容未找到";
            return getObjectRestResponse(msg);
        }
        return contentServiceFeign.getContentSubject(id);
    }

    /**
     * 绑定内容查询
     * @param entity
     * @return
     */
    @RequestMapping(value = "/getBindContent", method = RequestMethod.POST)
    public TableResultResponse<ContentOfSearchBindInfo> getBindContent(@RequestBody ContentInfo entity) {
        //排除已经绑定的
        TableResultResponse<ContentOfSearchBindInfo> list = contentServiceFeign.getContentPageForBind(entity);
        return list;
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
