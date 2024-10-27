package com.dr.service.user;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class CoolSmsService {

    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    @Value("${coolsms.api.number}")
    private String fromPhoneNumber;

    public String sendSms(String to) throws Exception {
        String numStr = generateRandomNumber(); // 인증번호 생성

        Message coolsms = new Message(apiKey, apiSecret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", to);
        params.put("from", fromPhoneNumber);
        params.put("type", "sms");
        params.put("text", "인증번호는 [" + numStr + "] 입니다.");
        params.put("app_version", "test app 1.2"); // 선택 사항

        // 로그 추가
        System.out.println("Sending SMS with params: " + params);

        try {
            coolsms.send(params);
            return numStr;
        } catch (CoolsmsException e) {
            System.err.println("SMS 전송 실패: " + e.getMessage());
            throw new Exception("Failed to send SMS: " + e.getMessage());
        }
    }

    // 4자리 인증번호 생성
    private String generateRandomNumber() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}