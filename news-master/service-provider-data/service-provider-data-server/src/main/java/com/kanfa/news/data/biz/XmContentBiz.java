package com.kanfa.news.data.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.data.common.vo.channel.ChannelInfo;
import com.kanfa.news.data.common.vo.channel.ContentInfo;
import com.kanfa.news.data.common.vo.kpiCount.KpiCountContentInfo;
import com.kanfa.news.data.common.vo.video.VideoContentInfo;
import com.kanfa.news.data.common.vo.video.VideoDemand;
import com.kanfa.news.data.common.vo.vr.VRContentAddInfo;
import com.kanfa.news.data.entity.*;
import com.kanfa.news.data.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.kanfa.news.common.constant.news.NewsEnumsConsts.ContentOfContentType.VR;

/**
 * 内容表（含专题，文章，图集，视频类型）
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-06 16:41:42
 */
@Service
public class XmContentBiz extends BaseBiz<XmContentMapper,XmContent> {

    @Autowired
    private XmChannelContentMapper xmChannelContentMapper;
    @Autowired
    private XmContentVideoMapper xmContentVideoMapper;
    @Autowired
    private XmVideoTagMapper xmVideoTagMapper;
    @Autowired
    private XmVideoTagBindMapper xmVideoTagBindMapper;
    @Autowired
    private XmContentMapper xmContentMapperl;
    @Autowired
    private XmDemandMapper xmDemandMapper;
    @Autowired
    private XmContentStandardMapper xmContentStandardMapper;
    @Autowired
    private XmKpiCountMapper xmKpiCountMapper;
    @Autowired
    private XmContentVrMapper xmContentVrMapper;
    @Autowired
    private XmContentArticleMapper xmContentArticleMapper;
    @Autowired
    private XmActivityContentBindMapper xmActivityContentBindMapper;
    @Autowired
    private XmContentBurstMapper xmContentBurstMapper;

    /**
     * 得到视频详尽信息
     * @param id
     * @return
     */
    public VideoContentInfo getVideoDetail(Integer id) {
        VideoContentInfo videoContentInfo = new VideoContentInfo();
        //得到content表的信息
        XmContent content = xmContentMapperl.selectByPrimaryKey(id);
        videoContentInfo.setCategory(content.getCate());
        videoContentInfo.setContentType(content.getType());
        videoContentInfo.setIntroduction(content.getDesc());
        videoContentInfo.setViewCount(content.getViews());
        videoContentInfo.setContentState(content.getStat());
        videoContentInfo.setCheckStatus(content.getCheck());
        BeanUtils.copyProperties(content, videoContentInfo);
        //得到所绑定的频道信息
        List<Integer> appChannels = xmChannelContentMapper.selectAppChannels(id);
        List<Integer> pcChannels = xmChannelContentMapper.selectPcChannels(id);
        videoContentInfo.setAppChannelIdList(appChannels);
        videoContentInfo.setPcChannelIdList(pcChannels);
        //获得来源作者的信息
        XmContentVideo contentVideo = xmContentVideoMapper.selectByPrimaryKey(id);
        if (contentVideo != null) {
            Integer sourceId = contentVideo.getSourceId();
            videoContentInfo.setSourceId(sourceId);
        }
        //获得推荐权重的信息
        XmChannelContent channelContent = new XmChannelContent();
        channelContent.setCid(id);
        List<XmChannelContent> select = xmChannelContentMapper.select(channelContent);
        for (XmChannelContent c : select) {
            Integer recommendWeight = c.getRecommendWeight();
            videoContentInfo.setRecommendWeight(recommendWeight);
            break;
        }
        //获得标签的信息
        List<XmVideoTagBind> select1 = xmVideoTagBindMapper.seletByVideoId(id);
        List<String> tagNames = new ArrayList<>();
        for (XmVideoTagBind v : select1) {
            Integer videoTagBindId = v.getId();
            XmVideoTagBind videoTagBind2 = xmVideoTagBindMapper.selectByPrimaryKey(videoTagBindId);
            Integer videoTagId = videoTagBind2.getVideoTagId();
            XmVideoTag videoTag = xmVideoTagMapper.selectByPrimaryKey(videoTagId);
            String name = videoTag.getName();
            tagNames.add(name);
        }
        videoContentInfo.setTagNameList(tagNames);
        //获得视频地址和时长的信息
        //获得视频时长
        String duration = contentVideo.getDuration();
        String source = contentVideo.getSrc();
        videoContentInfo.setVideoDuration(duration);
        videoContentInfo.setSource(source);
        //获得视频
        Integer did = contentVideo.getDid();
        VideoDemand videoDemand = new VideoDemand();
        videoDemand.setId(did);
        XmDemand demand = xmDemandMapper.selectByPrimaryKey(did);
        String title = demand.getTitle();
        videoDemand.setTitle(title);
        String name = demand.getName();
        videoDemand.setUrl("http://devvideotest.oss-cn-beijing.aliyuncs.com/outactmvp/" + name);
        videoContentInfo.setVideoDemand(videoDemand);
        //获取绑定的评论
        XmContentStandard contentStandard = new XmContentStandard();
        contentStandard.setCid(id);
        List<XmContentStandard> select2 = xmContentStandardMapper.select(contentStandard);
        ArrayList<String> commentContentList = new ArrayList<>();
        for (XmContentStandard contentStandard1 : select2) {
            String content1 = contentStandard1.getContent();
            commentContentList.add(content1);
        }
        videoContentInfo.setCommentContentList(commentContentList);
        return videoContentInfo;
    }

