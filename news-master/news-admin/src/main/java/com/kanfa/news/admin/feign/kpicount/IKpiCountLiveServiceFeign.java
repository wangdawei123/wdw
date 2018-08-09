package com.kanfa.news.admin.feign.kpicount;

import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.admin.entity.CorpUser;
import com.kanfa.news.admin.entity.KpiCount;
import com.kanfa.news.admin.vo.kpicount.KpiCountInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountLiveDetailInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountLiveInfo;
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
public interface IKpiCountLiveServiceFeign {

    /**
     * 直播工作统计
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param departmentId
     * @return
     */
    @GetMapping("/kpiCountLive/pageKpiCountLive")
    TableResultResponse<KpiCountLiveInfo> getContentPage(@RequestParam("page") Integer page,
                                                                @RequestParam("limit") Integer limit,
                                                                @RequestParam("startDate") String startDate,
                                                                @RequestParam("endDate") String endDate,
                                                                @RequestParam(required=false,value="departmentId")Integer departmentId,
                                                                @RequestParam(required=false,value="uid") Integer uid);

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
    @GetMapping("/kpiCountLive/pageKpiCountLiveDetail")
    TableResultResponse<KpiCountLiveDetailInfo> getDetailPageList(@RequestParam("page") Integer page,
                                                                         @RequestParam("limit") Integer limit,
                                                                         @RequestParam("uid") Integer uid,
                                                                         @RequestParam("startDate") String startDate,
                                                                         @RequestParam("endDate") String endDate,
                                                                         @RequestParam("pvEndDate") String pvEndDate,
                                                                         @RequestParam (required = false,value="liveTypeName")String liveTypeName,
                                                                         @RequestParam (required = false,value = "title")String title);

    /**
     * 人员列表
     * @return
     */
    @GetMapping("/kpiCountLive/corpUserList")
    List<CorpUser> getCorpUserList();

    /**
     * 部门列表
     * @return
     */
    @GetMapping("/kpiCountLive/corpDepartmentList")
    List<CorpDept> corpDepartmentList();

    /**
     * 备注修改
     * @param id
     * @param isSpecial
     * @param remarks
     * @return
     */
    @PutMapping("/kpiCountLive/updateRemark")
    ObjectRestResponse<KpiCount> updateRemark(@RequestParam("id") Integer id,
                                                 @RequestParam(required = false,value = "isSpecial") Integer isSpecial,
                                                 @RequestParam (required = false,value = "remarks")String remarks);

    /**
     * 直播工作统计导出Excel
     * @param excelName
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/kpiCountLive/pageKpiCountLive/excel")
    ExcelData pageKpiCountLiveExcel( @RequestParam("excelName") String excelName,
                                    @RequestParam("startDate") String startDate,
                                    @RequestParam("endDate") String endDate,
                                    @RequestParam(required=false,value = "departmentId")Integer departmentId,
                                    @RequestParam(required=false,value = "uid") Integer uid);

    /**
     * 直播工作详情统计导出Excel
     * @param excelName
     * @param uid
     * @param startDate
     * @param endDate
     * @param pvEndDate
     * @throws Exception
     */
    @GetMapping("/kpiCountLive/pageKpiCountLiveDetail/excel")
    ExcelData pageKpiDetailCountLiveExcel( @RequestParam("excelName") String excelName,
                                         @RequestParam("uid") Integer uid,
                                         @RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,
                                         @RequestParam("pvEndDate") String pvEndDate,
                                         @RequestParam (required = false,value = "sourceTypeName")String sourceTypeName,
                                         @RequestParam (required = false,value = "title")String title);


    /**
     * 直播工作统计导出Excel  （new）
     * @param excelName
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws Exception
     */
    @GetMapping("/kpiCountLive/pageKpiCountLiveNew/excel")
    ExcelData pageKpiCountNewExcel( @RequestParam("excelName") String excelName,
                                     @RequestParam("startDate") String startDate,
                                     @RequestParam("endDate") String endDate,
                                     @RequestParam(required=false,value = "departmentId")Integer departmentId);

}
