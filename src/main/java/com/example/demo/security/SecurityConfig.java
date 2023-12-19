package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request ->
                 request.requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN").
                         requestMatchers(HttpMethod.GET,"/api/users/**").hasAnyRole("USER", "ADMIN").
                         requestMatchers(HttpMethod.GET,"/**").permitAll()
        ).formLogin(Customizer.withDefaults());
//        http.httpBasic(Customizer.withDefaults());
       // http.csrf(csrf ->csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails userAdmin = User.withUsername("admin")
                .password(passwordEncoder().encode("manager"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,userAdmin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
