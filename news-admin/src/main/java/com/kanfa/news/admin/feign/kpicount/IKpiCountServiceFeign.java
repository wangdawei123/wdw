package com.kanfa.news.admin.feign.kpicount;

import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.entity.KpiCount;
import com.kanfa.news.admin.vo.kpicount.KpiCountDetailInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/17 10:51
 */
@FeignClient(name = "service-provider-news")
public interface IKpiCountServiceFeign {


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
    @GetMapping("/kpiCount/pageKpiCount")
    TableResultResponse<KpiCountInfo> get(@RequestParam("page") Integer page,
                                          @RequestParam("limit") Integer limit,
                                          @RequestParam("startDate") String startDate,
                                          @RequestParam("endDate") String endDate,
                                          @RequestParam("pvEndDate") String pvEndDate,
                                          @RequestParam("topPv") Integer topPv,
                                          @RequestParam("departmentId") Integer departmentId,
                                          @RequestParam(required=false,value="uid") Integer uid);

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
    @GetMapping("/kpiCount/pageKpiCountDetail")
    TableResultResponse<KpiCountDetailInfo> getDetail(@RequestParam("page") Integer page,
                                                      @RequestParam("limit") Integer limit,
                                                      @RequestParam("departmentId") Integer departmentId,
                                                      @RequestParam("uid") Integer uid,
                                                      @RequestParam("startDate") String startDate,
                                                      @RequestParam("endDate") String endDate,
                                                      @RequestParam (value="pvEndDate") String pvEndDate,
                                                      @RequestParam (required = false,value="articleTypeName")String articleTypeName,
                                                      @RequestParam (required = false,value = "title")String title);
    /**
     * 根据部门查询记者列表
     * @param departmentId
     * @return
     */
    @GetMapping("/kpiCount/corpUserList")
    List<CorpUser> getCorpUserList(@RequestParam("departmentId") Integer departmentId);

    /**
     * 备注修改
     * @param id
     * @param isSpecial
     * @param remarks
     * @return
     */
    @GetMapping("/kpiCount/updateRemark")
    ObjectRestResponse<KpiCount> updateRemark(@RequestParam("id") Integer id,
                                                 @RequestParam("isSpecial") Integer isSpecial,
                                                 @RequestParam("remarks") String remarks);

    /**
     * 记者工作统计导出Excel
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param topPv
     * @param departmentId
     * @param uid
     */
    @GetMapping("/kpiCount/pageKpiCount/excel")
    ExcelData pageKpiCountExcel(@RequestParam("excelName") String excelName,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,
                                @RequestParam ("pvEndDate")String pvEndDate,
                                @RequestParam(value = "topPv") Integer topPv,
                                @RequestParam (value = "departmentId")Integer departmentId,
                                @RequestParam(required=false,value = "uid") Integer uid);

    /**
     * 记者工作详情统计导出Excel
     * @param departmentId
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @param articleTypeName
     * @param title
     */
    @GetMapping("/pageKpiDetailCount/excel")
    ExcelData pageKpiDetailCountlExcel(@RequestParam("excelName") String excelName,
                                         @RequestParam("departmentId") Integer departmentId,
                                         @RequestParam ("uid")Integer uid,
                                         @RequestParam ("startDate")String startDate,
                                         @RequestParam ("endDate")String endDate,
                                         @RequestParam ("pvEndDate")String pvEndDate,
                                         @RequestParam (required = false,value = "articleTypeName")String articleTypeName,
                                         @RequestParam (required = false,value = "title")String title);
}
