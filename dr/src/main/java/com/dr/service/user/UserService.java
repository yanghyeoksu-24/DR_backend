package com.dr.service.user;

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

    public void registerUser(UserDTO userDTO) {
        userMapper.insertUser(userDTO);
    }

    public Long selectEmail(String loginEmail, String userPw) {
        return userMapper.selectEmail(loginEmail, userPw).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원정보"));
        //selectId는 Optional 클래스의 타입으로 되어있으므로 Optional 클래스에 있는 메소드를 사용가능
        //Optional 객체 : 메소드 체이닝 이전에 호출된 메소드는 Optional 객체를 반환, Optional 은 값이 존재할 수 도 있고 존재하지 않을수도 있는 컨테이너 클래스
        //orElseThrow() Optional에 값이 존재하지 않을 경우(isPresent()가 false일경우)에 해당한다
    }

    public UserSessionDTO findLoginInfo(String loginEmail, String userPw) {
        return userMapper.selectLoginInfo(loginEmail, userPw).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원정보"));

    }

}