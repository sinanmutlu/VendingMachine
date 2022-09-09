package com.sinanmutlu.vendingmachine.config;

import com.sinanmutlu.vendingmachine.auth.JwtFilter;
import com.sinanmutlu.vendingmachine.auth.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    //    http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic();
    //    http.authorizeRequests().anyRequest().permitAll();
       http.authorizeRequests()
    //           .antMatchers(HttpMethod.POST,"/user/add").permitAll()
               .antMatchers(HttpMethod.POST, "/login/authenticate").permitAll()
               .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
               .antMatchers(HttpMethod.POST, "/swagger-ui.html").permitAll()
               //.antMatchers(HttpMethod.POST, "/products/**").permitAll()
               .antMatchers(HttpMethod.GET, "/products/**").permitAll()
               .antMatchers(HttpMethod.GET, "/user/**").permitAll()
               .antMatchers(HttpMethod.POST, "/user/**").permitAll()
               .antMatchers(HttpMethod.GET, "/transaction/**").permitAll()
               .antMatchers(HttpMethod.POST, "/transaction/**").permitAll()
               .anyRequest().fullyAuthenticated()
               .and()
               .httpBasic();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/login/authenticate")
                .antMatchers(HttpMethod.POST, "/products/**");
                //.antMatchers(HttpMethod.GET, "/swagger-ui.html");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}