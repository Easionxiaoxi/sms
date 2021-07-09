package com.xyz.sms.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;;
import com.xyz.sms.config.MasSmsProperties;
import com.xyz.sms.service.SmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 移动短信服务
 */
@Service
public class MasSmsServiceImpl implements SmsService {
    // 注入属性配置
    @Resource
    private MasSmsProperties masSmsProperties;

    /**
     * 发送短信
     *
     * @param phoneNumbers      手机号，多个用逗号分隔
     * @param templateCode      模板code
     * @param templateParamJson 数组格式，多个参数值用空格分隔
     * @return Boolean
     */
    @Override
    public Boolean sendSms(String phoneNumbers, String templateCode, String templateParamJson) {
        masSmsProperties.setMobiles(phoneNumbers);
        masSmsProperties.setTemplateId(templateCode);
        // 数组类型的参数,多个参数值用空格分隔
        masSmsProperties.setParams(templateParamJson);
        // 参数签名
        masSmsProperties.setMac(getMac(masSmsProperties));
        // 发送http短信
        String httpSmsResult = sendHttpSms(masSmsProperties);
        // 解析结果
        JSONObject jsonObject = JSONUtil.parseObj(httpSmsResult);
        return ObjectUtil.equals(true, jsonObject.get("success"));
    }

    /**
     * 群发短信
     *
     * @param phoneNumberJson   接收短信的手机号码，JSON数组格式
     * @param SignNameJson      短信签名名称，JSON数组格式
     * @param templateCode      短信模板CODE
     * @param templateParamJson 短信模板变量对应的实际值，JSON格式
     * @return Boolean
     */
    @Override
    public Boolean sendBatchSms(String phoneNumberJson, String SignNameJson, String templateCode, String templateParamJson) {
        return null;
    }

    /**
     * 从属性中获取参数签名
     *
     * @param masSmsProperties 属性
     * @return 签名
     */
    private String getMac(MasSmsProperties masSmsProperties) {
        StringBuilder macData = new StringBuilder();
        macData.append(masSmsProperties.getEcName());
        macData.append(masSmsProperties.getApid());
        macData.append(masSmsProperties.getSecretKey());
        macData.append(masSmsProperties.getTemplateId());
        macData.append(masSmsProperties.getMobiles());
        macData.append(masSmsProperties.getParams());
        macData.append(masSmsProperties.getSign());
        macData.append(masSmsProperties.getAddSerial());
        return DigestUtil.md5Hex(macData.toString()).toLowerCase();
    }

    /**
     * 发送http短信
     *
     * @param masSmsProperties 属性
     * @return 发送结果
     */
    private String sendHttpSms(MasSmsProperties masSmsProperties) {
        String encodeParam = Base64.encode(JSONUtil.toJsonStr(masSmsProperties));
        return HttpRequest.post(masSmsProperties.getUrl()).contentType("UTF-8").keepAlive(true).body(encodeParam).execute().body();
    }
}
