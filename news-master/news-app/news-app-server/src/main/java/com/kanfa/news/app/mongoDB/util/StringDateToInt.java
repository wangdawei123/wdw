package com.kanfa.news.app.mongoDB.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringDateToInt {

    public static Integer transForMilliSecondByTim(String dateStr,String tim){
        SimpleDateFormat sdf=new SimpleDateFormat(tim);
        Date date =null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date == null ? null : transForMilliSecond(date);
    }

    /**
     * 日期转时间戳
     * @param date
     * @return
     */
    public  static Integer transForMilliSecond(Date date){
        if(date==null){ return null;}
        return (int)(date.getTime()/1000);
    }

    /**
     * 时间戳转日期时间
     * @param date
     * @return
     */
    public static String TimeStamp2DateTime(String timestampString){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return date;
    }
    /**
     * 时间戳转日期
     * @param date
     * @return
     */
    public static String TimeStamp2Date(String timestampString){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp));
        return date;
    }
}
