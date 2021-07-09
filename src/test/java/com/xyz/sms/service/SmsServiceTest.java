package com.xyz.sms.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SmsServiceTest {
    @Resource(name = "aliSmsServiceImpl")
    private SmsService aliSmsService;
    @Resource(name = "masSmsServiceImpl")
    private SmsService masSmsService;

    /**
     * ali短信验证码
     */
    @Test
    void sendAliSms() {
        List<String> phoneNumbers = new LinkedList<>();
        phoneNumbers.add("手机号");
        JSONObject templateParam = new JSONObject();
        templateParam.set("code", "验证码");
        aliSmsService.sendSms(String.join(",", phoneNumbers), "", templateParam.toString());
    }

    /**
     * 移动短信验证码
     */
    @Test
    void sendMasSms() {
        List<String> phoneNumbers = new LinkedList<>();
        phoneNumbers.add("手机号");
        JSONArray param = new JSONArray();
        param.add("验证码");
        Boolean sendSms = masSmsService.sendSms(String.join(",", phoneNumbers), "短信模板ID", param.toString());
        System.out.println(sendSms);
    }
}