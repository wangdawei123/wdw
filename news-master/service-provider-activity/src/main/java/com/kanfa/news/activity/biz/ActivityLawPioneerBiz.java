package com.kanfa.news.activity.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.activity.entity.ActivityContentBind;
import com.kanfa.news.activity.entity.ActivityLawPioneer;
import com.kanfa.news.activity.mapper.ActivityContentBindMapper;
import com.kanfa.news.activity.mapper.ActivityLawPioneerMapper;
import com.kanfa.news.activity.mapper.ContentMapper;
import com.kanfa.news.activity.vo.info.ActivityLawPioneerInfo;
import com.kanfa.news.activity.vo.info.ContentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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



}