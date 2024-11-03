package com.dr.service.board;

import com.dr.dto.board.*;
import com.dr.dto.recipe.MyRecipeWriteCommentDTO;
import com.dr.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    // 최신순으로 자유게시판 리스트 가져오기
    public List<FreeBoardListDTO> freeBoardList() {
        return boardMapper.freeBoardList();
    }

    // 추천순으로 자유게시판 리스트 가져오기
    public List<FreeBoardListDTO> freeBoardListGood() {
        return boardMapper.freeBoardListGood();
    }


    // 최신순으로 꿀팁게시판 리스트 가져오기
    public List<HoneyBoardListDTO> honeyBoardList() {
        return boardMapper.honeyBoardList();
    }

    // 추천순으로 꿀팁게시판 리스트 가져오기
    public List<HoneyBoardListDTO> honeyBoardListGood() {
        return boardMapper.honeyBoardListGood();
    }


    // 자유게시판 상세 조회
    public FreeBoardDetailDTO freeBoardDetail(Long boardNumber) {
        return boardMapper.freeBoardDetail(boardNumber);
    }

    // 자유게시판 댓글 목록 조회
    public List<FreeBoardCommentDTO> freeBoardCommentList(Long boardNumber) {
        return boardMapper.freeBoardCommentList(boardNumber);
    }

    // 꿀팁게시판 상세 조회
    public HoneyBoardDetailDTO honeyBoardDetail(Long boardNumber) {
        return boardMapper.honeyBoardDetail(boardNumber);
    }

    // 꿀팁게시판 댓글 목록 조회
    public List<HoneyBoardCommentDTO> honeyBoardCommentList(Long boardNumber) {
        return boardMapper.honeyBoardCommentList(boardNumber);
    }

    //    자유게시판 댓글작성
    public void freeBoardInsertReply(FreeBoardCommentDTO freeBoardCommentDTO) {
        boardMapper.freeBoardInsertReply(freeBoardCommentDTO);
    }


}
