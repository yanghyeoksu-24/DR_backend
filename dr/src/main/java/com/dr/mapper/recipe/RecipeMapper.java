package com.dr.mapper.recipe;

import com.dr.dto.recipe.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecipeMapper {
    // 나만의 레시피 목록 조회 (최신순)
    List<MyRecipeListDTO> selectAllPages();

    // 나만의 레시피 목록 조회 (추천순)
    List<MyRecipeListDTO> selectRecipesGood();

//    챗봇레시피 목록 조회 (최신순)
    List<ChatBotRecipeListDTO> selectAllPages1();

    // 챗봇의 레시피 목록 조회 (추천순)
    List<ChatBotRecipeListDTO> selectRecipesGood1();

//    나만의레시피 상세페이지
    MyRecipeDetailDTO selectMyRecipeDetail(@Param("recipeNumber") Long recipeNumber);

//    챗봇 레시피 상세페이지
    ChatBotRecipeDetailDTO selectChatBotRecipeDetail(@Param("recipeNumber") Long recipeNumber);

//    나만의 레시피 댓글조회
    List<MyRecipeCommentDTO> selectMyRecipeComment(@Param("recipeNumber") Long recipeNumber);

//    챗봇 레시피 댓글조회
    List<ChatBotRecipeCommentDTO> selectChatBotRecipeComment(@Param("recipeNumber") Long recipeNumber);

    // 나만의 레시피 댓글 작성
    void insertReply(MyRecipeWriteCommentDTO myRecipeWriteCommentDTO);


//    나만의 레시피 글쓰기
    void insertMyRecipe(MyRecipeWriteDTO myRecipeWriteDTO);
}
