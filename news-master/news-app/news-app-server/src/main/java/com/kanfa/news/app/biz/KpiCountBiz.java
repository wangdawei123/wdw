package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.KpiCount;
import com.kanfa.news.app.mapper.KpiCountMapper;
import com.kanfa.news.app.mongoDB.biz.ViewContentService;
import com.kanfa.news.app.mongoDB.entity.ViewContent;
import com.kanfa.news.app.vo.admin.kpicount.KpiCountDetailInfo;
import com.kanfa.news.app.vo.admin.kpicount.KpiCountInfo;
import com.kanfa.news.app.vo.admin.kpicount.KpiTypeIdInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 
 *
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-14 11:46:04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KpiCountBiz extends BaseBiz<KpiCountMapper,KpiCount> {

    @Autowired
    private KpiCountMapper kpiCountMapper;

    @Resource
    private ViewContentService viewContentService;

    public TableResultResponse<KpiCountInfo> getPageList(Integer page, Integer limit , String startDate, String endDate, String pvEndDate, Integer topPv , Integer departmentId, Integer uid) {
        Page<Object> result = PageHelper.startPage(page,limit);
        List<KpiCountInfo> list = getList(startDate,endDate,pvEndDate,topPv,departmentId,uid);
        return new TableResultResponse<KpiCountInfo>(result.getTotal(),list);
    }

    public List<KpiCountInfo> getList( String startDate, String endDate,String pvEndDate,Integer topPv ,Integer departmentId,Integer uid) {

        List<KpiCountInfo> list = kpiCountMapper.listKpiCount(startDate,endDate,departmentId,uid);
        for (KpiCountInfo kpiCountInfo:list    ) {
            List<KpiTypeIdInfo> typeIdLsit = getKpiCoutTypeList(startDate,endDate,kpiCountInfo.getUid(),departmentId);
            Set cidArray = new HashSet();
            for(KpiTypeIdInfo kpiTypeIdInfo :typeIdLsit){
                cidArray.add(kpiTypeIdInfo.getTypeId());
            }

            //查询mongo 时间段从startDate 到pvEndDate rpv
            GroupByResults<ViewContent> mongoResult = viewContentService.listByDate("cid",cidArray,startDate,pvEndDate);
            List<Document>  mongoResultList = ( List<Document> ) mongoResult.getRawResults().get("retval");

            Document obj = null;
            List<Integer> appPvList = new ArrayList<Integer>();
            //遍历查询集合
            for (int i = 0,length = mongoResultList.size(); i < length; i++) {
                obj = (Document) mongoResultList.get(i);
                if(obj!=null){
                    int cid = Double.valueOf(obj.get("cid").toString()).intValue();
                    int appPv = Double.valueOf(obj.get("appPv").toString()).intValue();
                    for (KpiTypeIdInfo kpiTypeIdInfo :typeIdLsit) {
                        Integer typeId = kpiTypeIdInfo.getTypeId();
                        if(typeId == cid){
                            Integer weight = kpiTypeIdInfo.getWeight();
                            //计算权重pv
                            Integer countResult = appPv * weight;
                            //加入到List中
                            appPvList.add(countResult);

                        }
                    }
                }
            }

            //List降序排序
            Collections.sort(appPvList,Collections.reverseOrder());
            Integer resultPv = 0;
            Integer times = topPv>=appPvList.size()?appPvList.size():topPv;
            for(int i=0;i<times;i++){
                resultPv += appPvList.get(i).intValue();
            }
            kpiCountInfo.setEightPv(resultPv.toString());
        }
        return  list;
    }

    public List<KpiTypeIdInfo> getKpiCoutTypeList(String startDate,String endDate,Integer uid,Integer departmentId) {
        //获取typeId列表
        List<KpiTypeIdInfo> typeIdList = kpiCountMapper.listKpiTypeId(startDate,endDate,uid,departmentId);
        return typeIdList;
    }


    public TableResultResponse<KpiCountDetailInfo> getDetailPageList(Integer page, Integer limit , Integer departmentId, Integer uid, String startDate, String endDate, String pvEndDate, String articleTypeName, String title) {


        Page<Object> result = PageHelper.startPage(page,limit);
        List<KpiCountDetailInfo> list = getDetailList(departmentId,uid,startDate,endDate,pvEndDate,articleTypeName,title);
        return new TableResultResponse<KpiCountDetailInfo>(result.getTotal(), list);
    }

    public List<KpiCountDetailInfo> getDetailList(Integer departmentId,Integer uid, String startDate, String endDate, String pvEndDate,String articleTypeName, String title) {
        List<KpiCountDetailInfo> list = kpiCountMapper.listKpiCountDetail(startDate,endDate,uid,articleTypeName,title);

        List<KpiTypeIdInfo> typeIdLsit = getKpiCoutTypeList(startDate,endDate,uid,departmentId);
        Set cidArray = new HashSet();
        for(KpiTypeIdInfo kpiTypeIdInfo :typeIdLsit){
            cidArray.add(kpiTypeIdInfo.getTypeId());
        }

        //查询mongo 时间段从startDate 到pvEndDate rpv
        GroupByResults<ViewContent> mongoResult = viewContentService.listByDate("cid",cidArray,startDate,pvEndDate);
        List<Document> mongoResultList = (List<Document>) mongoResult.getRawResults().get("retval");
        Document obj = null;
        for (KpiCountDetailInfo kpiCountDetailInfo:list    ) {
            //遍历查询集合
            for (int i = 0,length = mongoResultList.size(); i < length; i++) {
                obj = (Document) mongoResultList.get(i);
                if(obj!=null){
                    int cid = Double.valueOf(obj.get("cid").toString()).intValue();
                    int appPv = Double.valueOf(obj.get("appPv").toString()).intValue();
                    int allPv = Double.valueOf(obj.get("allPv").toString()).intValue();
                    Integer typeId = kpiCountDetailInfo.getTypeId();
                    if(cid == typeId){
                        kpiCountDetailInfo.setAppPv((kpiCountDetailInfo.getAppPv()==null?0:kpiCountDetailInfo.getAppPv().intValue())+appPv);
                        kpiCountDetailInfo.setAllPv((kpiCountDetailInfo.getAllPv()==null?0:kpiCountDetailInfo.getAllPv().intValue())+allPv);
                        kpiCountDetailInfo.setUidAppPv(kpiCountDetailInfo.getAppPv()==null?0:kpiCountDetailInfo.getAppPv().intValue() * (kpiCountDetailInfo.getWeight()==null?0:kpiCountDetailInfo.getWeight().intValue()));
                    }
                }
            }
        }
        return list;
    }

}