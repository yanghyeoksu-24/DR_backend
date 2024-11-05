package com.dr.service.user;

import com.dr.dto.user.*;
import com.dr.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService<BCryptPasswordEncoder> {
    private final UserMapper userMapper;


    public boolean isEmailExists(String userEmail) {
        return userMapper.checkEmailExists(userEmail);
    }

    public boolean isPhoneExists(String userPhone) {
        return userMapper.checkPhoneExists(userPhone);
    }


    public void registerUser(UserDTO userDTO) {
        userMapper.insertUser(userDTO);
    }


    public UserSessionDTO userLogin(String userEmail, String userPw) {
        return userMapper.userLogin(userEmail, userPw).orElse(null);
    }

    public EmailFindDTO userFindEmail(String userPhone) {
        return userMapper.userEmailFind(userPhone);
    }


    //비밀번호 찾기
    public String userPwFind(String userPhone, String userEmail) {
        return userMapper.userPwFind(userPhone, userEmail);
    }

    //비밀번호 변경
    public void updatePassword(String userPw , String userPhone) {
        userMapper.updatePassword(userPw , userPhone);
    }

    // 닉네임 중복확인
    public int checkNickName(String userNickName) {
        return userMapper.checkNickName(userNickName);
    }

}






