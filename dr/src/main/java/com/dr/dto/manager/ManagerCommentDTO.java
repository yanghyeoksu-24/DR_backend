package com.dr.dto.manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ManagerCommentDTO {
    private int replyNumber;
    private String replyText;
    private String userNickName;
    private String userPhone;
    private String replyWriteDate;
}
