package com.dr.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FreeBoardDetailDTO {
    private int boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardWriteDate;
    private String userNickName;
    private int recommendCount;
    private List<FreeBoardCommentDTO> comments;
}
