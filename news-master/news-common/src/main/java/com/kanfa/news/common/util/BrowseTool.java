package com.kanfa.news.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jiqc
 * @date 2018/6/15 17:56
 */
public class BrowseTool {

    private static List<String> getData(){
        List<String> clientList=new ArrayList<>(16);
        clientList.add("TencentTraveler");
        clientList.add("Baiduspider+");
        clientList.add("BaiduGame");
        clientList.add("Googlebot");
        clientList.add("Sosospider+");
        clientList.add("Sogou web spider");
        clientList.add("YoudaoBot");
        clientList.add("BaiDuSpider");
        clientList.add("BSpider");
        clientList.add("Sogou Spider");
        clientList.add("Speedy Spider");
        clientList.add("Google AdSense");
        clientList.add("Python-urllib");
        clientList.add("MSIECrawler");
        clientList.add("WGet tools");
        clientList.add("Fish search");
        return clientList;
    }

    public static Boolean checkBrowse(String userAgent){
        List<String> data = getData();
        for (String datum : data) {
            if(regex(datum,userAgent)){
                return true;
            }
        }
        return false;
    }
    public static boolean regex(String regex,String str){
        Pattern p =Pattern.compile(regex,Pattern.MULTILINE);
        Matcher m=p.matcher(str);
        return m.find();
    }

    public static void main(String[] args) {
        String ie9    ="Baiduspider/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)";
        String ie8    ="Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322)";
        String ie7    ="Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; .NET CLR 1.1.4322)";
        String ie6    ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)";
        String aoyou  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; Maxthon 2.0)";
        String qq     ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322) QQBrowser/6.8.10793.201";
        String green  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; GreenBrowser)";
        String se360  ="Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322; 360SE)";

        String chrome ="Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/534.11 (KHTML, like Gecko) Chrome/9.0.570.0 Safari/534.11";
        String safari ="Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN) AppleWebKit/533.17.8 (KHTML, like Gecko) Version/5.0.1 Safari/533.17.8";
        String fireFox="Mozilla/5.0 (Windows NT 5.2; rv:7.0.1) Gecko/20100101 Firefox/7.0.1";
        String opera  ="Opera/9.80  (Windows NT 5.2; U; zh-cn) Presto/2.9.168 Version/11.51";
        String other  ="(Windows NT 5.2; U; zh-cn) Presto/2.9.168 Version/11.51";
        BrowseTool b=new BrowseTool();
        System.out.println(b.checkBrowse(ie9));
        System.out.println(b.checkBrowse(ie8));
        System.out.println(b.checkBrowse(ie7));
        System.out.println(b.checkBrowse(ie6));
        System.out.println(b.checkBrowse(aoyou));
        System.out.println(b.checkBrowse(qq));
        System.out.println(b.checkBrowse(green));
        System.out.println(b.checkBrowse(se360));
        System.out.println(b.checkBrowse(chrome));
        System.out.println(b.checkBrowse(safari));
        System.out.println(b.checkBrowse(fireFox));
        System.out.println(b.checkBrowse(opera));
        System.out.println(b.checkBrowse(other));
    }
}
