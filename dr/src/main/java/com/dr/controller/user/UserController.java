package com.dr.controller.user;

import com.dr.dto.user.EmailFindDTO;
import com.dr.dto.user.UserDTO;
import com.dr.dto.user.UserSessionDTO;
import com.dr.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    // dr회원가입 페이지 이동
    @GetMapping("/user/drJoin")
    public String drJoinPage() {
        return "/user/drJoin";
    }

    // 로그인 페이지 이동
    @GetMapping("/user/login")
    public String loginPage() {
        return "/user/login";
    }

    // api회원가입 페이지 이동
    @GetMapping("/user/apijoin")
    public String apiJoinPage() {
        return "/user/apiJoin";
    }

    // 아이디 찾기 페이지 이동
    @GetMapping("/user/emailFind")
    public String emailFindPage() {
        return "/user/emailFind";
    }

    // 아이디 찾기 완료 페이지 이동
    @PostMapping("/user/emailFindOk")
    public String emailFindPage(@RequestParam("phone") String userPhone, Model model) {
        EmailFindDTO userEmail = userService.userFindEmail(userPhone);

        if (userEmail == null) {
            return "redirect:/user/emailFind"; // 전화번호가 일치하지 않으면 다시 이메일 찾기 페이지로 리다이렉트
        }

        model.addAttribute("userEmail", userEmail);
        return "/user/emailFindFinish"; // 이메일 찾기 완료 페이지로 이동
    }









    //drjoin 회원가입 요청 컨트롤러
    @PostMapping("/user/drJoin")
    public String join(@ModelAttribute UserDTO userDTO) {
        userService.registerUser(userDTO);

        return "/user/login";
    }


    // 이메일 중복 확인 요청 처리
    @GetMapping("/api/user/checkEmail")
    @ResponseBody // 특정 메서드만 JSON 형식 응답 반환
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam("userEmail") String userEmail) {
        boolean exists = userService.isEmailExists(userEmail);
        return ResponseEntity.ok(exists);
    }


    @PostMapping("/api/user/checkPhone")
    @ResponseBody
    public Map<String, Boolean> checkPhone(@RequestBody Map<String, String> request) {
        String userPhone = request.get("userPhone");
        boolean exists = userService.isPhoneExists(userPhone);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }




    // 로그인 요청 처리
    @PostMapping("/user/login")
    public RedirectView login(@RequestParam("userEmail") String userEmail,
                              @RequestParam("userPw") String userPw,
                              HttpSession session) {

        UserSessionDTO userLogin = userService.userLogin(userEmail, userPw);
        log.info("로그인 시도 확인: {}", userLogin);

        if (userLogin != null) {
            // 세션에 사용자 정보를 설정
            session.setAttribute("userNumber", userLogin.getUserNumber());
            session.setAttribute("userNickName", userLogin.getUserNickName());
            session.setAttribute("photoLocal", userLogin.getPhotoLocal()); // photoLocal 추가

            // 메인 페이지로 리다이렉트
            return new RedirectView("/main");
        } else {
            // 로그인 실패 시 로그인 페이지로 다시 리다이렉트
            return new RedirectView("/user/login?error=로그인실패");
        }
    }

    // 로그아웃 요청 처리
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/main");
    }
}

