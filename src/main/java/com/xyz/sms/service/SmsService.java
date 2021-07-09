package com.xyz.sms.service;

/**
 * 发送短信接口
 */
public interface SmsService {
    /**
     * 发送短信
     *
     * @param phoneNumbers      手机号
     * @param templateCode      模板code
     * @param templateParamJson 短信模板变量对应的实际值，JSON格式
     * @return Boolean
     */
    Boolean sendSms(String phoneNumbers, String templateCode, String templateParamJson);

    /**
     * 群发短信
     *
     * @param phoneNumberJson   接收短信的手机号码，JSON数组格式
     * @param SignNameJson      短信签名名称，JSON数组格式
     * @param templateCode      短信模板CODE
     * @param templateParamJson 短信模板变量对应的实际值，JSON格式
     * @return Boolean
     */
    Boolean sendBatchSms(String phoneNumberJson, String SignNameJson, String templateCode, String templateParamJson);
}
