package com.kanfa.news.common.util;

/**
 * Created by kanfa on 2018/4/20.
 */
public class CommentViewUtil {

    public static String getCommentViewDisplay(String type ,Integer num){
        if(("view").equals(type)) {
            // 阅读量
            // 小于1000直接显示
            if (num < 1000) {
                return num+"";
            }
            // 小于10000转换单位为k+
            if (num < 10000) {
                return Math.floor(num / 1000) + "k+";
            }
            // 大于等于10000的转换单位为w+
            if (num >= 10000) {
                return Math.floor(num / 10000) + "w+";
            }
        } else { // 评论量
            // 小于100直接显示
            if (num < 100) {
                return num+"";
            }
            return "99+";
        }
        return "";
    }

}
