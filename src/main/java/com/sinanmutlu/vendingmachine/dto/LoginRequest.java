package com.sinanmutlu.vendingmachine.dto;

import com.sinanmutlu.vendingmachine.entity.Role;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginRequest {

    private String username;

    private String password;
}
