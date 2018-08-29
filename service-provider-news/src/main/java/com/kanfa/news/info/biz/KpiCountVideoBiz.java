package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.KpiCount;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.excel.utils.ExportExcelUtils;
import com.kanfa.news.info.mapper.KpiCountVideoMapper;
import com.kanfa.news.info.mapper.KpiUserInfoMapper;
import com.kanfa.news.info.mongodb.biz.ViewContentService;
import com.kanfa.news.info.mongodb.entity.ViewContent;
import com.kanfa.news.info.vo.admin.kpicount.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
public class KpiCountVideoBiz extends BaseBiz<KpiCountVideoMapper,KpiCount> {

    @Autowired
    private KpiCountVideoMapper kpiCountVideoMapper;
    @Autowired
    private KpiUserInfoMapper kpiUserInfoMapper;
    @Resource
    private ViewContentService viewContentService;

    public TableResultResponse<KpiCountVideoInfo> getPageList(Integer page, Integer limit , String startDate, String endDate, String pvEndDate,Integer departmentId, Integer uid) {
        Page<Object> result = PageHelper.startPage(page,limit);
        List<KpiCountVideoInfo> list = getList(startDate,endDate,pvEndDate,departmentId,uid);
        return new TableResultResponse<KpiCountVideoInfo>(result.getTotal(), list);
    }

    public List<KpiCountVideoInfo> getList( String startDate, String endDate, String pvEndDate,  Integer departmentId, Integer uid) {
        List<KpiCountVideoInfo> list = kpiCountVideoMapper.listKpiCountVideo(startDate,endDate,pvEndDate,departmentId,uid);
        return list;
    }


    public List<KpiUserInfo> getUserList(Integer departmentId){
        return kpiUserInfoMapper.listUserInfo(departmentId);
    }


    public TableResultResponse<KpiCountVideoDetailInfo> getDetailPageList(Integer page, Integer limit ,Integer uid, String startDate, String endDate, String pvEndDate,String sourceTypeName, String title) {


        Page<Object> result = PageHelper.startPage(page, limit);
        List<KpiCountVideoDetailInfo> list = getDetailList(uid,startDate,endDate,pvEndDate,sourceTypeName,title);
        return new TableResultResponse<KpiCountVideoDetailInfo>(result.getTotal(),list );
    }

    public List<KpiCountVideoDetailInfo> getDetailList(Integer uid, String startDate, String endDate, String pvEndDate,String sourceTypeName, String title) {
        List<KpiCountVideoDetailInfo> list = kpiCountVideoMapper.listKpiCountVideoDetail(startDate, endDate, uid, sourceTypeName, title);

        List<KpiVideoIdInfo> typeIdLsit = kpiCountVideoMapper.listKpiVideoId(startDate, endDate, uid);
        Set vidArray = new HashSet();
        for (KpiVideoIdInfo kpiVideoIdInfo : typeIdLsit) {
            vidArray.add(kpiVideoIdInfo.getId());
        }

        //查询mongo 时间段从startDate 到pvEndDate rpv
        GroupByResults<ViewContent> mongoResult = viewContentService.listVideoByDate("vid", vidArray, startDate, pvEndDate);
//        BasicDBList mongoResultList = (BasicDBList) mongoResult.getRawResults().get("retval");
        List<Document> mongoResultList = (List<Document>) mongoResult.getRawResults().get("retval");
        Set ipSet = new HashSet();
        Document obj = null;
        //遍历查询集合
        for (int i = 0,length = mongoResultList.size(); i < length; i++) {
            obj = (Document) mongoResultList.get(i);
            if(obj!=null) {
                if(Double.valueOf(obj.get("uid").toString()).intValue() == 0){
                    ipSet.add(obj.get("ip").toString());
                }else{
                    if(!ipSet.contains(obj.get("ip").toString())){
                        ipSet.add(obj.get("uid").toString());
                    }
                }
            }
        }
        for (KpiCountVideoDetailInfo kpiCountVideoDetailInfo:list    ) {
            int allPv = 0;
            int appPv = 0;
            //遍历查询集合
            for (int i = 0; i < mongoResultList.size(); i++) {
                obj = (Document) mongoResultList.get(i);
                if(obj!=null){
                    ipSet.add(obj.get("ip").toString());
                    int vid = Double.valueOf(obj.get("vid").toString()).intValue();
                    if("in".equals(obj.get("locf").toString())){
                        appPv += Double.valueOf(obj.get("appPv").toString()).intValue();
                    }

                    allPv += Double.valueOf(obj.get("allPv").toString()).intValue();
                    Integer videoId = kpiCountVideoDetailInfo.getVideoId();
                    if(vid == videoId){
                        kpiCountVideoDetailInfo.setAppPv(appPv);
                        kpiCountVideoDetailInfo.setAllPv(allPv);
                        kpiCountVideoDetailInfo.setUv(ipSet.size());
                        kpiCountVideoDetailInfo.setIsFinish(appPv >= kpiCountVideoDetailInfo.getPvLimit()?"是":"否");
                    }
                }

            }
        }
        return list;
    }

