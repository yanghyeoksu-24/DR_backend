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
    private String title;       //레시피제목
    private Long id;              // 레시피 ID
    private String image;       // 이미지 경로
    private String recipeTitle;    // 레시피 제목
    private String writer;         // 작성자
    private String date;           // 작성일
    private int recommendations;         // 추천수
}