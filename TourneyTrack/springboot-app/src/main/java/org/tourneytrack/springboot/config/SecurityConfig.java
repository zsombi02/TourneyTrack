package org.tourneytrack.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // ideiglenesen letiltva pl. Postmanhez
            .authorizeHttpRequests()
                .anyRequest().permitAll() // MINDEN kérés engedélyezett
            .and()
            .httpBasic().disable()
            .formLogin().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}