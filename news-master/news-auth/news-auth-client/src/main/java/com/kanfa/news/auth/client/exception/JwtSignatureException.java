package com.kanfa.news.auth.client.exception;

/**
 *
 * @author ace
 * @date 2017/9/15
 */
public class JwtSignatureException extends Exception {
    public JwtSignatureException(String s) {
        super(s);
    }
}
