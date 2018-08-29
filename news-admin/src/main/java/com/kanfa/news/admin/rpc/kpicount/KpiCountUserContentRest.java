package com.kanfa.news.admin.rpc.kpicount;

import com.kanfa.news.admin.feign.IBaseUserServiceFeign;
import com.kanfa.news.admin.feign.kpicount.IKpiCountUserContentServiceFeign;
import com.kanfa.news.admin.vo.AdminUserInfo;
import com.kanfa.news.admin.vo.kpicount.KpiCountUserContentInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/17 11:57
 */
@RestController
@RequestMapping("kpiCountUserContent")
public class KpiCountUserContentRest {

    @Autowired
    private IBaseUserServiceFeign baseUserServiceFeign;
    @Autowired
    private IKpiCountUserContentServiceFeign iKpiCountUserContentServiceFeign;

    /**
     * 人员发布统计列表
     * @param page
     * @param limit
     * @param startDate
     * @param endDate
     * @param dayShow
     * @param uid
     * @param sourceType
     * @param dutyType
     * @return
     */
    @GetMapping("/pageList")
    public TableResultResponse<KpiCountUserContentInfo> get(@RequestParam (value = "page",required = false) Integer page,
                                                            @RequestParam (value = "limit",required = false) Integer limit ,
                                                            @RequestParam (value = "startDate",required = false) String startDate,
                                                            @RequestParam (value = "endDate",required = false) String endDate,
                                                            @RequestParam (value = "dayShow",required = false) Integer dayShow,
                                                            @RequestParam (value = "uid",required = false) Integer uid,
                                                            @RequestParam (value = "sourceType",required = false) Integer sourceType,
                                                            @RequestParam (value = "dutyType",required = false) Integer dutyType) {

        TableResultResponse<KpiCountUserContentInfo> result = iKpiCountUserContentServiceFeign.getPageList(page,limit,startDate,endDate,dayShow,uid,sourceType,dutyType);
        return result;

    }

    @GetMapping(value = "/getUserByCid")
    public ObjectRestResponse<List<AdminUserInfo>> getUserByCid() {
        // 1.编辑人加上选中的人@PathVariable("id") Integer id
        //所有编辑人
        List<AdminUserInfo> data = baseUserServiceFeign.getAllBaseUser();
        //文章对应的编辑人
        ObjectRestResponse<List<AdminUserInfo>> baseUserResp = new ObjectRestResponse<>();
        baseUserResp.setData(data);
        return baseUserResp;
    }


    /**
     * 人员发布统计导出Excel
     * @param excelName
     * @param startDate
     * @param endDate
     * @param dayShow
     * @param uid
     * @param sourceType
     * @param dutyType
     * @return
     */
    @GetMapping("/pageList/excel")
    public void pageKpiCountExcel(HttpServletResponse response,
                                  @RequestParam (value = "startDate",required = false) String startDate,
                                  @RequestParam (value = "endDate",required = false) String endDate,
                                  @RequestParam (value = "dayShow",required = false) Integer dayShow,
                                  @RequestParam (value = "uid",required = false) Integer uid,
                                  @RequestParam (value = "sourceType",required = false) Integer sourceType,
                                  @RequestParam (value = "dutyType",required = false) Integer dutyType){
        StringBuilder excelName = new StringBuilder();
        excelName.append("人员发布统计");
        ExcelData data = iKpiCountUserContentServiceFeign.pageKpiCountExcel(excelName.toString(),startDate, endDate,dayShow,uid,sourceType,dutyType);
       try {
           ExportExcelUtils.exportExcel(response,excelName.append(".xls").toString(),data);
       } catch (Exception e){
           e.printStackTrace();
       }

    }



}
