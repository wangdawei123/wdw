package com.kanfa.news.common.rest;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.SimplifiedObjectMeta;

import java.text.ParseException;

public class Test {

    public static void main(String[] args) throws ParseException {
        // endpoint以杭州为例，其它region请按实际情况填写
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
        String accessKeyId = "LTAI2sitJLUoat5J";
        String accessKeySecret = "lppXeF8e4RAteJOCoit2G7pd4PIvuq";
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 获取文件的部分元信息
        SimplifiedObjectMeta objectMeta = ossClient.getSimplifiedObjectMeta("devvideotest", "outactmvp/LTAI2sitJLUoat5J.mp4");
        System.out.println(objectMeta.getSize());
        System.out.println(objectMeta.getETag());
        System.out.println(objectMeta.getLastModified());
        // 获取文件的全部元信息
        ObjectMetadata metadata = ossClient.getObjectMetadata("devvideotest", "outactmvp/LTAI2sitJLUoat5J.mp4");
        System.out.println(metadata.getContentType());
        System.out.println(metadata.getLastModified());
       // System.out.println(metadata.getExpirationTime());
        // 关闭client
        ossClient.shutdown();
    }

//    @Test
//    public void testProgress(){
//        // endpoint以杭州为例，其它region请按实际情况填写
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
//        String accessKeyId = "LTAI2sitJLUoat5J";
//        String accessKeySecret = "lppXeF8e4RAteJOCoit2G7pd4PIvuq";
//
//
//        File fh = createSampleFile();
//        // 创建OSSClient实例
//        OSS client  = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//        // 带进度条的上传
//        client.putObject(new PutObjectRequest(bucketName, key, fh).
//                <PutObjectRequest>withProgressListener(new GetProgressSample.PutObjectProgressListener()));
//        // 获取文件的部分元信息
//        SimplifiedObjectMeta objectMeta = ossClient.getSimplifiedObjectMeta("devvideotest", "outactmvp/LTAI2sitJLUoat5J.mp4");
//    }

}
