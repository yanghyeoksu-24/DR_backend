package com.dr.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HoneyBoardDetailDTO {
    private Long boardNumber;
    private String boardTitle;
    private String boardText;
    private String boardWriteDate;
    private String userNickName;
    private int goodCount;
    private String photoLocal;
}
