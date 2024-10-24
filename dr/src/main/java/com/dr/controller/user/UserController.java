package com.dr.controller.user;

import com.dr.dto.user.UserDTO;
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
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/drJoin")
    public String drJoinPage() {
        return "/user/drJoin";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "/user/login";
    }

    @PostMapping("/drjoin")
    public String drjoin(UserDTO userDTO) {
        log.info("userDTO={}", userDTO);
        userService.registerUser(userDTO);

        return "redirect:/user/drJoin";
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam("loginEmail") String loginEmail, @RequestParam("userPw") String userPw, HttpSession session) {
        log.info("로그인 시도 : {}", loginEmail);

        UserSessionDTO loginInfo = userService.findLoginInfo(loginEmail, userPw);

        session.setAttribute("userEmail", loginInfo.getUserEmail());
        session.setAttribute("loginEmail", loginInfo.getLoginEmail());

        return new RedirectView("/board/freeBoardList");
    }
    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();

        return new RedirectView("/user/login");
    }

}