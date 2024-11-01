package com.dr.service.board;

import com.dr.dto.board.FreeBoardCommentDTO;
import com.dr.dto.board.FreeBoardDetailDTO;
import com.dr.dto.board.FreeBoardListDTO;
import com.dr.dto.board.HoneyBoardListDTO;
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

    // 자유게시판 최신순
    public List<FreeBoardListDTO> freeBoardList() {
        return boardMapper.freeBoardList();
    }


    // 추천순으로 자유게시판 리스트 가져오기
    public List<FreeBoardListDTO> freeBoardListGood() {
        return boardMapper.freeBoardListGood();
    }


    // 꿀팁게시판
    public List<HoneyBoardListDTO> honeyBoardList() {
        return boardMapper.honeyBoardList();
    }

    // 자유게시판 상세 조회
    public FreeBoardDetailDTO freeBoardDetail(Long boardNumber) {
        return boardMapper.freeBoardDetail(boardNumber);
    }

    // 댓글 목록 조회
    public List<FreeBoardCommentDTO> freeBoardCommentList(Long boardNumber) {
        return boardMapper.freeBoardCommentList(boardNumber);
    }


}
