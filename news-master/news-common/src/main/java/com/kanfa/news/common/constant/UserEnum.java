package com.kanfa.news.common.constant;

/**
 * @author Jezy
 */
public class UserEnum {
    public enum ValidateCodeEnum {
        VALIDATE_CODE_EXPIRE(40201, "验证码已过期"),
        VALIDATE_CODE_ERR(40202, "验证码错误");
        private Integer key;
        private String value;

        ValidateCodeEnum(Integer key, String value) {
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


    public enum LoginExceptionEnum {
        PARAM_VALIDATE_FAIL(4001, "参数校验失败");
        private Integer key;
        private String value;

        LoginExceptionEnum(Integer key, String value) {
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

    public enum LoginPlatformEnum {
        WEI_BO(1, "微博"),
        WEI_XIN(2, "微信"),
        QQ(3, "QQ");
        private Integer key;
        private String value;

        LoginPlatformEnum(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }

        public static LoginPlatformEnum getByValue(int value) {
            for (LoginPlatformEnum code : values()) {
                if (code.key == value) {
                    return code;
                }
            }
            return null;
        }

    }

}
