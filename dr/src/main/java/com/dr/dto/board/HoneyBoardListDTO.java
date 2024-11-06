package com.dr.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HoneyBoardListDTO {
    private int boardNumber;
    private String boardTitle;
    private String userNickName;
    private int goodCount;
    private String boardWriteDate;
}
