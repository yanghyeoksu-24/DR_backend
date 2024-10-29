package com.dr.controller.recipe;

import com.dr.dto.recipe.*;
import com.dr.service.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/myRecipeList")
    public String MyRecipeList(Model model) {
        // 전체 레시피 목록 조회
        List<MyRecipeListDTO> recipeList = recipeService.findAllRecipes();

        // 모델에 레시피 목록 추가
        model.addAttribute("recipeList", recipeList);

        return "recipe/myRecipeList";  // myRecipeList.html로 데이터 전달
    }

    @GetMapping("/chatBotRecipeList")
    public String chatBotRecipeList(Model model) {
        // 전체 레시피 목록 조회
        List<ChatBotRecipeListDTO> recipeList = recipeService.findAllRecipes1();

        // 모델에 레시피 목록 추가
        model.addAttribute("recipeList", recipeList);

        return "recipe/chatBotRecipeList";  // chatBotRecipeList.html로 데이터 전달
    }

    @GetMapping("/myDetailPage")
    public String myDetailPage(@RequestParam("recipeNumber") Long recipeNumber, Model model) {
        // 특정 레시피의 상세 정보 조회
        MyRecipeDetailDTO recipeDetail = recipeService.findMyRecipeDetail(recipeNumber);

        log.info(recipeDetail+"ekwnlfgml;frmekrfgbn.");

        // 모델에 레시피 상세 정보 추가
        model.addAttribute("recipeDetail", recipeDetail);

        return "recipe/myDetailPage";  // myDetailPage.html로 데이터 전달
    }

    @GetMapping("/ChatBotDetailPage")
    public String ChatBotDetailPage(@RequestParam("recipeNumber") Long recipeNumber, Model model) {
        // 특정 레시피의 상세 정보 조회
        ChatBotRecipeDetailDTO recipeDetail = recipeService.findChatBotRecipeDetail(recipeNumber);

        log.info(recipeDetail + "ekwnlfgml;frmekrfgbn.");

        // 모델에 레시피 상세 정보 추가
        model.addAttribute("recipeDetail", recipeDetail);

        return "recipe/ChatBotDetailPage";  // ChatBotDetailPage.html로 데이터 전달
    }

    @GetMapping("/write")  // 레시피 작성 페이지로 이동
    public String recipeWriteForm(Model model) {
        model.addAttribute("myRecipeWriteDTO", new MyRecipeWriteDTO());  // 빈 DTO 객체 생성 및 전달
        return "recipe/writeRecipe";  // writeRecipe.html로 데이터 전달
    }

    @PostMapping("/write")  // 레시피 작성 폼에서 제출된 데이터를 처리
    public String submitRecipe(@ModelAttribute MyRecipeWriteDTO myRecipeWriteDTO, Model model) {
        try {
            recipeService.insertMyRecipe(myRecipeWriteDTO);  // 작성된 레시피를 DB에 삽입
            log.info("Recipe inserted successfully: " + myRecipeWriteDTO);
            return "redirect:/recipe/myDetailPage";  // 레시피 상세 페이지로 리다이렉트
        } catch (Exception e) {
            log.error("Error inserting recipe: " + e.getMessage());
            model.addAttribute("errorMessage", "레시피 작성 중 오류가 발생했습니다.");
            return "recipe/writeRecipe";  // 오류 발생 시 작성 페이지로 다시 이동
        }
    }


}
