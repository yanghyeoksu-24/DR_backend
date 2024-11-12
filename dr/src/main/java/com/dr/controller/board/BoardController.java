package com.dr.controller.board;

import com.dr.dto.board.*;
import com.dr.service.board.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

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
                                @RequestParam(value = "otherReasonText", required = false) String otherReasonText
    ) {

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




    // 자유게시판 글 쓰기 페이지 이동
    @GetMapping("/freeBoardWrite")
    public String freeBoardWritePage(Model model) {
        model.addAttribute("freeBoardWriteDTO",new FreeBoardWriteDTO());
        return "/board/freeBoardWrite";
    }




    // 꿀팁게시판 글 쓰기 페이지 이동
    @GetMapping("/honeyBoardWrite")
    public String honeyBoardWritePage(Model model) {
        model.addAttribute("honeyBoardWriteDTO", new HoneyBoardWriteDTO());
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


    // 자유게시판 게시글 작성
    @PostMapping("/freeBoardWriteOk")
    public String freeBoardWrite(
            @RequestParam("boardTitle") String boardTitle,
            @RequestParam("boardText") String boardText,
            @RequestParam("photoOriginal") String photoOriginal,
            @RequestParam("photoLocal") String photoLocal,
            @RequestParam("photoSize") String photoSize,
            @SessionAttribute(value = "userNumber", required = false) Long userNumber) {
        // 1. FreeBoardWriteDTO 생성 (게시판정보)
       FreeBoardWriteDTO freeBoardWriteDTO = new FreeBoardWriteDTO();
       freeBoardWriteDTO.setBoardTitle(boardTitle);
       freeBoardWriteDTO.setBoardText(boardText);
       freeBoardWriteDTO.setUserNumber(userNumber);
       freeBoardWriteDTO.setBoardType("자유게시판");

       log.info(freeBoardWriteDTO.toString() + "잘 왔나나나ㅏ나나아아아아아");

       //2. FreeBoardPhotoDTO 생성(사진 정보)
        FreeBoardPhotoDTO freeBoardPhotoDTO = new FreeBoardPhotoDTO();
        freeBoardPhotoDTO.setPhotoOriginal(photoOriginal);
        freeBoardPhotoDTO.setPhotoLocal(photoLocal);
        freeBoardPhotoDTO.setPhotoSize(photoSize);

        log.info(freeBoardPhotoDTO.toString() + "sldfjldsakfjldakfjlskd");

        //3. BoardService 호출하여 게시판과 사진 저장
        boardService.saveFreeBoard(freeBoardWriteDTO, freeBoardPhotoDTO);

        //4. 성공 메시지 전달 후 , 리다이렉션
        return "redirect:/board/freeBoardList"; // 리다이렉트 URL은 필요에 맞게 수정

    }

    //자유게시판 게시글 삭제
    @PostMapping("/deleteFreeBoard")
    public String deleteFreeBoard(@RequestParam("boardNumber2") Long boardNumber) {
        boardService.freeBoardDeleteWriteAndPhoto(boardNumber);

        return "redirect:/board/freeBoardList";
    }



    //자유게시판 수정
    @GetMapping("/freeBoardModify")
    public String freeBoardModify(@RequestParam("boardNumber3") Long boardNumber,
                                  @RequestParam("boardTitle") String boardTitle,
                                  @RequestParam("boardText") String boardText,
                                  @RequestParam(value = "photoLocal", required = false) String photoLocal, // 사진 파일 경로 추가
                                  @RequestParam(value = "photoOriginal", required = false) String photoOriginal,
                                  @RequestParam(value = "photoSize", required = false) String photoSize,// 사진 파일 경로 추가
                                  Model model) {
        // 필요한 로직 처리 (예: 데이터베이스에서 게시글 정보 조회 등)

        model.addAttribute("boardNumber", boardNumber);
        model.addAttribute("boardTitle", boardTitle);
        model.addAttribute("boardText", boardText);
        model.addAttribute("photoLocal", photoLocal); // photoLocal 추가
        model.addAttribute("photoOriginal", photoOriginal); // photoOriginal 추가
        model.addAttribute("photoSize", photoSize);

        return "/board/freeBoardModify"; // 수정 페이지의 템플릿 이름
    }


    //자유게시판 수정
    @PostMapping("/updateFreeBoard")
    public String updateFreeBoard(@ModelAttribute FreeBoardUpdateDTO freeBoardUpdateDTO, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("photoLocal: " + freeBoardUpdateDTO.getPhotoLocal());
            System.out.println("photoOriginal: " + freeBoardUpdateDTO.getPhotoOriginal());

            boardService.freeBoardUpdateWriteAndPhoto(freeBoardUpdateDTO);
            redirectAttributes.addAttribute("boardNumber", freeBoardUpdateDTO.getBoardNumber());
            return "redirect:/board/freeBoardDetail";  // 수정된 글 상세 페이지로 이동
        } catch (Exception e) {
            // 에러 처리 (예: 에러 메시지 저장 후 수정 페이지로 이동)
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 수정에 실패했습니다.");
            return "redirect:/board/freeBoardModify"; // 실패 시 다시 수정 페이지로 이동
        }
    }



    // 꿀팁게시판 게시글 작성
    @PostMapping("/honeyBoardWriteOk")
    public String honeyBoardWrite(
            @RequestParam("boardTitle") String boardTitle,
            @RequestParam("boardText") String boardText,
            @RequestParam("photoOriginal") String photoOriginal,
            @RequestParam("photoLocal") String photoLocal,
            @RequestParam("photoSize") String photoSize,
            @SessionAttribute(value = "userNumber", required = false) Long userNumber) {
        // 1. HoneyBoardWriteDTO 생성 (게시판정보)
        HoneyBoardWriteDTO honeyBoardWriteDTO = new HoneyBoardWriteDTO();
        honeyBoardWriteDTO.setBoardTitle(boardTitle);
        honeyBoardWriteDTO.setBoardText(boardText);
        honeyBoardWriteDTO.setUserNumber(userNumber);
        honeyBoardWriteDTO.setBoardType("꿀팁게시판");

        log.info(honeyBoardWriteDTO.toString() + "잘 왔나나나ㅏ나나아아아아아");

        //2. FreeBoardPhotoDTO 생성(사진 정보)
        HoneyBoardPhotoDTO honeyBoardPhotoDTO = new HoneyBoardPhotoDTO();
        honeyBoardPhotoDTO.setPhotoOriginal(photoOriginal);
        honeyBoardPhotoDTO.setPhotoLocal(photoLocal);
        honeyBoardPhotoDTO.setPhotoSize(photoSize);

        log.info(honeyBoardPhotoDTO.toString() + "sldfjldsakfjldakfjlskd");

        //3. BoardService 호출하여 게시판과 사진 저장
        boardService.saveHoneyBoard(honeyBoardWriteDTO, honeyBoardPhotoDTO);

        //4. 성공 메시지 전달 후 , 리다이렉션
        return "redirect:/board/honeyBoardList"; // 리다이렉트 URL은 필요에 맞게 수정

    }

    //꿀팁게시판 게시글 삭제
    @PostMapping("/deleteHoneyBoard")
    public String deleteHoneyBoard(@RequestParam("boardNumber2") Long boardNumber) {
        boardService.honeyBoardDeleteWriteAndPhoto(boardNumber);

        return "redirect:/board/honeyBoardList";
    }




    //꿀팁게시판 수정
    @GetMapping("/honeyBoardModify")
    public String honeyBoardModify(@RequestParam("boardNumber3") Long boardNumber,
                                  @RequestParam("boardTitle") String boardTitle,
                                  @RequestParam("boardText") String boardText,
                                  @RequestParam(value = "photoLocal", required = false) String photoLocal, // 사진 파일 경로 추가
                                  @RequestParam(value = "photoOriginal", required = false) String photoOriginal,
                                  @RequestParam(value = "photoSize", required = false) String photoSize,// 사진 파일 경로 추가
                                  Model model) {
        // 필요한 로직 처리 (예: 데이터베이스에서 게시글 정보 조회 등)

        model.addAttribute("boardNumber", boardNumber);
        model.addAttribute("boardTitle", boardTitle);
        model.addAttribute("boardText", boardText);
        model.addAttribute("photoLocal", photoLocal); // photoLocal 추가
        model.addAttribute("photoOriginal", photoOriginal); // photoOriginal 추가
        model.addAttribute("photoSize", photoSize);

        return "/board/honeyBoardModify"; // 수정 페이지의 템플릿 이름
    }


    //자유게시판 수정
    @PostMapping("/updateHoneyBoard")
    public String updateHoneyBoard(@ModelAttribute HoneyBoardUpdateDTO honeyBoardUpdateDTO, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("photoLocal: " + honeyBoardUpdateDTO.getPhotoLocal());
            System.out.println("photoOriginal: " + honeyBoardUpdateDTO.getPhotoOriginal());

            boardService.honeyBoardUpdateWriteAndPhoto(honeyBoardUpdateDTO);
            redirectAttributes.addAttribute("boardNumber", honeyBoardUpdateDTO.getBoardNumber());
            return "redirect:/board/honeyBoardDetail";  // 수정된 글 상세 페이지로 이동
        } catch (Exception e) {
            // 에러 처리 (예: 에러 메시지 저장 후 수정 페이지로 이동)
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 수정에 실패했습니다.");
            return "redirect:/board/honeyBoardModify"; // 실패 시 다시 수정 페이지로 이동
        }
    }

}




