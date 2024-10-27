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
@RequestMapping("/shop") //최상위 경로
public class PointShopController {
    private final PointShopService pointShopService;
//    private final HttpSession session; // HttpSession 주입

    @PostMapping("/pointShop") //하위경로
    public String openPointShop(@SessionAttribute(value = "userNumber", required = false) Long userNumber, Model model) {
        //로그인하지 않았을 경우 처리
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

    @PostMapping("/buy")
    public String userBuyProducts(@RequestBody PointShopDTO pointShopDTO, HttpSession session) { //@RequestBody 로 DTO에 구매상품,갯수,총액 저장(ajax로 전송받은 데이터)
        // 세션에서 사용자 정보 가져오기
        Long userNumber = (Long) session.getAttribute("userNumber");

        // null 체크 - 로그인하지 않았을 경우 처리
        if (userNumber == null) {
            return "redirect:/user/login"; // 로그인 페이지로 리다이렉션
        }

        // 상품코드 가져오기
        List<String> code = pointShopService.getProductCode(pointShopDTO);
        System.out.println(code);
        // 상품코드 보낼 유저 핸드폰 가져오기
        String phone = pointShopService.getUserPhone(userNumber);
        System.out.println(phone);
        // 상품코드 문자로 전송

        // 전송끝난 코드 테이블에서 삭제
        pointShopService.deleteCode(pointShopDTO);

        // 포인트 테이블에 사용한 포인트 행 추가
        pointShopDTO.setUserNumber(userNumber);
        pointShopService.insertUsePoint(pointShopDTO);

        return "shop/pointShop";
    }

}
