package com.kanfa.news.common.biz;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;


/**
 * Created by Chen
 * on 2018/6/5 15:39
 */
public class CallbackSample {

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAI2sitJLUoat5J";
    private static String accessKeySecret = "lppXeF8e4RAteJOCoit2G7pd4PIvuq";
    private static String bucketName = "devvideotest";

    // The callback url，for example: http://oss-demo.aliyuncs.com:23450或http://0.0.0.0:9090
    // The service of that url must support the post method.
    private static final String callbackUrl = "http://5ea5cn.natappfree.cc/api/admin/demand/insertDemand";


    public static void main(String[] args) throws IOException {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            String content = "Hello OSS";
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "key",
                    new ByteArrayInputStream(content.getBytes()));

            Callback callback = new Callback();
            callback.setCallbackUrl(callbackUrl);
            callback.setCallbackHost("oss-cn-hangzhou.aliyuncs.com");
            callback.setCallbackBody("{\\\"bucket\\\":${bucket},\\\"object\\\":${object},"
                    + "\\\"mimeType\\\":${mimeType},\\\"size\\\":${size},"
                    + "\\\"my_var1\\\":${x:var1},\\\"my_var2\\\":${x:var2}}");
            callback.setCalbackBodyType(Callback.CalbackBodyType.JSON);
            callback.addCallbackVar("x:var1", "value1");
            callback.addCallbackVar("x:var2", "value2");
            putObjectRequest.setCallback(callback);

            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            byte[] buffer = new byte[1024];
            putObjectResult.getResponse().getContent().read(buffer);
            putObjectResult.getResponse().getContent().close();

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }
}
