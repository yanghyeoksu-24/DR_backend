package com.dr.controller.myPage;

import com.dr.dto.myPage.*;
import com.dr.service.myPage.MyPageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //닉네임 중복 확인
    @PostMapping("/checkNickname")
    public ResponseEntity<Boolean> checkNickname(@RequestBody Map<String, String> requestBody) {
        String nickname = requestBody.get("nickname");
        String currentNickname = requestBody.get("currentNickname"); // 현재 사용자의 닉네임

        // 닉네임 중복 확인
        boolean isAvailable = myPageService.checkNickname(nickname, currentNickname);

        if (isAvailable) {
            return ResponseEntity.ok(false); //중복된 닉네임
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(true);
//            HTTP 응답 상태를 409(CONFLICT)로 설정하고 true를 반환합// 사용 가능
        }
    }


    //닉네임 및 이미지 파일 수정
    @PostMapping("/updateProfile")
    public String updateProfile(
            @SessionAttribute("userNumber") Long userNumber,  // 현재 로그인한 사용자의 번호
            @RequestParam("nickname") String nickname,        // 변경할 닉네임
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage, //MultipartFile 파일 업로드를 처리할 때 사용하는 인터페이스
            Model model, HttpSession session) throws IOException {

        // 닉네임이 입력되면 닉네임을 업데이트
        if (nickname != null && !nickname.isEmpty()) {
            myPageService.updateNickname(userNumber, nickname);
        }

        // 이미지 파일이 있으면 저장하기
        if (profileImage != null && !profileImage.isEmpty()) {
            String photoPath = myPageService.saveProfileImage(userNumber, profileImage);
            String photoPathWithTimestamp = photoPath + "?timestamp=" + System.currentTimeMillis();
            model.addAttribute("photoPath", photoPathWithTimestamp);
        }

        return "redirect:/myPage/myPageInformation";  // 업데이트 후 마이페이지로 리다이렉트. . . . .
    }


    // -- 회원탈퇴 주의사항 페이지로 넘어가기 --
    @GetMapping("/myPageCaution")
    public String getUserCaution() {

        return "myPage/myPageCaution";
    }

    //+테스트용 추가
    @GetMapping("/myPageUserDelete")
    public String deleteUser(){
        return "/myPage/myPageDeleted";
    }

    // -- 회원탈퇴 -- //
    @PostMapping("/myPageUserDelete")
    public RedirectView deleteUser(@SessionAttribute(value = "userNumber", required = false) Long userNumber,
                                   HttpSession session) {

        // 세션 무효화 및 사용자 삭제 처리
//        myPageService.deleteUser(userNumber);
        session.invalidate(); // 세션 무효화
        myPageService.deleteUser(userNumber);

        return new RedirectView("/myPage/myPageDeleted"); // 탈퇴 후 성공 페이지로 리다이렉트
//        return new RedirectView("/myPageDeleted"); // 탈퇴 후 성공 페이지로 리다이렉트
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

    // -- 내정보 찜 목록 확인 --
    @GetMapping("/myPageSteamedList")
    public String getUserSteam(@SessionAttribute(value = "userNumber", required = false) Long userNumber, Model model) {
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        List<UserSteamDTO> userSteamList = myPageService.getUserSteam(userNumber);
        System.out.println("찜 번호 : "+userSteamList);
        model.addAttribute("userSteamList", userSteamList);

        return "myPage/myPageSteamedList";
    }

    // -- 찜 삭제 -- //
    @PostMapping("/steamedDelete")
    public RedirectView deleteSteam(@SessionAttribute(value = "userNumber", required = false) Long userNumber,
                              @RequestParam(name = "recipeNumber") Long recipeNumber) {

        UserSteamDTO userSteamDTO = new UserSteamDTO();
        userSteamDTO.setUserNumber(userNumber);
        userSteamDTO.setRecipeNumber(recipeNumber);

        myPageService.deleteUserSteam(userSteamDTO);

        return new RedirectView("/myPage/myPageSteamedList");// 목록 페이지로 리다이렉트
    }

    // -- 신고 내역 목록 -- //
    @GetMapping("/myPageMyComplaint")
    public String getSirenList(@SessionAttribute(value = "userNumber", required = false) Long userNumber, Model model) {
        // 세션에 userNumber가 없는 경우 로그인 페이지로 리다이렉트
        if (userNumber == null) {
            return "redirect:/user/login";
        }

        // 사용자의 신고 내역 목록 가져오기
        List<SirenListDTO> sirenList = myPageService.getSirenList(userNumber);
        model.addAttribute("sirenList", sirenList);

        return "myPage/myPageMyComplaint";
    }

    @GetMapping("/myPageCheck")
    @ResponseBody
    public ResponseEntity<String> checkAttendance(@RequestParam("userNumber") String userNumber,
                                                  @RequestParam("date") String date) {
        // 출석 체크 로직 추가
        boolean isCheckedIn = checkAttendanceLogic(userNumber, date); // 예시 함수

        if (isCheckedIn) {
            return ResponseEntity.ok("출석 체크가 완료되었습니다.");
        } else {
            return ResponseEntity.ok("이미 출석 체크가 완료되었습니다."); // 예시 메시지
        }
    }

    private boolean checkAttendanceLogic(String userNumber, String date) {
        // 출석 체크 로직을 구현
        // 출석 체크된 상태인지 확인하고, 그렇지 않으면 체크 처리
        return true; // 출석 체크 성공 예시
    }
}


