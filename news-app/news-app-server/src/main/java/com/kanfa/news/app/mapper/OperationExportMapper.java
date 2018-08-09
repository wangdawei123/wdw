package com.kanfa.news.app.mapper;

import com.kanfa.news.app.vo.admin.kpicount.OperationExportDataInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-28 14:33:33
 */
public interface OperationExportMapper  {
	/**
	 * 根据日期段，获取运维数据列表
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<OperationExportDataInfo> list(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
