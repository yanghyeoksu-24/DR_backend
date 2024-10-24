package com.dr.service.shop;

import com.dr.dto.shop.PointShopDTO;
import com.dr.mapper.shop.PointShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //메서드나 클래스에 트랜잭션을 적용하여 데이터베이스 작업의 원자성을 보장하는 데 사용됩니다.
// 트랜잭션은 여러 데이터베이스 작업을 하나의 단위로 묶어 처리하며,
// 이 중 하나라도 실패하면 전체 작업이 취소되어 데이터 무결성을 유지합니다.
@RequiredArgsConstructor //final 필드의 생성자를 자동생성
public class PointShopService {
    // 맵퍼 인터페이스의 메소드를 사용하기 위한 필드
    private final PointShopMapper pointShopMapper;

    // 인터페이스의 내 포인트 조회 메소드 리턴
    public Long getMyPoint(Long userNumber) {
        return pointShopMapper.getMyPoint(userNumber);
    }

    // 인터페이스의 상품 조회 메소드 리턴
    public List<PointShopDTO> selectAllProduct() {
        return pointShopMapper.selectAllProduct();
    }
}
