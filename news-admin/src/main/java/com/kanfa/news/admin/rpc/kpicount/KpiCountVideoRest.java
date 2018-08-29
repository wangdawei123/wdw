package com.kanfa.news.admin.rpc.kpicount;

import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.entity.KpiCount;
import com.kanfa.news.admin.feign.kpicount.IKpiCountVideoServiceFeign;
import com.kanfa.news.admin.vo.kpicount.KpiCountVideoDetailConnectContentInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountVideoDetailInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountVideoInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@RestController
@RequestMapping("kpiCountVideo")
public class KpiCountVideoRest {

    @Autowired
    private IKpiCountVideoServiceFeign iKpiCountVideoServiceFeign;


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
        return iKpiCountVideoServiceFeign.getVideoPage(page, limit, startDate, endDate, pvEndDate, departmentId, uid);
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
                                                                          @RequestParam (required = false)String title){
        return iKpiCountVideoServiceFeign.getDetailPageList(page, limit, uid, startDate, endDate, pvEndDate, sourceTypeName, title);
    }

    /**
     * 人员列表
     * @return
     */
    @GetMapping("/corpUserList")
    public List<CorpUser> getCorpUserList(@RequestParam(required=false)Integer departmentId){

        return iKpiCountVideoServiceFeign.getCorpUserList(departmentId);
    }
   /**
     * 关联内容列表
     * @return
     */
    @GetMapping("/connectContentList")
    public List<KpiCountVideoDetailConnectContentInfo> getContentList(@RequestParam Integer vid,
                                                                       @RequestParam String startDate,
                                                                       @RequestParam String endDate,
                                                                       @RequestParam String pvEndDate){

        return iKpiCountVideoServiceFeign.getContentList(vid, startDate, endDate, pvEndDate);
    }
    /**
     * 部门列表
     * @return
     */
    @GetMapping("/corpDepartmentList")
    public List<CorpDept> corpDepartmentList(){

        return iKpiCountVideoServiceFeign.corpDepartmentList();
    }



    /**
     * 备注修改
     * @param id
     * @param isSpecial
     * @param remarks
     * @return
     */
    @PutMapping("/updateRemark")
    public ObjectRestResponse<KpiCount> updateRemark(@RequestParam Integer id,
                                                        @RequestParam(required = false) Integer isSpecial,
                                                        @RequestParam (required = false)String remarks){

        return iKpiCountVideoServiceFeign.updateRemark(id, isSpecial, remarks);
    }

    /**
     * 视频工作统计导出Excel
     * @param response
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/pageKpiCountVideo/excel")
    public void pageKpiCountExcel(HttpServletResponse response,
                                  @RequestParam String startDate,
                                  @RequestParam String endDate,
                                  @RequestParam String pvEndDate,
                                  @RequestParam(required=false)Integer departmentId,
                                  @RequestParam(required=false) Integer uid
    )  {
        StringBuilder excelName = new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("视频工作统计");
        ExcelData data = iKpiCountVideoServiceFeign.pageKpiVideoExcel(excelName.toString(),startDate, endDate, pvEndDate, departmentId, uid);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 视频工作详情统计导出Excel
     * @param response
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @throws Exception
     */
    @GetMapping("/pageKpiCountVideoDetail/excel")
    public void pageKpiDetailCountlExcel(HttpServletResponse response,
                                         @RequestParam Integer uid,
                                         @RequestParam String startDate,
                                         @RequestParam String endDate,
                                         @RequestParam String pvEndDate,
                                         @RequestParam (required = false)String sourceTypeName,
                                         @RequestParam (required = false)String title    ){
        StringBuilder excelName = new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("KPI详情统计");
        ExcelData data =  iKpiCountVideoServiceFeign.pageKpiDetailVideolExcel(excelName.toString(), uid, startDate, endDate, pvEndDate, sourceTypeName, title);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 视频工作统计导出Excel  （new）
     * @param response
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/pageKpiCountVideoNew/excel")
    public void pageKpiCountNewExcel(HttpServletResponse response,
                                      @RequestParam String startDate,
                                      @RequestParam String endDate,
                                      @RequestParam(required=false)Integer departmentId
    ){
        StringBuilder excelName = new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("视频工作统计");
        ExcelData data =  iKpiCountVideoServiceFeign.pageKpiCountNewExcel(excelName.toString(), startDate, endDate, departmentId);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}