    /**
     * 得到Vr详尽信息
     * @param id
     * @return
     */
    public VRContentAddInfo getVrDetail(Integer id) {
        VRContentAddInfo vrContentAddInfo = new VRContentAddInfo();

        //得到content表中的信息
        XmContent content = xmContentMapperl.selectByPrimaryKey(id);
        vrContentAddInfo.setCategory(content.getCate());
        vrContentAddInfo.setContentType(content.getType());
        vrContentAddInfo.setIntroduction(content.getDesc());
        vrContentAddInfo.setContentState(content.getStat());
        vrContentAddInfo.setCheckStatus(content.getCheck());
        BeanUtils.copyProperties(content, vrContentAddInfo);
        //得到绑定的vr频道
        List<Integer> integers = xmChannelContentMapper.selectVrChannels(id);
        vrContentAddInfo.setVrChannelList(integers);
        //得到绑定的app频道
        List<Integer> integers1 = xmChannelContentMapper.selectAppChannelsForVr(id);
        vrContentAddInfo.setAppChannelList(integers1);
        //得到绑定的Vr作者信息
        List<KpiCountContentInfo> kpiCountContentInfos = new ArrayList<>();
        XmKpiCount kpiCount = new XmKpiCount();
        kpiCount.setType(VR.getKey());
        kpiCount.setTypeId(id);
        List<XmKpiCount> kpiCounts = xmKpiCountMapper.select(kpiCount);
        for (XmKpiCount k : kpiCounts) {
            KpiCountContentInfo kpiCountContentInfo = new KpiCountContentInfo();
            kpiCountContentInfo.setUid(k.getUid());
            String remarks = k.getRemarks();
            kpiCountContentInfo.setRemarks(remarks);
            BigDecimal weight = k.getWeight();
            kpiCountContentInfo.setWeight(weight);
            kpiCountContentInfos.add(kpiCountContentInfo);
            vrContentAddInfo.setKpiCountList(kpiCountContentInfos);
        }
        //获得视频地址和时间
        VideoDemand videoDemand = new VideoDemand();
        XmContentVr contentVr = new XmContentVr();
        contentVr.setCid(id);
        XmContentVr contentVr1 = xmContentVrMapper.selectOne(contentVr);
        if (contentVr1 != null) {
            Integer did = contentVr1.getDid();
            videoDemand.setId(did);
            XmDemand demand = xmDemandMapper.selectByPrimaryKey(contentVr1.getDid());
            String title = demand.getTitle();
            videoDemand.setTitle(title);
            String name = demand.getName();
            videoDemand.setUrl("http://devvideotest.oss-cn-beijing.aliyuncs.com/outactmvp/" + name);
            vrContentAddInfo.setVideoDuration(contentVr1.getDuration());
        } else {
            videoDemand.setId(null);
            videoDemand.setUrl("");
            videoDemand.setTitle("");
            vrContentAddInfo.setVideoDuration("");
        }
        vrContentAddInfo.setVideoDemand(videoDemand);
        //剔除重复元素
        if (vrContentAddInfo.getKpiCountList() != null) {
            HashSet hashSet = new HashSet(vrContentAddInfo.getKpiCountList());
            vrContentAddInfo.getKpiCountList().clear();
            vrContentAddInfo.getKpiCountList().addAll(hashSet);
        }
        return vrContentAddInfo;
    }


