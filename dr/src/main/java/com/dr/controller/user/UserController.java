package com.dr.controller.user;

import com.dr.dto.user.UserDTO;
import com.dr.dto.user.UserSessionDTO;
import com.dr.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @PostMapping("/drjoin")
//    public String drjoin(UserDTO userDTO) {
//        log.info("userDTO={}", userDTO);
//        userService.registerUser(userDTO);
//
//        return "redirect:/user/drJoin";
//    }




    //로그인 Controller
    @PostMapping("/user/login")
    public RedirectView login(@RequestParam("userEmail") String userEmail,
                              @RequestParam("userPw") String userPw,
                              HttpSession session) {
        UserDTO userLogin = userService.userLogin(userEmail, userPw);
        log.info("확인: {}", userLogin);

        if (userLogin != null) {
            System.out.println(userLogin);
            session.setAttribute("userNumber", userLogin.getUserNumber());
            session.setAttribute("userNickName", userLogin.getUserNickName());
            session.setAttribute("photoLocal", userLogin.getPhotoLocal());

            // 로그인 성공 시 헤더로 리다이렉트
            return new RedirectView("/fragment/header");
        } else {
            // 로그인 실패 시 로그인 페이지로 리다이렉트
            return new RedirectView("/user/login");
        }
    }

    @GetMapping("/fragment/header")
    public String mainPage(HttpSession httpSession) {
        // 세션에서 사용자 정보 가져오기
        long userNumber = (long) httpSession.getAttribute("userNumber");
        String userNickName = (String) httpSession.getAttribute("userNickName");
        String photoLocal = (String) httpSession.getAttribute("photoLocal");

        // 세션 정보를 사용하여 필요한 작업 수행
        System.out.println("User Number: " + userNumber);
        System.out.println("User Nickname: " + userNickName);
        System.out.println("Photo Local: " + photoLocal);

        // 세션 정보를 사용하여 메인 페이지로 이동
        return "/main"; // "/main"은 템플릿 이름으로, 해당 템플릿에서 세션 데이터를 사용할 수 있습니다.
    }

}


//    @GetMapping("/logout")
//    public RedirectView logout(HttpSession session) {
//        session.invalidate();
//
//        return new RedirectView("/user/login");
//    }

