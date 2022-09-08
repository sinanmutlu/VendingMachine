package com.sinanmutlu.vendingmachine;

import com.sinanmutlu.vendingmachine.entity.Role;
import com.sinanmutlu.vendingmachine.entity.User;
import com.sinanmutlu.vendingmachine.repository.UserRepository;
import com.sinanmutlu.vendingmachine.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class VendingmachineApplication {
	public static void main(String[] args) {
		SpringApplication.run(VendingmachineApplication.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception {
		/*if (userRepository.count() == 0) {
			User user = new User();
			user.setUsername("user");
			user.setPassword("user");
			user.setDeposit(0);
			//user.setRole(Stream.of(new Role("SELLER"), new Role("BUYER")).collect(Collectors.toSet()));
			userRepository.save(user);
		}*/
		builder.userDetailsService((username) -> new CustomUserDetails(userRepository.findByUsername(username).get()));
	}

}
