package com.kanfa.news.app.security;

/**
 * 定义私钥
 *
 * @author Jezy
 */
public class ClientAuthSecurity {
   public enum ClientAuthEnum {
        SECURITY_APP(1001, "0accb6db15cfb03515c08845a98133a574ef2a34"),
        SECURITY_PC(1002, "31d81edf340fe8caab085d31cc4511d204313530"),
       SECURITY_ADMIN(1003, "31d81edf340fe8caab085d31cc4511d204313530");
        private Integer key;
        private String value;

        ClientAuthEnum(Integer key, String value) {
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
