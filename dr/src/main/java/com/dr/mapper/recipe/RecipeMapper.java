package com.dr.mapper.recipe;

import com.dr.dto.recipe.MyRecipeListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecipeMapper {
    // 페이지 번호와 항목 수를 파라미터로 받는 레시피 목록 조회 메서드
//    List<MyRecipeListDTO> selectAllPage(@Param("page") int page, @Param("amount") int amount);

    // 전체 레시피 목록 조회 메서드
    List<MyRecipeListDTO> selectAllPages();
}
