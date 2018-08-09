package com.kanfa.news.common.util;

import org.apache.commons.codec.digest.Md5Crypt;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

/**
 * @author Jezy
 */
public class RandomNameUtil {
    private static final String NICKNAME_PREFIX = "看法用户";

    public static void main(String args[]) {
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < 1; i++) {
            String chineseName = getRandomJianHan(3);
            if (!set.contains(chineseName)) {
                set.add(chineseName);
            }
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.err.print(iterator.next() + "\n");
        }

        System.out.println("args = [" + getRandomNickName("18600195712") + "]");
        System.out.println("args = [" + UUID.randomUUID().toString().replace("-","")+ "]");
    }

    public static String getRandomJianHan(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            // 定义高低位
            int hightPos, lowPos;
            Random random = new Random();
            // 获取高位值
            hightPos = (176 + Math.abs(random.nextInt(39)));
            // 获取低位值
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (new Integer(hightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                // 转成中文
                str = new String(b, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    public static String getRandomNickName(String phone) {
        return NICKNAME_PREFIX + Md5Crypt.apr1Crypt(phone.getBytes());
    }
}
