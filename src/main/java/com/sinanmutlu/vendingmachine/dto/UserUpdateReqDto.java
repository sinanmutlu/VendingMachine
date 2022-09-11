package com.sinanmutlu.vendingmachine.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserUpdateReqDto {

    private Long id;
    private String password;
    private String roles;
}
