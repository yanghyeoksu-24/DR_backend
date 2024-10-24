package com.dr.service.recipe;

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

    // 페이지 번호와 항목 수를 파라미터로 받는 메서드
    public List<MyRecipeListDTO> findAllPage(int page, int amount) {
        return recipeMapper.selectAllPage(page, amount);
    }
}
