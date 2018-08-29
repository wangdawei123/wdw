package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CorpDeptBiz;
import com.kanfa.news.info.biz.CorpUserBiz;
import com.kanfa.news.info.biz.KpiCountLiveBiz;
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
@RequestMapping("kpiCountLive")
public class KpiCountLiveController extends BaseController<KpiCountLiveBiz, KpiCount> {

    @Autowired
    private KpiCountLiveBiz kpiCountLiveBiz;
    @Autowired
    private CorpUserBiz corpUserBiz;
    @Autowired
    private CorpDeptBiz corpDeptBiz;

    /**
     * 直播工作统计
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param departmentId
     * @return
     */
    @GetMapping("/pageKpiCountLive")
    public TableResultResponse<KpiCountLiveInfo> getContentPage(@RequestParam Integer page,
                                                                @RequestParam Integer limit,
                                                                @RequestParam String startDate,
                                                                @RequestParam String endDate,
                                                                @RequestParam(required=false)Integer departmentId,
                                                                @RequestParam(required=false) Integer uid){
        return kpiCountLiveBiz.getPageList(page,limit,startDate,endDate,departmentId,uid);
    }

 /**
     * 视频工作详情统计
     * @param page
     * @param limit
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param liveTypeName
     * @param title
     * @return
     */
    @GetMapping("/pageKpiCountLiveDetail")
    public TableResultResponse<KpiCountLiveDetailInfo> getDetailPageList(@RequestParam Integer page,
                                                                         @RequestParam Integer limit,
                                                                         @RequestParam Integer uid,
                                                                         @RequestParam String startDate,
                                                                         @RequestParam String endDate,
                                                                         @RequestParam String pvEndDate,
                                                                         @RequestParam (required = false)String liveTypeName,
                                                                         @RequestParam (required = false)String title
                                                            ){
        return kpiCountLiveBiz.getDetailPageList(page,limit,uid,startDate,endDate,pvEndDate,liveTypeName,title);
    }

    /**
     * 人员列表
     * @return
     */
    @GetMapping("/corpUserList")
    public List<CorpUser> getCorpUserList(){
         CorpUser entity = new CorpUser();
        return corpUserBiz.selectList(entity);
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
     * 直播工作统计导出Excel
     * @param excelName
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/pageKpiCountLive/excel")
    public ExcelData pageKpiCountExcel(@RequestParam String excelName,
                                  @RequestParam String startDate,
                                  @RequestParam(  ) String endDate,
                                  @RequestParam(required=false)Integer departmentId,
                                  @RequestParam(required=false) Integer uid ){

           return  kpiCountLiveBiz.getKpiCountLiveExcel(excelName,startDate,endDate,departmentId,uid);

    }

   /**
     * 直播工作详情统计导出Excel
     * @param excelName
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @throws Exception
     */
   @GetMapping("/pageKpiCountLiveDetail/excel")
    public ExcelData pageKpiDetailCountlExcel( @RequestParam String excelName,
                                         @RequestParam Integer uid,
                                         @RequestParam String startDate,
                                         @RequestParam String endDate,
                                         @RequestParam String pvEndDate,
                                         @RequestParam (required = false)String sourceTypeName,
                                         @RequestParam (required = false)String title    ) {

           return kpiCountLiveBiz.getKpiCountLiveDetailExcel(excelName,uid,startDate,endDate,pvEndDate,sourceTypeName,title);


    }


    /**
     * 直播工作统计导出Excel  （new）
     * @param excelName
     * @param startDate
     * @param endDate
     * @param departmentId
     */
    @GetMapping("/pageKpiCountLiveNew/excel")
    public ExcelData pageKpiCountNewExcel(@RequestParam String excelName,
                                     @RequestParam String startDate,
                                     @RequestParam String endDate,
                                     @RequestParam(required=false)Integer departmentId
    ){
         return   kpiCountLiveBiz.getKpiCountLiveNewExcel(excelName,startDate,endDate,departmentId);
    }
}