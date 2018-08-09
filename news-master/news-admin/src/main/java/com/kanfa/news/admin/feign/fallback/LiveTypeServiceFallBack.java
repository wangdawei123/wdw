package com.kanfa.news.admin.feign.fallback;

import com.kanfa.news.admin.entity.LiveType;
import com.kanfa.news.admin.feign.ILiveTypeServiceFeign;
import com.kanfa.news.admin.vo.live.LiveTypeInfo;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LiveTypeServiceFallBack implements ILiveTypeServiceFeign {
    @Override
    public ObjectRestResponse<LiveType> add(LiveType entity) {
        return null;
    }

    @Override
    public ObjectRestResponse<LiveType> get(int id) {
        return null;
    }

    @Override
    public ObjectRestResponse<LiveType> update(Integer id, LiveType entity) {
        return null;
    }

    @Override
    public TableResultResponse<LiveTypeInfo> list(Map<String, Object> params) {
        TableResultResponse tableResultResponse = new TableResultResponse();
        tableResultResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        tableResultResponse.setMessage(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
        return tableResultResponse;
    }

    @Override
    public ObjectRestResponse<LiveType> remove(int id) {
        return null;
    }

    @Override
    public List<LiveType> all() {
        return null;
    }



}
