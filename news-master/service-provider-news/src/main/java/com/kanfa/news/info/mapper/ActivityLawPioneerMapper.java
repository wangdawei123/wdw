package com.kanfa.news.info.mapper;

import com.kanfa.news.info.entity.ActivityLawPioneer;
import com.kanfa.news.info.vo.admin.activity.ActivityLawPioneerPageInfo;
import com.kanfa.news.info.vo.admin.info.ActivityLawPioneerInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-26 17:22:05
 */
public interface ActivityLawPioneerMapper extends Mapper<ActivityLawPioneer> {

    /**
     * 查询政法先锋机构或个人
     * @param entity
     * @return
     */
    List<ActivityLawPioneerInfo> getList(@Param("entity") ActivityLawPioneerInfo entity);


    /**
     * app -- 查询青春微普法机构或个人
     * @return
     */
    List<ActivityLawPioneer> getLawList();

    /**
     * 机构或个人
     * @param
     * @return
     */

    List<ActivityLawPioneerPageInfo> getActivityLawPioneerPage(ActivityLawPioneerPageInfo entity);

    /**
     * 机构或个人 通过activityLawId 查找最大值
     * @param
     * @return
     */
    Integer getMaxSort(@Param("activityLawId")Integer activityLawId);

}
