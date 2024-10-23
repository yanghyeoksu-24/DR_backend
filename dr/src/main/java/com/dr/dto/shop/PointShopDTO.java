package com.dr.dto.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PointShopDTO {
    private Long totalPoint;
    private String photoLocal;
    private String productName;
    private Long productPrice;
    private Long productCount;
}
