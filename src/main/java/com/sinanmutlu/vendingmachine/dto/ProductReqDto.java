package com.sinanmutlu.vendingmachine.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductReqDto {

    private int amountAvailable;
    private int cost;
    private String productName;
    private Long sellerId;
}
