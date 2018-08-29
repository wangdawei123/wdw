package com.kanfa.news.admin.rpc.kpicount;

import com.kanfa.news.admin.feign.kpicount.IContentKpiCountServiceFeign;
import com.kanfa.news.admin.vo.kpicount.ContentKpiCountInfo;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/17 11:57
 */
@RestController
@RequestMapping("contentKpiCount")
public class ContentKpiCountRest {

    @Autowired
    private IContentKpiCountServiceFeign iContentKpiCountServiceFeign;

    /**
     * 发稿明细计列表
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param sourceType
     * @param viewType
     * @return
     */
    @GetMapping("/pageList")
    public TableResultResponse<ContentKpiCountInfo> get(@RequestParam (value = "page",required = false) Integer page,
                                                        @RequestParam (value = "limit",required = false) Integer limit ,
                                                        @RequestParam (value = "startDate",required = false) String startDate,
                                                        @RequestParam (value = "endDate",required = false) String endDate,
                                                        @RequestParam (value = "sourceType",required = false) Integer sourceType,
                                                        @RequestParam (value = "viewType") Integer viewType) {

        TableResultResponse<ContentKpiCountInfo> result = iContentKpiCountServiceFeign.getPageList(page,limit,startDate,endDate,sourceType,viewType);
        return result;
    }



    /**
     * 发稿明细统计导出Excel
     * @param startDate
     * @param endDate
     * @param sourceType
     * @param viewType
     * @return
     */
    @GetMapping("/pageList/excel")
    public void pageKpiCountExcel(HttpServletResponse response,
                                  @RequestParam (value = "startDate",required = false) String startDate,
                                  @RequestParam (value = "endDate",required = false) String endDate,
                                  @RequestParam (value = "sourceType",required = false) Integer sourceType,
                                  @RequestParam (value = "viewType") Integer viewType){
        StringBuilder excelName = new StringBuilder();
        excelName.append(startDate.split(" ")[0]);
        excelName.append("至");
        excelName.append(endDate.split(" ")[0]);
        if(1== viewType){
            excelName.append("发稿明细统计报表");
        }else{
            excelName.append("PV排序统计表");
        }
        ExcelData data = iContentKpiCountServiceFeign.pageKpiCountExcel(excelName.toString(),startDate, endDate,sourceType,viewType);
       try {
           ExportExcelUtils.exportExcel(response,excelName.append(".xls").toString(),data);
       } catch (Exception e){
           e.printStackTrace();
       }

    }



}
