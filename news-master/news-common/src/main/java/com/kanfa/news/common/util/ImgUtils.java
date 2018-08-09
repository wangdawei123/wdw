package com.kanfa.news.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jiqc
 * @date 2018/3/25 10:56
 */
public class ImgUtils {
    public static List<String> getImgStr(String htmlStr) {
        List<String> list = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        // String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                list.add(m.group(1));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> imgStr = getImgStr("<img class=\"wscnph\" style=\"max-width: 100%;\" src=\"http://kf-javatest.oss-cn-beijing.aliyuncs.com/imgupload/1528804284355.jpg\" data-mce-src=\"http://kf-javatest.oss-cn-beijing.aliyuncs.com/imgupload/1528804284355.jpg\" data-mce-style=\"max-width: 100%;\"><img class=\"wscnph\" style=\"max-width: 100%;\" src=\"http://kf-javatest.oss-cn-beijing.aliyuncs.com/imgupload/1528804324233.jpg\" data-mce-src=\"http://kf-javatest.oss-cn-beijing.aliyuncs.com/imgupload/1528804324233.jpg\" data-mce-style=\"max-width: 100%;\"><img class=\"wscnph\" style=\"max-width: 100%;\" src=\"http://kf-javatest.oss-cn-beijing.aliyuncs.com/imgupload/1528804330714.jpg\" data-mce-src=\"http://kf-javatest.oss-cn-beijing.aliyuncs.com/imgupload/1528804330714.jpg\" data-mce-style=\"max-width: 100%;\"></p>");
        System.out.println(imgStr.toString());
        HashSet<String> set = new HashSet<>(16);
        set.addAll(imgStr);
        System.out.println(set);
    }
}
