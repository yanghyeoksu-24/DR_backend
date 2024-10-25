package com.dr.controller.shop;

import com.dr.dto.shop.PointShopDTO;
import com.dr.service.shop.PointShopService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userNumber")
@RequestMapping("/shop") //최상위 경로
public class PointShopController {
    private final PointShopService pointShopService;
//    private final HttpSession session; // HttpSession 주입

    @PostMapping("/pointShop") //하위경로
    public String openPointShop(Model model, HttpSession session) {
        // 세션에서 userNumber 가져오기
        Long userNumber = (Long) session.getAttribute("userNumber");

        // null 체크 - 로그인하지 않았을 경우 처리
        if (userNumber == null) {
            return "redirect:/user/login"; // 로그인 페이지로 리다이렉션
        }

        // 내 포인트 가져오기
        Long myPoint = pointShopService.getMyPoint(userNumber);

        // 상품 목록 가져오기
        List<PointShopDTO> productList = pointShopService.selectAllProduct();

        // 모델에 데이터를 담아 뷰에 전달
        model.addAttribute("myPoint", myPoint);       // 유저의 포인트
        model.addAttribute("productList", productList); // 상품 목록

        // shop/pointShop.html 템플릿을 반환
        return "shop/pointShop";
    }


}
