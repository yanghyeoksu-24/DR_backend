package com.dr.controller.board;

import com.dr.dto.board.*;
import com.dr.dto.recipe.MyRecipeWriteCommentDTO;
import com.dr.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public String boardReportPage(@RequestParam("boardNumber") Long boardNumber,
                                  @RequestParam(value = "replyNumber", required = false) Long replyNumber,
                                  Model model) {
        // boardNumber, boardType, replyNumber를 사용하여 필요한 로직 처리
        model.addAttribute("boardNumber", boardNumber);
        model.addAttribute("replyNumber", replyNumber);
        return "/board/boardReport";
    }

    // 게시판 신고 컨트롤러
    @PostMapping("/reportOk")
    public String boardReportOk(@RequestParam("boardNumber") Long boardNumber,
                                @RequestParam(value = "replyNumber", required = false) Long replyNumber,
                                @SessionAttribute(value = "userNumber", required = false) Long userNumber,
                                @RequestParam("reason") String reason,
                                @RequestParam(value = "otherReasonText", required = false) String otherReasonText,
                                RedirectAttributes redirectAttributes) {

        FreeBoardDetailDTO freeBoardDetailDTO = boardService.freeBoardDetail(boardNumber);
        BoardReportDTO boardReportDTO = new BoardReportDTO();

        // 1. 사유 지정
        if (otherReasonText != null && !otherReasonText.trim().isEmpty()) {
            boardReportDTO.setSirenReason(otherReasonText);
        } else {
            boardReportDTO.setSirenReason(reason);
        }

        // 2. sirenType 지정 및 게시판, 댓글 번호 지정
        if (replyNumber == null) {
            boardReportDTO.setSirenType("게시글");
            boardReportDTO.setBoardNumber(boardNumber);
        } else {
            boardReportDTO.setSirenType("댓글");
            boardReportDTO.setReplyNumber(replyNumber);
        }

        // 3. 유저넘버 지정
        boardReportDTO.setUserNumber(userNumber);

        // 신고 처리
        boardService.report(boardReportDTO);

        // 4. 리디렉션 처리
        if ("자유게시판".equals(freeBoardDetailDTO.getBoardType())) {
            return "redirect:/board/freeBoardDetail?boardNumber=" + boardNumber;
        } else {
            return "redirect:/board/honeyBoardDetail?boardNumber=" + boardNumber;
        }
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


    //자유게시판 상세페이지(게시글 상세 + 댓글 조회)
    @GetMapping("/freeBoardDetail")
    public String freeBoardDetail(@RequestParam("boardNumber") Long boardNumber, Model model, @SessionAttribute(value = "userNickName", required = false) String userNickName) {


        FreeBoardDetailDTO freeBoardDetail = boardService.freeBoardDetail(boardNumber);

        List<FreeBoardCommentDTO> freeBoardComments = boardService.freeBoardCommentList(boardNumber);

        model.addAttribute("freeBoardDetail", freeBoardDetail);
        model.addAttribute("freeBoardComments", freeBoardComments);
        model.addAttribute("userNickName", userNickName);

        log.info(userNickName + "아아dkfsjgaljsdkgjng");
        log.info("===== BoardController 확인 : " + freeBoardComments);

        return "/board/freeBoardDetail";
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

    // 자유게시판 추천 수 증가
    @PostMapping("/freeGoodPlus")
    public ResponseEntity<Void> freeGoodPlus(
            @RequestBody FreeGoodDTO freeGoodDTO,
            @SessionAttribute(value = "userNumber", required = false) Long userNumber
    ) {
        freeGoodDTO.setUserNumber(userNumber);
        boardService.freeGoodPlus(freeGoodDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 자유게시판 추천 수 감소
    @PostMapping("/freeGoodMinus")
    public ResponseEntity<Void> freeGoodMinus(
            @RequestBody FreeGoodDTO freeGoodDTO,
            @SessionAttribute(value = "userNumber", required = false) Long userNumber
    ) {
        freeGoodDTO.setUserNumber(userNumber);
        boardService.freeGoodMinus(freeGoodDTO);

        return new ResponseEntity<>(HttpStatus.OK);
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

    //꿀팁게시판 상세페이지(게시글 상세 + 댓글)
    @GetMapping("/honeyBoardDetail")
    public String honeyBoardDetail(@RequestParam("boardNumber") Long boardNumber, Model model, @SessionAttribute(value = "userNickName", required = false) String userNickName) {
        HoneyBoardDetailDTO honeyBoardDetail = boardService.honeyBoardDetail(boardNumber);
        List<HoneyBoardCommentDTO> honeyBoardComments = boardService.honeyBoardCommentList(boardNumber);


        model.addAttribute("honeyBoardDetail", honeyBoardDetail);
        model.addAttribute("honeyBoardComments", honeyBoardComments);
        model.addAttribute("userNickName", userNickName);

        log.info(userNickName + "여긴 꿀팁게시판 상세 up");
        log.info("=======Honey Board 컨트롤러 확인용 : " + honeyBoardComments);

        return "/board/honeyBoardDetail";
    }


    //꿀팁게시판 상세 페이지 댓글 작성
    @PostMapping("/honeyBoardDetail")
    public String honeyBoardInsertReply(@RequestParam("boardNumber") Long boardNumber,
                                        @RequestParam("replyText") String replyText,
                                        @RequestParam("userNumber") Long userNumber,
                                        RedirectAttributes redirectAttributes) {

        if (boardNumber == null) {
            throw new IllegalArgumentException("Board number is required");
        }

        HoneyBoardCommentDTO honeyBoardCommentDTO = new HoneyBoardCommentDTO();
        honeyBoardCommentDTO.setBoardNumber(boardNumber);
        honeyBoardCommentDTO.setReplyText(replyText);
        honeyBoardCommentDTO.setUserNumber(userNumber);

        boardService.honeyBoardInsertReply(honeyBoardCommentDTO);

        // Redirect 후 댓글을 로드하기 위해 boardNumber를 추가
        redirectAttributes.addAttribute("boardNumber", boardNumber);

        return "redirect:/board/honeyBoardDetail"; // boardNumber를 쿼리 매개변수로 포함
    }

    //꿀팁게시판 댓글 수정
    @PostMapping("/honeyUpdateReply")
    public ResponseEntity<Void> updateHoneyBoardReply(@RequestParam("replyNumber") Long replyNumber,
                                                      @RequestParam("replyText") String replyText) {
        if (replyNumber == null || replyText == null || replyText.trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 잘못된 요청 처리
        }

        // 댓글 수정 서비스 호출
        boardService.honeyBoardUpdateReply(replyNumber, replyText);

        // 수정 완료 후 성공 응답 반환
        return ResponseEntity.ok().build();
    }

    // 꿀팁게시판 댓글 삭제
    @PostMapping("/honeyDeleteReply")
    public ResponseEntity<Void> deleteHoneyBoardReply(@RequestParam("replyNumber") Long replyNumber) {
        if (replyNumber == null) {
            return ResponseEntity.badRequest().build(); // 잘못된 요청 처리
        }

        // 댓글 삭제 서비스 호출
        boardService.honeyBoardDeleteReply(replyNumber);

        // 삭제 완료 후 성공 응답 반환
        return ResponseEntity.ok().build();
    }

    // 꿀팁게시판 추천 수 증가
    @PostMapping("/goodPlus")
    public ResponseEntity<Void> goodPlus(
            @RequestBody HoneyGoodDTO honeyGoodDTO,
            @SessionAttribute(value = "userNumber", required = false) Long userNumber
    ) {
        honeyGoodDTO.setUserNumber(userNumber);
        boardService.honeyGoodPlus(honeyGoodDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 꿀팁게시판 추천 수 감소
    @PostMapping("/goodMinus")
    public ResponseEntity<Void> goodMinus(
            @RequestBody HoneyGoodDTO honeyGoodDTO,
            @SessionAttribute(value = "userNumber", required = false) Long userNumber
    ) {
        honeyGoodDTO.setUserNumber(userNumber);
        boardService.honeyGoodMinus(honeyGoodDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}

