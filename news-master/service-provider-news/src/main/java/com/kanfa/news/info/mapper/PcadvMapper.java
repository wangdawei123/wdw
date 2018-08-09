package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Pcadv;
import com.kanfa.news.info.vo.admin.adv.AdvOnlineNumInfo;
import com.kanfa.news.info.vo.admin.pcadv.PcadvInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 广告
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-05-30 18:51:24
 */
public interface PcadvMapper extends Mapper<Pcadv> {

    Integer addPcAdv(PcadvInfo pcadvInfo);

    List<PcadvInfo> getPcAdvPage(Map<String, Object> param);

    AdvOnlineNumInfo getOnlineNumPcAdv();

    List<Pcadv> getPcadvInfoByIds(@Param("oldIds") List<Integer> oldIds);
}
