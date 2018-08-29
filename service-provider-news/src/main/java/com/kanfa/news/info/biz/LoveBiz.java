package com.kanfa.news.info.biz;

import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.Love;
import com.kanfa.news.info.mapper.LoveMapper;
import com.kanfa.news.common.biz.BaseBiz;

import java.util.List;

/**
 * 喜欢表
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-06-14 10:54:39
 */
@Service
public class LoveBiz extends BaseBiz<LoveMapper,Love> {

    public Love getLoveOne(Love love) {
        return this.mapper.getLoveOne(love);
    }

    /**
     * 获取文章点赞集合
     * @param love
     * @return
     */
    public List<Love> getLoveList(Love love) {
        return this.mapper.getLoveList(love);
    }
}