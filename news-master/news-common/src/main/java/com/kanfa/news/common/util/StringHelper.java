package com.kanfa.news.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ace on 2017/9/10.
 */
public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }

    /**
     * 清除所有空格和换行
     */
    public static String trimString(String str){
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 判断字符是否全是中文字符组成
     * 2 全是 1部分是 0没有中文
     */
    public static Integer chk_cn_str(String str){
        int result = 0;
        for(int i = 0 ; i < str.length() ; i ++){
            if((byte)str.charAt(i) > 127){
                result ++;
                i++;
            }else{
                result = 1;
                break;
            }
        }
        if(result > 1){
            result = 2;
        }
        return result;
    }

    /**
     * 得到字符串的SHA1哈希散列运算
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(String data){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA1");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for(int i=0;i<bits.length;i++){
            int a = bits[i];
            if(a<0){
                a += 256;
            }
            if(a<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }

}
