package com.sinanmutlu.vendingmachine.auth;

import com.sinanmutlu.vendingmachine.entity.Role;
import com.sinanmutlu.vendingmachine.entity.UserEnt;
import com.sinanmutlu.vendingmachine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEnt> userEnt = userRepository.findByUsername(s);
        logger.info("input: " + s);
        //return new User("foo", "foo1", new ArrayList<>());

        List<String> roleNames = new ArrayList<>();
        if(userEnt.isPresent()){
            logger.info("*********");
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();

            for(Role role: userEnt.get().getRole()){
                roleNames.add(role.getName());
            }
            roleNames.stream().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority)));

            return new User(userEnt.get().getUsername(), userEnt.get().getPassword(), authorities);
        }else {
            logger.info("-----------");
            throw new UsernameNotFoundException("User " + s + " does not exist...");
        }
    }
}
