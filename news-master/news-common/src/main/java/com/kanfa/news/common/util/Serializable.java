package com.kanfa.news.common.util;

/**
 * @author Jiqc
 * @date 2018/3/23 19:07
 */
public interface Serializable {
    byte[] serialize();
    void unserialize(byte[] ss);
}
