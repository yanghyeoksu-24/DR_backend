package com.dr.mapper.board;

import com.dr.dto.board.*;
import com.dr.dto.recipe.MyRecipeWriteCommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    //자유게시판 최신순
    List<FreeBoardListDTO> freeBoardList();

    //자유게시판 추천순
    List<FreeBoardListDTO> freeBoardListGood();

    //꿀팁 게시판 최신순
    List<HoneyBoardListDTO> honeyBoardList();

    //꿀팁 게시판 추천순
    List<HoneyBoardListDTO> honeyBoardListGood();

    // 자유게시판 상세페이지 조회 메서드
    FreeBoardDetailDTO freeBoardDetail(@Param("boardNumber") Long boardNumber);

    // 자유게시판 댓글 목록 조회 메서드
    List<FreeBoardCommentDTO> freeBoardCommentList(@Param("boardNumber") Long boardNumber);

    // 꿀팁게시판 상세페이지 조회 메서드
    HoneyBoardDetailDTO honeyBoardDetail(@Param("boardNumber") Long boardNumber);

    // 꿀팁게시판 댓글 목록 조회 메서드
    List<HoneyBoardCommentDTO> honeyBoardCommentList(@Param("boardNumber") Long boardNumber);

    //  자유게시판 댓글 작성
    void freeBoardInsertReply(FreeBoardCommentDTO freeBoardCommentDTO);


}
