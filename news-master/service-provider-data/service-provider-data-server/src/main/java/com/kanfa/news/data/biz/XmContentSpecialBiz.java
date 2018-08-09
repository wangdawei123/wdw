package com.kanfa.news.data.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.data.entity.XmContentSpecial;
import com.kanfa.news.data.mapper.XmContentSpecialMapper;
import com.kanfa.news.common.biz.BaseBiz;

/**
 * 内容附表-专题。一对多
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-08-07 11:28:32
 */
@Service
public class XmContentSpecialBiz extends BaseBiz<XmContentSpecialMapper,XmContentSpecial> {
}