package com.dr.controller.recipe;

import com.dr.dto.recipe.*;
import com.dr.service.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @ResponseBody
    public List<MyRecipeListDTO> myRecipeListGood() {
        return recipeService.findAllRecipesGood();
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

    //    나만의레시피 상세페이지
    @GetMapping("/myDetailPage")
    public String myDetailPage(@RequestParam("recipeNumber") Long recipeNumber, Model model) {
        // 특정 레시피의 상세 정보 조회
        MyRecipeDetailDTO recipeDetail = recipeService.findMyRecipeDetail(recipeNumber);

        // 특정 레시피의 댓글 목록 조회
        List<MyRecipeCommentDTO> recipeComments = recipeService.selectMyRecipeReply(recipeNumber);

        log.info(recipeDetail+"ekwnlfgml;frmekrfgbn.");

        // 모델에 레시피 상세 정보 추가
        model.addAttribute("recipeDetail", recipeDetail);
        model.addAttribute("recipeComments", recipeComments);

        return "recipe/myDetailPage";  // myDetailPage.html로 데이터 전달
    }
//    나만의 레시피 상세페이지 댓글작성
// 댓글 작성 처리
@PostMapping("/myDetailPage")
public String insertComment(@RequestParam("recipeNumber") Long recipeNumber,
                            @RequestParam("replyText") String replyText,
                            @RequestParam("userNumber") Long userNumber) {
    MyRecipeWriteCommetDTO commentDTO = new MyRecipeWriteCommetDTO();
    commentDTO.setRecipeNumber(recipeNumber);
    commentDTO.setReplyText(replyText);
    commentDTO.setUserNumber(userNumber);

    recipeService.insertMyRecipeComment(commentDTO);

    // 댓글 작성 후 동일한 상세 페이지로 리다이렉트하여 갱신된 댓글 목록을 확인
    return "redirect:/recipe/myDetailPage?recipeNumber=" + recipeNumber;
}


    //    챗봇레시피 상세페이지
    @GetMapping("/ChatBotDetailPage")
    public String ChatBotDetailPage(@RequestParam("recipeNumber") Long recipeNumber, Model model) {
        // 특정 레시피의 상세 정보 조회
        ChatBotRecipeDetailDTO recipeDetail = recipeService.findChatBotRecipeDetail(recipeNumber);

        log.info(recipeDetail + "ekwnlfgml;frmekrfgbn.");

        // 모델에 레시피 상세 정보 추가
        model.addAttribute("recipeDetail", recipeDetail);

        return "recipe/ChatBotDetailPage";  // ChatBotDetailPage.html로 데이터 전달
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



}
