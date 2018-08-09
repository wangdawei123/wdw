package com.kanfa.news.common.exception;

/**
 * APP 用户异常
 *
 * @author Jezy
 */
public class AppUserException extends BaseException {
    public AppUserException(String message, int status) {
        super(message, status);
    }

    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    public enum APPUserEnum {
        USER_SHIELD(40401, "该用户已经被屏蔽"),
        USER_PLATFORM_ERROR(40402, "Platform不合法"),
        USER_API_WEIBO_ERROR(40403, "微博API错误"),
        USER_API_WEIBO_RETURN_ERROR(40404, "微博API请求出错或返回数据格式异常"),
        USER_API_WEIXIN_ERROR(40405, "微信API错误"),
        USER_API_WEIXIN_RETURN_ERROR(40406, "微信API请求出错或返回数据格式异常"),
        USER_API_QQ_ERROR(40407, "QQ API错误"),
        USER_API_QQ_RETURN_ERROR(40408, "QQ API请求出错或返回数据格式异常"),
        USER_LOGIN_FAIL(40409, "登录失败");

        private Integer key;
        private String value;

        APPUserEnum(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
}
