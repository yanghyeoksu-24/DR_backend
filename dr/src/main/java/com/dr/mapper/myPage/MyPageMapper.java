package com.dr.mapper.myPage;

import com.dr.dto.myPage.PointDetailDTO;
import com.dr.dto.myPage.UserInfoDTO;
import com.dr.dto.myPage.UserRecipeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPageMapper {
    // 내 정보 확인 : userNumber를 받아서 해당 유저의 정보 가지고 오기 !
    UserInfoDTO getUserInfo(@Param("userNumber") Long userNumber);

    // 회원 탈퇴
    int deleteUser(@Param("userNumber") Long userNumber);

    // 내 정보 포인트 내역 확인
    List<PointDetailDTO> pointHistory(@Param("userNumber") Long userNumber);

    //내 정보 내가 쓴 레시피 목록 확인
    List<UserRecipeDTO> getUserRecipe(@Param("userNumber") Long userNumber);
}
