package com.dr.dto.chatBot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {
    private String reply;
    private Long sessionNumber;
}
