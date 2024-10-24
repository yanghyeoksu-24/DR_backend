package com.dr.controller.shop;

import com.dr.dto.shop.PointShopDTO;
import com.dr.service.shop.PointShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop") //최상위 경로
public class PointShopController {
    private final PointShopService pointShopService;

    @GetMapping("/pointShop") //하위경로
    public String openPointShop(Model model) {
        Long userNumber = 7L; // 임시로 하드코딩한 유저 번호 나중에 유저번호로 변경
        PointShopDTO myPoint = pointShopService.getMyPoint(userNumber);

        // 상품 목록 가져오기
        List<PointShopDTO> productList = pointShopService.selectAllProduct();

        // 모델에 데이터를 담아 뷰에 전달
        model.addAttribute("myPoint", myPoint);       // 유저의 포인트
        model.addAttribute("productList", productList); // 상품 목록

        // shop/pointShop.html 템플릿을 반환
        return "shop/pointShop";
    }
}
