package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CorpUserBiz;
import com.kanfa.news.info.biz.KpiCountBiz;
import com.kanfa.news.info.biz.KpiCountVideoBiz;
import com.kanfa.news.info.entity.CorpUser;
import com.kanfa.news.info.entity.KpiContent;
import com.kanfa.news.info.entity.KpiCount;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.excel.utils.ExportExcelUtils;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountDetailInfo;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@RestController
@RequestMapping("kpiCount")
public class KpiCountController extends BaseController<KpiCountBiz, KpiCount> {

    @Autowired
    private KpiCountBiz kpiCountBiz;
    @Autowired
    private CorpUserBiz corpUserBiz;

    /**
     * 记者工作统计
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param topPv
     * @param departmentId
     * @return
     */
    @GetMapping("/pageKpiCount")
    public TableResultResponse<KpiCountInfo> getContentPage(@RequestParam Integer page,
                                                            @RequestParam Integer limit,
                                                            @RequestParam String startDate,
                                                            @RequestParam String endDate,
                                                            @RequestParam String pvEndDate,
                                                            @RequestParam Integer topPv,
                                                            @RequestParam Integer departmentId,
                                                            @RequestParam(required=false) Integer uid){
        TableResultResponse<KpiCountInfo> result = kpiCountBiz.getPageList(page,limit,startDate,endDate,pvEndDate,topPv,departmentId,uid);
        return result;
    }

    /**
     * 记者工作详情统计
     * @param page
     * @param limit
     * @param departmentId
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param articleTypeName
     * @param title
     * @return
     */
    @GetMapping("/pageKpiCountDetail")
    public TableResultResponse<KpiCountDetailInfo> getDetailPageList(@RequestParam Integer page,
                                                                     @RequestParam Integer limit,
                                                                     @RequestParam Integer departmentId,
                                                                     @RequestParam Integer uid,
                                                                     @RequestParam String startDate,
                                                                     @RequestParam String endDate,
                                                                     @RequestParam String pvEndDate,
                                                                     @RequestParam (required = false)String articleTypeName,
                                                                     @RequestParam (required = false)String title
                                                            ){
        return kpiCountBiz.getDetailPageList(page,limit,departmentId,uid,startDate,endDate,pvEndDate,articleTypeName,title);
    }

    /**
     * 根据部门查询记者列表
     * @param departmentId
     * @return
     */
    @GetMapping("/corpUserList")
    public List<CorpUser> getCorpUserList(@RequestParam Integer departmentId){
         CorpUser entity = new CorpUser();
         entity.setLevel2Id(departmentId);
        return corpUserBiz.selectList(entity);
    }



    /**
     * 备注修改
     * @param id
     * @param isSpecial
     * @param remarks
     * @return
     */
    @PutMapping("/updateRemark")
    public ObjectRestResponse<KpiCount> getCorpUserList(@RequestParam Integer id,@RequestParam Integer isSpecial,@RequestParam String remarks){
        KpiCount kpiCount = new KpiCount();
        kpiCount.setId(id);
        kpiCount.setIsSpecial(isSpecial);
        kpiCount.setRemarks(remarks);
        ObjectRestResponse<KpiCount> restResponse =update(kpiCount);
        restResponse.setData(kpiCount);
        return restResponse;
    }

