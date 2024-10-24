package com.dr.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    //메인페이지
    @GetMapping("/main")
    public String openMain() {
        return "main";
    }

    //개인정보 처리방침
    @GetMapping("/privacyPolicy")
    public String openPrivacyPolicy() {
        return "main/privacyPolicy";
    }

    //이용약관
    @GetMapping("/terms")
    public String openTerms() {
        return "main/terms";
    }

}
