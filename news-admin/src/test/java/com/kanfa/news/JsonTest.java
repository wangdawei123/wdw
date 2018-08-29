package com.kanfa.news;

import com.alibaba.fastjson.JSONObject;

public class JsonTest {
    public static void main(String[] args) {
        String message = "{\"RunId\":\"f582bfbf60b545c081b2e363bed01757\",\"Name\":\"activityStart\",\"Type\":\"Start\",\"JobId\":\"306a91ce715a4ca398f598ef9e3a11c3\",\"State\":\"Success\",\"MediaWorkflowExecution\":{\"MediaWorkflowId\":\"5e53a0e2dd2c4e7898a595813a29cc93\",\"Name\":\"新建工作流_1509333498194\",\"RunId\":\"f582bfbf60b545c081b2e363bed01757\",\"MediaId\":\"fb1d25c4479d42cea8791412a8ed4b6e\",\"Input\":{\"InputFile\":{\"Bucket\":\"devvideotest\",\"Location\":\"oss-cn-beijing\",\"Object\":\"outactmvp1528716339204b7tm96ud2al.mp4\"}},\"State\":\"Running\",\"ActivityList\":[{\"RunId\":\"f582bfbf60b545c081b2e363bed01757\",\"Name\":\"activityStart\",\"Type\":\"Start\",\"JobId\":\"306a91ce715a4ca398f598ef9e3a11c3\",\"State\":\"Success\",\"StartTime\":\"2018-06-11T11:25:45Z\",\"EndTime\":\"2018-06-11T11:25:46Z\"}],\"CreationTime\":\"2018-06-11T11:25:44Z\",\"RequestId\":\"5B1E5C38DA90A7201C791C03\"}}";
        JSONObject jsonObject = JSONObject.parseObject(message);
        JSONObject MediaWorkflowExecutionJSO =  (JSONObject)jsonObject.get("MediaWorkflowExecution");

        System.out.println(MediaWorkflowExecutionJSO.getJSONArray("ActivityList").get(0));
        System.out.println(jsonObject);
    }
}
