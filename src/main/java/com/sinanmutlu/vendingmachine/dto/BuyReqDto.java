package com.sinanmutlu.vendingmachine.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BuyReqDto {

    private Long userId;
    private Long productId;
    private int numberOfProducts;

}
