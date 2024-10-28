package com.dr.controller.manager;

import com.dr.dto.manager.*;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


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

    // 1. 로그인
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

    // 3-1. 회원관리
    @GetMapping("/manageUser")
    public String manageUser(Model model) {
        List<ManagerUserDTO> userList = managerService.manageUser();
        model.addAttribute("userList" , userList);

        return "/manager/manageUser";
    }

    // 3-2. 회원탈퇴
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

    // 3-3. 회원정지
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

    //4-1. 게시판 관리
    @GetMapping("/manageBoard")
    public String showBoard(Model model) {
    List<ManagerBoardDTO> boardList = managerService.showBoard();
    model.addAttribute("boardList" , boardList);
        return "/manager/manageBoard";
    }

    //4-2. 게시판 삭제
    @PostMapping("/boardDelete")
    public ResponseEntity<?> boardDelete(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> boardLists = request.get("boardNumber");
        boolean allDeleted = true;

        for (Integer boardList : boardLists) {
            if (!managerService.boardDelete(boardList)) {
                allDeleted = false; // 하나라도 실패하면 false
            }
        }

        if (allDeleted) {
            return ResponseEntity.ok("선택된 게시글이 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 삭제에 실패했습니다.");
        }
    }

    //5-1. 레시피 관리
    @GetMapping("/manageRecipe")
    public String showRecipe(Model model) {
        List<ManagerRecipeDTO> recipeList = managerService.showRecipe();
        model.addAttribute("recipeList" , recipeList);
        return "/manager/manageRecipe";
    }

    //5-2. 레시피 삭제
    @PostMapping("/recipeDelete")
    public ResponseEntity<?> recipeDelete(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> recipeLists = request.get("recipeNumber");
        boolean allDeleted = true;

        for (Integer recipeList : recipeLists) {
            if (!managerService.recipeDelete(recipeList)) {
                allDeleted = false; // 하나라도 실패하면 false
            }
        }

        if (allDeleted) {
            return ResponseEntity.ok("선택된 레피시가 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 삭제에 실패했습니다.");
        }
    }

    // 6-1. 댓글 관리
    @GetMapping("/manageComment")
    public String showComment(Model model) {
        List<ManagerCommentDTO> replyList = managerService.showReply();
        model.addAttribute("replyList" , replyList);
        return "/manager/manageComment";
    }

    // 6-2. 댓글 삭제
    @PostMapping("/replyDelete")
    public ResponseEntity<?> replyDelete(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> replyLists = request.get("replyNumber");
        boolean allDeleted = true;

        for (Integer replyList : replyLists) {
            if (!managerService.replyDelete(replyList)) {
                allDeleted = false; // 하나라도 실패하면 false
            }
        }

        if (allDeleted) {
            return ResponseEntity.ok("선택된 댓글이 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 삭제에 실패했습니다.");
        }
    }

    // 7-1. 포인트 관리
    @GetMapping("/managePoint")
    public String showPoint(@RequestParam(value = "month", required = false) Integer month, Model model) {
        List<ManagerPointDTO> pointList = managerService.showPoint();

        // 날짜 형식에 맞는 포맷터 생성 (예: "yyyy-MM-dd")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (month != null && month >= 1 && month <= 12) {
            pointList = pointList.stream()
                    .filter(point -> {
                        // 문자열에서 날짜 부분만 추출
                        String dateString = point.getPointDate().split(" ")[0]; // "2024-10-24"
                        LocalDate date = LocalDate.parse(dateString, formatter); // LocalDate로 변환
                        int pointMonth = date.getMonthValue();  // 월 값 추출
                        return pointMonth == month;
                    })
                    .collect(Collectors.toList());
        }

        model.addAttribute("pointList", pointList);
        model.addAttribute("selectedMonth", month);
        return "/manager/managePoint";
    }

    // 7-2. 포인트 삭제
    @PostMapping("/pointDelete")
    public ResponseEntity<?> pointDelete(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> pointLists = request.get("pointNumber");
        boolean allDeleted = true;

        for (Integer pointList : pointLists) {
            if (!managerService.pointDelete(pointList)) {
                allDeleted = false; // 하나라도 실패하면 false
            }
        }

        if (allDeleted) {
            return ResponseEntity.ok("선택된 포인트가 삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 삭제에 실패했습니다.");
        }
    }

    // 7-3. 포인트 회수
    @PostMapping("/takePoint")
    public ResponseEntity<?> takePoint(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> takePoints = request.get("pointNumber");
        boolean allUpdated = true;

        for (Integer takePoint : takePoints) {
            if (!managerService.takePoint(takePoint)) {
                allUpdated = false; // 하나라도 실패하면 false
            }
        }

        if (allUpdated) {
            return ResponseEntity.ok("선택된 포인트가 회수되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 삭제에 실패했습니다.");
        }
    }

    // 8-1. 신고 관리
    @GetMapping("/manageReport")
    public String showReport(Model model) {
        List<ManagerReportDTO> reportList = managerService.showReport();
        model.addAttribute("reportList" , reportList);
        return "/manager/manageReport";
    }

    // 8-2. 신고 삭제
    @PostMapping("/deleteReport")
    public ResponseEntity<?> deleteReport(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> sirenLists = request.get("sirenNumber");
        boolean allUpdated = true;

        for (Integer sirenList : sirenLists ) {
            if (!managerService.reportDelete(sirenList)) {
                allUpdated = false; // 하나라도 실패하면 false
            }
        }

        if (allUpdated) {
            return ResponseEntity.ok("선택된 신고가 삭제 되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 삭제에 실패했습니다.");
        }
    }

    //9-1. 상품 관리
    @GetMapping("/manageProduct")
    public String showProduct(Model model) {
        List<ManagerProductDTO> productList = managerService.showProduct();
        model.addAttribute("productList", productList);
        return "/manager/manageProduct";
    }

    //9-2. 상품 삭제
    @PostMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestBody Map<String, List<String>> request) {
        List<String> productLists = request.get("productName");
        boolean allUpdated = true;

        for (String productList : productLists ) {
            if (!managerService.productDelete(productList)) {
                allUpdated = false; // 하나라도 실패하면 false
            }
        }

        if (allUpdated) {
            return ResponseEntity.ok("선택된 상품이 삭제 되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일부 사용자 삭제에 실패했습니다.");
        }
    }

    // 9-3. 상품 등록





    // 9-4. 상품 수정



    // 로그아웃
    @GetMapping("/managerLogout")
    public RedirectView logout(HttpSession session, HttpServletResponse response){
        session.invalidate();
        return new RedirectView("/manager/managerLogin");
    }

}
