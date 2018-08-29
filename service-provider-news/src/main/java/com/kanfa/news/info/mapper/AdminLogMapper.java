package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.AdminLog;
import com.kanfa.news.info.vo.admin.adminlog.AdminLogInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-04-03 11:36:11
 */
public interface AdminLogMapper extends Mapper<AdminLog> {

    /**
     * 分页
     * @param params
     * @return
     */
    List<AdminLogInfo> getAdminLogPage(Map<String, Object> params);
}
