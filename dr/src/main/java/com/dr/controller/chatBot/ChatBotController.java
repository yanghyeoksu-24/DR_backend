package com.dr.controller.chatBot;

import com.dr.dto.chatBot.ChatRequest;
import com.dr.dto.chatBot.ChatResponse;
import com.dr.dto.chatBot.NangjangbotDTO;
import com.dr.service.chatBot.ChatBotService;
import com.dr.service.chatBot.NangjangbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 형태로 데이터를 반환 하기 위해 주로 REST API를 구현할 때 사용
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
public class ChatBotController {

    private final ChatBotService chatBotService;
    private final NangjangbotService nangjangbotService;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request, @SessionAttribute(value = "userNumber", required = false) Long userNumber) {
        //로그인하지 않았을 경우 처리
        if (userNumber == null) {
//            checkUserLogin(userNumber);
        }

        // 메세지와 현재 세션 가져오기
        String userMessage = request.getMessage();
        Long sessionNumber = request.getSessionNumber();

        // 가져온 세션 Service 메소드 이용하기 위해 DTO에 저장
        NangjangbotDTO nangjangbotDTO = new NangjangbotDTO();
        nangjangbotDTO.setUserNumber(userNumber);
        nangjangbotDTO.setSessionTitle(userMessage);

        // 현재 채팅방 번호가 0이라면 채팅방 생성하여 sessionNumber 생성후 조회
        if(sessionNumber == 0L){
            // 채팅방 생성메소드
            nangjangbotService.createChatSession(nangjangbotDTO);
            // 방금 생성한 채팅방 조회하여 변수에 저장
            sessionNumber = nangjangbotService.getSessionNumber(userNumber);
        }

        // 응답메세지 저장할 변수
        String botReply;

        // 채팅방에 유저 질문 저장
        nangjangbotDTO.setUserNumber(userNumber);
        nangjangbotDTO.setSessionNumber(sessionNumber);
        nangjangbotDTO.setUserMsg(userMessage);
        nangjangbotService.insertUserMsg(nangjangbotDTO);

        //이전 채팅방 목록 불러오기
        List<NangjangbotDTO> chatList = nangjangbotService.getChatList(userNumber);

        // api 응답
        try {
            botReply = chatBotService.getChatbotResponse(userMessage);
            System.out.println("지피티 답변 : " + botReply);

            // 채팅방에 챗봇 응답 저장 (세션번호와 유저번호는 위에서 저장했으므로 여기서는 botReply만 셋팅)
            nangjangbotDTO.setBotReply(botReply);
            nangjangbotService.insertBotReply(nangjangbotDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ChatResponse("죄송합니다. 오류가 발생했습니다. 다시 시도해 주세요. 오류발생 채팅방 : ", sessionNumber));
        }

        // 생성된,현재 유지중인 세션 번호와 함께 챗봇 메세지 반환
        return ResponseEntity.ok(new ChatResponse(botReply, sessionNumber));
    }

    @PostMapping("/delete")
    public ResponseEntity<List> deleteChat(@RequestBody Long sessionNumber, @SessionAttribute(value = "userNumber", required = false) Long userNumber) {
//        //로그인하지 않았을 경우 처리
//        if (userNumber == null) {
//            return "redirect:/user/login";
//        }
        System.out.println("ddd" + sessionNumber);

        nangjangbotService.deleteChat(sessionNumber);
        List<NangjangbotDTO> newChatList = nangjangbotService.getChatList(userNumber);

        // 새로운 채팅 목록을 응답으로 반환
        return ResponseEntity.ok(newChatList);
    }

}
