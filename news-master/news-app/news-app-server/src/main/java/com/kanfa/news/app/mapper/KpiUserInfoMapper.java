package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.KpiCount;
import com.kanfa.news.app.vo.admin.kpicount.KpiUserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-14 11:46:04
 */
public interface KpiUserInfoMapper extends Mapper<KpiCount> {

    /**
     * 根据部门ID获取人员列表
     * @param departmentId
     * @return
     */
    List<KpiUserInfo> listUserInfo(@Param("departmentId") Integer departmentId);

}
