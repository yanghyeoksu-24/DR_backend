package com.dr.mapper.recipe;

import com.dr.dto.recipe.MyRecipeListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecipeMapper {
    // 전체 레시피 목록 조회 메서드
    List<MyRecipeListDTO> selectAllPages();
}
