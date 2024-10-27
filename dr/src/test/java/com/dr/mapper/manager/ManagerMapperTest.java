package com.dr.mapper.manager;

import com.dr.dto.manager.DashBoardDTO;
import com.dr.dto.manager.ManagerDTO;
import com.dr.dto.manager.ManagerSessionDTO;
import com.dr.dto.manager.ManagerUserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ManagerMapperTest {

    @Autowired
    ManagerMapper managerMapper;
    ManagerSessionDTO managerSessionDTO;
    List<ManagerDTO> managerList;
    DashBoardDTO dashBoardDTO;
    List<ManagerUserDTO> managerUserList;
    ManagerUserDTO managerUserDTO;

    @BeforeEach
    void setUp() {
        managerSessionDTO = new ManagerSessionDTO();
        managerSessionDTO.setManagerName("송아성");
        managerSessionDTO.setManagerEmail("manager1@dr.com");
        managerSessionDTO.setManagerPw("password1!");
    }

    // 1.관리자 로그인
    @Test
    void managerLogin() {
        //when
        String managerName = String.valueOf(managerMapper.managerLogin("manager1@dr.com", "password1!").get());

        //then
        System.out.println(managerName);
    }

    // 2. 대시보드
    @Test
    void dashBoard() {
        // when
        dashBoardDTO = managerMapper.dashBoardInfo();
        managerList = managerMapper.managerInfo(); // 관리자 목록 초기화

        // 0번째 인덱스만 비교
        ManagerDTO managerDTO = managerList.get(0); // managerList 초기화 후 0번째 인덱스 가져오기

        // then
        assertNotNull(dashBoardDTO);
        assertNotNull(managerDTO);

        assertEquals(27, dashBoardDTO.getNumAll());
        assertEquals(60, dashBoardDTO.getUserAll());

        assertEquals("송아성", managerDTO.getManagerName());
    }

    //3. 회원 관리
    @Test
    void manageUser(){
        //when
        managerUserList = managerMapper.manageUser();

        managerUserDTO = managerUserList.get(3);

        //then
        assertNotNull(managerUserDTO);

        assertEquals("닉네임4" , managerUserDTO.getUserNickName());
    }
}