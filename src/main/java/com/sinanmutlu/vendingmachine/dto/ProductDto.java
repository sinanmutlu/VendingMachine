package com.sinanmutlu.vendingmachine.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductDto {

    private Long id;
    private int amountAvailable;
    private int cost; //should be in multiples of 5
    private String productName;
    private Long sellerId;
}
