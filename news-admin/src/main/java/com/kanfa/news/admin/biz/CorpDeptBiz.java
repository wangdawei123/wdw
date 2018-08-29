package com.kanfa.news.admin.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.admin.entity.CorpDept;
import com.kanfa.news.admin.mapper.CorpDeptMapper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公司部门表
 *
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-18 12:03:22
 */
@Service
public class CorpDeptBiz extends BaseBiz<CorpDeptMapper, CorpDept> {

    public TableResultResponse<Map<String, Object>> getPage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Map<String, Object>> list = this.mapper.page(
                Integer.parseInt(params.get("level1").toString()),
                Integer.parseInt(params.get("level2").toString())
        );
        for (Map<String, Object> map : list) {
            if ((map.get("leve1") == null)) {
                map.put("leve1", map.get("leve2"));
                map.put("leve2", "");
            }
        }
        return new TableResultResponse<>(result.getTotal(), list);
    }

}