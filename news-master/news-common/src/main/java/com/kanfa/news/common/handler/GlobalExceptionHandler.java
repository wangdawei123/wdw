package com.kanfa.news.common.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.exception.BaseException;
import com.kanfa.news.common.exception.auth.ClientTokenException;
import com.kanfa.news.common.exception.auth.UserTokenException;
import com.kanfa.news.common.msg.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ace on 2017/9/8.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(200);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(CommonConstants.EX_OTHER_CODE, ex.getMessage());
    }

    @ExceptionHandler(ClientTokenException.class)
    public BaseResponse clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        response.setStatus(403);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(UserTokenException.class)
    public BaseResponse userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(401);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(AppCommonMessageEnum.SERVER_RUNTIME_EXCEPTION.key(), AppCommonMessageEnum.SERVER_RUNTIME_EXCEPTION.value());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public BaseResponse invalidFormatHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(CommonConstants.EX_OTHER_CODE, ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse missingServletRequestParameterHandler(HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key(), AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse httpRequestMethodNotSupportedHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(AppCommonMessageEnum.METHOD_NOT_ALLOWED.key());
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(AppCommonMessageEnum.METHOD_NOT_ALLOWED.key(), AppCommonMessageEnum.METHOD_NOT_ALLOWED.value());
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public BaseResponse httpMediaTypeNotAcceptableHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(AppCommonMessageEnum.NOT_ACCEPTABLE.key());
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(AppCommonMessageEnum.NOT_ACCEPTABLE.key(), AppCommonMessageEnum.NOT_ACCEPTABLE.value());
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public BaseResponse indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(AppCommonMessageEnum.SERVER_ARRAY_BOUND_EXCEPTION.key(), AppCommonMessageEnum.SERVER_ARRAY_BOUND_EXCEPTION.value());
    }

    @ExceptionHandler(NullPointerException.class)
    public BaseResponse nullPointerExceptionHandler(NullPointerException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(AppCommonMessageEnum.SERVER_NULL_POINT_EXCEPTION.key(), AppCommonMessageEnum.SERVER_NULL_POINT_EXCEPTION.value());
    }

    @ExceptionHandler(IOException.class)
    public BaseResponse iOExceptionHandler(IOException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(AppCommonMessageEnum.SERVER_IO_EXCEPTION.key(), AppCommonMessageEnum.SERVER_IO_EXCEPTION.value());
    }

    @ExceptionHandler(ClassCastException.class)
    public BaseResponse classCastExceptionHandler(ClassCastException ex) {
        logger.error(ex.getMessage(), ex);
        return new BaseResponse(AppCommonMessageEnum.SERVER_DATA_TYPE_TRANSFER_EXCEPTION.key(), AppCommonMessageEnum.SERVER_DATA_TYPE_TRANSFER_EXCEPTION.value());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse illegalArgumentHandler(HttpServletResponse response, IllegalArgumentException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(200);
        return new BaseResponse(AppCommonMessageEnum.INTERNAL_SERVER_ERROR.key(), ex.getMessage());
    }


}
