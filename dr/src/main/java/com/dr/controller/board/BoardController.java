package com.dr.controller.board;

import com.dr.dto.board.FreeBoardCommentDTO;
import com.dr.dto.board.FreeBoardDetailDTO;
import com.dr.dto.board.FreeBoardListDTO;
import com.dr.dto.board.HoneyBoardListDTO;
import com.dr.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    // 자유게시판 페이지 이동
//    @GetMapping("/freeBoardList")
//    public String freeBoardListPage() {
//        return "/board/freeBoardList";
//    }

    // 꿀팁게시판 페이지 이동
//    @GetMapping("/honeyBoardList")
//    public String honeyBoardListPage() {
//        return "/board/honeyBoardList";
//    }

    // 게시판 신고 페이지 이동
    @GetMapping("/boardReport")
    public String boardReportPage() {
        return "/board/boardReport";
    }

    // 자유게시판 상세 페이지 이동
//    @GetMapping("/freeBoardDetail")
//    public String freeBoardDetailPage() {
//        return "/board/freeBoardDetail";
//    }

    // 자유게시판 글 수정 페이지 이동
    @GetMapping("/freeBoardModify")
    public String freeBoardModifyPage() {
        return "/board/freeBoardModify";
    }

    // 자유게시판 글 쓰기 페이지 이동
    @GetMapping("/freeBoardWrite")
    public String freeBoardWritePage() {
        return "/board/freeBoardWrite";
    }

    // 꿀팁게시판 상세 페이지 이동
    @GetMapping("/honeyBoardDetail")
    public String honeyBoardDetailPage() {
        return "/board/honeyBoardDetail";
    }

    // 꿀팁게시판 글 수정 이동
    @GetMapping("/honeyBoardModify")
    public String honeyBoardModifyPage() {
        return "/board/honeyBoardModify";
    }

    // 꿀팁게시판 글 쓰기 페이지 이동
    @GetMapping("/honeyBoardWrite")
    public String honeyBoardWritePage() {
        return "/board/honeyBoardWrite";
    }


    //자유게시판 리스트 보여주기
    @GetMapping("/freeBoardList")
    public String freeBoardList(Model model) {
        List<FreeBoardListDTO> freeBoardList = boardService.freeBoardList();
        model.addAttribute("freeBoardList", freeBoardList);
        System.out.println(freeBoardList + "여기서 확인해보자");
        return "/board/freeBoardList";
    }

    //꿀팁게시판 리스트 보여주기
    @GetMapping("/honeyBoardList")
    public String honeyBoardList(Model model) {
        List<HoneyBoardListDTO> honeyBoardList = boardService.honeyBoardList();
        model.addAttribute("honeyBoardList", honeyBoardList);
        System.out.println(honeyBoardList + "여기서 확인해보자");
        return "/board/honeyBoardList";
    }




    // 자유게시판 상세 페이지
    @GetMapping("/freeBoardDetail")
    public String getFreeBoardDetail(@RequestParam("boardNumber") int boardNumber, Model model) {
        FreeBoardDetailDTO boardDetail = boardService.getFreeBoardDetail(boardNumber);
        List<FreeBoardCommentDTO> comments = boardService.getCommentsByBoardNumber(boardNumber);
        model.addAttribute("board", boardDetail);
        model.addAttribute("comments", comments);
        return "/board/freeBoardDetail"; // Thymeleaf 템플릿 경로
    }







}
