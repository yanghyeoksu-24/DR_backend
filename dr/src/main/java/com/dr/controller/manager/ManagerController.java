package com.dr.controller.manager;

import com.dr.dto.manager.DashBoardDTO;
import com.dr.dto.manager.ManagerDTO;
import com.dr.dto.manager.ManagerSessionDTO;
import com.dr.dto.manager.ManagerUserDTO;
import com.dr.service.manager.ManagerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;
import java.util.Date;
import java.util.Map;

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
    public String dashboard(@SessionAttribute(value = "managerName") String managerName, Model model, ManagerDTO managerDTO , DashBoardDTO dashBoardDTO) {

        List<ManagerDTO> managerList = managerService.managerInfo();
        dashBoardDTO = managerService.dashBoardInfo();

        model.addAttribute("manager" , managerList);
        model.addAttribute("dashBoard" ,dashBoardDTO);

        return "manager/dashBoard";
    }

    // 3. 회원관리
    @GetMapping("/manageUser")
    public String manageUser(ManagerDTO managerDTO, Model model , @SessionAttribute(value = "managerName") String managerName ) {
        List<ManagerUserDTO> userList = managerService.manageUser();
        model.addAttribute("userList" , userList);

        return "/manager/manageUser";
    }

    // 회원탈퇴
    @PostMapping("/userOut")
    public ResponseEntity<?> deleteUser(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> userNumbers = request.get("userNumber");
        boolean allDeleted = true;

        for (Integer userNumber : userNumbers) {
            if (!managerService.userOut(userNumber)) {
                allDeleted = false; // 하나라도 실패하면 false
            }
        }

        if (allDeleted) {
            return ResponseEntity.ok("선택된 사용자가 탈퇴 처리되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 삭제에 실패했습니다.");
        }
    }

    //회원정지
    @PostMapping("/userPause")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> userNumbers = request.get("userNumber");
        boolean allUpdated = true;


        for (Integer userNumber : userNumbers) {
            if (!managerService.userPause(userNumber)) {
                allUpdated = false; // 하나라도 실패하면 false
            }
        }

        if (allUpdated) {
            return ResponseEntity.ok("선택된 사용자가 정지 처리되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 정지에 실패했습니다.");
        }
    }

    //4. 게시판 관리
    @GetMapping("/manageBoard")
    public String manageBoard(){
        return "/manager/manageBoard";
    }




















    // 로그아웃
    @GetMapping("/managerLogout")
    public RedirectView logout(HttpSession session, HttpServletResponse response){
        session.invalidate();
        return new RedirectView("/manager/managerLogin");
    }

}
