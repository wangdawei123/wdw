package com.kanfa.news.common.exception;


import com.kanfa.news.common.util.StringToDateConverter;

/**
 * @author Jezy
 */
public class StringToDateException extends BaseException{
    public StringToDateException(String message) {
        super(message, Integer.valueOf(StringToDateConverter.DateFormatEnum.PARSE_EXCEPTION.toString()));
    }
}
