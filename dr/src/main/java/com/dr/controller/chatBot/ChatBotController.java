package com.dr.controller.chatBot;

import com.dr.dto.chatBot.ChatRequest;
import com.dr.dto.chatBot.ChatResponse;
import com.dr.service.chatBot.ChatBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 형태로 데이터를 반환 하기 위해 주로 REST API를 구현할 때 사용
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
public class ChatBotController {

    private final ChatBotService chatBotService;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        String userMessage = request.getMessage();
        String botReply;

        try {
            botReply = chatBotService.getChatbotResponse(userMessage);
            System.out.println("지피티 답변 : " + botReply);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ChatResponse("죄송합니다. 오류가 발생했습니다. 다시 시도해 주세요."));
        }

        return ResponseEntity.ok(new ChatResponse(botReply));
    }
}
