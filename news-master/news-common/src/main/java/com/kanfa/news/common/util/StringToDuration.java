package com.kanfa.news.common.util;

/**
 * Created by kanfa on 2018/3/29.
 * 把数字转换为x分x秒
 */
public class StringToDuration {

    /**
     * 时长转为时、分、秒格式
     */
    public static String  changeToFormat(String str){
        int seconds = Integer.parseInt(str);
        int temp=0;
        StringBuffer sb=new StringBuffer();
        temp = seconds/3600;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=seconds%3600/60;
        sb.append((temp<10)?"0"+temp+":":""+temp+":");

        temp=seconds%3600%60;
        sb.append((temp<10)?"0"+temp:""+temp);

        return sb.toString();
    }

}
