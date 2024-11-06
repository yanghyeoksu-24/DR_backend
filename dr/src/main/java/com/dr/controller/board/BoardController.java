package com.dr.controller.board;

import com.dr.dto.board.*;
import com.dr.dto.recipe.MyRecipeWriteCommentDTO;
import com.dr.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;


    // 게시판 신고 페이지 이동
    @GetMapping("/boardReport")
    public String boardReportPage() {
        return "/board/boardReport";
    }


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


    // 자유게시판 최신순 리스트 보여주기
    @GetMapping("/freeBoardList")
    public String freeBoardList(Model model) {
        List<FreeBoardListDTO> freeBoardList = boardService.freeBoardList();
        model.addAttribute("freeBoardList", freeBoardList);
        return "/board/freeBoardList";
    }

    // 자유게시판 추천순 리스트 보여주기
    @GetMapping("/freeBoardListGood")
    public String freeBoardListGood(Model model) {
        List<FreeBoardListDTO> freeBoardListGood = boardService.freeBoardListGood();
        model.addAttribute("freeBoardList", freeBoardListGood);
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

    // 꿀팁게시판 최신순 리스트 보여주기
    @GetMapping("/honeyBoardListGood")
    public String honeyBoardListGood(Model model) {
        List<HoneyBoardListDTO> honeyBoardListGood = boardService.honeyBoardListGood();
        model.addAttribute("honeyBoardList", honeyBoardListGood);
        return "/board/honeyBoardList";
    }

    //자유게시판 상세페이지(게시글 상세 + 댓글 조회)
    @GetMapping("/freeBoardDetail")
    public String freeBoardDetail(@RequestParam("boardNumber") Long boardNumber, Model model, @SessionAttribute(value = "userNickName", required = false) String userNickName) {


        FreeBoardDetailDTO freeBoardDetail = boardService.freeBoardDetail(boardNumber);

        List<FreeBoardCommentDTO> freeBoardComments = boardService.freeBoardCommentList(boardNumber);

        model.addAttribute("freeBoardDetail", freeBoardDetail);
        model.addAttribute("freeBoardComments", freeBoardComments);
        model.addAttribute("userNickName",userNickName);

        log.info(userNickName + "아아dkfsjgaljsdkgjng");
        log.info("===== BoardController 확인 : " + freeBoardComments);

        return "/board/freeBoardDetail";
    }



    //꿀팁게시판 상세페이지(게시글 상세 + 댓글)
    @GetMapping("/honeyBoardDetail")
    public String honeyBoardDetail(@RequestParam("boardNumber") Long boardNumber, Model model) {
        HoneyBoardDetailDTO honeyBoardDetail = boardService.honeyBoardDetail(boardNumber);
        List<HoneyBoardCommentDTO> honeyBoardComments = boardService.honeyBoardCommentList(boardNumber);

        model.addAttribute("honeyBoardDetail", honeyBoardDetail);
        model.addAttribute("honeyBoardComments", honeyBoardComments);

        return "/board/honeyBoardDetail";
    }

    //자유게시판 상세 페이지 댓글 작성
    @PostMapping("/freeBoardDetail")
    public String freeBoardInsertReply(@RequestParam("boardNumber") Long boardNumber,
                                       @RequestParam("replyText") String replyText,
                                       @RequestParam("userNumber") Long userNumber,
                                       RedirectAttributes redirectAttributes) {

        if (boardNumber == null) {
            throw new IllegalArgumentException("Board number is required");
        }

        FreeBoardCommentDTO freeBoardCommentDTO = new FreeBoardCommentDTO();
        freeBoardCommentDTO.setBoardNumber(boardNumber);
        freeBoardCommentDTO.setReplyText(replyText);
        freeBoardCommentDTO.setUserNumber(userNumber);

        boardService.freeBoardInsertReply(freeBoardCommentDTO);

        // Redirect 후 댓글을 로드하기 위해 boardNumber를 추가
        redirectAttributes.addAttribute("boardNumber", boardNumber);

        return "redirect:/board/freeBoardDetail"; // boardNumber를 쿼리 매개변수로 포함
    }

    //자유게시판 댓글 수정
    @PostMapping("/updateReply")
    public ResponseEntity<Void> updateFreeBoardReply(@RequestParam("replyNumber") Long replyNumber,
                                                     @RequestParam("replyText") String replyText) {
        if (replyNumber == null || replyText == null || replyText.trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 잘못된 요청 처리
        }

        // 댓글 수정 서비스 호출
        boardService.freeBoardUpdateReply(replyNumber, replyText);



        // 수정 완료 후 성공 응답 반환
        return ResponseEntity.ok().build();
    }



    // 자유게시판 댓글 삭제
    @PostMapping("/deleteReply")
    public ResponseEntity<Void> deleteFreeBoardReply(@RequestParam("replyNumber") Long replyNumber) {
        if (replyNumber == null) {
            return ResponseEntity.badRequest().build(); // 잘못된 요청 처리
        }

        // 댓글 삭제 서비스 호출
        boardService.freeBoardDeleteReply(replyNumber);

        // 삭제 완료 후 성공 응답 반환
        return ResponseEntity.ok().build();
    }




}
