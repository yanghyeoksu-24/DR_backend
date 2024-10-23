package com.dr.mapper.shop;

import com.dr.dto.shop.PointShopDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointShopMapper {
    PointShopDTO getMyPoint(Long userNumber);

    List<PointShopDTO> selectAllProduct();
}
