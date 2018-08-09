package com.kanfa.news.admin.feign.kpicount;

import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.entity.KpiCount;
import com.kanfa.news.admin.vo.kpicount.KpiCountVideoDetailConnectContentInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountVideoDetailInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountVideoInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/17 10:51
 */
@FeignClient(name = "service-provider-news")
public interface IKpiCountVideoServiceFeign {

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
    @GetMapping("/kpiCountVideo/pageKpiCountVideo")
    TableResultResponse<KpiCountVideoInfo> getVideoPage(@RequestParam("page") Integer page,
                                                                 @RequestParam("limit") Integer limit,
                                                                 @RequestParam("startDate") String startDate,
                                                                 @RequestParam("endDate") String endDate,
                                                                 @RequestParam("pvEndDate") String pvEndDate,
                                                                 @RequestParam(required=false,value="departmentId")Integer departmentId,
                                                                 @RequestParam(required=false,value = "uid") Integer uid);

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
    @GetMapping("/kpiCountVideo/pageKpiCountVideoDetail")
    TableResultResponse<KpiCountVideoDetailInfo> getDetailPageList(@RequestParam("page") Integer page,
                                                                          @RequestParam("limit") Integer limit,
                                                                          @RequestParam("uid") Integer uid,
                                                                          @RequestParam("startDate") String startDate,
                                                                          @RequestParam("endDate") String endDate,
                                                                          @RequestParam("pvEndDate") String pvEndDate,
                                                                          @RequestParam (required = false,value = "sourceTypeName")String sourceTypeName,
                                                                          @RequestParam (required = false,value = "title")String title);

    /**
     * 人员列表
     * @return
     */
    @GetMapping("/kpiCountVideo/corpUserList")
    List<CorpUser> getCorpUserList( @RequestParam(required=false,value = "departmentId")Integer departmentId);
    /**
     * 关联内容列表
     * @return
     */
    @GetMapping("/kpiCountVideo/connectContentList")
    List<KpiCountVideoDetailConnectContentInfo> getContentList(@RequestParam("vid") Integer vid,
                                                                       @RequestParam("startDate") String startDate,
                                                                       @RequestParam("endDate") String endDate,
                                                                       @RequestParam("pvEndDate") String pvEndDate);
    /**
     * 部门列表
     * @return
     */
    @GetMapping("/kpiCountVideo/corpDepartmentList")
    List<CorpDept> corpDepartmentList();



    /**
     * 备注修改
     * @param id
     * @param isSpecial
     * @param remarks
     * @return
     */
    @PutMapping("/kpiCountVideo/updateRemark")
    ObjectRestResponse<KpiCount> updateRemark(@RequestParam("id") Integer id,
                                                 @RequestParam(required = false,value = "isSpecial") Integer isSpecial,
                                                 @RequestParam (required = false,value = "remarks")String remarks);

    /**
     * 视频工作统计导出Excel
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/kpiCountVideo/pageKpiCountVideo/excel")
    ExcelData pageKpiVideoExcel(@RequestParam("excelName") String excelName,
                                 @RequestParam("startDate") String startDate,
                                 @RequestParam("endDate") String endDate,
                                 @RequestParam("pvEndDate") String pvEndDate,
                                 @RequestParam(required=false,value = "departmentId")Integer departmentId,
                                 @RequestParam(required=false,value = "uid") Integer uid);
    /**
     * 视频工作详情统计导出Excel
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @throws Exception
     */
    @GetMapping("/kpiCountVideo/pageKpiCountVideoDetail/excel")
    ExcelData pageKpiDetailVideolExcel(@RequestParam("excelName") String excelName,
                                        @RequestParam("uid") Integer uid,
                                        @RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate,
                                        @RequestParam("pvEndDate") String pvEndDate,
                                        @RequestParam (required = false,value = "sourceTypeName")String sourceTypeName,
                                        @RequestParam (required = false,value = "title")String title    );


    /**
     * 视频工作统计导出Excel  （new）
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/kpiCountVideo/pageKpiCountVideoNew/excel")
    ExcelData pageKpiCountNewExcel(@RequestParam("excelName") String excelName,
                                    @RequestParam("startDate") String startDate,
                                    @RequestParam ("endDate")String endDate,
                                    @RequestParam(required=false,value = "departmentId")Integer departmentId);

}
