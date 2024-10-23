package com.dr.service.shop;

import com.dr.dto.shop.PointShopDTO;
import com.dr.mapper.shop.PointShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PointShopService {
    private final PointShopMapper pointShopMapper;

    public PointShopDTO getMyPoint(Long userNumber) {
        return pointShopMapper.getMyPoint(userNumber);
    }

    public List<PointShopDTO> selectAllProduct() {
        return pointShopMapper.selectAllProduct();
    }
}
