package com.sinanmutlu.vendingmachine.dto;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BuyResDto {

    private Long userId;
    private Long productId;
    private int numberOfProducts;
    private int price;
    private Map<String,Integer> change;

}