    public List<KpiCountVideoDetailConnectContentInfo> listKpiCountVideoDetailConnectContent(Integer vid,String startDate, String endDate,String pvEndDate){

        List<KpiCountVideoDetailConnectContentInfo> list = kpiCountVideoMapper.listKpiCountVideoDetailConnectContent(vid);

        Set vidArray = new HashSet();
        for (KpiCountVideoDetailConnectContentInfo kpiCountVideoDetailConnectContentInfo : list) {
            vidArray.add(kpiCountVideoDetailConnectContentInfo.getCid());
        }

        //查询mongo 时间段从startDate 到pvEndDate rpv
        GroupByResults<ViewContent> mongoResult = viewContentService.listByDate("cid", vidArray, startDate, pvEndDate);
        List<Document> mongoResultList = (List<Document>) mongoResult.getRawResults().get("retval");
        Document obj = null;
        for (KpiCountVideoDetailConnectContentInfo kpiCountVideoDetailConnectContentInfo:list    ) {
            int allPv = 0;
            int appPv = 0;
            //遍历查询集合
            for (int i = 0, length = mongoResultList.size(); i < length; i++) {
                 obj  = (Document) mongoResultList.get(i);
                if(obj!=null){
                        if("in".equals(obj.get("locf").toString())){
                            appPv += Double.valueOf(obj.get("appPv").toString()).intValue();
                        }
                        allPv += Double.valueOf(obj.get("allPv").toString()).intValue();
                    }
                }
            kpiCountVideoDetailConnectContentInfo.setAppPv(appPv);
            kpiCountVideoDetailConnectContentInfo.setAllPv(allPv);
            }
            return list;
        }


    public List<KpiCountWorkTypeInfo> getWorkTypeList() {
        List<KpiCountWorkTypeInfo> list = kpiCountVideoMapper.listWorkType();
        return list;
    }

    public List<KpiCountWorkTypeResultInfo> getWorkTypeResultList(String startDate, String endDate) {
        List<KpiCountWorkTypeResultInfo> list = kpiCountVideoMapper.listWorkTypeResult(startDate, endDate);
        return list;
    }

    /**
     * 视频工作统计导出Excel
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param departmentId
     * @param uid
     * @throws Exception
     */
    public ExcelData getKpiCountVideoExcel(String excelName,String startDate, String endDate, String pvEndDate, Integer departmentId, Integer uid) {
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("姓名");
        titles.add("部门（二级）");
        titles.add("岗位");
        titles.add("原创视频（含VR）");
        titles.add("原创栏目视频动画后期");
        titles.add("动画原画设计");
        titles.add("评论视频");
        titles.add("转载视频");
        titles.add("配音");
        titles.add("动画、策划脚本");
        titles.add("其他");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<KpiCountVideoInfo> list =getList(startDate,endDate,pvEndDate,departmentId,uid);
        List<Object> row = null;
        for (KpiCountVideoInfo kpiCountVideoInfo:list  ) {
            row = new ArrayList();
            row.add(kpiCountVideoInfo.getId());
            row.add(kpiCountVideoInfo.getName());
            row.add(kpiCountVideoInfo.getDeptName());
            row.add(kpiCountVideoInfo.getJobName());
            row.add(kpiCountVideoInfo.getOriginalVideo());
            row.add(kpiCountVideoInfo.getOriginalLater());
            row.add(kpiCountVideoInfo.getAnimationDesing());
            row.add(kpiCountVideoInfo.getReviewVideo());
            row.add(kpiCountVideoInfo.getReprintVideo());
            row.add(kpiCountVideoInfo.getDubbing());
            row.add(kpiCountVideoInfo.getAnimationScript());
            row.add(kpiCountVideoInfo.getOther());
            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }

    /**
     * 视频工作统计详情导出Excel
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param sourceTypeName
     * @param title
     * @throws Exception
     */
    public ExcelData getKpiCountVideoDetailExcel(String excelName, Integer uid,  String startDate,String endDate, String pvEndDate,  String sourceTypeName,  String title) {
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("视频ID");
        titles.add("标题");
        titles.add("特殊稿件");
        titles.add("工作人员");
        titles.add("上传时间");
        titles.add("类型");
        titles.add("个人工作类型");
        titles.add("关联内容数");
        titles.add("全渠道播放量");
        titles.add("app播放量");
        titles.add("uv");
        titles.add("是否达标");
        titles.add("备注");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<KpiCountVideoDetailInfo> list =getDetailList(uid,startDate,endDate,pvEndDate,sourceTypeName,title);
        List<Object> row = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (KpiCountVideoDetailInfo kpiCountVideoDetailInfo:list  ) {
            row = new ArrayList();
            row.add(kpiCountVideoDetailInfo.getVideoId());
            row.add(kpiCountVideoDetailInfo.getTitle());
            row.add(kpiCountVideoDetailInfo.getIsSpecial());
            row.add(kpiCountVideoDetailInfo.getName());
            row.add(kpiCountVideoDetailInfo.getCreateTime()==null?"":formatter.format(kpiCountVideoDetailInfo.getCreateTime()));
            row.add(kpiCountVideoDetailInfo.getSourceTypeName());
            row.add(kpiCountVideoDetailInfo.getWorkName());
            row.add(kpiCountVideoDetailInfo.getContentCount());
            row.add(kpiCountVideoDetailInfo.getAllPv());
            row.add(kpiCountVideoDetailInfo.getAppPv());
            row.add(kpiCountVideoDetailInfo.getUv());
            row.add(kpiCountVideoDetailInfo.getIsFinish());
            row.add(kpiCountVideoDetailInfo.getRemarks());

            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }

    /**
     * 视频工作统计导出Excel (new)
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    public ExcelData getKpiCountVideoNewExcel(String excelName,String startDate,  String endDate, Integer departmentId)  {
        ExcelData data = new ExcelData();
        data.setName(excelName);
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
                    if( kpiCountWorTypeResultInfo.getUid().intValue() == user.getId().intValue() && kpiCountWorTypeResultInfo.getWorkTypeId().intValue() == kpiCountWorTypeInfo.getId().intValue()){//& kpiCountWorTypeResultInfo.getWorkTypeId() == kpiCountWorTypeInfo.getId()
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
        return  data;
    }

}

