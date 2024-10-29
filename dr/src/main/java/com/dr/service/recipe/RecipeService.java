package com.dr.service.recipe;

import com.dr.dto.recipe.ChatBotRecipeDetailDTO;
import com.dr.dto.recipe.ChatBotRecipeListDTO;
import com.dr.dto.recipe.MyRecipeDetailDTO;
import com.dr.dto.recipe.MyRecipeListDTO;
import com.dr.mapper.recipe.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeMapper recipeMapper;

    public List<MyRecipeListDTO> findAllRecipes() {
        return recipeMapper.selectAllPages();  // selectAllPage()는 페이지네이션 없이 전체 목록을 조회합니다.
    }

    public List<ChatBotRecipeListDTO> findAllRecipes1() {
        return recipeMapper.selectAllPages1();  // selectAllPage()는 페이지네이션 없이 전체 목록을 조회합니다.
    }

    public MyRecipeDetailDTO findMyRecipeDetail(Long recipeNumber) {
        return recipeMapper.selectMyRecipeDetail(recipeNumber);  // 특정 레시피의 상세 정보를 조회합니다.
    }

    public ChatBotRecipeDetailDTO findChatBotRecipeDetail(Long recipeNumber) {
        return recipeMapper.selectChatBotRecipeDetail(recipeNumber);  // 특정 레시피의 상세 정보를 조회합니다.
    }


}
