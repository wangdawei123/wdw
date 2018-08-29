package com.kanfa.news.admin.mapper;

import com.kanfa.news.admin.entity.CorpUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 公司人员表
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-12 14:22:33
 */
public interface CorpUserMapper extends Mapper<CorpUser> {

    //我的里边视频上传所需要的记者Id name字典表
    List<CorpUser> getCorpUserIdName();

    //同上
    List<CorpUser> selectCropUserIdName();

    List<Map<String, Object>> search(
            @Param("name") String name,
            @Param("level1") Integer level1,
            @Param("level2") Integer level2,
            @Param("corpJobId") Integer corpJobId,
            @Param("status") Integer status
    );

}
