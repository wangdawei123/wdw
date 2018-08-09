package com.kanfa.news.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串转日期
 *
 * @author Jezy
 */
public class StringToDateConverter implements Converter<String, Date> {
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String dateFormat2 = "yyyy/MM/dd HH:mm:ss";
    private static final String shortDateFormat2 = "yyyy/MM/dd";
    private static final String OTHER_DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Override
    public Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();
        try {
            SimpleDateFormat formatter;
            if (source.contains(DateFormatEnum.BAR.value)) {
                if (source.contains(DateFormatEnum.COLON.value)) {
                    if(source.contains(DateFormatEnum.T.value)){
                        formatter = new SimpleDateFormat(OTHER_DATEFORMAT);
                    }else{
                        formatter = new SimpleDateFormat(dateFormat);
                    }
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            } else if (source.contains(DateFormatEnum.SLASH.value)) {
                if (source.contains(DateFormatEnum.COLON.value)) {
                    formatter = new SimpleDateFormat(dateFormat2);
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat2);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum DateFormatEnum {
        BAR("-"),
        SLASH("/"),
        COLON(":"),
        T("T"),
        PARSE_EXCEPTION("10001"),
        EXCEPTION_MESSAGE("parser to Date fail");

        private final String value;

        String getValue() {
            return value;
        }

        DateFormatEnum(String value) {
            this.value = value;
        }
    }



}
