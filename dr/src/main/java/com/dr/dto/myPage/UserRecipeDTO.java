package com.dr.dto.myPage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserRecipeDTO {
    private String recipePhoto;
    private String recipeTitle;
    private String recipeWriteDate;
    private int goodCount;
}
