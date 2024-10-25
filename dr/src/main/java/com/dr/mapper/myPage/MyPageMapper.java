package com.dr.mapper.myPage;

import com.dr.dto.myPage.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyPageMapper {
    // 내 정보 확인 : userNumber를 받아서 해당 유저의 정보 가지고 오기 !
    UserInfoDTO getUserInfo(@Param("userNumber") Long userNumber);
}
