package com.sinanmutlu.vendingmachine.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserReqDto {

    private String username;
    private String password;
    private int deposit;
    private String roles;
}
