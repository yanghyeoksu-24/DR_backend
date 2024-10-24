package com.dr.mapper.shop;

import com.dr.dto.shop.PointShopDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointShopMapper {
    //현재 로그인 중인 회원의 포인트
    PointShopDTO getMyPoint(Long userNumber);

    //상품을 보여주기 위한 메소드
    List<PointShopDTO> selectAllProduct();
}
