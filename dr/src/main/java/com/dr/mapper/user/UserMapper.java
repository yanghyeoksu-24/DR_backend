package com.dr.mapper.user;

import com.dr.dto.user.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    void insertUser(UserDTO userDTO);

    Optional<UserSessionDTO> userLogin(@Param("userEmail") String userEmail, @Param("userPw") String userPw);


    boolean checkEmailExists(String userEmail);


    boolean checkPhoneExists(String userPhone);

    EmailFindDTO userEmailFind(String userPhone);


    // 비밀번호 찾기 메서드
   String userPwFind(String userPhone, String userEmail);


    // 비밀번호 변경 메서드
    void updatePassword(String userPw , String userPhone);



}