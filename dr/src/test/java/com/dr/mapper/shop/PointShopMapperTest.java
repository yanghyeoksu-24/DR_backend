package com.dr.mapper.shop;

import com.dr.dto.shop.PointShopDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PointShopMapperTest {

    @Autowired
    PointShopMapper pointShopMapper;

    @Test
    void testGetMyPoint() {
        Long userNumber = 7L; // 테스트할 사용자 번호
        Long totalPoint = pointShopMapper.getMyPoint(userNumber);

        // 예상 포인트 값과 비교할 수 있도록 설정
        assertNotNull(totalPoint, "포인트 조회 결과가 null이어서는 안됩니다.");
    }

    @Test
    void testSelectAllProduct() {
        List<PointShopDTO> products = pointShopMapper.selectAllProduct();

        // 결과가 비어있지 않음을 확인
        assertNotNull(products, "상품 목록이 null이어서는 안됩니다.");
        assertFalse(products.isEmpty(), "상품 목록이 비어있어서는 안됩니다.");


    }
}
