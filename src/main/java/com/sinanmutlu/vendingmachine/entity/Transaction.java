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
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String transactionType;
    private Long userId;
    private int transactionAmount;
    //TODO add createdDate and updatedDate generated
}
