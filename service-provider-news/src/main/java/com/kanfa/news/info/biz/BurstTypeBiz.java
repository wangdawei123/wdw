package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.entity.BurstType;
import com.kanfa.news.info.mapper.BurstTypeMapper;
import com.kanfa.news.info.vo.admin.burst.BurstTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 新闻爆料类型表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BurstTypeBiz extends BaseBiz<BurstTypeMapper,BurstType> {

    @Autowired
    private BurstTypeMapper burstTypeMapper;

    public TableResultResponse<BurstTypeInfo> burstTypePage(Map<String, Object> params) {
        Query query=new Query(params);
        Page<BurstTypeInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<BurstTypeInfo> list = burstTypeMapper.getPage(params);
        return new TableResultResponse<>(result.getTotal(), list);
    }

    /**
     * 查询全部
     * @param burstType
     * @return
     */
    public List<BurstType> burstTypeListAll(BurstType burstType) {
        return burstTypeMapper.burstTypeListAll(burstType);
    }
}