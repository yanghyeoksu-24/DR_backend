package com.dr.controller.main;

import com.dr.dto.main.ApiDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

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

    //검색창
    @GetMapping("/search")
    public String search() {
        return "main";
    }
}
