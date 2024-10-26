package com.dr.controller.user;

import com.dr.service.user.CoolSmsService;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private CoolSmsService coolSmsService;

    @PostMapping("/send")
    public String sendSms(@RequestBody Map<String, String> body) {
        String phoneNumber = body.get("phoneNumber");
        try {
            String generatedCode = coolSmsService.sendSms(phoneNumber);
            return "인증코드가 발송되었습니다. " ; // 성공 메시지
        } catch (CoolsmsException e) {
            e.printStackTrace();
            return "Sms컨트롤러 sendSms 메소드 -------  " + e.getMessage(); // 실패 메시지
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그
            return "Sms컨트롤러 예외발생 : " + e.getMessage(); // 일반 예외 메시지
        }
    }
}