    /**
     * 记者工作统计导出Excel
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param topPv
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/pageKpiCount/excel")
    public ExcelData pageKpiCountExcel(@RequestParam String excelName,
                                  @RequestParam String startDate,
                                  @RequestParam String endDate,
                                  @RequestParam String pvEndDate,
                                  @RequestParam Integer topPv,
                                  @RequestParam Integer departmentId,
                                  @RequestParam(required=false) Integer uid){
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("工号");
        titles.add("姓名");
        titles.add("部门");
        titles.add("职务");
        titles.add("文章(纯文本)");
        titles.add("文字+图片&文字+视频");
        titles.add("文字+图片+视频");
        titles.add("图集");
        titles.add("视频");
        titles.add("其他");
        titles.add("Top"+topPv+"pv");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<KpiCountInfo> list =kpiCountBiz.getList(startDate,endDate,pvEndDate,topPv,departmentId,uid);
        for (KpiCountInfo kpiCountInfo:list  ) {
            List<Object> row = new ArrayList();
            NumberFormat nf = new DecimalFormat("0.#");
            row.add(kpiCountInfo.getUid());
            row.add(kpiCountInfo.getName());
            row.add(kpiCountInfo.getDeptName());
            row.add(kpiCountInfo.getJobName());
            row.add(nf.format(kpiCountInfo.getW().floatValue()));
            row.add(nf.format(kpiCountInfo.getWp().floatValue()));
            row.add(nf.format(kpiCountInfo.getWpv().floatValue()));
            row.add(nf.format(kpiCountInfo.getT().floatValue()));
            row.add(nf.format(kpiCountInfo.getVideo().floatValue()));
            row.add(nf.format(kpiCountInfo.getOther().floatValue()));
            row.add(kpiCountInfo.getEightPv());
            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }

    /**
     * 记者工作详情统计导出Excel
     * @param departmentId
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @throws Exception
     */
    @GetMapping("/pageKpiCountDetail/excel")
    public ExcelData pageKpiDetailCountlExcel(
                                         @RequestParam String excelName,
                                         @RequestParam Integer departmentId,
                                         @RequestParam Integer uid,
                                         @RequestParam String startDate,
                                         @RequestParam String endDate,
                                         @RequestParam String pvEndDate,
                                         @RequestParam (required = false)String articleTypeName,
                                         @RequestParam (required = false)String title    ){
        ExcelData data = new ExcelData();
        data.setName(excelName);
        List<String> titles = new ArrayList();
        titles.add("内容ID");
        titles.add("标题");
        titles.add("特殊稿件");
        titles.add("发布人");
        titles.add("审核人");
        titles.add("提审时间");
        titles.add("上线时间");
        titles.add("稿件类别");
        titles.add("稿件形式");
        titles.add("个人工作类型");
        titles.add("个人工作量");
        titles.add("记者人数");
        titles.add("全渠道PV");
        titles.add("appPV");
        titles.add("个人分配appPV");
        titles.add("备注");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<KpiCountDetailInfo> list =kpiCountBiz.getDetailList(departmentId,uid,startDate,endDate,pvEndDate,articleTypeName,title);
        for (KpiCountDetailInfo kpiCountDetailInfo:list  ) {
            List<Object> row = new ArrayList();
            NumberFormat nf = new DecimalFormat("0.#");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            row.add(kpiCountDetailInfo.getTypeId());
            row.add(kpiCountDetailInfo.getTitle());
            row.add(kpiCountDetailInfo.getIsSpecial());
            row.add(kpiCountDetailInfo.getPubUser());
            row.add(kpiCountDetailInfo.getCheckUser());
            row.add(kpiCountDetailInfo.getCreateTime()==null?"":formatter.format(kpiCountDetailInfo.getCreateTime()));
            row.add(kpiCountDetailInfo.getFirstCheckTime()==null?"":formatter.format(kpiCountDetailInfo.getFirstCheckTime()));
            row.add(kpiCountDetailInfo.getArticleTypeName());
            row.add(kpiCountDetailInfo.getArticleShapeName());
            row.add(kpiCountDetailInfo.getWorkTypeName());
            row.add(nf.format(kpiCountDetailInfo.getWeight().floatValue()));
            row.add(kpiCountDetailInfo.getAuthorNum());
            row.add(kpiCountDetailInfo.getAllPv());
            row.add(kpiCountDetailInfo.getAppPv());
            row.add(kpiCountDetailInfo.getUidAppPv());
            row.add(kpiCountDetailInfo.getRemarks());

            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }

    @GetMapping("/getListByCid")
    List<KpiCount> getKpiContentList(@RequestParam("contentId") Integer contentId){
        KpiCount entity=new KpiCount();
        entity.setTypeId(contentId);
        List<KpiCount> list = kpiCountBiz.selectList(entity);
        return list;
    }

}