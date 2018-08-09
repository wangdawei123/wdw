package com.kanfa.news.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class SSPUtil {
    private static final String POST_URL = "https://ssp.kanfanews.com/req_ad";

    private Map<String, Object> commonParams = new HashMap<String, Object>() {{
        put("type", "api");
        put("is_mobile", 1);
    }};

    private Integer connectTimeout = 0;

    private Integer responseTimeout = 2;

    /**
     * 原生广告类型
     */
    private static final int AD_TYPE_ORG = 2;

    /**
     * 视频类型
     */
    private static final int AD_TYPE_VIDEO = 3;

    /**
     * 横幅banner
     */
    private static final int AD_TYPE_BANNER = 1;

    /**
     * 单大图模式,用文章大图模式渲染，不用原有广告大图处理
     */
    private static final int CARD_TYPE_AD_IMAGE_ONE = 2;

    /**
     * 三图模式
     */
    private static final int CARD_TYPE_AD_IMAGE_THREE = 3;

    /**
     * 视频展示类型
     */
    private static final int CARD_TYPE_AD_VIDEO = 5;

    /**
     * 广告跳转类型
     */
    private static final int AD_KANFA_TYPE = 16;

    public SSPUtil() {
    }

    public static List getPositionAdsId() {
        return getPositionAdsId("ios", "chan", 2, "");
    }

    public static List getPositionAdsId(String platform) {
        return getPositionAdsId(platform, "chan");
    }

    public static List getPositionAdsId(Integer fieldName) {
        return getPositionAdsId("chan", fieldName);
    }

    public static List getPositionAdsId(Integer fieldName, String pos) {
        return getPositionAdsId("chan", fieldName, pos);
    }

    public static List getPositionAdsId(String type, Integer fieldName, String pos) {
        return getPositionAdsId("ios", type, fieldName, pos);
    }

    public static List getPositionAdsId(String platform, String type, String pos) {
        return getPositionAdsId(platform, type, 2, pos);
    }


    public static List getPositionAdsId(String type, Integer fieldName) {
        return getPositionAdsId("ios", type, fieldName);
    }

    public static List getPositionAdsId(String platform, String type) {
        return getPositionAdsId(platform, type, "");
    }

    public static List getPositionAdsId(String platform, String type, Integer fieldName) {
        return getPositionAdsId(platform, type, fieldName, "");
    }

    public static List getPositionAdsId(String platform, String type, Integer fieldName, String pos) {
        /*$result = [];
        if(!$adconfig[$platform]){
            return [];
        }
        switch($type){
            case 'chan':
                $result = $adconfig[$platform][$fieldName];
                break;
            case 'pos':
                if($fieldName&&$pos){
                    $result = $adconfig[$platform][$fieldName][$pos];
                }
                break;
            default:
                $result = $adconfig[$platform];
                break;

        }
        return $result;*/
        return null;
    }

    /**
     * 频道广告位位置对应表
     * 平台-》频道-》位置-》广告位ID
     */
    private static final Map PLATFORM_POSITION_ADS = new HashMap(16);

    public static void main(String[] args) {
        System.out.println(PLATFORM_POSITION_ADS);
    }

}
