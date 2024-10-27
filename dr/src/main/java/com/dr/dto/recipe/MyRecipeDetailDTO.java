package com.dr.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyRecipeDetailDTO {
    private Long recipeNumber;           // 레시피 번호 (ID 대신 사용)
    private String recipeTitle;          // 레시피 제목
    private String photoOriginal;          // 이미지 경로
    private String userNickName;          // 작성자
    private String recipeWriteDate;            // 작성일
    private Long replyNumber;           //댓글번호
    private String replyText;           //댓글내용
    private String replyWriteDate;      //댓글작성일자
    private int goodCount;     // 추천수
}
