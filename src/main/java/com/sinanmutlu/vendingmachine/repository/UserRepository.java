package com.sinanmutlu.vendingmachine.repository;

import com.sinanmutlu.vendingmachine.entity.Role;
import com.sinanmutlu.vendingmachine.entity.UserEnt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEnt, Long> {

    Optional<UserEnt> findByIdAndRole(Long sellerId, Role role);

    Optional<UserEnt> findByUsername(String username);

    Optional<UserEnt> findByUsernameAndPassword(String login, String password);
}