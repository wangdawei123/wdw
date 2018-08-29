package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ColumnNew;
import com.kanfa.news.info.vo.admin.column.ColumnNewPageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 栏目表
 * 
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-04-25 15:17:43
 */
public interface ColumnNewMapper extends Mapper<ColumnNew> {


    List<ColumnNewPageInfo> getColumnPage(@RequestBody ColumnNewPageInfo entity);
}
