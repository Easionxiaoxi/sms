package com.xyz.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信V3版配置
 */
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliSmsProperties {
    /**
     * ID
     */
    private String accessKeyId;
    /**
     * 秘钥Secret
     */
    private String accessKeySecret;
    /**
     * 模板code
     */
    private String templateCode;
    /**
     * 短信签名名称
     */
    private String signName;
    /**
     * 阿里云短信域名
     */
    private String endpoint;


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

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
