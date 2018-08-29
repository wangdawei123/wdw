package com.kanfa.news.admin.feign.fallback;

import com.kanfa.news.admin.entity.ActivityRaffle;
import com.kanfa.news.admin.feign.IActivityRaffleServiceFeign;
import com.kanfa.news.admin.vo.activity.ActivityRafflePageInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.stereotype.Service;

@Service
public class ActivityRaffleServiceFallBack implements IActivityRaffleServiceFeign {

    @Override
    public ObjectRestResponse<ActivityRaffle> add(ActivityRaffle entity) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setStatus(200);
        objectRestResponse.setMessage("服务不可用");
        objectRestResponse.setRel(true);
        return objectRestResponse;
    }



    @Override
    public ObjectRestResponse<ActivityRaffle> get(int id) {
        return null;
    }

    @Override
    public ObjectRestResponse<ActivityRaffle> update(Integer id, ActivityRaffle entity) {
        return null;
    }

    @Override
    public TableResultResponse<ActivityRafflePageInfo> getPioneerActivityPage(ActivityRafflePageInfo entity) {
        return null;
    }
}
