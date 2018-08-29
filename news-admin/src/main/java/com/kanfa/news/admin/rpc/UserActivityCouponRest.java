package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.feign.IUserActivityCouponServiceFeign;
import com.kanfa.news.admin.vo.activity.UserActivityCouponInfo;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.excel.ExportExcelUtils;
import com.kanfa.news.common.util.excel.entity.ExcelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Chen
 * on 2018/4/23 17:57
 */

@RestController
@RequestMapping("userActivityCoupon")
public class UserActivityCouponRest {

    @Autowired
    private IUserActivityCouponServiceFeign userActivityCouponServiceFeign;

    @RequestMapping(value = "/getUserActivityCouponpage",method = RequestMethod.POST)
    public TableResultResponse<UserActivityCouponInfo> getUserActivityCouponpage(@RequestBody UserActivityCouponInfo entity){
        return  userActivityCouponServiceFeign.getUserActivityCouponpage(entity);
    }

    @RequestMapping(value = "/userActivityCouponExcel",method = RequestMethod.POST)
    public void getUserActivityCouponExcel(HttpServletResponse response,
                                           @RequestBody UserActivityCouponInfo entity){
        ExcelData data = userActivityCouponServiceFeign.getUserActivityCouponExcel(entity);
        StringBuilder excelName = new StringBuilder();
        excelName.append(data.getTitles());
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/activityCouponExcel",method = RequestMethod.POST)
    public void getActivityCouponExcel(HttpServletResponse response,
                                       @RequestBody UserActivityCouponInfo entity){
        ExcelData data = userActivityCouponServiceFeign.getActivityCouponExcel(entity);
        StringBuilder excelName = new StringBuilder();
        excelName.append(data.getTitles());
        try {
            ExportExcelUtils.exportExcel(response,excelName.append(".xlsx").toString(),data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
