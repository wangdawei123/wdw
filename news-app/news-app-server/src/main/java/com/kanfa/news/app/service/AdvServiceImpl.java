package com.kanfa.news.app.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 */
public class AdvServiceImpl implements AdvService {
    @Value("${app.ad.ssp.postUrl}")
    private String postUrl;

    @Value("${app.ad.ssp.connectTimeout}")
    private Integer connectTimeout;

    @Value("${app.ad.ssp.responseTimeout}")
    private Integer responseTimeout;

    @Value("${app.ad.ssp.params.type}")
    private Integer type;

    @Value("${app.ad.ssp.params.isMobile}")
    private Boolean isMobile;

    @Override
    public List getPositionAdsId(String platform, String type, Integer fieldName, String pos) {
        switch (type) {
            case "chan":
                break;
            case "pos":
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public List getAdvData(String platform, String type, Integer chanId, String pos, Map<String, Object> params, String method) {
        List posArr = this.getPositionAdsId(platform, type, chanId, pos);
        this.getPositionAdsId(platform, type, chanId, pos);
        if (posArr == null) {
            return new ArrayList();
        }
        if ("chan".equals(type)) {
            posArr.forEach((Object v) -> {
                if ("ios".equals(platform)) {
                    //ios流量源
                    params.put("app_id", 3);
                } else {
                    //安卓流量源
                    params.put("app_id", 4);
                }

            });
        } else {
        }
        return null;
    }
}
