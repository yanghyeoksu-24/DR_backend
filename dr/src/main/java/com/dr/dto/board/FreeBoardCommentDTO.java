package com.dr.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FreeBoardCommentDTO {
    private int commentId;
    private String commentContent;
    private String commentWriteDate;
    private String userNickName;
}
