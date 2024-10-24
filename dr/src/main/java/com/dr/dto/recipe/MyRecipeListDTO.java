package com.dr.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyRecipeListDTO {
    private String imageUrl;       // 이미지 경로
    private String recipeTitle;    // 레시피 제목
    private String writer;         // 작성자
    private String date;           // 작성일
    private int recommand;         // 추천수
}