package com.dr.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MyRecipeWriteDTO {
    private String recipeTitle;
    private String recipeText;
    private String recipeOriginal;
}
