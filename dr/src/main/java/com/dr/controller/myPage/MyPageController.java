package com.dr.controller.myPage;

import com.dr.dto.myPage.PointDetailDTO;
import com.dr.dto.myPage.UserInfoDTO;
import com.dr.dto.myPage.UserPostDTO;
import com.dr.dto.myPage.UserRecipeDTO;
import com.dr.service.myPage.MyPageService;
import com.dr.service.rank.RankService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor // 생성자를 자동 생성
@RequestMapping("/myPage") // 기본 url 설정
@SessionAttributes("userNumber") // 모델 속성 값을 세션에 저장
@Slf4j //로그 출력하려고. .
public class MyPageController {

    private final MyPageService myPageService;

    // -- 내 정보 확인하기 --
    @GetMapping("/myPageInformation")
    public String getUserInfo(@SessionAttribute(value = "userNumber", required = false) Long userNumber, Model model) {
    // value = userNumber는 세션에 저장된 값
    // userNumber가 없을 경우 null이 나오고, required = true로 설정하면 "userNumber" 세션이 없을 때 예외 발생
        // 세션에 userNumber가 없는 경우 로그인 페이지로 리다이렉트
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        UserInfoDTO userInfo = myPageService.getUserInfo(userNumber);
        model.addAttribute("userInfo", userInfo);
        //html에서 타임리프로 사용하기위해 userInfo 객체를 model에 추가

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

        if (userNumber == null) {
            return "redirect:/user/login"; // 로그인 페이지로 리다이렉트
        }

        myPageService.deleteUser(userNumber);

        //세션 종료
        session.invalidate();
        log.info("세션 종료됨: userNumber={}", userNumber);

        return "redirect:/myPage/myPageDelete";
    }

    // -- 회원탈퇴 완료 페이지 --
    @GetMapping("/myPageDelete")
    public String getDeleteConfirmation() {

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

    // -- 내정보 내가 쓴 레시피 확인 -- //
    @GetMapping("/myPageMyRecipe")
    public String getUserRecipes(@SessionAttribute(value = "userNumber", required = false) Long userNumber, Model model) {

        // 세션에 userNumber가 없는 경우 로그인 페이지로 리다이렉트
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        // 사용자가 쓴 레시피 목록 가져오기
        List<UserRecipeDTO> userRecipes = myPageService.getUserRecipe(userNumber);
        model.addAttribute("userRecipes", userRecipes);

        return "myPage/myPageMyRecipe";
    }

    // -- 내정보 내가 쓴 게시글 목록 확인 -- //
    @GetMapping("/myPageMyPost")
    public String getUserPosts(@SessionAttribute(value = "userNumber", required = false) Long userNumber, Model model){
        // 세션에 userNumber가 없는 경우 로그인 페이지로 리다이렉트
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        // 사용자가 쓴 게시글 목록 가져오기
        List<UserPostDTO> userPosts = myPageService.getUserPost(userNumber);
        model.addAttribute("userPosts", userPosts);

        return "myPage/myPageMyPost";
    }

        }