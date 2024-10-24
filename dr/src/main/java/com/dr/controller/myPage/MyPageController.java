package com.dr.controller.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MyPageController {

    @GetMapping("myPage/myPageCaution")
    public String showCautionPage() {
        return "myPage/myPageCaution";
    }

    @GetMapping("myPage/myPageDelete")
    public String showNextPage() {
        return "myPage/myPageDelete";
    }

    @GetMapping("myPage/myPageInformation")
    public String showNextPage11() {
        return "myPage/myPageInformation";
    }





}