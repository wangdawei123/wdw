package com.kanfa.news.app.mapper;


import com.kanfa.news.app.entity.Adv;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 广告
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-05-30 13:54:21
 */
public interface AdvMapper extends Mapper<Adv> {

    /**
     *  app首页开屏广告
     *  @return
     */
    List<Adv> getStartUpAdv();

}
