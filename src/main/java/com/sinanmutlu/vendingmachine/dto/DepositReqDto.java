package com.sinanmutlu.vendingmachine.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DepositReqDto {

    private Long userId;
    private String coinType;

}
