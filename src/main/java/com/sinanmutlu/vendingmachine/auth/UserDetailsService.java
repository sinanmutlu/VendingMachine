package com.sinanmutlu.vendingmachine.auth;

import com.sinanmutlu.vendingmachine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    //private final UserRepository userRepository;
    private Map<String, String> users = new HashMap<>();

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        users.put("temelt", passwordEncoder.encode("123"));
   /*     List<com.sinanmutlu.vendingmachine.entity.User> userList = userRepository.findAll();

        if(userList.size() > 0) {
            for (com.sinanmutlu.vendingmachine.entity.User user : userList) {
                users.put(user.getUsername(),user.getPassword());
            }
        }*/
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        if (users.containsKey(username)) {
            return new User(username, users.get(username), new ArrayList<>());
        }

        throw new UsernameNotFoundException(username);
    }
}
