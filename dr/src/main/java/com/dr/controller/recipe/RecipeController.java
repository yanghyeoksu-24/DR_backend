package com.dr.controller.recipe;

import com.dr.dto.board.BoardReportDTO;
import com.dr.dto.board.FreeBoardDetailDTO;
import com.dr.dto.recipe.*;
import com.dr.service.recipe.RecipeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.CtBehavior;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    //    나만의레시피최신순
    @GetMapping("/myRecipeList")
    public String MyRecipeList(Model model) {
        // 전체 레시피 목록 조회
        List<MyRecipeListDTO> recipeList = recipeService.findAllRecipes();
        // 모델에 레시피 목록 추가
        model.addAttribute("recipeList", recipeList);
        return "recipe/myRecipeList";  // myRecipeList.html로 데이터 전달
    }

    //    나만의 레시피 추천순
    @GetMapping("/myRecipeListGood")
    public String myRecipeListGood(Model model) {
        List<MyRecipeListDTO> recipeListGood = recipeService.findAllRecipesGood();
        model.addAttribute("recipeList", recipeListGood);
        return "recipe/myRecipeList";  // myRecipeList.html로 데이터 전달
    }



    //    나만의레시피 상세페이지 + 댓글 조회
    @GetMapping("/myDetailPage")
    public String myDetailPage(@RequestParam("recipeNumber") Long recipeNumber,  @SessionAttribute(value = "userNickName", required = false) String userNickName ,  Model model) {
        // 특정 레시피의 상세 정보 조회
        MyRecipeDetailDTO recipeDetail = recipeService.findMyRecipeDetail(recipeNumber);

        // 특정 레시피의 댓글 목록 조회
        List<MyRecipeCommentDTO> recipeComments = recipeService.selectMyRecipeComment(recipeNumber);
        log.info(recipeDetail.toString());
        log.info(recipeComments.toString());

        // 모델에 레시피 상세 정보 추가
        model.addAttribute("recipeDetail", recipeDetail);
        model.addAttribute("recipeComments", recipeComments);
        model.addAttribute("userNickName",userNickName);

        return "recipe/myDetailPage";  // myDetailPage.html로 데이터 전달
    }


    //    나만의 레시피 상세페이지 댓글작성
    @PostMapping("/myDetailPage")
    public String insertComment(@RequestParam("recipeNumber") Long recipeNumber,
                                @RequestParam("replyText") String replyText,
                                @RequestParam("userNumber") Long userNumber,
                                RedirectAttributes redirectAttributes) {
        if (recipeNumber == null) {
            throw new IllegalArgumentException("Recipe number is required.");
        }

        MyRecipeCommentDTO commentDTO = new MyRecipeCommentDTO();
        commentDTO.setRecipeNumber(recipeNumber);
        commentDTO.setReplyText(replyText);
        commentDTO.setUserNumber(userNumber);
        log.info(commentDTO.getReplyNumber() + "작성 확인====");
        log.info(commentDTO.getReplyText() + "작성 확인 === ");
        recipeService.insertMyRecipeComment(commentDTO);

        redirectAttributes.addAttribute("recipeNumber", recipeNumber);
        return "redirect:/recipe/myDetailPage";
    }

