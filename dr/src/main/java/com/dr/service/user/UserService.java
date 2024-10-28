package com.dr.service.user;

import com.dr.dto.user.EmailFindDTO;
import com.dr.dto.user.PwFindDTO;
import com.dr.dto.user.UserDTO;
import com.dr.dto.user.UserSessionDTO;
import com.dr.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
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

    public boolean userPwFind(PwFindDTO pwFindDTO) {
        int count = userMapper.userPwFind(pwFindDTO); // int로 결과 받기
        return count > 0; // 결과가 1 이상이면 true
    }
}