package com.kanfa.news.common.util;

/**
 * Created by kanfa on 2018/4/17.
 */
public class CovertLiveNum {

    /**
     * 将直播数转化成特定格式]
     * @param num
     * @return
     */
    public static String change(Integer num){
        if(num<=0){
            return "";
        }else if(num<=9999){
            return num + "人";
        }else if(num < 11000){
            return "1万人";
        }else{
            double floor = Math.floor(num/1000)/10;
            return floor + "万人";
        }
    }

}
