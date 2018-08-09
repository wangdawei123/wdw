package com.kanfa.news.info.rest;

import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.UserActivityCouponBiz;
import com.kanfa.news.info.entity.UserActivityCoupon;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.vo.admin.activity.UserActivityCouponInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("userActivityCoupon")
public class UserActivityCouponController extends BaseController<UserActivityCouponBiz,UserActivityCoupon> {


    @Autowired
    private UserActivityCouponBiz userActivityCouponBiz;


    @RequestMapping(value = "/getUserActivityCouponpage",method = RequestMethod.POST)
    TableResultResponse<UserActivityCouponInfo> getUserActivityCouponpage(@RequestBody UserActivityCouponInfo entity){
        return  userActivityCouponBiz.getUserActivityCouponpage(entity);
    }


    /**
     * 优惠券 导出Excel
     * @param
     * @return
     */
    @RequestMapping(value = "/excel",method = RequestMethod.POST)
    public ExcelData userActivityCouponExcel(@RequestBody UserActivityCouponInfo entity){
        ExcelData data = new ExcelData();
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("商家名称");
        titles.add("券码名称");
        titles.add("券码号");
        titles.add("发出时间");
        titles.add("领取人");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<UserActivityCouponInfo> list = userActivityCouponBiz.getList(entity);
        for (UserActivityCouponInfo userActivityCoupon:list) {
            data.setName(userActivityCoupon.getCouponCodeName()+"领取情况表");
            List<Object> row = new ArrayList();
            //NumberFormat nf = new DecimalFormat("0.#");
            //id
            row.add(userActivityCoupon.getId());
            //商家名称 activityMerchant
            row.add(userActivityCoupon.getActivityMerchant());
            //券码名称 coupon_code_name
            row.add(userActivityCoupon.getCouponCodeName());
            //券码号 activity_coupon_code
            row.add(userActivityCoupon.getActivityCouponCode());
            //发出时间 createTime
            row.add(userActivityCoupon.getCreateTime());
            //领取人 userName
            row.add(userActivityCoupon.getUserName());
            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }

    @RequestMapping(value = "/excel1",method = RequestMethod.POST)
    public ExcelData activityCouponExcel(@RequestBody UserActivityCouponInfo entity){
        ExcelData data = new ExcelData();
        data.setName("领取情况表");
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("商家名称");
        titles.add("券码名称");
        titles.add("券码号");
        titles.add("发出时间");
        titles.add("领取人");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        List<UserActivityCouponInfo> list = userActivityCouponBiz.getList(entity);
        for (UserActivityCouponInfo userActivityCoupon:list) {
            List<Object> row = new ArrayList();
            //NumberFormat nf = new DecimalFormat("0.#");
            //id
            row.add(userActivityCoupon.getId());
            //商家名称 activityMerchant
            row.add(userActivityCoupon.getActivityMerchant());
            //券码名称 coupon_code_name
            row.add(userActivityCoupon.getCouponCodeName());
            //券码号 activity_coupon_code
            row.add(userActivityCoupon.getActivityCouponCode());
            //发出时间 createTime
            row.add(userActivityCoupon.getCreateTime());
            //领取人 userName
            row.add(userActivityCoupon.getUserName());
            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }
}