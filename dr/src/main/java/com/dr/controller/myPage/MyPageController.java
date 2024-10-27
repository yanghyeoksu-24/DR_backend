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
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage")
@SessionAttributes("userNumber")
public class MyPageController {

    private final MyPageService myPageService;

    // -- 내 정보 확인하기 --
    @GetMapping("/myPageInformation")
    public String getUserInfo(@SessionAttribute(value = "userNumber", required = false) Long userNumber, Model model) {

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
    public String getUserCaution(@SessionAttribute(value = "userNumber", required = false) Long userNumber) {

        return "myPage/myPageCaution";
    }

    // 회원탈퇴 처리 메서드
    @PostMapping("/myPageDelete")
    public String deleteUser(@SessionAttribute(value = "userNumber", required = false) Long userNumber,HttpSession session) {


        myPageService.deleteUser(userNumber);

        //세션 종료
        session.invalidate();

        return "redirect:/myPage/myPageDelete";
    }

    // -- 회원탈퇴 완료 페이지 --
    @GetMapping("/myPageDelete")
    public String getDeleteConfirmation(HttpSession session, Model model) {
        // 세션 정보가 없음을 확인
        if (session.getAttribute("userNumber") == null) {
            // 세션 정보가 없으면 추가적인 처리를 할 수 있음
        }
        return "myPage/myPageDelete";
    }

    // -- 내정보 포인트 내역 확인 -- //
    @GetMapping("/myPageMyPoint")
    public String getPointHistory(@SessionAttribute(value = "userNumber", required = false) Long userNumber,HttpSession session, Model model) {

        // 세션에 userNumber가 없는 경우 로그인 페이지로 리다이렉트
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        List<PointDetailDTO> pointHistory = myPageService.pointHistory(userNumber);
        model.addAttribute("pointHistory", pointHistory);

        return "myPage/myPageMyPoint";
    }

        }