package com.dr.controller.myPage;

import com.dr.dto.myPage.PointDetailDTO;
import com.dr.dto.myPage.UserInfoDTO;
import com.dr.service.myPage.MyPageService;
import com.dr.service.rank.RankService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
@SessionAttributes("userNumber")
public class MyPageController {

    private final MyPageService myPageService;

    // -- 내 정보 확인하기 --
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

    // -- 회원탈퇴 주의사항 페이지로 넘어가기 --
    @GetMapping("/myPageCaution")
    public String getUserCaution() {

        return "myPage/myPageCaution";
    }

    // -- 회원 탈퇴 처리 -- //
    @PostMapping("/myPageDelete")
    public String deleteUser(HttpSession session) {
        Long userNumber = (Long) session.getAttribute("userNumber");

        // 회원탈퇴 서비스 호출
        myPageService.deleteUser(userNumber);

        // 세션 종료
       session.invalidate();

        // 회원 탈퇴 완료 페이지로!
        return "/myPage/myPageDelete";
    }

    // -- 내정보 포인트 내역 확인 -- //
    @GetMapping("/myPageMyPoint")
    public String getPointHistory(HttpSession session, Model model) {
        // 세션에서 userNumber 가져오고..
        Long userNumber = (Long) session.getAttribute("userNumber");
        // 세션에 userNumber가 없는 경우 로그인 페이지로 리다이렉트
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        List<PointDetailDTO> pointHistory = myPageService.pointHistory(userNumber);
        model.addAttribute("pointHistory", pointHistory);

        return "myPage/myPageMyPoint";
    }

        }