package com.dr.controller.myPage;

import com.dr.dto.myPage.UserInfoDTO;
import com.dr.service.myPage.MyPageService;
import com.dr.service.rank.RankService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    // 내 정보 확인하기
    @GetMapping("/myPageInformation")
    public String getUserInfo(HttpSession session, Model model) {
        // 세션에서 userNumber 가져오고..
        Long userNumber = (Long) session.getAttribute("userNumber");
        // 세션에 userNumber가 없는 경우 로그인 페이지로 리다이렉트
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        UserInfoDTO userInfo = myPageService.getUserInfo(userNumber);
        model.addAttribute("userInfo", userInfo);

        return "myPage/myPageInformation";

    }
}