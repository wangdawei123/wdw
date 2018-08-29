package com.kanfa.news.admin.rpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.mts.model.v20140618.QueryMediaListByURLRequest;
import com.aliyuncs.mts.model.v20140618.QueryMediaListByURLResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.kanfa.news.admin.entity.Demand;
import com.kanfa.news.admin.feign.IDemandServiceFeign;
import com.kanfa.news.admin.feign.IFileServiceFeign;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    IFileServiceFeign fileServiceFeign;
    @Autowired
    IDemandServiceFeign demandServiceFeign;


    private DefaultAcsClient client = null;
    private final String REGION = "cn-beijing";
    private final String ID="LTAIwe3NbzyiDsnD";
    private final String KEY ="BOph9T3nTAN0UaD8F2Rqei2BLfoYC8";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ObjectRestResponse add(HttpServletRequest httpServletRequest) {
        Map<String, MultipartFile> multipartFileMap = ((MultipartHttpServletRequest) httpServletRequest).getFileMap();
        return fileServiceFeign.upload(multipartFileMap.get("file"));
    }

    @RequestMapping(value = "/imgDownload", method = RequestMethod.GET)
    public ObjectRestResponse imgDownload(@RequestParam(value = "url",required = false) String url) {
        return fileServiceFeign.imgDownload(url);
    }


    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public void callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // 将资料解码
        String reqBody = sb.toString();

        //转为json
        JSONObject jsonObject = JSONObject.parseObject(reqBody);

        JSONObject message = JSONObject.parseObject(jsonObject.getString("Message"));

        String name = message.getJSONObject("MediaWorkflowExecution").getJSONObject("Input").getJSONObject("InputFile").getString("Object");
        int idx = name.lastIndexOf("/");
        //取出name
        name = name.substring(idx + 1, name.length());
        //取出RunId
        String runId = message.getJSONObject("MediaWorkflowExecution").getString("RunId");
        //取出MediaId
        String mediaId = message.getJSONObject("MediaWorkflowExecution").getString("MediaId");

        //获得时长
        FileController fileController = new FileController();
        String time = fileController.queryMediaListByURL(name);

        if (message.getString("Type").equals("Start") && message.getJSONObject("MediaWorkflowExecution").getString("State").equals("Running")) {
            //通过name查询信息
            Demand demandId = demandServiceFeign.getDemandId(name);
            Demand demand = new Demand();
            demand.setId(demandId.getId());
            demand.setStatus("3");
            demand.setDuration(time);
            demand.setMedid(mediaId);
            demandServiceFeign.update(demandId.getId(), demand);
            response(request, response, "正在转码", 204);
        }
       System.out.println(message.getString("Type") + "+++++++++++++++++++++++++++++");
       System.out.println(message.getJSONObject("MediaWorkflowExecution").getString("State") + "@@@@@@@@@@@@@@@@@@@@");
        if (message.getString("Type").equals("Report") && message.getJSONObject("MediaWorkflowExecution").getString("State").equals("Completed")) {
            JSONArray jsonArray = message.getJSONObject("MediaWorkflowExecution").getJSONArray("ActivityList");
            if (jsonArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成json对象
                    JSONObject job = jsonArray.getJSONObject(i);
                    System.out.println(job.get("Name"));
                    if (job.getString("Name").equals("activityStart")) {
                        Demand demandByMedid = demandServiceFeign.getDemandByMedid(mediaId);
                        Demand demand = new Demand();
                        demand.setId(demandByMedid.getId());
                        demand.setStatus("4");
                        demand.setMedid(mediaId);
                        demand.setDuration(time);
                        demand.setRunid(job.getString("RunId"));
                        demand.setUrl(job.getString("Name") + "/" + job.getString("RunId") + "/" + demandByMedid.getName());
                        demandServiceFeign.update(demandByMedid.getId(), demand);
                        response(request, response, "转码完成", 204);
                    }
                }
            }
        }else {
            response(request, response, "转码失败", 500);
        }
    }

    private void response(HttpServletRequest request, HttpServletResponse response, String results, int status) throws IOException {
        String callbackFunName = request.getParameter("callback");
        response.addHeader("Content-Length", String.valueOf(results.length()));
        if (callbackFunName == null || callbackFunName.equalsIgnoreCase("")) {
            response.getWriter().println(results);}
        else{
            response.getWriter().println(callbackFunName + "( " + results + " )");
        }
        response.setStatus(status);
        response.flushBuffer();
    }


    public FileController() throws ClientException {
        this.client = new DefaultAcsClient(DefaultProfile.getProfile(REGION, ID, KEY));
    }

    //根据视频源OSS地址查询媒体信息， 如: 媒体ID, 媒体状态及其他属性
    private String queryMediaListByURL(String name) throws ClientException, UnsupportedEncodingException, ParseException {
        QueryMediaListByURLRequest request = new QueryMediaListByURLRequest();

        String ossHost = "http://devvideotest.oss-cn-beijing.aliyuncs.com/";
        String ossObject = "outactmvp/"+name;
        //ossObject需要符合rfc3986标准
        String rfc3986Object = encodeByRFC3986(ossObject);
        request.setFileURLs(ossHost + rfc3986Object);
        QueryMediaListByURLResponse response = this.client.getAcsResponse(request);
        String response2 = JSONObject.toJSONString(response.getMediaList());
        System.out.println(response2);
        JSONArray objects = JSONObject.parseArray(response2);
        for (int i = 0; i < objects.size(); i++) {
            // 遍历 jsonarray 数组，把每一个对象转成json对象
            JSONObject job = objects.getJSONObject(i);
            String duration = job.getString("duration");
            System.out.println(duration);

            String dateFormat = "ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date date = simpleDateFormat.parse(duration);
            //System.out.println();

            String dateFormat1 = "HH:mm:ss";
            System.out.println(date.toString());
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(dateFormat1);
            String dateTimeStr = simpleDateFormat1.format(date);
            if(StringUtils.contains(dateTimeStr,"00:")){
                dateTimeStr = StringUtils.substring(dateTimeStr,3);
            }
            System.out.println(dateTimeStr);
            return dateTimeStr;
        }
        return null;
    }
    private String encodeByRFC3986(String object) throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        String[] segments = object.split("/");
        for (int i = 0; i < segments.length; i++) {
            builder.append(percentEncode(segments[i]));
            if (i != segments.length - 1) {
                builder.append("/");
            }
        }
        return builder.toString();
    }
    private static String percentEncode(String value) throws UnsupportedEncodingException {
        if (value == null) {
            return null;
        }
        return URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    }
