package com.dr.service.myPage;

import com.dr.dto.myPage.*;
import com.dr.mapper.myPage.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    // myPageMapper 의존성 주입
    @Autowired
    private final MyPageMapper myPageMapper;

    // userNumber를 사용하여 내 정보 정보 조회!
    public UserInfoDTO getUserInfo(Long userNumber) {
        return myPageMapper.getUserInfo(userNumber);

    }

    // 닉네임 중복 확인 !
    public boolean checkNickname(String userNickname, String currentNickname) {
        // 현재 닉네임과 같은 경우는 중복으로 처리하지 않음
        if (userNickname.equals(currentNickname)) {
            return true; // 자기 자신은 중복이 아님
        }
        // 현재 닉네임과 다른 경우 중복 확인
        int count = myPageMapper.checkNickname(userNickname);
        return count == 0; // 카운트가 0이면 중복 안됨 !
    }

    // userNumber를 사용하여 회원 탈퇴하기!
    public void deleteUser(Long userNumber) {
        myPageMapper.deleteUser(userNumber);
    }

    // userNumber를 사용하여 내 정보 포인트내역 확인!
    public List<PointDetailDTO> pointHistory(Long userNumber) {
        return myPageMapper.pointHistory(userNumber);

    }

    // userNumber를 사용하여 내가 쓴 레시피 목록 확인!
    public List<UserRecipeDTO> getUserRecipe(Long userNumber) {
        return myPageMapper.getUserRecipe(userNumber);
    }

    // userNumber를 사용하여 내가 쓴 게시글 목록 확인!
    public List<UserPostDTO> getUserPost(Long userNumber) {
        return myPageMapper.getUserPost(userNumber);
    }

    // 해당 메서드는 userNumber에 해당하는 사용자의 찜 목록을 반환!
    public List<UserSteamDTO> getUserSteam(Long userNumber) {
        return myPageMapper.getUserSteam(userNumber);
    }

    // 찜 목록 삭제
    public void deleteUserSteam(UserSteamDTO userSteamDTO) {
        myPageMapper.deleteUserSteam(userSteamDTO);
    }

    // 신고 내역
    public List<SirenListDTO> getSirenList(Long userNumber) {
        return myPageMapper.getSirenList(userNumber);
    }

    // 출석 체크를 기록하는 메서드
    public void insertAttendanceCheck(UserCheckDTO userCheckDTO) {
        myPageMapper.insertAttendanceCheck(userCheckDTO.getUserNumber(), userCheckDTO.getDate());
    }

    // 포인트 기록을 추가하는 메서드
    public void insertPointRecord(PointRecordDTO pointRecordDTO) {
        myPageMapper.insertPointRecord(pointRecordDTO);
    }

    // 월간 출석 체크 카운트 조회 메서드
    public int checkMonthlyAttendance(UserCheckDTO userCheckDTO) {
        return myPageMapper.checkMonthlyAttendance(userCheckDTO.getUserNumber());
    }


}
