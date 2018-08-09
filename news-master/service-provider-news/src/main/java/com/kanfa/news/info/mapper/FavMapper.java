package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.Fav;
import com.kanfa.news.info.vo.admin.app.AppFavInfo;
import com.kanfa.news.info.vo.admin.live.LiveInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户对内容的收藏
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:01:31
 */
public interface FavMapper extends Mapper<Fav> {

    Integer delFav(@Param("cid") Integer cid ,@Param("uid") Integer uid ,@Param("type") Integer type);

    Long selectCountNum(@Param("cid") Integer cid ,@Param("type") Integer type);

    List<Fav> getListByUid(Map<String ,Object> liveInfo);

    Fav selectByFav(Fav f);
}
