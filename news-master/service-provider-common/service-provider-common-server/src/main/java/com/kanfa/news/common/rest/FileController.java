package com.kanfa.news.common.rest;

import com.kanfa.news.common.biz.FileBiz;
import com.kanfa.news.common.biz.VideoBiz;
import com.kanfa.news.common.config.AliOSSConfig;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.util.ImageHandleHelper;
import com.kanfa.news.common.util.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private AliOSSConfig aliOSSConfig;

    @Autowired
    private FileBiz fileBiz;

    @Autowired
    private VideoBiz videoBiz;

    private final  static  String IMAGE_PATH = "http://kf-javatest.oss-cn-beijing.aliyuncs.com/imgupload/1530687747672.png";

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ObjectRestResponse upload(@RequestPart(value = "file", required = false) MultipartFile file) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        try {
            Assert.notNull(file, "图片不能为空");
            Map<String, Object> map = new HashMap<>(16);
            OSSClientUtil ossClient = new OSSClientUtil(aliOSSConfig);
            String name = ossClient.uploadImg2Oss(file);
            String imgUrl = ossClient.getImgUrl(name);
            map.put("name", name);
            map.put("imageUrl", imgUrl);
            objectRestResponse.setRel(false);
            objectRestResponse.setStatus(200);
            objectRestResponse.setData(map);
        } catch (Exception ex) {
            objectRestResponse.setRel(true);
            objectRestResponse.setStatus(500);
            objectRestResponse.setMessage(ex.getMessage());
        } finally {

        }
        return objectRestResponse;
    }

    @PostMapping(value = "/merger")
    public ObjectRestResponse upload(@RequestParam(value = "file", required = false) String imagePath) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        try {
            Assert.notNull(imagePath, "图片不能为空");
            BufferedImage bufferedImage = ImageHandleHelper.overlapImage(new URL(IMAGE_PATH),new URL(imagePath));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            Map<String, Object> map = new HashMap<>(16);
            OSSClientUtil ossClient = new OSSClientUtil(aliOSSConfig);
            String name = ossClient.uploadBufferedImage(is);
            String imgUrl = ossClient.getImgUrl(name);
            map.put("name", name);
            map.put("imageUrl", imgUrl);
            objectRestResponse.setRel(false);
            objectRestResponse.setStatus(200);
            objectRestResponse.setData(map);
        } catch (Exception ex) {
            objectRestResponse.setRel(true);
            objectRestResponse.setStatus(500);
            objectRestResponse.setMessage(ex.getMessage());
        } finally {

        }
        return objectRestResponse;
    }


    @RequestMapping(value = "/uploadVideo", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ObjectRestResponse uploadVideo(@RequestPart(value = "file") MultipartFile file) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        try {
            Assert.notNull(file, "视频不能为空");
            Map<String, Object> map = new HashMap<>(16);
            this.videoBiz.uploadVideo(file);
            objectRestResponse.setRel(false);
            objectRestResponse.setStatus(200);
            objectRestResponse.setData(map);
        } catch (Exception ex) {
            objectRestResponse.setRel(true);
            objectRestResponse.setStatus(500);
            objectRestResponse.setMessage(ex.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {

        }
        return objectRestResponse;
    }


    @RequestMapping(value = "/imgDownload", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ObjectRestResponse imgDownload(@RequestParam(value = "url") String url) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        try {
            Map<String, Object> map = new HashMap<>(16);
            OSSClientUtil ossClient = new OSSClientUtil(aliOSSConfig);
            String name = ossClient.uploadImgUrlOss(url);
            String imgUrl = ossClient.getImgUrl(name);
            map.put("name", name);
            map.put("imageUrl", imgUrl);
            objectRestResponse.setRel(false);
            objectRestResponse.setStatus(200);
            objectRestResponse.setData(map);
            ossClient.destory();
        } catch (Exception e) {
            objectRestResponse.setRel(true);
            objectRestResponse.setStatus(500);
            objectRestResponse.setMessage(e.getMessage());
        } finally {
        }
        return objectRestResponse;
    }


//    @RequestMapping(value = "/uploadTxt", method = RequestMethod.POST,
//            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ResponseBody
//    public ObjectRestResponse<Map<String,List<String>>> uploadTxt(@RequestPart(value = "file") MultipartFile file) {
//        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
//        // 使用一个字符串集合来存储文本中信息
//        List<String> list = new ArrayList<String>();
//        try {
//            InputStream inputStream = file.getInputStream();
//            // 防止路径乱码 如果utf-8 乱码  改GBK eclipse里创建的txt  用UTF-8，在电脑上自己创建的txt  用 GBK
//            InputStreamReader isr = new InputStreamReader(inputStream, "GBK");
//            BufferedReader br = new BufferedReader(isr);
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                // 如果 t x t文件里的路径 不包含---字符串       这里是对里面的内容进行一个筛选
//                if (line.lastIndexOf("---") < 0) {
//                    list.add(line);
//                }
//            }
//            br.close();
//            isr.close();
//            inputStream.close();
//            HashMap<String, List<String>> stringListHashMap = new HashMap<>();
//            stringListHashMap.put("codes",list);
//            ObjectRestResponse<Map<String, List<String>>> mapObjectRestResponse = new ObjectRestResponse<>();
//            mapObjectRestResponse.setData(stringListHashMap);
//            return mapObjectRestResponse;
//        } catch (Exception ex) {
//            objectRestResponse.setRel(true);
//            objectRestResponse.setStatus(500);
//            objectRestResponse.setMessage(ex.getMessage());
//        } finally {
//
//        }
//        return null;
//    }


}
