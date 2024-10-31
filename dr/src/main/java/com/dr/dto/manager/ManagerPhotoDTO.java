package com.dr.dto.manager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ManagerPhotoDTO {
    private int photoNumber;
    private String photoName;
    private String photoUpload;
    private int productNumber;
    private String photoUuid;

}
