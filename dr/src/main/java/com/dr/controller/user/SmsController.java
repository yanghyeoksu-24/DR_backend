//package com.dr.controller.user;
//
//import com.dr.service.user.CoolSmsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/sms")
//public class SmsController {
//
//    @Autowired
//    private CoolSmsService coolSmsService;
//
//    @PostMapping("/send")
//    public String sendSms(@RequestBody Map<String, String> body) {
//        String phoneNumber = body.get("phoneNumber");
//        try {
//            String generatedCode = coolSmsService.sendSms(phoneNumber);
//            return "Generated verification code: " + generatedCode;
//        } catch (CoolsmsException e) {
//            e.printStackTrace();
//            return "Failed to send SMS: " + e.getMessage();
//        }
//    }
//}
//
