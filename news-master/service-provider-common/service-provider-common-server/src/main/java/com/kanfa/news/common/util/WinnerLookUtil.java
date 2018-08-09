package com.kanfa.news.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.List;

public class WinnerLookUtil {
    public static String DESEncrypt(String encryptStr, String key) {
        String result = "";
        try {
            String encryptKey = SHA1(key).substring(0, 8).toUpperCase();

            DESKeySpec desKeySpec = new DESKeySpec(encryptKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec ivp = new IvParameterSpec(encryptKey.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, securekey, ivp);
            byte[] encryptResult = cipher.doFinal(encryptStr.getBytes("GB2312"));
            result = BinaryToHexString(encryptResult);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String SHA1(String key) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(key.getBytes("GB2312"));
            byte[] resultsha = md.digest();
            for (byte b : resultsha) {
                int i = b & 0xff;
                if (i < 0xf) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex;
        }
        return result;
    }

    public static String httpPost(String url, List<NameValuePair> params) {
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instreams = entity.getContent();
                result = convertStreamToString(instreams);
                System.out.println(result);
            }
        } catch (Exception e) {
        }
        return result;
    }

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
                System.out.println(result);
            }
        } catch (Exception e) {
        }
        return result;
    }

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

    /**
     * 随机生成4位随机验证码
     *
     * @return String
     * @Author: jezy
     */
    public static String createRandomVcode(){
        //验证码
        String vcode = "";
        for (int i = 0; i < 4; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
}
