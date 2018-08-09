package com.kanfa.news.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/6/15 10:32
 */
public class UrlAddParamUtil {
    /**
     * 拼接get请求的url请求地址
     */
    public static String getRqstUrl(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url);
        boolean isFirst = true;
        for (String key : params.keySet()) {
            if (key != null && params.get(key) != null) {
                if (isFirst) {
                    isFirst = false;
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(key)
                        .append("=")
                        .append(params.get(key));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        //http://dev.vrcdkj.cn/admin/news/index?chan_id=2&cate=1
        String url="http://dev.vrcdkj.cn/admin/news/index";
        Map<String, String> param = new HashMap<>(16);
        param.put("chan_id","2");
        param.put("cate","1");
        String rqstUrl = getRqstUrl(url, param);
        System.out.println(rqstUrl);
    }
}
