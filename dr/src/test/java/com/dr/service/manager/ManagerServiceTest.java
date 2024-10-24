package com.dr.service.manager;

import com.dr.dto.manager.ManagerSessionDTO;
import com.dr.mapper.manager.ManagerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
    void managerLogin() {
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


}