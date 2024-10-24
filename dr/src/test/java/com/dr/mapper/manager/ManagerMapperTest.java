package com.dr.mapper.manager;

import com.dr.dto.manager.ManagerSessionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ManagerMapperTest {

    @Autowired
    ManagerMapper managerMapper;
    ManagerSessionDTO managerSessionDTO;

    @BeforeEach
    void setUp() {
    managerSessionDTO = new ManagerSessionDTO();
    managerSessionDTO.setManagerName("송아성");
    managerSessionDTO.setManagerEmail("manager1@dr.com");
    managerSessionDTO.setManagerPw("password1!");
    }

    // 1.관리자 로그인
    @Test
    void managerLogin(){
        //when
        String managerName = String.valueOf(managerMapper.managerLogin("manager1@dr.com", "password1!").get());

        //then
        System.out.println(managerName);
    }
}