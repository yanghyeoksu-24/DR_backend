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

    public PwFindDTO userPwFind(String userPhone , String userEmail) {
        return userMapper.userPwFind(userPhone, userEmail);
    }






}






