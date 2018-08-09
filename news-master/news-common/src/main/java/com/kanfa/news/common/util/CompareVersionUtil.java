package com.kanfa.news.common.util;

/**
 * Created by user on 2018/3/14.
 */
public class CompareVersionUtil {
    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     规则是按日期订的例如：2.10.15   项目启动第2年的8月15号
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) throws Exception {

        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        for(int i = 0 ; i<versionArray1.length ; i++){ //如果位数只有一位则自动补零（防止出现一个是04，一个是5 直接以长度比较）
            if(versionArray1[i].length() == 1){
                versionArray1[i] = "0" + versionArray1[i];
            }
        }
        String[] versionArray2 = version2.split("\\.");
        for(int i = 0 ; i<versionArray2.length ; i++){//如果位数只有一位则自动补零
            if(versionArray2[i].length() == 1){
                versionArray2[i] = "0" + versionArray2[i];
            }
        }
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }
}
