package com.kanfa.news.common.constant.app;

/**
 * APP 客户端接口所有异常消息提示
 *
 * @author Jezy
 */

public enum AppCommonMessageEnum {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request!"),
    NOT_AUTHORIZATION(401, "NotAuthorization"),
    NOT_FOUND(404, "Not found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    SERVER_RUNTIME_EXCEPTION(1000, "[服务器]运行时异常"),
    SERVER_NULL_POINT_EXCEPTION(1001, "[服务器]空值异常"),
    SERVER_DATA_TYPE_TRANSFER_EXCEPTION(1002, "[服务器]数据类型转换异常"),
    SERVER_IO_EXCEPTION(1003, "[服务器]IO异常"),
    SERVER_UNKNOWN_METHOD_EXCEPTION(1004, "[服务器]未知方法异常"),
    SERVER_ARRAY_BOUND_EXCEPTION(1005, "[服务器]数组越界异常"),
    SERVER_NETWORK_EXCEPTION(1006, "[服务器]网络异常"),

    USER_UNREGISTER(1010, "用户未注册"),
    USER_REGISTERED(1011, "用户已注册"),
    USER_USERNAME_PASSWORD_ERROR(1012, "用户名或密码错误"),
    USER_NOT_LOGIN(1013, "用户未登陆"),
    USER_NAME_EXIST(1014, "昵称已存在"),

    VERIFICATION_CODE_SEND_FAIL(1020, "验证码发送失败"),
    VERIFICATION_CODE_INVALID(1021, "验证码失效"),
    VERIFICATION_CODE_ERROR(1022, "验证码错误"),
    VERIFICATION_CODE_UNAVAILABLE(1023, "验证码不可用"),
    VERIFICATION_CODE_PLATFORM_EXCEPTION(1029, "短信平台异常"),

    APP_DEL_COMMENT_SUCCESS(200,"删除成功"),
    APP_PARAMETERS_MISSING(2010, "缺少参数或值为空"),
    APP_OPERATION_FAILED(2011,"操作失败"),
    APP_NO_OPERATION_PERMISSIONS(2021, "无操作权限"),
    APP_RE_LOGGED_IN(2023, "请重新登录"),
    APP_PARAMETERS_ILLEGALITY(2029, "参数不合法"),
    APP_COMMENT_NOT_EXIST(2030,"删除的评论不存在"),
    APP_DEL_COMMENT_ERROR(2031,"只能删除自己发出的评论"),
    APP_USERNAME_IS_CHANGED(2041,"昵称已被注册请重新填写"),
    APP_USER_NAME_MISSING(2040,"用户id不能为空"),
    APP_USERNAME_TOO_LONG(2042,"你输入的昵称过长"),
    APP_GENDER_MISSING(2043,"请选择性别"),
    APP_NO_DATA(2044,"暂无数据。"),
    APP_PHONE_MISSING(2045,"手机号不能为空"),
    APP_PHONE_ILLEGALITY(2046,"手机号不合法"),
    APP_COMMENT_MISSING(2047,"评论内容不能为空"),
    APP_DEVID_ERROR(2048,"未登录用户请指定用户名和设备号"),
    APP_HAS_BADWORDS(2049,"含有不允许发布的词语"),
    APP_FORBID_COMMENT(2050,"很抱歉，您已经被禁止发表评论"),

    APP_ADV_NOTFOUND(301,"未找到广告");

    private int key;
    private String value;

    AppCommonMessageEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int key() {
        return key;
    }

    public String value() {
        return value;
    }
}