    /**
     * 查询内容
     * @param id
     * @return
     */
    public ContentInfo getContentInfo(int id) {
        XmContent xmContent = this.mapper.getContentInfo(id);
        if(null==xmContent || null==xmContent.getId()){
            Assert.isNull("XmContent","内容不存在！");
        }
        ContentInfo contentInfo = new ContentInfo();
        BeanUtils.copyProperties(xmContent,contentInfo);
        contentInfo.setCreateUid(xmContent.getCreateUid().intValue());
        contentInfo.setCategory(xmContent.getCate());
        contentInfo.setCheckStatus(xmContent.getCheck());
        contentInfo.setContentState(xmContent.getStat());
        contentInfo.setContentType(xmContent.getType());
        contentInfo.setCoverImg(xmContent.getImg());
        contentInfo.setIntroduction(xmContent.getDesc());
        //添加频道绑定
        XmChannelContent xmChannelContent = new XmChannelContent();
        xmChannelContent.setCid(id);
        xmChannelContent.setFromType(NewsEnumsConsts.ChannelContentOfFromType.Content.key());
        List<ChannelInfo> channelIdList = xmChannelContentMapper.getChannelContentChannelId(xmChannelContent);
        List<Integer> appChannelList = new ArrayList<>(8);
        List<Integer> pcChannelList = new ArrayList<>(8);
        for (ChannelInfo channelInfo : channelIdList) {
            contentInfo.setRecommendWeight(channelInfo.getRecommendWeight());
            contentInfo.setCardType(channelInfo.getCardType());
            if(NewsEnumsConsts.ChannelOfCategory.INFO_APP.key().equals(channelInfo.getCategory())){
                appChannelList.add(channelInfo.getId());
            }else if (NewsEnumsConsts.ChannelOfCategory.INFO_PC.key().equals(channelInfo.getCategory())){
                pcChannelList.add(channelInfo.getId());
            }
        }
        contentInfo.setAppChannelIdList(appChannelList);
        contentInfo.setPcChannelIdList(pcChannelList);
        //绑定政法先锋
        List<Integer> activityIdList = xmActivityContentBindMapper.getActityIdListByCid(id);
        if(activityIdList!=null && activityIdList.size()>0){
            contentInfo.setChildChannelIdList(activityIdList);
        }
        //添加标签绑定
        XmVideoTagBind xmVideoTagBind = new XmVideoTagBind();
        xmVideoTagBind.setVideoId(id);
        List<XmVideoTagBind> videoTagBindList = xmVideoTagBindMapper.select(xmVideoTagBind);
        if(videoTagBindList!=null && videoTagBindList.size()>0){
            List<String> tagList = new ArrayList<>(8);
            for (XmVideoTagBind videoTagBind : videoTagBindList) {
                XmVideoTag xmVideoTag = xmVideoTagMapper.selectByPrimaryKey(videoTagBind.getVideoTagId());
                tagList.add(xmVideoTag.getName());
            }
            contentInfo.setTagNameList(tagList);
        }
        //添加记者绑定
        XmKpiCount xmKpiCount = new XmKpiCount();
        xmKpiCount.setTypeId(id);
        List<XmKpiCount> xmKpiCountList = xmKpiCountMapper.select(xmKpiCount);
        if(videoTagBindList!=null && videoTagBindList.size()>0){
            List<KpiCountContentInfo> userList = new ArrayList<>(8);
            for (XmKpiCount kpiCount : xmKpiCountList) {
                KpiCountContentInfo kpiCountContentInfo = new KpiCountContentInfo();
                kpiCountContentInfo.setUid(kpiCount.getUid());
                kpiCountContentInfo.setWorkType(kpiCount.getWorkType());
            }
        }
        //文章详情
        XmContentArticle xmContentArticle = xmContentArticleMapper.selectByPrimaryKey(id);
        if(null!=xmContentArticle){
            contentInfo.setContentDetail(xmContentArticle.getContent());
            contentInfo.setDid(xmContentArticle.getDid());
            contentInfo.setSource(xmContentArticle.getSrc());
            contentInfo.setVideoImage(xmContentArticle.getVideoImg());
        }
        //绑定评论
        XmContentStandard xmContentStandard = new XmContentStandard();
        xmContentStandard.setCid(id);
        List<XmContentStandard> xmContentStandardList = xmContentStandardMapper.select(xmContentStandard);
        //添加内容地理位置
        //爆料绑定
        XmContentBurst xmContentBurst=new XmContentBurst();
        xmContentBurst.setCid(id);
        List<XmContentBurst> xmContentBurstList = xmContentBurstMapper.select(xmContentBurst);
        if(xmContentBurstList!=null && xmContentBurstList.size()>0){
            contentInfo.setBurstId(xmContentBurstList.get(0).getBurstId());
        }
        return contentInfo;
    }
}