package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CorpDeptBiz;
import com.kanfa.news.info.biz.CorpUserBiz;
import com.kanfa.news.info.biz.KpiCountVideoBiz;
import com.kanfa.news.info.entity.CorpDept;
import com.kanfa.news.info.entity.CorpUser;
import com.kanfa.news.info.entity.KpiCount;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.excel.utils.ExportExcelUtils;
import com.kanfa.news.info.vo.admin.kpicount.*;
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
@RequestMapping("kpiCountVideo")
public class KpiCountVideoController extends BaseController<KpiCountVideoBiz, KpiCount> {

    @Autowired
    private KpiCountVideoBiz kpiCountVideoBiz;
    @Autowired
    private CorpUserBiz corpUserBiz;
    @Autowired
    private CorpDeptBiz corpDeptBiz;

    /**
     * 视频工作统计
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param departmentId
     * @return
     */
    @GetMapping("/pageKpiCountVideo")
    public TableResultResponse<KpiCountVideoInfo> getContentPage(@RequestParam Integer page,
                                                                 @RequestParam Integer limit,
                                                                 @RequestParam String startDate,
                                                                 @RequestParam String endDate,
                                                                 @RequestParam String pvEndDate,
                                                                 @RequestParam(required=false)Integer departmentId,
                                                                 @RequestParam(required=false) Integer uid){
        return kpiCountVideoBiz.getPageList(page,limit,startDate,endDate,pvEndDate,departmentId,uid);
    }

    /**
     * 视频工作详情统计
     * @param page
     * @param limit
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param sourceTypeName
     * @param title
     * @return
     */
    @GetMapping("/pageKpiCountVideoDetail")
    public TableResultResponse<KpiCountVideoDetailInfo> getDetailPageList(@RequestParam Integer page,
                                                                          @RequestParam Integer limit,
                                                                          @RequestParam Integer uid,
                                                                          @RequestParam String startDate,
                                                                          @RequestParam String endDate,
                                                                          @RequestParam String pvEndDate,
                                                                          @RequestParam (required = false)String sourceTypeName,
                                                                          @RequestParam (required = false)String title
                                                            ){
        return kpiCountVideoBiz.getDetailPageList(page,limit,uid,startDate,endDate,pvEndDate,sourceTypeName,title);
    }

    /**
     * 人员列表
     * @return
     */
    @GetMapping("/corpUserList")
    public List<CorpUser> getCorpUserList( @RequestParam(required=false)Integer departmentId){
         CorpUser entity = new CorpUser();
         entity.setLevel2Id(departmentId);
        return corpUserBiz.selectList(entity);
    }
   /**
     * 关联内容列表
     * @return
     */
    @GetMapping("/connectContentList")
    public List<KpiCountVideoDetailConnectContentInfo> getCorpUserList(@RequestParam Integer vid,
                                                                       @RequestParam String startDate,
                                                                       @RequestParam String endDate,
                                                                       @RequestParam String pvEndDate){

        return kpiCountVideoBiz.listKpiCountVideoDetailConnectContent(vid,startDate,endDate,pvEndDate);
    }
    /**
     * 部门列表
     * @return
     */
    @GetMapping("/corpDepartmentList")
    public List<CorpDept> corpDepartmentList(){
         CorpDept entity = new CorpDept();
         //二级部门列表
        entity.setLevel(2);
        return corpDeptBiz.selectList(entity);
    }



    /**
     * 备注修改
     * @param id
     * @param isSpecial
     * @param remarks
     * @return
     */
    @PutMapping("/updateRemark")
    public ObjectRestResponse<KpiCount> getCorpUserList(@RequestParam Integer id,@RequestParam(required = false) Integer isSpecial,@RequestParam (required = false)String remarks){
        KpiCount kpiCount = new KpiCount();
        kpiCount.setId(id);
        kpiCount.setIsSpecial(isSpecial);
        kpiCount.setRemarks(remarks);
        ObjectRestResponse<KpiCount> restResponse =update(kpiCount);
        restResponse.setData(kpiCount);
        return restResponse;
    }

    /**
     * 视频工作统计导出Excel
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/pageKpiCountVideo/excel")
    public ExcelData pageKpiCountExcel(@RequestParam String excelName,
                                  @RequestParam String startDate,
                                  @RequestParam String endDate,
                                  @RequestParam String pvEndDate,
                                  @RequestParam(required=false)Integer departmentId,
                                  @RequestParam(required=false) Integer uid){
            return kpiCountVideoBiz.getKpiCountVideoExcel(excelName,startDate,endDate,pvEndDate,departmentId,uid);
    }
    /**
     * 视频工作详情统计导出Excel
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     */
    @GetMapping("/pageKpiCountVideoDetail/excel")
    public ExcelData pageKpiDetailCountlExcel(@RequestParam String excelName,
                                         @RequestParam Integer uid,
                                         @RequestParam String startDate,
                                         @RequestParam String endDate,
                                         @RequestParam String pvEndDate,
                                         @RequestParam (required = false)String sourceTypeName,
                                         @RequestParam (required = false)String title){
           return kpiCountVideoBiz.getKpiCountVideoDetailExcel(excelName,uid,startDate,endDate,pvEndDate,sourceTypeName,title);
    }

    /**
     * 视频工作统计导出Excel  （new）
     * @param startDate
     * @param endDate
     * @param departmentId
     */
    @GetMapping("/pageKpiCountVideoNew/excel")
    public ExcelData pageKpiCountNewExcel(@RequestParam String excelName,
                                  @RequestParam String startDate,
                                  @RequestParam String endDate,
                                  @RequestParam(required=false)Integer departmentId){
            return kpiCountVideoBiz.getKpiCountVideoNewExcel(excelName,startDate,endDate,departmentId);
    }


}