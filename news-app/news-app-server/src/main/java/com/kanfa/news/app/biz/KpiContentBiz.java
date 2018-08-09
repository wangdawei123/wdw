package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.KpiContent;
import com.kanfa.news.app.mapper.KpiContentMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 内容绩效表
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-09 17:42:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KpiContentBiz extends BaseBiz<KpiContentMapper,KpiContent> {
}