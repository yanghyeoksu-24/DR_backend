package com.dr.service.user;

import com.dr.dto.user.EmailFindDTO;
import com.dr.dto.user.PwFindDTO;
import com.dr.dto.user.UserDTO;
import com.dr.dto.user.UserSessionDTO;
import com.dr.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




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

    public boolean userPwFind(PwFindDTO pwFindDTO) {
        int count = userMapper.userPwFind(pwFindDTO); // int로 결과 받기
        return count > 0; // 결과가 1 이상이면 true
    }


    public void changePassword(String userEmail, String newPassword) {
        // 비밀번호 유효성 검사 추가 가능 (예: 빈 문자열 또는 특정 패턴 검사)

        // 데이터베이스에서 비밀번호 업데이트
        userMapper.updatePassword(userEmail, newPassword);


    }

}






