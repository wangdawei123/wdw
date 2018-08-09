package com.kanfa.news.common.util;

import org.apache.http.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 2018/3/20.
 */
public class DateUtil {

    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;

    public static SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public  static String  between(Date begin,Date end){
        try{

            //除以1000是为了转换成秒
            long between=(end.getTime()-begin.getTime())/1000;

            long day1=between/(24*3600);

            long hour1=between%(24*3600)/3600;

            long minute1=between%3600/60;

            long second1=between%60/60;

            System.out.println(""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒");
            return ""+day1+"天"+hour1+"小时"+minute1+"分"+second1+"秒";
        }catch (Exception e){

        }
        return null;
    }

    /**
        获取当天的0点时间
     */
    public static Date todayFirstDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天的23点59分59秒
     */
    public static Date todayLastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取明天天的23点59分59秒
     */
    public static Date tomorrowLastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 47);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取后天天的23点59分59秒
     */
    public static Date tomorrow2LastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 71);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    public static Calendar calendar = Calendar.getInstance();

    /**
     *
     * @return yyyy-mm-dd
     *  2012-12-25
     */
    public static String getDate() {
        return getYear() + "-" + getMonth() + "-" + getDay();
    }

    /**
     * @param format
     * @return
     * yyyy年MM月dd HH:mm
     * MM-dd HH:mm 2012-12-25
     *
     */
    public static String getDate(String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        return simple.format(calendar.getTime());
    }

    /**
     * @return yyyy-MM-dd HH:mm
     * 2012-12-29 23:47
     */
    public static String getDateAndMinute() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simple.format(calendar.getTime());
    }

    /**
     * @return
     *  yyyy-MM-dd HH:mm:ss
     *  2012-12-29 23:47:36
     */
    public static String getFullDate() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simple.format(calendar.getTime());
    }

    /**
     * 距离今天多久之前
     * @param date
     * @return
     *
     */
    public static String fromTodayFormat(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long time = date.getTime() / 1000;
        long now = System.currentTimeMillis() / 1000;
        long ago = now - time;
        if (ago <= ONE_MINUTE) {
            return "刚刚";
        } else if (ago <= ONE_HOUR) {
            return ago / ONE_MINUTE + "分钟前";
        } else if (ago <= ONE_DAY) {
            return ago / ONE_HOUR + "小时前";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            return month + "个月前";
        } else {
            long year = ago / ONE_YEAR;
            return year + "年前";
        }
    }

    /**
     * 距离今天多久之前
     * @param date
     * @return
     *
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = System.currentTimeMillis() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR){
            return ago / ONE_MINUTE + "分钟前";
        }else if (ago <= ONE_DAY) {
            return ago / ONE_HOUR + "小时"
                   + (ago % ONE_HOUR / ONE_MINUTE)
                    + "分钟前"
                   + "前";
        }else if (ago <= ONE_DAY * 2){
            return "昨天"
                    + calendar.get(Calendar.HOUR_OF_DAY)
                    + "点"
                    + calendar.get(Calendar.MINUTE) + "分"
                    ;
        }else if (ago <= ONE_DAY * 3){
            return "前天"
                    + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分"
                    ;
        }else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分前";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月" + day + "天"
                    + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分前";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;
            // JANUARY which is 0 so month+1
            return year + "年" + month + "月" + calendar.get(Calendar.DATE)
                    + "日前";
        }

    }

    /**
     * 距离某日期还有多长时间
     *
     * @param date
     * @return
     */
    public static String fromDeadline(Date date) {
        long deadline = date.getTime() / 1000;
        long now = (System.currentTimeMillis()) / 1000;
        long remain = deadline - now;
        if (remain <= ONE_HOUR) {
            return  remain / ONE_MINUTE + "分钟后开始";
        }else if (remain <= ONE_DAY) {
            return remain / ONE_HOUR + "小时"
                    + (remain % ONE_HOUR / ONE_MINUTE) + "分钟后开始";
        }else {
            long day = remain / ONE_DAY;
            long hour = remain % ONE_DAY / ONE_HOUR;
            long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return  day + "天" + hour + "小时" + minute + "分钟后开始";
        }

    }

    /**
     * 距离今天的绝对时间
     *
     * @param date
     * @return
     */
    public static String toToday(Date date) {
        long time = date.getTime() / 1000;
        long now = (System.currentTimeMillis()) / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR) {
            return ago / ONE_MINUTE + "分钟";
        }else if (ago <= ONE_DAY) {
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";
        }else if (ago <= ONE_DAY * 2) {
            return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY)
                    % ONE_HOUR / ONE_MINUTE + "分";
        }else if (ago <= ONE_DAY * 3) {
            long hour = ago - ONE_DAY * 2;
            return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE
                    + "分";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            long hour = ago % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return day + "天前" + hour + "点" + minute + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;
            long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return month + "个月" + day + "天" + hour + "点" + minute + "分前";
        } else {
            long year = ago / ONE_YEAR;
            long month = ago % ONE_YEAR / ONE_MONTH;
            long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;
            return year + "年前" + month + "月" + day + "天";
        }

    }

    public static String getYear() {
        return calendar.get(Calendar.YEAR) + "";
    }

    public static String getMonth() {
        int month = calendar.get(Calendar.MONTH) + 1;
        return month + "";
    }

    public static String getDay() {
        return calendar.get(Calendar.DATE) + "";
    }

    public static String get24Hour() {
        return calendar.get(Calendar.HOUR_OF_DAY) + "";
    }

    public static String getMinute() {
        return calendar.get(Calendar.MINUTE) + "";
    }

    public static String getSecond() {
        return calendar.get(Calendar.SECOND) + "";
    }

   public static void main(String[] args) throws ParseException {
       Date date = DateUtil.tomorrowLastDate();
       System.out.println(dfs.format(date));
   }




}
