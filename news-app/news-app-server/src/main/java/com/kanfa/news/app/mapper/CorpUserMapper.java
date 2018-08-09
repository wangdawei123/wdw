package com.kanfa.news.app.mapper;

import com.kanfa.news.app.entity.CorpUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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

}
