package com.dr.mapper.board;

import com.dr.dto.board.FreeBoardCommentDTO;
import com.dr.dto.board.FreeBoardDetailDTO;
import com.dr.dto.board.FreeBoardListDTO;
import com.dr.dto.board.HoneyBoardListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    //자유게시판 최신순
    List<FreeBoardListDTO> freeBoardList();

    //자유게시판 추천순 추가
    List<FreeBoardListDTO> freeBoardListGood();



    //꿀팁 게시판
    List<HoneyBoardListDTO> honeyBoardList();

    // 자유게시판 상세페이지 조회 메서드
    FreeBoardDetailDTO freeBoardDetail(@Param("boardNumber") Long boardNumber);

    // 자유게시판 댓글 목록 조회 메서드
    List<FreeBoardCommentDTO> freeBoardCommentList(@Param("boardNumber") Long boardNumber);


}
