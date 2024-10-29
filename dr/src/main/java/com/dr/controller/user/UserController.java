package com.dr.controller.user;

import com.dr.dto.user.*;
import com.dr.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    @GetMapping("/user/emailFindFinish")
    public String emailFindFinishPage() {
        return "/user/emailFindFinish";
    }

    // 회원가입 종류 페이지 이동
    @GetMapping("/user/join")
    public String joinPage() {
        return "/user/join";
    }


    // 비밀번호 찾기 페이지 이동
    @GetMapping("/user/PwFind")
    public String PwFindPage() {
        return "/user/PwFind";
    }

    // 비밀번호 재설정 페이지 이동
    @GetMapping("/user/PwReset")
    public String PwResetPage() {
        return "/user/PwReset";
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

    //비밀번호 찾기 페이지
    @PostMapping("/user/PwFind")
    public String PwFind(RedirectAttributes redirectAttributes, @RequestParam("userEmail") String userEmail, @RequestParam("userPhone") String userPhone) {
        PwFindDTO pwFindDTO = userService.userPwFind(userEmail, userPhone);
        log.info(pwFindDTO.toString());

        if (pwFindDTO != null && pwFindDTO.getUserPhone() != null) {
            redirectAttributes.addFlashAttribute("userEmail", pwFindDTO.getUserEmail());
            redirectAttributes.addFlashAttribute("userPhone", pwFindDTO.getUserPhone()); // userPhone 추가
            log.info(pwFindDTO.toString());

            return "redirect:/user/PwReset"; // 이메일이 존재하면 PwReset 페이지로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "이메일이 일치하지 않습니다."); // 에러 메시지 추가
            return "redirect:/user/PwFind"; // 이메일이 존재하지 않으면 PwFind 페이지로 리다이렉트
        }
    }



    //비밀번호 변경 페이지
    @PostMapping("/user/PwReset")
    public String PwReset(Model model, @RequestParam("newPassword") String userPw, @RequestParam("userPhone") String userPhone) {
        PwFindDTO pwFindDTO = new PwFindDTO();

        model.addAttribute("userPhone", pwFindDTO.getUserPhone());
        log.info(pwFindDTO.getUserPhone() +"출력");

        userService.updatePassword(userPw, userPhone);
        return "redirect:/user/login";
    }


    //drjoin 회원가입 요청 컨트롤러
    @PostMapping("/user/drJoin")
    public String join(@ModelAttribute UserDTO userDTO) {
        userService.registerUser(userDTO);
        return "redirect:/user/login";
    }


    // 이메일 중복 확인 요청 처리
    @GetMapping("/api/user/checkEmail")
    @ResponseBody // 특정 메서드만 JSON 형식 응답 반환
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam("userEmail") String userEmail) {
        boolean exists = userService.isEmailExists(userEmail);
        return ResponseEntity.ok(exists);
    }

    // 핸드폰 중복 확인 요청 처리
    @PostMapping("/api/user/checkPhone")
    @ResponseBody
    public Map<String, Boolean> checkPhone(@RequestBody Map<String, String> request) {
        String userPhone = request.get("phoneNumber");
        boolean exists = userService.isPhoneExists(userPhone);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

    //로그인 페이지
    @PostMapping("/user/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam("userEmail") String userEmail,
                                                     @RequestParam("userPw") String userPw,
                                                     HttpSession session) {
        UserSessionDTO userLogin = userService.userLogin(userEmail, userPw);
        log.info("로그인 시도 확인: {}", userLogin);

        Map<String, String> response = new HashMap<>();

        if (userLogin != null) {
            // 세션에 사용자 정보를 설정
            session.setAttribute("userNumber", userLogin.getUserNumber());
            session.setAttribute("userNickName", userLogin.getUserNickName());
            session.setAttribute("photoLocal", userLogin.getPhotoLocal());

            // 로그인 성공 시 성공 메시지와 리다이렉트 URL 반환
            response.put("message", "환영합니다.");
            response.put("redirect", "/main");
            return ResponseEntity.ok(response);
        } else {
            // 로그인 실패 시 오류 메시지와 리다이렉트 URL 반환
            response.put("message", "이메일이나 비밀번호가 잘못되었습니다.");
            response.put("redirect", "/user/login"); // 로그인 페이지로 리다이렉트
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // 401 Unauthorized
        }
    }


    // 로그아웃 요청 처리
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/main");
    }

}