//    나만의 레시피 댓글 수정
    @PostMapping("/updateMyReply")
    public ResponseEntity<Void> updateMyRecipeComment(@RequestParam(name="replyNumber", required = false) Long replyNumber, @RequestParam("replyText") String replyText){
        log.info("컨트롤러 확인============");
        if (replyNumber == null || replyText == null || replyText.trim().isEmpty()) {
            log.info(replyNumber +" replyNumber 확인====");
            log.info(replyText +" replyText 확인====");
            log.info(replyText.trim().isEmpty() +" replyNumber 확인====");
            return ResponseEntity.badRequest().build(); // 잘못된 요청 처리
        }

        // 댓글 수정 서비스 호출
        recipeService.updateMyRecipeComment(replyNumber, replyText);
        // 수정 완료 후 성공 응답 반환
        return ResponseEntity.ok().build();
    }


    //      나만의 레시피 상세페이지 댓글 삭제
    //      @RequestParam(name = "userId", required = true
    @PostMapping("/deleteMyReply")
    public ResponseEntity<Void> deleteMyRecipeComment(@RequestParam("replyNumber") Long replyNumber) {
        if (replyNumber == null) {
            return ResponseEntity.badRequest().build(); // 잘못된 요청 처리
        }
        // 댓글 삭제 서비스 호출
        log.info(replyNumber.toString() + "댓글삭제");
        recipeService.deleteMyRecipeComment(replyNumber);
        // 삭제 완료 후 성공 응답 반환
        return ResponseEntity.ok().build();
    }

    //    챗봇 레시피 최신순
    @GetMapping("/chatBotRecipeList")
    public String chatBotRecipeList(Model model) {
        // 전체 레시피 목록 조회
        List<ChatBotRecipeListDTO> recipeList = recipeService.findAllRecipes1();
        // 모델에 레시피 목록 추가
        model.addAttribute("recipeList", recipeList);
        return "recipe/chatBotRecipeList";  // chatBotRecipeList.html로 데이터 전달
    }

    //    챗봇 레시피 추천순
    @GetMapping("/chatBotRecipeListGood")
    public String chatBotRecipeListGood(Model model) {
        List<ChatBotRecipeListDTO> recipeListGood = recipeService.findAllRecipes1Good();
        model.addAttribute("recipeList", recipeListGood);
        return "recipe/chatBotRecipeList";
    }

    //    챗봇레시피 상세페이지 + 댓글조회
    @GetMapping("/chatBotDetailPage")
    public String ChatBotDetailPage(@RequestParam("recipeNumber") Long recipeNumber,  @SessionAttribute(value = "userNickName", required = false) String userNickName ,  Model model) {
        // 특정 레시피의 상세 정보 조회
        ChatBotRecipeDetailDTO recipeDetail = recipeService.findChatBotRecipeDetail(recipeNumber);
        List<ChatBotRecipeCommentDTO> recipeComments = recipeService.selectChatBotRecipeComment(recipeNumber);
        // 모델에 레시피 상세 정보 추가
        model.addAttribute("recipeDetail", recipeDetail);
        model.addAttribute("recipeComments", recipeComments);
        model.addAttribute("userNickName",userNickName);
        return "recipe/chatBotDetailPage";  // ChatBotDetailPage.html로 데이터 전달
    }

    // 챗봇 레시피 댓글 작성
    @PostMapping("/chatBotDetailPage")
    public String ChatBotinsertComment(@RequestParam("recipeNumber") Long recipeNumber,
                                @RequestParam("replyText") String replyText,
                                @RequestParam("userNumber") Long userNumber,
                                RedirectAttributes redirectAttributes) {
        if (recipeNumber == null) {
            throw new IllegalArgumentException("Recipe number is required.");
        }

        ChatBotRecipeCommentDTO commentDTO = new ChatBotRecipeCommentDTO();
        commentDTO.setRecipeNumber(recipeNumber);
        commentDTO.setReplyText(replyText);
        commentDTO.setUserNumber(userNumber);
        recipeService.insertChatBotRecipeComment(commentDTO);

        redirectAttributes.addAttribute("recipeNumber", recipeNumber);
        return "redirect:/recipe/chatBotDetailPage";
    }

    //    챗봇 레시피 댓글 수정
    @PostMapping("/updateChatBotReply")
    public ResponseEntity<Void> updateChatBotRecipeComment(@RequestParam(name="replyNumber", required = false) Long replyNumber, @RequestParam("replyText") String replyText){
        if (replyNumber == null || replyText == null || replyText.trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // 잘못된 요청 처리
        }
        // 댓글 수정 서비스 호출
        recipeService.updateChatBotRecipeComment(replyNumber, replyText);
        // 수정 완료 후 성공 응답 반환
        return ResponseEntity.ok().build();
    }

    // 챗봇 레시피 상세페이지 댓글 삭제
    @PostMapping("/deleteChatBotReply")
    public ResponseEntity<Void> deleteChatBotRecipeComment(@RequestParam("replyNumber") Long replyNumber) {
        if (replyNumber == null) {
            return ResponseEntity.badRequest().build(); // 잘못된 요청 처리
        }
        // 댓글 삭제 서비스 호출
        recipeService.deleteChatBotRecipeComment(replyNumber);
        // 삭제 완료 후 성공 응답 반환
        return ResponseEntity.ok().build();
    }

    //    나만의레시피 글쓰기 페이지로 이동
    @GetMapping("/myRecipeWriter")  // 레시피 작성 페이지로 이동
    public String recipeWriteForm(Model model) {
        model.addAttribute("myRecipeWriteDTO", new MyRecipeWriteDTO());
        // 빈 DTO 객체 생성 및 전달
        log.info("여기는 GetMapping@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "recipe/myRecipeWriter";  // myRecipeWriter.html로 데이터 전달
    }

    //    나만의 레시피 글쓰기 db에 저장
    @PostMapping("/myRecipeWriter")  // 레시피 작성 폼에서 제출된 데이터를 처리
    public String submitRecipe(@ModelAttribute MyRecipeWriteDTO myRecipeWriteDTO, Model model) {
        try {
            recipeService.insertMyRecipe(myRecipeWriteDTO);  // 작성된 레시피를 DB에 삽입
            log.info("Recipe inserted successfully: " + myRecipeWriteDTO);
            return "redirect:/recipe/myDetailPage";  // 레시피 상세 페이지로 리다이렉트
        } catch (Exception e) {
            log.error("Error inserting recipe: " + e.getMessage());
            model.addAttribute("errorMessage", "레시피 작성 중 오류가 발생했습니다.");
            return "recipe/myRecipeWriter";  // 오류 발생 시 작성 페이지로 다시 이동
        }
    }

    // 나만의 레시피 추천 수 증가
    @PostMapping("/goodPlus")
    public ResponseEntity<Void> addGood(@RequestBody MyRecipeGoodDTO myRecipeGoodDTO,
                                     @SessionAttribute(value = "userNumber",required = false) Long userNumber) {
        myRecipeGoodDTO.setUserNumber(userNumber);
        recipeService.addGood(myRecipeGoodDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 나만의 레시피추천 수 감소
    @PostMapping("/goodMinus")
    public ResponseEntity<Void> removeGood(@RequestBody MyRecipeGoodDTO myRecipeGoodDTO,
                                        @SessionAttribute(value = "userNumber",required = false) Long userNumber) {
        myRecipeGoodDTO.setUserNumber(userNumber);
        recipeService.removeGood(myRecipeGoodDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 챗봇 레시피 추천 수 증가
    @PostMapping("/ChatBotGoodPlus")
    public ResponseEntity<Void> ChatBotAddGood(@RequestBody ChatBotRecipeGoodDTO chatBotRecipeGoodDTO,
                                        @SessionAttribute(value = "userNumber",required = false) Long userNumber) {
        chatBotRecipeGoodDTO.setUserNumber(userNumber);
        recipeService.ChatBotAddGood(chatBotRecipeGoodDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 챗봇 레시피 추천 수 감소
    @PostMapping("/ChatBotgoodMinus")
    public ResponseEntity<Void> ChatBotRemoveGood(@RequestBody ChatBotRecipeGoodDTO chatBotRecipeGoodDTO,
                                           @SessionAttribute(value = "userNumber",required = false) Long userNumber) {
        chatBotRecipeGoodDTO.setUserNumber(userNumber);
        recipeService.ChatBotRemoveGood(chatBotRecipeGoodDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

        @GetMapping("/report")
        public String recipeReportPage(@RequestParam("recipeNumber") Long recipeNumber,
                                      @RequestParam(value = "replyNumber", required = false) Long replyNumber,
                                      Model model) {
            model.addAttribute("recipeNumber", recipeNumber);
            model.addAttribute("replyNumber", replyNumber);
            return "/recipe/report";
        }


        @PostMapping("/reportOk")
        public String recipeReportOk(@RequestParam("recipeNumber") Long recipeNumber,
                                    @RequestParam(value = "replyNumber", required = false) Long replyNumber,
                                    @SessionAttribute(value = "userNumber", required = false) Long userNumber,
                                    @RequestParam("reason") String reason,
                                    @RequestParam(value = "otherReasonText", required = false) String otherReasonText,
                                    RedirectAttributes redirectAttributes) {
            MyRecipeDetailDTO myRecipeDetailDTO = recipeService.findMyRecipeDetail(recipeNumber);
            RecipeReportDTO recipeReportDTO = new RecipeReportDTO();

            // 1. 사유 지정
            if (otherReasonText != null && !otherReasonText.trim().isEmpty()) {
                recipeReportDTO.setSirenReason(otherReasonText);
            } else {
                recipeReportDTO.setSirenReason(reason);
            }

            // 2. sirenType 지정 및 게시판, 댓글 번호 지정
            if (replyNumber == null) {
                recipeReportDTO.setSirenType("레시피");
                recipeReportDTO.setRecipeNumber(recipeNumber);
            } else {
                recipeReportDTO.setSirenType("댓글");
                recipeReportDTO.setReplyNumber(replyNumber);
            }

            // 3. 유저넘버 지정
            recipeReportDTO.setUserNumber(userNumber);

            // 신고 처리
            recipeService.report(recipeReportDTO);

            // 4. 리디렉션 처리
            if ("나만의레시피".equals(myRecipeDetailDTO.getRecipeType())) {
                return "redirect:/recipe/myDetailPage?recipeNumber=" + recipeNumber;
            } else {
                return "redirect:/recipe/myDetailPage?recipeNumber=" + recipeNumber;
            }
        }


}
