package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.CorpUser;
import com.kanfa.news.app.entity.Demand;
import com.kanfa.news.app.entity.DemandAuthor;
import com.kanfa.news.app.entity.KpiCount;
import com.kanfa.news.app.mapper.CorpUserMapper;
import com.kanfa.news.app.mapper.DemandAuthorMapper;
import com.kanfa.news.app.mapper.DemandMapper;
import com.kanfa.news.app.mapper.KpiCountMapper;
import com.kanfa.news.app.vo.admin.kpicount.KpiCountContentInfo;
import com.kanfa.news.app.vo.admin.my.DemandInfo;
import com.kanfa.news.app.vo.admin.my.MyDemandPageInfo;
import com.kanfa.news.app.vo.admin.video.VideoDemandInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-15 11:52:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DemandBiz extends BaseBiz<DemandMapper,Demand> {
    @Autowired
    private  DemandMapper  demandMapper;
    @Autowired
    private DemandAuthorMapper demandAuthorMapper;
    @Autowired
    private CorpUserMapper corpUserMapper;
    @Autowired
    private KpiCountMapper kpiCountMapper;

    public TableResultResponse<VideoDemandInfo> getPage(Query query) {
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoDemandInfo> list = demandMapper.getPage();
        for (VideoDemandInfo videoDemandInfo:list) {
            videoDemandInfo.setStatusCh(NewsEnumsConsts.VideoLibraryOfStatus.getType(videoDemandInfo.getStatus()));
        }
        return new TableResultResponse<VideoDemandInfo>(result.getTotal(), list);
    }

    public TableResultResponse<VideoDemandInfo> searchPage(Query query, String title){
        Page<Map> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<VideoDemandInfo> searchlist = demandMapper.getSearchPage(title);
        for (VideoDemandInfo videoDemandInfo:searchlist) {
           videoDemandInfo.setStatusCh(NewsEnumsConsts.VideoLibraryOfStatus.getType(videoDemandInfo.getStatus()));
        }
        return new TableResultResponse<VideoDemandInfo>(result.getTotal(), searchlist);
    }


    public TableResultResponse<MyDemandPageInfo> getMyDemandPage(MyDemandPageInfo entity){
        Page<Map> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        entity.setStat(1);
        List<MyDemandPageInfo> myDemandPage = demandMapper.getMyDemandPage(entity);
        return new TableResultResponse<MyDemandPageInfo>(result.getTotal(), myDemandPage);
    }


    public void insertDemand(DemandInfo entity){
        entity.setUrl("Act-ss-mp4-hd/"+entity.getRunid()+"/"+entity.getName());
        demandMapper.insertDemand(entity);
        Integer demandId = entity.getId();
        //绑定作者
        if (entity.getCropUserIds()!=null){
            for (Integer uid:entity.getCropUserIds()) {
                DemandAuthor demandAuthor = new DemandAuthor();
                demandAuthor.setUid(uid);
                demandAuthor.setVideoId(demandId);
                demandAuthor.setCreateTime(new Date());
                demandAuthorMapper.insertSelective(demandAuthor);
            }
        }
        //绑定工作人员
        List<KpiCountContentInfo> userList = entity.getKpiCountList();
        if (userList != null && userList.size() > 0) {
            for (KpiCountContentInfo kpiCountInfo : userList) {
                if (kpiCountInfo.getWorkTypeList() != null && kpiCountInfo.getWorkTypeList().size() > 0) {
                    for (Integer workType : kpiCountInfo.getWorkTypeList()) {
                        KpiCount kpiCount = new KpiCount();
                        CorpUser corpUser = corpUserMapper.selectByPrimaryKey(kpiCountInfo.getUid());
                        kpiCount.setWorkType(workType);
                        kpiCount.setIsNumPass(1);
                        kpiCount.setArticleType(0);
                        kpiCount.setName(corpUser.getName());
                        kpiCount.setUid(corpUser.getId());
                        kpiCount.setType(4);
                        kpiCount.setTypeId(demandId);
                        kpiCount.setCreateTime(new Date());
                        kpiCount.setWeight(BigDecimal.valueOf(1));
                        kpiCount.setJobId(corpUser.getJobId());
                        kpiCount.setDepartmentId(corpUser.getLevel2Id());
                        kpiCountMapper.insertSelective(kpiCount);
                    }
                }
            }
        }

    }
}