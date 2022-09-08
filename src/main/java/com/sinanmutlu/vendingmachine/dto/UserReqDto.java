package com.sinanmutlu.vendingmachine.dto;

import com.sinanmutlu.vendingmachine.entity.Role;
import lombok.*;

import java.util.List;
import java.util.Set;

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
    private Set<Role> role;
}
