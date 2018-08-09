package com.kanfa.news.web.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by kanfa on 2018/3/22.
 * 用来发送Http请求到第三方接口
 */
public class HttpSendUtil {


    /**
     * 发送第三方get请求
     * @param url
     * @param params
     * @return
     */
    public static String httpGet(String url, String params) {
        String result = "";
        try {
            HttpClient client = new DefaultHttpClient();
            if (params != "") {
                url = url + "?" + params;
            }
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = client.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                result = convertStreamToString(instreams);
            }
        } catch (Exception e) {
        }
        return result;
    }


    /**
     * 发送第三方post请求
     * @param list
     * @param path
     * @return
     */
    public static String sentPost(List<NameValuePair> list, String path){
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(path);
            httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                result = convertStreamToString(instreams);
            }
        } catch (Exception e) {
        }
        return result;
    }


    /**
     * 发送第三方请求要调用的方法
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void httpGet(String path) {
    }
}
