package com.sinanmutlu.vendingmachine.auth;

import com.sinanmutlu.vendingmachine.entity.Role;
import com.sinanmutlu.vendingmachine.entity.UserEnt;
import com.sinanmutlu.vendingmachine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEnt> userEnt = userRepository.findByUsername(s);

        List<String> roleNames = new ArrayList<>();
        if(userEnt.isPresent()){
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            for(Role role: userEnt.get().getRole()){
                roleNames.add(role.getName());
            }
            roleNames.stream().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority)));

            return new User(userEnt.get().getUsername(), userEnt.get().getPassword(), authorities);
        }else {
            throw new UsernameNotFoundException("User " + s + " does not exist...");
        }
    }
}
