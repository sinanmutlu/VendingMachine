package com.sinanmutlu.vendingmachine.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEnt {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private int deposit;
    private String role;

}