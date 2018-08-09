package com.kanfa.news.common.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.live.model.v20161101.DescribeLiveStreamOnlineUserNumRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.io.IOException;

/**
 * 视频点播OpenAPI调用示例
 * 以GetVideoPlayAuth接口为例，其他接口请替换相应接口名称及私有参数
 *
 */
public class AliyunUtilSDK {
    //账号AK信息请填写(必选)
    private static String access_key_id = "LTAIwe3NbzyiDsnD";
    //账号AK信息请填写(必选)
    private static String access_key_secret = "BOph9T3nTAN0UaD8F2Rqei2BLfoYC8";

    public static void main(String[] args) throws IOException {

        requestInitSample();

    }
    public static void init() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", "<your accessKey>", "<your accessSecret>");
        //DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "live", "live.aliyuncs.com"); //添加自定义endpoint。
        DefaultAcsClient client = new DefaultAcsClient(profile);
        //System.setProperty("http.proxyHost", "127.0.0.1"); //此设置用于设置代理，可用fiddler拦截查看http请求，便于调试。
        //System.setProperty("http.proxyPort", "8888");
    }


    public static void requestInitSample() {
        DescribeLiveStreamOnlineUserNumRequest describeLiveSnapshotConfigRequest = new DescribeLiveStreamOnlineUserNumRequest();
        describeLiveSnapshotConfigRequest.setDomainName("live.aliyunlive.com");
        //describeLiveSnapshotConfigRequest.setProtocol(ProtocolType.HTTPS); //指定访问协议
//        describeLiveSnapshotConfigRequest.setAcceptFormat(FormatType.JSON); //指定api返回格式
        //describeLiveSnapshotConfigRequest.setMethod(MethodType.POST); //指定请求方法
        //describeLiveSnapshotConfigRequest.setRegionId("cn-shanghai");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", access_key_id, access_key_secret);
            //DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "live", "live.aliyuncs.com"); //添加自定义endpoint。
            DefaultAcsClient client = new DefaultAcsClient(profile);
            HttpResponse httpResponse = client.doAction(describeLiveSnapshotConfigRequest);

           /* //另一种方式
            DescribeLiveSnapshotConfigRequest request =new DescribeLiveSnapshotConfigRequest();
            request.setDomainName("live.aliyunlive.com");
            DescribeLiveSnapshotConfigResponse response = client.getAcsResponse(request);
            System.out.println("aliyun return : "+response.getTotalNum());*/


            System.out.println(httpResponse.getUrl());
            System.out.println(new String(httpResponse.getHttpContent()));
            //todo something.
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


}