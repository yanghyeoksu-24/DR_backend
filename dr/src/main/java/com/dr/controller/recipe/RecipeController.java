package com.dr.controller.recipe;

import com.dr.dto.recipe.MyRecipeListDTO;
import com.dr.service.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/myRecipeList")
    public String MyRecipeList(
            @RequestParam(defaultValue = "1") int page,      // 기본 페이지 번호
            Model model
    ) {
        int amount = 20;  // 페이지당 20개 항목

        // 레시피 목록을 가져옵니다.
        List<MyRecipeListDTO> recipeList = recipeService.findAllPage(page, amount);

        // 모델에 레시피 목록을 추가합니다.
        model.addAttribute("recipeList", recipeList);
        model.addAttribute("currentPage", page);
        model.addAttribute("amount", amount);

        return "recipe/myRecipeList";  // 레시피 목록 페이지로 이동
    }
}
