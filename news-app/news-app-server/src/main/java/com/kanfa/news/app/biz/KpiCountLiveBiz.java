package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.entity.KpiCount;
import com.kanfa.news.app.mapper.KpiCountLiveMapper;
import com.kanfa.news.app.mapper.KpiUserInfoMapper;
import com.kanfa.news.app.mongoDB.biz.ViewContentService;
import com.kanfa.news.app.mongoDB.entity.ViewContent;
import com.kanfa.news.app.vo.admin.kpicount.*;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 *
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-17 11:46:04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KpiCountLiveBiz extends BaseBiz<KpiCountLiveMapper,KpiCount> {

    @Autowired
    private KpiCountLiveMapper kpiCountLiveMapper;

    @Autowired
    private KpiUserInfoMapper kpiUserInfoMapper;
    @Resource
    private ViewContentService viewContentService;

    public TableResultResponse<KpiCountLiveInfo> getPageList(Integer page, Integer limit , String startDate, String endDate, Integer departmentId, Integer uid) {
        Page<Object> result = PageHelper.startPage(page,limit);
        List<KpiCountLiveInfo> list =  getList(startDate,endDate,departmentId,uid);
        return new TableResultResponse<KpiCountLiveInfo>(result.getTotal(),list);
    }

    public List<KpiCountLiveInfo> getList(String startDate, String endDate, Integer departmentId, Integer uid) {
        List<KpiCountLiveInfo> list = kpiCountLiveMapper.listKpiCountLive(startDate,endDate,departmentId,uid);
        return list;
    }


    public List<KpiUserInfo> getUserList(Integer departmentId){
        return kpiUserInfoMapper.listUserInfo(departmentId);
    }

    public TableResultResponse<KpiCountLiveDetailInfo> getDetailPageList(Integer page, Integer limit , Integer uid, String startDate, String endDate, String pvEndDate, String liveTypeName, String title) {


        Page<Object> result = PageHelper.startPage(page, limit);
        List<KpiCountLiveDetailInfo> list = getDetailList(uid,startDate,endDate,pvEndDate,liveTypeName,title);
        return new TableResultResponse<KpiCountLiveDetailInfo>(result.getTotal(), list);
    }

    public List<KpiCountLiveDetailInfo> getDetailList(Integer uid, String startDate, String endDate, String pvEndDate,String liveTypeName, String title) {
        List<KpiCountLiveDetailInfo> list = kpiCountLiveMapper.listKpiCountLiveDetail(startDate, endDate, uid, liveTypeName, title);

        List<KpiLiveIdInfo> typeIdLsit = kpiCountLiveMapper.listKpiLiveId(startDate, endDate, uid);
        Set vidArray = new HashSet();
        for (KpiLiveIdInfo kpiLiveIdInfo : typeIdLsit) {
            vidArray.add(kpiLiveIdInfo.getId());
        }

        //查询mongo 时间段从startDate 到pvEndDate rpv
        GroupByResults<ViewContent> mongoResult = viewContentService.listVideoByDate("live_id", vidArray, startDate, pvEndDate);
        List<Document> mongoResultList = (List<Document>) mongoResult.getRawResults().get("retval");
        Set<String> ipSet = new HashSet<String>();
        Document obj = null;
        //遍历查询集合
        for (int i = 0,length =mongoResultList.size(); i < length; i++) {
             obj = (Document) mongoResultList.get(i);
            if(obj!=null ) {
                if(Double.valueOf(obj.get("uid").toString()).intValue() == 0){
                    ipSet.add(obj.get("ip").toString());
                }else{
                    if(!ipSet.contains(obj.get("ip").toString())){
                        ipSet.add(obj.get("uid").toString());
                    }
                }
            }
        }
        for (KpiCountLiveDetailInfo kpiCountLiveDetailInfo:list    ) {
//            List<Integer> appPvList = new ArrayList<Integer>();
            int allPv = 0;
            int appPv = 0;
            //遍历查询集合
            for (int i = 0,length = mongoResultList.size(); i < length; i++) {
               obj = (Document) mongoResultList.get(i);
                //如果从mysql中查询出来的数据没有对应的id
                if(vidArray.size() == 0){
                    int vid = Double.valueOf(obj.get("live_id").toString()).intValue();
                    if(vid == 0){
                        ipSet.add(obj.get("ip").toString());
                        if("in".equals(obj.get("locf").toString())){
                            appPv += Double.valueOf(obj.get("appPv").toString()).intValue();
                        }
                        allPv += Double.valueOf(obj.get("allPv").toString()).intValue();
                        kpiCountLiveDetailInfo.setAppPv(appPv);
                        kpiCountLiveDetailInfo.setAllPv(allPv);
                        kpiCountLiveDetailInfo.setUv(ipSet.size());
                        kpiCountLiveDetailInfo.setIsFinish(appPv >= kpiCountLiveDetailInfo.getPvLimit()?"是":"否");

                    }
                }
                //正常的数据
                else if(obj!=null){
                    ipSet.add(obj.get("ip").toString());
                    int vid = Double.valueOf(obj.get("live_id").toString()).intValue();
                    if("in".equals(obj.get("locf").toString())){
                        appPv += Double.valueOf(obj.get("appPv").toString()).intValue();
                    }

                    allPv += Double.valueOf(obj.get("allPv").toString()).intValue();
                    Integer videoId = kpiCountLiveDetailInfo.getLiveId();
                    if(vid == videoId ){
                        kpiCountLiveDetailInfo.setAppPv(appPv);
                        kpiCountLiveDetailInfo.setAllPv(allPv);
                        kpiCountLiveDetailInfo.setUv(ipSet.size());
                        kpiCountLiveDetailInfo.setIsFinish(appPv >= kpiCountLiveDetailInfo.getPvLimit()?"是":"否");
                    }

                }

            }
        }
        return list;
    }

    public List<KpiCountWorkTypeInfo> getWorkTypeList() {
        List<KpiCountWorkTypeInfo> list = kpiCountLiveMapper.listWorkType();
        return list;
    }

    public List<KpiCountWorkTypeResultInfo> getWorkTypeResultList(String startDate, String endDate) {
        List<KpiCountWorkTypeResultInfo> list = kpiCountLiveMapper.listWorkTypeResult(startDate, endDate);
        return list;
    }

    /**
     * 直播工作统计导出Excel表格
     * @param excelName
     * @param startDate
     * @param endDate
     * @param departmentId
     * @param uid
     * @throws Exception
     */
    public ExcelData getKpiCountLiveExcel(String excelName, String startDate, String endDate, Integer departmentId, Integer uid){
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("姓名");
        titles.add("部门（二级）");
        titles.add("岗位");
        titles.add("栏目直播统计");
        titles.add("普通直播统计");
        titles.add("合计");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<KpiCountLiveInfo> list = getList(startDate,endDate,departmentId,uid);
        List<Object> row = null;
        for (KpiCountLiveInfo kpiCountLiveInfo:list  ) {
            row = new ArrayList();
            row.add(kpiCountLiveInfo.getId());
            row.add(kpiCountLiveInfo.getName());
            row.add(kpiCountLiveInfo.getDeptName());
            row.add(kpiCountLiveInfo.getJobName());
            row.add(kpiCountLiveInfo.getColumnLive());
            row.add(kpiCountLiveInfo.getOrdinaryLive());
            row.add(kpiCountLiveInfo.getAllCount());
            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }

    /**
     * 直播工作详情导出Excel
     * @param excelName
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param sourceTypeName
     * @param title
     * @throws Exception
     */
    public ExcelData getKpiCountLiveDetailExcel(String excelName,Integer uid,String startDate,String endDate,String pvEndDate,String sourceTypeName,String title) {
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("直播ID");
        titles.add("标题");
        titles.add("特殊稿件");
        titles.add("工作人员");
        titles.add("上传时间");
        titles.add("类型");
        titles.add("个人工作类型");
        titles.add("全渠道播放量");
        titles.add("app播放量");
        titles.add("uv");
        titles.add("是否达标");
        titles.add("编辑备注");
        titles.add("人资备注");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<KpiCountLiveDetailInfo> list =getDetailList(uid,startDate,endDate,pvEndDate,sourceTypeName,title);
        List<Object> row = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (KpiCountLiveDetailInfo kpiCountLiveDetailInfo:list  ) {
            row = new ArrayList();
            row.add(kpiCountLiveDetailInfo.getLiveId());
            row.add(kpiCountLiveDetailInfo.getTitle());
            row.add(kpiCountLiveDetailInfo.getIsSpecial());
            row.add(kpiCountLiveDetailInfo.getName());
            row.add(kpiCountLiveDetailInfo.getFirstCheckTime()==null?"":formatter.format(kpiCountLiveDetailInfo.getFirstCheckTime()));
            row.add(kpiCountLiveDetailInfo.getLiveTypeName());
            row.add(kpiCountLiveDetailInfo.getWorkName());
            row.add(kpiCountLiveDetailInfo.getAllPv());
            row.add(kpiCountLiveDetailInfo.getAppPv());
            row.add(kpiCountLiveDetailInfo.getUv());
            row.add(kpiCountLiveDetailInfo.getIsFinish());
            row.add(kpiCountLiveDetailInfo.getRemarks());
            row.add(kpiCountLiveDetailInfo.getFromRemark());

            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }

    /**
     * 直播工作统计导出Excel (new)
     * @param exceName
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    public ExcelData getKpiCountLiveNewExcel(String exceName,String startDate,String endDate,Integer departmentId) {

//
        ExcelData data = new ExcelData();
        data.setName(exceName);
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("姓名");
        titles.add("部门（二级）");
        titles.add("岗位");
        //获取workType
        List<KpiCountWorkTypeInfo> workTypeList =getWorkTypeList();
        for (KpiCountWorkTypeInfo kpiCountWorTypeInfo:workTypeList ) {
            titles.add(kpiCountWorTypeInfo.getWorkTypeName());
        }

        titles.add("合计");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();

        //人员列表
        List<KpiUserInfo> userList = getUserList(departmentId);

        //工作类型结果
        List<KpiCountWorkTypeResultInfo> worTypeResultInfoList =  getWorkTypeResultList(startDate,endDate);
        List<Object> row = null;
        for (KpiUserInfo user:userList ) {
            row = new ArrayList();
            row.add(user.getId());
            row.add(user.getName());
            row.add(user.getDepartmentName());
            row.add(user.getJobName());
            int sum = 0;
            for (KpiCountWorkTypeInfo kpiCountWorTypeInfo:workTypeList ) {
                int count = 0;

                for(KpiCountWorkTypeResultInfo kpiCountWorTypeResultInfo:worTypeResultInfoList){
                    if( kpiCountWorTypeResultInfo.getUid().intValue() == user.getId().intValue() && kpiCountWorTypeResultInfo.getWorkTypeId().intValue() == kpiCountWorTypeInfo.getId().intValue()){
                        count = kpiCountWorTypeResultInfo.getCount();
                        sum += count;
                        break;
                    }
                }
                row.add(count);
            }
            row.add(sum);
            rows.add(row);
        }
        data.setRows(rows);
        return data;
        //生成Excel并导出
//        ExportExcelUtils.exportExcel(response,exceName+".xlsx",data);
    }
}

