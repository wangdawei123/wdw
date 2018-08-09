package com.kanfa.news.data.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.data.common.vo.kpiCount.KpiCountContentInfo;
import com.kanfa.news.data.common.vo.live.LiveAddInfo;
import com.kanfa.news.data.entity.XmChannelContent;
import com.kanfa.news.data.entity.XmKpiCount;
import com.kanfa.news.data.entity.XmLive;
import com.kanfa.news.data.mapper.XmChannelContentMapper;
import com.kanfa.news.data.mapper.XmKpiCountMapper;
import com.kanfa.news.data.mapper.XmLiveMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 直播表
 *
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-08-07 11:13:56
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XmLiveBiz extends BaseBiz<XmLiveMapper,XmLive> {

    @Autowired
    private XmChannelContentMapper xmChannelContentMapper;
    @Autowired
    private XmKpiCountMapper xmKpiCountMapper;
    @Autowired
    private XmLiveMapper xmLiveMapper;

    /**
     * 得到直播详情
     * @param id
     * @return
     */
    public LiveAddInfo getOneLive(Integer id) {
        LiveAddInfo liveAddInfo = new LiveAddInfo();
        //得到live表中的信息
        XmLive xmLive = xmLiveMapper.selectLiveById(id);
        liveAddInfo.setLiveStatus(xmLive.getStat());
        liveAddInfo.setIsTop(xmLive.getTop());
        liveAddInfo.setIsPublish(xmLive.getPub());
        liveAddInfo.setIsDelete(xmLive.getDeleted());
        liveAddInfo.setIsLock(xmLive.getLocked());
        liveAddInfo.setCheckState(xmLive.getCheck());

        BeanUtils.copyProperties(xmLive, liveAddInfo);
        //得到绑定的app频道信息
        List<Integer> appChannels = xmChannelContentMapper.getAppChannels(id);
        liveAddInfo.setAppChannelList(appChannels);
        //获得推荐权重的信息
        List<XmChannelContent> select = xmChannelContentMapper.selectChannelContentByCid(id);
        for (XmChannelContent c : select) {
            Integer recommendWeight = c.getRecommendWeight();
            liveAddInfo.setRecommendWeight(recommendWeight);
            break;
        }
        //获得工作人员的信息
        List<KpiCountContentInfo> kpiCountContentInfos = new ArrayList<>();
        List<XmKpiCount> kpiCounts = xmKpiCountMapper.selectXmKpiCounts(9,id);
        for (XmKpiCount k : kpiCounts) {
            KpiCountContentInfo kpiCountContentInfo = new KpiCountContentInfo();
            kpiCountContentInfo.setUid(k.getUid());
            Integer uid = k.getUid();
            Integer typeId = k.getTypeId();
            Integer type = k.getType();
            List<Integer> integers = xmLiveMapper.getworkTypeList(uid, typeId, type);
            kpiCountContentInfo.setWorkTypeList(integers);
            kpiCountContentInfos.add(kpiCountContentInfo);
            liveAddInfo.setKpiCountList(kpiCountContentInfos);
        }
        //剔除重复元素 KpiCountContentInfo
        if(liveAddInfo.getKpiCountList()!=null){
            HashSet hashSet = new HashSet(liveAddInfo.getKpiCountList());
            liveAddInfo.getKpiCountList().clear();
            liveAddInfo.getKpiCountList().addAll(hashSet);
            return  liveAddInfo;
        }
        return liveAddInfo;
    }


 }