package com.dr.service.manager;

import com.dr.dto.manager.DashBoardDTO;
import com.dr.dto.manager.ManagerDTO;
import com.dr.dto.manager.ManagerSessionDTO;
import com.dr.dto.manager.ManagerUserDTO;
import com.dr.mapper.manager.ManagerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {
    @Mock
    ManagerMapper managerMapper;

    @InjectMocks
    ManagerService managerService;

    // 1. 관리자 로그인
    @Test
    public void managerLogin() {
        // given
        ManagerSessionDTO dto = new ManagerSessionDTO();
        dto.setManagerName("송아성");
        doReturn(Optional.of(dto)).when(managerMapper).managerLogin(any(), any());

        // when
        Optional<ManagerSessionDTO> result = Optional.ofNullable(managerService.managerLogin("manager1@dr.com", "password1!"));

        // then
        assertThat(result).isPresent(); // 결과가 존재하는지 확인
        assertThat(result.get().getManagerName()).isEqualTo("송아성"); // DTO의 managerName과 비교
    }

    // 2. 대시보드
    @Test
    public void managerDashBoard() {
        // given
        DashBoardDTO expectedDashBoardDTO = new DashBoardDTO();
        expectedDashBoardDTO.setUserAll(60); // 예상 사용자 수
        expectedDashBoardDTO.setNumAll(27); // 예상 총 수

        ManagerDTO expectedManagerDTO = new ManagerDTO();
        expectedManagerDTO.setManagerName("송아성"); // 예상 관리자 이름

        // Mocking
        doReturn(expectedDashBoardDTO).when(managerMapper).dashBoardInfo(); // 대시보드 정보 Mocking
        doReturn(List.of(expectedManagerDTO)).when(managerMapper).managerInfo(); // 관리자 정보 Mocking

        // when
        DashBoardDTO result = managerService.dashBoardInfo(); // 서비스 메서드 호출
        List<ManagerDTO> managerList = managerService.managerInfo(); // 관리자 정보 가져오기

        // then
        assertNotNull(result); // 결과가 null이 아님을 확인
        assertEquals(expectedDashBoardDTO.getUserAll(), result.getUserAll()); // 사용자 수 비교

        // 관리자 정보 비교
        ManagerDTO actualManagerDTO = managerList.get(0); // 0번째 인덱스 가져오기
        assertEquals(expectedManagerDTO.getManagerName(), actualManagerDTO.getManagerName()); // 관리자 이름 비교
    }

    // 3. 회원 관리
    @Test
    public void manageUser(){
        ManagerUserDTO managerUserDTO = new ManagerUserDTO();
        managerUserDTO.setUserEmail("manager1@dr.com");

        doReturn(List.of(managerUserDTO)).when(managerMapper).manageUser();

        List<ManagerUserDTO> userLists = managerService.manageUser();

        assertNotNull(userLists);

        ManagerUserDTO userList  = userLists.get(0);
        assertEquals(managerUserDTO.getUserEmail(), userList.getUserEmail());
    }

}
