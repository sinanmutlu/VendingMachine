package com.sinanmutlu.vendingmachine.controller;

import com.sinanmutlu.vendingmachine.auth.JwtService;
import com.sinanmutlu.vendingmachine.auth.UserDetailService;
import com.sinanmutlu.vendingmachine.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String getJwtAuthToken(@RequestBody LoginRequest authRequestModel) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestModel.getUsername(), authRequestModel.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or password...", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestModel.getUsername());
        final String jwt = jwtService.getJWT(userDetails);
        return jwt;
    }
}
