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

    @PostMapping("/user/login")
    public RedirectView login(@RequestParam("userEmail") String userEmail, @RequestParam("userPw") String userPw, HttpSession session) {


        UserDTO userLogin = userService.userLogin(userEmail,userPw);
        log.info("확인" + userLogin);
        if(userLogin != null) {
            System.out.println(userLogin);
            session.setAttribute("userNumber", userLogin.getUserNumber());
            session.setAttribute("userNickName" , userLogin.getUserNickName());
            session.setAttribute("photoLocal", userLogin.getPhotoLocal());
            return new RedirectView("/fragment/header");
        }else{
            return new RedirectView("/user/login");

        }

    }
    
    @GetMapping("/fragment/header")
    public String mainPage(HttpSession httpSession, Model model) {
        long userNumber = (long)httpSession.getAttribute("userNumber");
        String userNickName = (String)httpSession.getAttribute("userNickName");
        model.addAttribute("userNumber", userNumber);
        model.addAttribute("userNickName", userNickName);
        model.addAttribute("photoLocal", (String)httpSession.getAttribute("photoLocal"));
        System.out.println(httpSession.getAttribute("photoLocal"));
        return "/main";
    }


    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();

        return new RedirectView("/user/login");
    }

}