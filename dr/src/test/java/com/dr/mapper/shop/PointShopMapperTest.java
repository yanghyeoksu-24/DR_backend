package com.dr.mapper.shop;

import com.dr.dto.shop.PointShopDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PointShopMapperTest {

    @Autowired
    PointShopMapper pointShopMapper;

    @Test
    void testGetMyPoint() {
        //given 초기값 설정
        Long userNumber = 7L; // 테스트할 사용자 번호

        //when 메소드 검사(실행)
        Long totalPoint = pointShopMapper.getMyPoint(userNumber); // given에 userNumber 넣어서 쿼리 실행

        //then 결과 검증
        assertThat(totalPoint)
                .isNotNull() // 결과가 null이 아님을 확인
                .isGreaterThanOrEqualTo(0L); // 포인트는 0 이상이어야 함

    }

    @Test
    void testSelectAllProduct() {
        List<PointShopDTO> products = pointShopMapper.selectAllProduct();


    }
}
