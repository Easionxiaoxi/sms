package com.xyz.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 移动短信属性配置
 */
@Component
@ConfigurationProperties(prefix = "mas.sms")
public class MasSmsProperties {
    /**
     * 客户名称
     */
    private String ecName;
    /**
     * 应用ID
     */
    private String apid;
    /**
     * 秘钥
     */
    private String secretKey;
    /**
     * 签名
     */
    private String sign;
    /**
     * 短信接口链接
     */
    private String url;

    // 以下非配置

    /**
     * 手机号
     */
    private String mobiles;
    /**
     * 短信模板ID
     */
    private String templateId;
    /**
     * 短信参数变量
     */
    private String params;
    /**
     * 参数签名
     */
    private String mac;
    /**
     * 扩展码
     */
    private String addSerial;

    public String getEcName() {
        return ecName;
    }

    public void setEcName(String ecName) {
        this.ecName = ecName;
    }

    public String getApid() {
        return apid;
    }

    public void setApid(String apid) {
        this.apid = apid;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAddSerial() {
        return addSerial;
    }

    public void setAddSerial(String addSerial) {
        this.addSerial = addSerial;
    }
}
