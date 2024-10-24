package com.dr.controller.manager;

import com.dr.dto.manager.ManagerSessionDTO;
import com.dr.service.manager.ManagerService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ManagerController.class)  // 컨트롤러 명시
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagerService managerService;

    @MockBean
    private HttpSession session;

    @Test
    void testLogin() throws Exception {

        ManagerSessionDTO mockManagerDTO = new ManagerSessionDTO();
        mockManagerDTO.setManagerName("Manager Name");

        when(managerService.managerLogin(any(), any())).thenReturn(mockManagerDTO);

        mockMvc.perform(post("/manager/managerLogin")
                        .param("managerEmail", "manager1@dr.com")
                        .param("managerPw", "password1!"))
                .andExpect(status().is3xxRedirection()) // 리다이렉션 상태 확인
                .andExpect(redirectedUrl("/manager/dashBoard")); // 리다이렉션 URL 확인
    }

}
