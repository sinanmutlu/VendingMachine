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
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private int amountAvailable;
    private int cost;
    private String productName;
    private Long sellerId;
}
