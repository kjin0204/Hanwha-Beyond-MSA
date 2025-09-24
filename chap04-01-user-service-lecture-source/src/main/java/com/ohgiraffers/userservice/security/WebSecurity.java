package com.ohgiraffers.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/* 설명. Spring Security 모듈 추가 후 default 로그인 페이지 제거 및 인가 설정 */
@Configuration
public class WebSecurity {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(authz ->
                        authz.requestMatchers("/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
        );

        /* 설명. Session 방식이 아닌 JWT Token 방식을 사용하겠다. */

        return http.build();
    }
}
