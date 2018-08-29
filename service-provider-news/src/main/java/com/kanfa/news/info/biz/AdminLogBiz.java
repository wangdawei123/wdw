package com.kanfa.news.info.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.AddressUtils;
import com.kanfa.news.common.util.DateHandler;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.entity.AdminLog;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.mapper.AdminLogMapper;
import com.kanfa.news.info.vo.admin.adminlog.AdminLogInfo;
import com.kanfa.news.info.vo.admin.kpicount.OperationExportDataInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-03 11:36:11
 */
@Service
public class AdminLogBiz extends BaseBiz<AdminLogMapper,AdminLog> {

    @Autowired
    private AdminLogMapper adminLogMapper;

    /**
     * 分页
     * @param params
     * @return
     */
    public TableResultResponse<AdminLogInfo> getAdminLogPage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<AdminLogInfo> adminLogList = adminLogMapper.getAdminLogPage(params);
        //检测重复ip
        Set<String> set = new HashSet<>();
        for (AdminLogInfo adminLog : adminLogList) {
            set.add(adminLog.getIp());
        }
        Map<String,String> ipMap = new HashMap<>(16);
        for (String ip : set) {
            String addresses = null;
            try {
                addresses = AddressUtils.getAddresses("ip="+ip, "utf-8");
                if(StringUtils.isNotEmpty(addresses)){
                    JSONObject jsonObject = JSON.parseObject(addresses);
                    Map data = JSON.parseObject(jsonObject.getString("data"), Map.class);
                    ipMap.put(ip,ip+"("+data.get("region")+")");
                }else{
                    ipMap.put(ip,ip+"(局域网)");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        for (AdminLogInfo adminLog : adminLogList) {
            adminLog.setActionUrl(adminLog.getActionUrl()+ "[" +NewsEnumsConsts.MessageOfTargetType.getType(adminLog.getType())+"]");
            try {
                adminLog.setTime(DateHandler.longToDate(Long.valueOf(adminLog.getCreateTime()*1000l), "yyyy-MM-dd HH:mm:ss"));
                adminLog.setIp(ipMap.get(adminLog.getIp()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new TableResultResponse(result.getTotal(),adminLogList);
    }
    /**
     * 导出Excel
     * @param params
     * @return
     */
    public ExcelData getExcelData(Map<String, Object> params) {
        List<AdminLogInfo> adminLogList = adminLogMapper.getAdminLogPage(params);
        //检测重复ip
        Set<String> set = new HashSet<>();
        for (AdminLogInfo adminLog : adminLogList) {
            set.add(adminLog.getIp());
        }
        Map<String,String> ipMap = new HashMap<>(16);
        for (String ip : set) {
            String addresses = null;
            try {
                addresses = AddressUtils.getAddresses("ip="+ip, "utf-8");
                if(StringUtils.isNotEmpty(addresses)){
                    JSONObject jsonObject = JSON.parseObject(addresses);
                    Map data = JSON.parseObject(jsonObject.getString("data"), Map.class);
                    ipMap.put(ip,ip+"("+data.get("region")+")");
                }else{
                    ipMap.put(ip,ip+"(局域网)");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        for (AdminLogInfo adminLog : adminLogList) {
            adminLog.setActionUrl(adminLog.getActionUrl()+ "[" +NewsEnumsConsts.MessageOfTargetType.getType(adminLog.getType())+"]");
            try {
                adminLog.setTime(DateHandler.longToDate(Long.valueOf(adminLog.getCreateTime()*1000l), "yyyy-MM-dd HH:mm:ss"));
                adminLog.setIp(ipMap.get(adminLog.getIp()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ExcelData data = new ExcelData();
        data.setName(params.get("excelName").toString());
        List<String> titles = new ArrayList();
        titles.add("操作说明");
        titles.add("操作URL");
        titles.add("操作IP");
        titles.add("操作人");
        titles.add("创建时间");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList();
        for (AdminLogInfo adminLogInfo:adminLogList) {
            List<Object> row = new ArrayList();
            row.add(adminLogInfo.getAction());
            row.add(adminLogInfo.getActionUrl());
            row.add(adminLogInfo.getIp());
            row.add(adminLogInfo.getCreateUser());
            row.add(adminLogInfo.getTime());
            rows.add(row);
        }
        data.setRows(rows);
        return data;
    }
}