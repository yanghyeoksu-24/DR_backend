package com.dr.controller.user;

import com.dr.dto.user.UserSessionDTO;
import com.dr.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @GetMapping("/user/drJoin")
    public String drJoinPage() {
        return "/user/drJoin";
    }

    @GetMapping("/user/login")
    public String loginPage() {
        return "/user/login";
    }

    // 로그인 Controller
    @PostMapping("/user/login")
    public RedirectView login(@RequestParam("userEmail") String userEmail,
                              @RequestParam("userPw") String userPw,
                              HttpSession session) {

        UserSessionDTO userLogin = userService.userLogin(userEmail, userPw);
        log.info("로그인 시도 확인: " + userLogin);

        if (userLogin != null) {
            // 세션에 사용자 정보를 설정
            session.setAttribute("userNumber", userLogin.getUserNumber());
            session.setAttribute("userNickName", userLogin.getUserNickName());
            session.setAttribute("photoLocal", userLogin.getPhotoLocal()); // photoLocal 추가

            // 메인 페이지로 리다이렉트
            return new RedirectView("/main");
        } else {
            // 로그인 실패 시 로그인 페이지로 다시 리다이렉트
            return new RedirectView("/user/login");
        }
    }



    // 로그아웃 Controller
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/user/login");
    }
}
