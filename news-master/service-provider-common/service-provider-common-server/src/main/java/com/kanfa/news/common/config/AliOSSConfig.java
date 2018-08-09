package com.kanfa.news.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jezy
 */
@ConfigurationProperties(prefix = "alioss")
@Component
public class AliOSSConfig {
    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String bucketVideoName;

    private String fileDir;

    private String videoDir;

    private String outVideoTempDir;

    public String getOutVideoTempDir() {
        return outVideoTempDir;
    }

    public void setOutVideoTempDir(String outVideoTempDir) {
        this.outVideoTempDir = outVideoTempDir;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketVideoName() {
        return bucketVideoName;
    }

    public void setBucketVideoName(String bucketVideoName) {
        this.bucketVideoName = bucketVideoName;
    }

    public String getVideoDir() {
        return videoDir;
    }

    public void setVideoDir(String videoDir) {
        this.videoDir = videoDir;
    }
}
