package com.dr.mapper.manager;

import com.dr.dto.manager.*;
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

    //3. 회원 관리 (조회)
    @Test
    void manageUser(){
        //when
        managerUserList = managerMapper.manageUser();

        managerUserDTO = managerUserList.get(3);

        //then
        assertNotNull(managerUserDTO);

        assertEquals("닉네임4" , managerUserDTO.getUserNickName());
    }

    //4.게시글 관리 (삭제)
    @Test
    void board() {

        // when
        managerMapper.boardDelete(82);
        List<ManagerBoardDTO> managerBoardList = managerMapper.showBoard();

        // 테스트 항목이 삭제되었는지 확인
        if (!managerBoardList.isEmpty()) {
            ManagerBoardDTO managerBoardDTO = managerBoardList.get(0);

            // then
            assertNotEquals(82, managerBoardDTO.getBoardNumber(), "삭제된 게시물이 리스트에 포함되어 있습니다.");
        } else {
            // 만약 삭제 후 목록이 비어있다면 이는 삭제가 성공적으로 이루어졌다는 의미입니다.
            assertTrue(managerBoardList.isEmpty(), "게시물 목록이 비어 있어야 합니다.");
        }
    }

    // 5. 레시피 관리 (삭제)



    // 6. 댓글 관리 (조회)

    // 7. 포인트 ( 회수)

    // 8. 신고 관리 (삭제)

    // 9. 상품 관리 (등록)


}