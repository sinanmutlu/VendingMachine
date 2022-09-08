package com.sinanmutlu.vendingmachine.repository;

import com.sinanmutlu.vendingmachine.entity.Role;
import com.sinanmutlu.vendingmachine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndRole(Long sellerId, Role role);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String login, String password);
}