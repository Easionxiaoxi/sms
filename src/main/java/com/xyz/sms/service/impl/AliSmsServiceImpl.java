package com.xyz.sms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.Config;
import com.xyz.sms.config.AliSmsProperties;
import com.xyz.sms.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 阿里云短信V3版本
 */
@Service
public class AliSmsServiceImpl implements SmsService {
    // 注入属性配置
    @Resource
    private AliSmsProperties aliSMSProperties;

    /**
     * 发送短信
     *
     * @param phoneNumbers      手机号,多个用逗号分隔
     * @param templateCode      模板code
     * @param templateParamJson 短信模板变量对应的实际值，JSON格式{key,value}
     * @return Boolean
     */
    @Override
    public Boolean sendSms(String phoneNumbers, String templateCode, String templateParamJson) {
        // 配置
        Config config = new Config();
        config.setAccessKeyId(aliSMSProperties.getAccessKeyId());
        config.setAccessKeySecret(aliSMSProperties.getAccessKeySecret());
        config.setEndpoint(aliSMSProperties.getEndpoint());
        // 请求信息封装
        SendSmsRequest sms = new SendSmsRequest();
        sms.setPhoneNumbers(phoneNumbers);
        sms.setSignName(aliSMSProperties.getSignName());
        sms.setTemplateCode(ObjectUtils.isEmpty(templateCode) ? aliSMSProperties.getTemplateCode() : templateCode);
        sms.setTemplateParam(templateParamJson);
        try {
            // 创建客户端发送消息
            Client client = new Client(config);
            SendSmsResponse sendSmsResponse = client.sendSms(sms);
            SendSmsResponseBody body = sendSmsResponse.getBody();
            return ObjectUtil.equals("OK", body.getCode());
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 群发短信
     *
     * @param phoneNumberJson   接收短信的手机号码，JSON数组格式
     * @param signNameJson      短信签名名称，JSON数组格式
     * @param templateCode      短信模板CODE
     * @param templateParamJson 短信模板变量对应的实际值，JSON格式{key,value}
     * @return Boolean
     */
    @Override
    public Boolean sendBatchSms(String phoneNumberJson, String signNameJson, String templateCode, String templateParamJson) {
        // 配置
        Config config = new Config();
        config.setAccessKeyId(aliSMSProperties.getAccessKeyId());
        config.setAccessKeySecret(aliSMSProperties.getAccessKeySecret());
        config.setEndpoint(aliSMSProperties.getEndpoint());
        // 请求信息封装
        SendBatchSmsRequest batchSms = new SendBatchSmsRequest();
        batchSms.setPhoneNumberJson(phoneNumberJson);
        batchSms.setSignNameJson(signNameJson);
        batchSms.setTemplateCode(ObjectUtils.isEmpty(templateCode) ? aliSMSProperties.getTemplateCode() : templateCode);
        batchSms.setTemplateParamJson(templateParamJson);
        try {
            // 创建客户端发送消息
            Client client = new Client(config);
            SendBatchSmsResponse sendBatchSmsResponse = client.sendBatchSms(batchSms);
            SendBatchSmsResponseBody body = sendBatchSmsResponse.getBody();
            return ObjectUtil.equals("OK", body.getCode());
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }
}
