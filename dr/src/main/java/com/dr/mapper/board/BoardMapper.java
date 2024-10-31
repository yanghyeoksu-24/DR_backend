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

    //자유게시판
    List<FreeBoardListDTO> freeBoardList();

    //꿀팁 게시판
    List<HoneyBoardListDTO> honeyBoardList();

    // 자유게시판 상세페이지 조회 메서드
    FreeBoardDetailDTO getFreeBoardDetail(@Param("boardNumber") int boardNumber);

    // 자유게시판 댓글 목록 조회 메서드
    List<FreeBoardCommentDTO> getCommentsByBoardNumber(@Param("boardNumber") int boardNumber);


}
