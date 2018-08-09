package com.kanfa.news.web.rest;

/**
 * 人脸融合图像处理
 *
 * @author Jezy
 * @version 1.0
 */

import com.kanfa.news.common.client.feign.IFileServiceFeign;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.web.util.ImageUtils;
import com.kanfa.news.web.util.youtu.Youtu;
import com.kanfa.news.web.util.youtu.YoutuSign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("faceMerger")
public class FaceMergerController {

    @Value("${youtu.appId}")
    private String appId;
    @Value("${youtu.secretId}")
    private String secretId;
    @Value("${youtu.secretKey}")
    private String secretKey;
    @Value("${youtu.modelId}")
    private String modelId;
    @Value("${youtu.userId}")
    private String userId;

    @Autowired
    private IFileServiceFeign fileServiceFeign;

    @PostMapping(value = "/merger")
    @CrossOrigin
    public ObjectRestResponse faceMerger(@RequestParam(value = "imageUrl") String imageUrl,
                                         @RequestParam(value = "modelId", required = false) String modelId,
                                         @RequestParam(value = "resImgType", required = false, defaultValue = "url") String resImgType)
            throws JSONException, KeyManagementException, NoSuchAlgorithmException, IOException {
        Assert.hasText(imageUrl, "请上传图片");
        if (StringUtils.isBlank(modelId)) {
            modelId = this.modelId;
        }

        Youtu youtu = new Youtu(appId, secretId, secretKey, Youtu.API_YOUTU_IMG_MERGER_END_POINT);
        StringBuffer mySign = new StringBuffer();
        YoutuSign.appSign(appId, secretId, secretKey, (System.currentTimeMillis() / 1000) + 100, userId, mySign);
        JSONObject requestData = new JSONObject();
        JSONArray opdata = new JSONArray();
        JSONObject opdataElement = new JSONObject();
        JSONObject params = new JSONObject();

        params.put("model_id", modelId);
        opdataElement.put("cmd", "doFaceMerge");
        opdataElement.put("params", params);
        opdata.put(opdataElement);
        requestData.put("img_data", StringUtils.trim("data:image/jpeg;base64," + ImageUtils.encodeImageToBase64(new URL(imageUrl+"?x-oss-process=image/resize,h_500"))).replaceAll("\r|\n*", ""));
        requestData.put("rsp_img_type", resImgType);
        requestData.put("opdata", opdata);

        JSONObject res = youtu.faceMerger(requestData);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (!"0".equals(res.getString("ret"))) {
            try {
                objectRestResponse.setStatus(Integer.valueOf(res.getString("ret")));
                objectRestResponse.setRel(true);
                throw new Exception("融合失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            resultMap.put("img_url", res.getString("img_url"));
            resultMap.put("img_url_thumb", res.getString("img_url_thumb"));
        }
        if (resultMap.get("img_url") == null) {
            return objectRestResponse;
        }
        log.info("图片融合后URL:" + resultMap.get("img_url"));
        ObjectRestResponse objectRestResponse1 = fileServiceFeign.merger(resultMap.get("img_url").toString());
        return objectRestResponse1;
    }

    /**
     * @param imageUrl
     * @return
     * @throws IOException
     */
    @Deprecated
    @PostMapping(value = "/merger_bg")
    @CrossOrigin
    public ObjectRestResponse faceMerger(@RequestParam(value = "imageUrl") String imageUrl) throws IOException {
        Assert.hasText(imageUrl, "请上传图片");
        ObjectRestResponse objectRestResponse = fileServiceFeign.merger(imageUrl);
        return objectRestResponse;
    }

    public static void main(String[] args) {
        String a = "adfasdf";
        System.out.println("args = [" + a.length() + "]");
    }
}