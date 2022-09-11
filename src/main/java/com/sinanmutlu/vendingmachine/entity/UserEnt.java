package com.sinanmutlu.vendingmachine.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEnt {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private int deposit;
    private String roles; //can be enum

}
