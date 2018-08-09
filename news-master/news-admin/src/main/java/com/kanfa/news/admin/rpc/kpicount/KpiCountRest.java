package com.kanfa.news.admin.rpc.kpicount;

import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.entity.KpiCount;
import com.kanfa.news.admin.feign.kpicount.IKpiCountServiceFeign;
import com.kanfa.news.admin.vo.channel.ChannelFocusInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountDetailInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountInfo;
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
 * @date 2018/4/17 11:57
 */
@RestController
@RequestMapping("kpiCount")
public class KpiCountRest {

    @Autowired
    private IKpiCountServiceFeign iKpiCountServiceFeign;

    /**
     * 记者工作统计
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param topPv
     * @param departmentId
     * @param uid
     * @return
     */
    @GetMapping("/pageKpiCount")
    public TableResultResponse<KpiCountInfo> get(@RequestParam("page") Integer page,
                                                 @RequestParam("limit") Integer limit,
                                                 @RequestParam("startDate") String startDate,
                                                 @RequestParam("endDate") String endDate,
                                                 @RequestParam("pvEndDate") String pvEndDate,
                                                 @RequestParam("topPv") Integer topPv,
                                                 @RequestParam("departmentId") Integer departmentId,
                                                 @RequestParam(required=false,value ="uid") Integer uid) {

        TableResultResponse<KpiCountInfo> result = iKpiCountServiceFeign.get(page,limit,startDate,endDate,pvEndDate,topPv,departmentId,uid);
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
   public TableResultResponse<KpiCountDetailInfo> getDetail(@RequestParam("page") Integer page,
                                                            @RequestParam("limit") Integer limit,
                                                            @RequestParam("departmentId") Integer departmentId,
                                                            @RequestParam("uid") Integer uid,
                                                            @RequestParam("startDate") String startDate,
                                                            @RequestParam("endDate") String endDate,
                                                            @RequestParam ("pvEndDate") String pvEndDate,
                                                            @RequestParam (required = false,value="articleTypeName")String articleTypeName,
                                                            @RequestParam (required = false,value = "title")String title){
        return iKpiCountServiceFeign.getDetail(page, limit, departmentId, uid, startDate, endDate, pvEndDate, articleTypeName, title);
    }
    /**
     * 根据部门查询记者列表
     * @param departmentId
     * @return
     */
    @GetMapping("/corpUserList")
    public List<CorpUser> getCorpUserList(@RequestParam("departmentId") Integer departmentId){
        return iKpiCountServiceFeign.getCorpUserList(departmentId);
    }

    /**
     * 备注修改
     * @param id
     * @param isSpecial
     * @param remarks
     * @return
     */
    @PutMapping("/updateRemark")
    public ObjectRestResponse<KpiCount> updateRemark(@RequestParam("id") Integer id,
                                                     @RequestParam("isSpecial") Integer isSpecial,
                                                     @RequestParam("remarks") String remarks){
        return iKpiCountServiceFeign.updateRemark(id, isSpecial, remarks);
    }

    /**
     * 记者工作统计导出Excel
     * @param response
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param topPv
     * @param departmentId
     * @param uid
     */
    @GetMapping("/pageKpiCount/excel")
    public void pageKpiCountExcel(HttpServletResponse response,
                           @RequestParam("startDate") String startDate,
                           @RequestParam("endDate") String endDate,
                           @RequestParam ("pvEndDate")String pvEndDate,
                           @RequestParam("topPv") Integer topPv,
                           @RequestParam (value = "departmentId")Integer departmentId,
                           @RequestParam(required=false,value = "uid") Integer uid){
        StringBuilder excelName = new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("KPI统计");
        ExcelData data = iKpiCountServiceFeign.pageKpiCountExcel(excelName.toString(),startDate, endDate, pvEndDate, topPv, departmentId, uid);
       try {
           ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
       } catch (Exception e){
           e.printStackTrace();
       }

    }

    /**
     * 记者工作详情统计导出Excel
     * @param response
     * @param departmentId
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param articleTypeName
     * @param title
     */
    @GetMapping("/pageKpiDetailCount/excel")
    public void pageKpiDetailCountlExcel(HttpServletResponse response,
                                  @RequestParam("departmentId") Integer departmentId,
                                  @RequestParam ("uid")Integer uid,
                                  @RequestParam ("startDate")String startDate,
                                  @RequestParam ("endDate")String endDate,
                                  @RequestParam ("pvEndDate")String pvEndDate,
                                  @RequestParam (required = false,value = "articleTypeName")String articleTypeName,
                                  @RequestParam (required = false,value = "title")String title){
        StringBuilder excelName =new StringBuilder();
        excelName.append(startDate.split(" " )[0])
                .append("至")
                .append(endDate.split(" ")[0])
                .append("KPI详情统计");
        ExcelData data = iKpiCountServiceFeign.pageKpiDetailCountlExcel(excelName.toString(),departmentId, uid, startDate, endDate, pvEndDate, articleTypeName, title);
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
