package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Adv;
import com.kanfa.news.info.vo.admin.adv.AdvInfo;
import com.kanfa.news.info.vo.admin.adv.AdvOnlineNumInfo;
import org.apache.ibatis.annotations.Param;
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

    int addAdv(AdvInfo advInfo);

    List<AdvInfo> getPage(@Param("advInfo") AdvInfo advInfo);

    AdvOnlineNumInfo getOnlineNum();

    List<Adv> getAdvInfoByIds(@Param("oldIds") List<Integer> oldIds);
    /**
     *  app首页开屏广告
     *  @return
     */
    List<Adv> getStartUpAdv();

}
