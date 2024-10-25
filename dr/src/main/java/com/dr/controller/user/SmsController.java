package com.dr.controller.user;

import com.dr.service.user.CoolSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private CoolSmsService coolSmsService;

    @PostMapping("/send-code")
    public Map<String, String> sendSms(@RequestBody Map<String, String> body) {
        String phoneNumber = body.get("phoneNumber");
        Map<String, String> response = new HashMap<>();

        try {
            String generatedCode = coolSmsService.sendSms(phoneNumber);
            response.put("status", "success");
            response.put("message", "인증번호가 발송되었습니다.");
            response.put("authCode", generatedCode); // 실제 서비스에서는 코드 반환 X
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "문자 전송에 실패했습니다.");
        }

        return response;
    }
}
