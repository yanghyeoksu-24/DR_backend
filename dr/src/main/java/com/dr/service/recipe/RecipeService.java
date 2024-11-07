package com.dr.service.recipe;

import com.dr.dto.recipe.*;
import com.dr.mapper.recipe.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeMapper recipeMapper;

    //    나만의 레시피 최신순
    public List<MyRecipeListDTO> findAllRecipes() {
        return recipeMapper.selectAllPages();  // selectAllPage()는 페이지네이션 없이 전체 목록을 조회합니다.
    }

    //    나만의 레시피 추천순
    public List<MyRecipeListDTO> findAllRecipesGood() {
        return recipeMapper.selectRecipesGood();
    }


    //챗봇의 레시피 최신순
    public List<ChatBotRecipeListDTO> findAllRecipes1() {
        return recipeMapper.selectAllPages1();  // selectAllPage()는 페이지네이션 없이 전체 목록을 조회합니다.
    }

    //챗봇의 레시피 추천순
    public List<ChatBotRecipeListDTO> findAllRecipes1Good() {
        return recipeMapper.selectRecipesGood1();
    }

    //나만의레시피상세페이지
    public MyRecipeDetailDTO findMyRecipeDetail(Long recipeNumber) {
        return recipeMapper.selectMyRecipeDetail(recipeNumber);  // 특정 레시피의 상세 정보를 조회합니다.
    }

    //챗봇레시피 상세페이지
    public ChatBotRecipeDetailDTO findChatBotRecipeDetail(Long recipeNumber) {
        return recipeMapper.selectChatBotRecipeDetail(recipeNumber);  // 특정 레시피의 상세 정보를 조회합니다.
    }

    // 나만의 레시피 댓글 작성
    public void insertMyRecipeComment(MyRecipeWriteCommentDTO myRecipeWriteCommentDTO) {
        recipeMapper.insertReply(myRecipeWriteCommentDTO);
    }

    //나만의 레시피 댓글 수정
    public void updateMyRecipeComment(Long replyNumber, String replyText) {
        MyRecipeCommentDTO myRecipeCommentDTO = new MyRecipeCommentDTO();
        myRecipeCommentDTO.setReplyNumber(replyNumber);
        myRecipeCommentDTO.setReplyText(replyText);
        recipeMapper.updateMyReply(myRecipeCommentDTO);
    }

    //나만의 레시피 댓글 삭제
    public void deleteMyRecipeComment(Long replyNumber) {
        recipeMapper.deleteMyReply(replyNumber);
    }

    //나만의 레시피 댓글조회
    public List<MyRecipeCommentDTO> selectMyRecipeComment(Long recipeNumber) {
        // 특정 레시피의 댓글 목록을 조회하여 반환
        return recipeMapper.selectMyRecipeComment(recipeNumber);
    }

    //챗봇 레시피 댓글조회
    public List<ChatBotRecipeCommentDTO> selectChatBotRecipeComment(Long recipeNumber) {
        return recipeMapper.selectChatBotRecipeComment(recipeNumber);
    }


    //나만으레시피 글쓰기
    public void insertMyRecipe(MyRecipeWriteDTO myRecipeWriteDTO) {
        recipeMapper.insertMyRecipe(myRecipeWriteDTO);  // Toast API로 전달된 레시피 데이터를 DB에 삽입
    }

    // 추천 수 증가
    public void addGood(MyRecipeGoodDTO myRecipeGoodDTO) {
        recipeMapper.increaseGoodCount(myRecipeGoodDTO);
    }

    // 추천 수 감소
    public void removeGood(MyRecipeGoodDTO myRecipeGoodDTO) {
        recipeMapper.decreaseGoodCount(myRecipeGoodDTO);
    }

    // 찜 추가 메서드
    public void addSteam(MyRecipeDetailDTO myRecipeDetailDTO) {
        recipeMapper.addSteam(myRecipeDetailDTO);
    }

    // 찜 삭제 메서드
    public void removeSteam(MyRecipeDetailDTO myRecipeDetailDTO) {
        recipeMapper.removeSteam(myRecipeDetailDTO);
    }







}
