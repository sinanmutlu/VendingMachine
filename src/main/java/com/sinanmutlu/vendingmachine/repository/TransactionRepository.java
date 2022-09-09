package com.sinanmutlu.vendingmachine.repository;

import com.sinanmutlu.vendingmachine.entity.TransactionEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEnt, Long> {

}