package com.kanfa.news.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.kanfa.news.common.config.AliOSSConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jezy
 */
public class OSSClientUtil {
    private AliOSSConfig aliOSSConfig;
    private OSSClient ossClient;

    Log log = LogFactory.getLog(OSSClientUtil.class);

    public OSSClientUtil(AliOSSConfig aliOSSConfig) {
        this.aliOSSConfig = aliOSSConfig;
        ossClient = new OSSClient(aliOSSConfig.getEndpoint(), aliOSSConfig.getAccessKeyId(), aliOSSConfig.getAccessKeySecret());
    }

    public OSSClient getOssClient() {
        return ossClient;
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }

    /**
     * 上传图片
     *
     * @param url
     */
    public void uploadImg1Oss(String url) throws Exception {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            this.uploadFile2OSS(fin, split[split.length - 1]);
        } catch (FileNotFoundException e) {
            throw new Exception("图片上传失败01");
        }
    }


    public String uploadImg2Oss(MultipartFile file) throws Exception {
        int imageSize = (1024 * 1024) * 2;
        if (file.getSize() > imageSize) {
            throw new Exception("上传图片大小不能超过2M！");
        }
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            InputStream inputStream = file.getInputStream();
            this.uploadFile2OSS(inputStream, name);
            return name;
        } catch (Exception e) {
            throw new Exception("图片上传失败02");
        }
    }

    public String uploadBufferedImage(InputStream inputStream) throws Exception {
        String originalFilename = "face_merger.png";
        String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        Random random = new Random();
        String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
        try {
            this.uploadFile2OSS(inputStream, name);
            return name;
        } catch (Exception e) {
            throw new Exception("图片上传失败02");
        }
    }

    public String uploadImgUrlOss(String url) throws Exception {
        String suffixes = "avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|apk|exe|pdf|rar|zip|docx|doc|";
        Pattern pat = Pattern.compile("[\\w]+[\\.](" + suffixes + ")");//正则判断
        Matcher mc = pat.matcher(url);//条件匹配
        try {
            while (mc.find()) {
                String substring = mc.group();//截取文件名后缀名
                if (substring.endsWith(".")){
                    substring = substring+"jpg";
                }
                Random random = new Random();
                String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
                InputStream inputStream = new URL(url).openStream();

                String suffix = substring.substring(substring.lastIndexOf("."));
                byte[] bs = new byte[1024];
                int len;
                File sf = File.createTempFile("oss-img-tmp", suffix);
                OutputStream os = new FileOutputStream(sf);

                while ((len = inputStream.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                //不能超过2M  1M=1024K=1024K x 1024B=1,048,576Byte
                long length = sf.length();
                int imageSize = 1024 * 1024;
                if (length > imageSize) {
                    throw new Exception("上传图片大小不能超过2M！");
                }
                os.flush();
                os.close();
                inputStream.close();

                InputStream inputStream1 = new FileInputStream(sf);
                this.uploadFile2OSS(inputStream1, name);
                return name;
            }
        } catch (IOException e) {
            throw new Exception("图片上传失败03");
         }
        return null;
    }


    /**
     * 获得图片路径
     *
     * @param fileUrl
     * @return
     */
    public String getImgUrl(String fileUrl) {
        if (!StringUtils.isEmpty(fileUrl)) {
            String[] split = fileUrl.split("/");
            return this.getUrl(aliOSSConfig.getFileDir() + split[split.length - 1]);
        }
        return null;
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param instream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    public String uploadFile2OSS(InputStream instream, String fileName) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(instream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(aliOSSConfig.getBucketName(), aliOSSConfig.getFileDir() + fileName, instream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (instream != null) {
                    instream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param filenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx") ||
                filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx") ||
                filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }

    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        Date expiration = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(aliOSSConfig.getBucketName(), key);
        request.setExpiration(expiration);
        URL url = ossClient.generatePresignedUrl(request);
        if (url != null) {
            return StringUtils.substringBefore(url.toString(), "?");
        }
        return null;
    }


    public static void download(String _url, String path) throws Exception {
        try {
            URL url = new URL(_url);
            URLConnection con = url.openConnection();
            con.setConnectTimeout(5000);
            InputStream is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            File sf = new File(path);
            OutputStream os = new FileOutputStream(sf);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();

        } catch (IOException e) {
        }
    }
}