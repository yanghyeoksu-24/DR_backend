package com.dr.mapper.myPage;

import com.dr.dto.myPage.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPageMapper {
    // 내 정보 확인 : 파라미터 타입 userNumber를 받아서 해당 유저의 정보 가지고 오기 !
    UserInfoDTO getUserInfo(@Param("userNumber") Long userNumber);

    // 닉네임 중복 확인
    int checkNickname(@Param("userNickname") String userNickname); // 파라미터 이름 변경

    // 회원 탈퇴
    int deleteUser(@Param("userNumber") Long userNumber);

    // 내 정보 포인트 내역
    List<PointDetailDTO> pointHistory(@Param("userNumber") Long userNumber);

    //내 정보 내가 쓴 레시피 목록
    List<UserRecipeDTO> getUserRecipe(@Param("userNumber") Long userNumber);

    //내 정보 내가 쓴 게시글 목록
    List<UserPostDTO> getUserPost(@Param("userNumber") Long userNumber);

    //UserSteamDTO 리스트를 반환하며, userNumber를 매개변수로 받아 사용자의 찜 목록을 조회
    List<UserSteamDTO> getUserSteam(@Param("userNumber") Long userNumber);

    //찜 목록 삭제
    void deleteUserSteam(UserSteamDTO userSteamDTO);

    //신고 내역
    List<SirenListDTO> getSirenList(@Param("userNumber") Long userNumber);

    // 내 정보 출석체크를 위한 데이터
    void insertAttendanceCheck(@Param("userNumber") Long userNumber, @Param("date") String date);

    // 내 정보 출석체크 포인트 기록을 위한 데이터
    void insertPointRecord(PointRecordDTO pointRecordDTO);

    // 월간 출석 체크 카운트 조회
    int checkMonthlyAttendance(@Param("userNumber") Long userNumber);




}
