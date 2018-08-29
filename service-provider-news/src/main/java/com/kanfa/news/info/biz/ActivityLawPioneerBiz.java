package com.kanfa.news.info.biz;

import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.info.entity.ActivityContentBind;
import com.kanfa.news.info.mapper.ActivityContentBindMapper;
import com.kanfa.news.info.mapper.ContentMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.vo.admin.activity.ActivityLawPioneerPageInfo;
import com.kanfa.news.info.vo.admin.info.ActivityLawPioneerInfo;
import com.kanfa.news.info.vo.admin.info.ContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.ActivityLawPioneer;
import com.kanfa.news.info.mapper.ActivityLawPioneerMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 政法先锋机构或个人
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-03-26 17:22:05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityLawPioneerBiz extends BaseBiz<ActivityLawPioneerMapper,ActivityLawPioneer> {

    @Autowired
    private ActivityLawPioneerMapper activityLawPioneerMapper;
    @Autowired
    private ActivityContentBindMapper activityContentBindMapper;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentBiz contentBiz;

    /**
     * 查询政法先锋机构或个人
     * @param entity
     * @return
     */
    public List<ActivityLawPioneerInfo> getList(ActivityLawPioneerInfo entity) {
        return activityLawPioneerMapper.getList(entity);
    }

    /**
     * app -- 查询青春微普法机构或个人
     * @return
     */
    public List<ActivityLawPioneer> getLawList() {
        return activityLawPioneerMapper.getLawList();
    }

    /**
     * 获取绑定相关内容
     * @return
     */
    public List<Map<String ,Object>> getBindContent(Integer lawId ,Integer page ,Integer pcount){
        Integer offset = (page - LiveCommonEnum.CONSTANT_ONE)* pcount;
        List<ActivityContentBind> bindList = activityContentBindMapper.findByid(lawId ,offset ,pcount);
        if(bindList.size() == LiveCommonEnum.CONSTANT_ZERO){
            return null;
        }
        List<Integer> ids = new ArrayList<>();
        Map<Integer ,Integer> bindMap = new HashMap<>(5);
        for(ActivityContentBind bind : bindList){
            bindMap.put(bind.getContentId() ,bind.getOrderNumber());
            ids.add(bind.getContentId());
        }

        List<ContentInfo> contents = contentMapper.selectByIds(ids);
        for(ContentInfo content :contents){
            Integer orderNum = bindMap.get(content.getId());
            if(orderNum != null){
                content.setOrderNumber(orderNum);
            }
        }
        List<Map<String ,Object>> contentList = null;
        if(contents.size() > 0){
            //将不同类型的资讯的内容补全
            contentList = contentBiz.getListTypeData(contents);
        }
        return contentList;
    }

    /**
     * 政法先锋-->机构或个人
     * @param entity
     * @return
     */
    public TableResultResponse<ActivityLawPioneerPageInfo> getActivityLawPioneerPage(ActivityLawPioneerPageInfo entity){
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //政法先锋
        entity.setIsDelete(1);
        List<ActivityLawPioneerPageInfo> pioneerActivityPage = activityLawPioneerMapper.getActivityLawPioneerPage(entity);
        for (ActivityLawPioneerPageInfo en:pioneerActivityPage) {
            if (en.getSort()==null){
                en.setSort(0);
            }
        }
        return new TableResultResponse<ActivityLawPioneerPageInfo>(result.getTotal(),pioneerActivityPage);
    }


    public Integer getMaxSort(Integer activityLawId){
        return  activityLawPioneerMapper.getMaxSort(activityLawId);
    }


}