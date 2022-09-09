package com.sinanmutlu.vendingmachine.dto;

import com.sinanmutlu.vendingmachine.entity.Role;
import lombok.*;

import java.util.List;

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
