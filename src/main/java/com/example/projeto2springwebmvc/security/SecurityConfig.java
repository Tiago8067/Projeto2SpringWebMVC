package com.example.projeto2springwebmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig { //extends WebSecurityConfigurerAdapter
    //@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        String encodePWD = passwordEncoder().encode("1234");
        auth.inMemoryAuthentication().withUser("user1").password(encodePWD).roles("USER");
        auth.inMemoryAuthentication().withUser("user2").password(encodePWD).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(encodePWD).roles("USER", "ADMIN");
    }

    /*protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin();
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
    }*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
