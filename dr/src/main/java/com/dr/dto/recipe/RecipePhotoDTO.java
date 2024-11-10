package com.dr.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RecipePhotoDTO {
    private Long photoNumber;
    private String photoOriginal;
    private String photoLocal;
    private String photoSize;
    private String photoUpload;
    private Long userNumber;
    private Long recipeNumber;
}