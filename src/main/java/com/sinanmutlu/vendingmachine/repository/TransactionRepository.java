package com.sinanmutlu.vendingmachine.repository;

import com.sinanmutlu.vendingmachine.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}