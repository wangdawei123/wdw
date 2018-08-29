package com.kanfa.news.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 */
@Service
public interface AdvService {

    /**
     * 获取广告位ID
     * @return List
     */
    default List getPositionAdsId() {
        return getPositionAdsId("ios", "chan", 2, "");
    }

    /**
     * 获取广告位ID
     *
     * @param type
     * @param fieldName
     * @param pos
     * @return
     */
    default List getPositionAdsId(String type, Integer fieldName, String pos) {
        return getPositionAdsId("ios", type, fieldName, pos);
    }

    /**
     * 获取广告位ID
     *
     * @param platform
     * @param type
     * @param pos
     * @return
     */
    default List getPositionAdsId(String platform, String type, String pos) {
        return getPositionAdsId(platform, type, 2, pos);
    }

    /**
     * 获取广告位ID
     *
     * @param platform
     * @param type
     * @param fieldName
     * @return
     */
    default List getPositionAdsId(String platform, String type, Integer fieldName) {
        return getPositionAdsId(platform, type, fieldName, "");
    }

    /**
     * 获取广告位ID
     *
     * @param platform  平台
     * @param type      频道/位置chan/pos
     * @param fieldName 频道ID或其他广告键值
     * @param pos       位置键值
     * @return
     */
    List getPositionAdsId(String platform, String type, Integer fieldName, String pos);

    /**
     * 获取广告数据
     *
     * @return
     */
    default List getAdvData() {
        return getAdvData("ios", "chan", 2, "", null, RequestMethod.POST.name());
    }

    /**
     * 获取广告数据
     *
     * @param type   类型
     * @param chanId 频道ID
     * @param pos    位置ID只有用位置请求时此处有效
     * @param params 客户端带回来的请求数据，附加再请求post数据里
     * @param method 请求方法
     * @return
     */
    default List getAdvData(String type, Integer chanId, String pos, Map<String, Object> params, String method) {
        return getAdvData("ios", type, chanId, pos, params, method);
    }


    /**
     * 获取广告数据
     *
     * @param platform 平台
     * @param type     类型
     * @param pos      位置ID只有用位置请求时此处有效
     * @param params   客户端带回来的请求数据，附加再请求post数据里
     * @param method   请求方法
     * @return
     */
    default List getAdvData(String platform, String type, String pos, Map<String, Object> params, String method) {
        return getAdvData(platform, type, 2, pos, params, method);
    }

    /**
     * 获取广告数据
     *
     * @param platform 平台
     * @param type     类型
     * @param chanId   频道ID
     * @param params   客户端带回来的请求数据，附加再请求post数据里
     * @param method   请求方法
     * @return
     */
    default List getAdvData(String platform, String type, Integer chanId, Map<String, Object> params, String method) {
        return getAdvData(platform, type, chanId, "", params, method);
    }

    /**
     * 获取广告数据
     *
     * @param platform 平台
     * @param type     类型
     * @param chanId   频道ID
     * @param pos      位置ID只有用位置请求时此处有效
     * @param method   请求方法
     * @return
     */
    default List getAdvData(String platform, String type, Integer chanId, String pos, String method) {
        return getAdvData(platform, type, chanId, pos, null, method);
    }

    /**
     * 获取广告数据
     *
     * @param platform 平台
     * @param type     类型
     * @param chanId   频道ID
     * @param pos      位置ID只有用位置请求时此处有效
     * @param params   客户端带回来的请求数据，附加再请求post数据里
     * @return
     */
    default List getAdvData(String platform, String type, Integer chanId, String pos, Map<String, Object> params) {
        return getAdvData(platform, type, chanId, pos, params, RequestMethod.POST.name());
    }

    /**
     * 获取广告数据
     *
     * @param platform 平台
     * @param type     类型
     * @param chanId   频道ID
     * @param pos      位置ID只有用位置请求时此处有效
     * @param params   客户端带回来的请求数据，附加再请求post数据里
     * @param method   请求方法
     * @return
     */
    List getAdvData(String platform, String type, Integer chanId, String pos, Map<String, Object> params, String method);
}
