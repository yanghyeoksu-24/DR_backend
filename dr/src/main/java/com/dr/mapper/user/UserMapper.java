package com.dr.mapper.user;

import com.dr.dto.user.EmailFindDTO;
import com.dr.dto.user.UserDTO;
import com.dr.dto.user.UserSessionDTO;
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
}