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

//    @GetMapping("/myRecipeList")
//    public String MyRecipeList(){
//
//        return "recipe/myRecipeList";
//    }

    @GetMapping("/myRecipeList")
    public String MyRecipeList(Model model) {
        // 전체 레시피 목록 조회
        List<MyRecipeListDTO> recipeList = recipeService.findAllRecipes();

        // 모델에 레시피 목록 추가
        model.addAttribute("recipeList", recipeList);

        return "recipe/myRecipeList";  // myRecipeList.html로 데이터 전달
    }

}
