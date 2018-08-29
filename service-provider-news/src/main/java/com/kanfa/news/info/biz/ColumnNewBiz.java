package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.ColumnNew;
import com.kanfa.news.info.mapper.ColumnNewMapper;
import com.kanfa.news.info.vo.admin.activity.PioneerActivityPageInfo;
import com.kanfa.news.info.vo.admin.column.ColumnNewPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 栏目表
 *
 * @author chen
 * @email chen@kanfanews.com
 * @date 2018-04-25 15:17:43
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ColumnNewBiz extends BaseBiz<ColumnNewMapper,ColumnNew> {

    @Autowired
    private ColumnNewMapper columnNewMapper;

    public TableResultResponse<ColumnNewPageInfo> getColumnPage(ColumnNewPageInfo entity){
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        List<ColumnNewPageInfo> list = columnNewMapper.getColumnPage(entity);
        return new TableResultResponse<ColumnNewPageInfo>(result.getTotal(),list);
    }

}