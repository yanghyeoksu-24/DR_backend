package com.dr.controller.manager;

import com.dr.dto.manager.ManagerSessionDTO;
import com.dr.service.manager.ManagerService;
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
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    // 1. 로그인
    @GetMapping("/managerLogin")
    public String login(HttpSession session , Model model) {
        String loginError = (String) session.getAttribute("loginError");
        if (loginError != null) {
            model.addAttribute("loginError", loginError); // 오류 메시지를 모델에 추가
            session.removeAttribute("loginError"); // 오류 메시지를 세션에서 제거
        }
        return "/manager/managerLogin";
    }

    @PostMapping("/managerLogin")
    public RedirectView login(@RequestParam("managerEmail") String managerEmail, @RequestParam("managerPw") String managerPw, HttpSession session) {
        log.info("로그인 시도: {}", managerEmail);
        log.info("로그인 시도: {}", managerPw);

        ManagerSessionDTO managerSession = managerService.managerLogin(managerEmail, managerPw);

        // 로그인 성공 시
        if (managerSession != null) {
            session.setAttribute("managerName", managerSession.getManagerName()); // 이름만 저장
            return new RedirectView("/manager/dashBoard");
        } else {
            session.setAttribute("loginError", "로그인 오류");
            return new RedirectView("/manager/managerLogin");
        }
    }

    // 2. 대시보드
    @GetMapping("/dashBoard")
    public String dashboard(HttpSession session, Model model) {
        String managerName = (String) session.getAttribute("managerName");
        model.addAttribute("managerName", managerName);
        return "/manager/dashBoard";
    }

}
