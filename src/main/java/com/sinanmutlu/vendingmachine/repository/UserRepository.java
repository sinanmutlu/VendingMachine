package com.sinanmutlu.vendingmachine.repository;

import com.sinanmutlu.vendingmachine.entity.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEnt, Long> {

    Optional<UserEnt> findByIdAndRoles(Long sellerId, String roles);

    Optional<UserEnt> findByUsername(String username);

}