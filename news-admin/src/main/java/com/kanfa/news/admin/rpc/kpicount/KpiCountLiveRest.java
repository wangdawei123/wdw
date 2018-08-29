package com.kanfa.news.admin.rpc.kpicount;

import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.entity.KpiCount;
import com.kanfa.news.admin.feign.kpicount.IKpiCountLiveServiceFeign;
import com.kanfa.news.admin.vo.kpicount.KpiCountLiveDetailInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountLiveInfo;
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
@RequestMapping("kpiCountLive")
public class KpiCountLiveRest  {

    @Autowired
    private IKpiCountLiveServiceFeign iKpiCountLiveServiceFeign;

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
        return iKpiCountLiveServiceFeign.getContentPage(page, limit, startDate, endDate, departmentId, uid);
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
        return iKpiCountLiveServiceFeign.getDetailPageList(page, limit, uid, startDate, endDate, pvEndDate, liveTypeName, title);
    }

    /**
     * 人员列表
     * @return
     */
    @GetMapping("/corpUserList")
    public List<CorpUser> getCorpUserList(){
        return iKpiCountLiveServiceFeign.getCorpUserList();
    }

    /**
     * 部门列表
     * @return
     */
    @GetMapping("/corpDepartmentList")
    public List<CorpDept> corpDepartmentList(){

        return iKpiCountLiveServiceFeign.corpDepartmentList();
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

        return iKpiCountLiveServiceFeign.updateRemark(id, isSpecial, remarks);
    }

    /**
     * 直播工作统计导出Excel
     * @param response
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/pageKpiCountLive/excel")
    public void pageKpiCountExcel(HttpServletResponse response,
                                  @RequestParam String startDate,
                                  @RequestParam String endDate,
                                  @RequestParam(required=false)Integer departmentId,
                                  @RequestParam(required=false) Integer uid
    )  {
        String excelName = startDate.split(" " )[0]+"至"+endDate.split(" ")[0]+"直播工作统计";
        ExcelData data =  iKpiCountLiveServiceFeign.pageKpiCountLiveExcel(excelName, startDate, endDate, departmentId, uid);
        try {
            ExportExcelUtils.exportExcel(response,excelName+".xlsx",data);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

   /**
     * 直播工作详情统计导出Excel
     * @param response
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @throws Exception
     */
   @GetMapping("/pageKpiCountLiveDetail/excel")
    public void pageKpiDetailCountlExcel(HttpServletResponse response,
                                         @RequestParam Integer uid,
                                         @RequestParam String startDate,
                                         @RequestParam String endDate,
                                         @RequestParam String pvEndDate,
                                         @RequestParam (required = false)String sourceTypeName,
                                         @RequestParam (required = false)String title    ) {

       String excelName = startDate.split(" " )[0]+"至"+endDate.split(" ")[0]+"KPI详情统计";
       ExcelData data =  iKpiCountLiveServiceFeign.pageKpiDetailCountLiveExcel(excelName, uid, startDate, endDate, pvEndDate, sourceTypeName, title);
       try {
           ExportExcelUtils.exportExcel(response,excelName+".xlsx",data);
       } catch (Exception e){
           e.printStackTrace();
       }

    }


    /**
     * 直播工作统计导出Excel  （new）
     * @param response
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/pageKpiCountLiveNew/excel")
    public void pageKpiCountNewExcel(HttpServletResponse response,
                                     @RequestParam String startDate,
                                     @RequestParam String endDate,
                                     @RequestParam(required=false)Integer departmentId
    ){
        StringBuilder excelName =new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("直播工作统计");
        ExcelData data =  iKpiCountLiveServiceFeign.pageKpiCountNewExcel(excelName.toString(), startDate, endDate, departmentId);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}