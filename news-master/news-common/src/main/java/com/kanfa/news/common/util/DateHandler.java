package com.kanfa.news.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/3/27 15:21
 */
public class DateHandler {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//            System.out.println("当前时间："+ new Date().toLocaleString());
//            System.out.println("当天0点时间："+ getTimesmorning().toLocaleString());
//            System.out.println("当天24点时间："+ getTimesnight().toLocaleString());
//            System.out.println("本周周一0点时间："+ getTimesWeekmorning().toLocaleString());
//            System.out.println("本周周日24点时间："+ getTimesWeeknight().toLocaleString());
//            System.out.println("本月初0点时间："+ getTimesMonthmorning().toLocaleString());
//            System.out.println("本月未24点时间："+ getTimesMonthnight().toLocaleString());
        try {
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse( "2008-07-10 19:20:00" );
            System.out.println(date);
            System.out.println(isAfterForDay(sdf.parse( "2008-07-11 19:20:00"),sdf.parse( "2008-07-10 19:20:00")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // 获得当天0点时间
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得当天24点时间
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return  cal.getTime();
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return  cal.getTime();
    }

    // 获得本周日24点时间
    public  static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return  cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }


    /**
     * 时间格式转换
     * @param currentTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * 第一个时间是否在第二个之后
     * <p>
     * 只比较到天
     * @param date1
     * @param date2
     */
    public static Boolean isAfterForDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(format.format(date1)).after(format.parse(format.format(date2)));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
