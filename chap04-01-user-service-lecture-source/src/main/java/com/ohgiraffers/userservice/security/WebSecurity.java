package com.ohgiraffers.userservice.security;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/* 설명. Spring Security 모듈 추가 후 default 로그인 페이지 제거 및 인가 설정 */
/* 필기. 조립 + 인가를 하는 부분 */
@Configuration
public class WebSecurity {

    private JwtAuthenticationProvider jwtAuthenticationProvider;
    private Environment env;        // JWT Token의 payload에 만료시간 만들기위해 추가

    @Autowired
    public WebSecurity(JwtAuthenticationProvider jwtAuthenticationProvider, Environment env) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.env = env;
    }

    /* 설명. 새로 생성한 프로바이더 bean으로 등록 */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(authz ->
//                                authz.requestMatchers("/**").permitAll()
                                authz.requestMatchers(HttpMethod.GET, "/health").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/test").permitAll()
                                        .requestMatchers("/actuator/**").permitAll()
//                                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("ENTERPRISE")
                                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ENTERPRISE","ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/test1/**", "/test2/**").hasAnyRole("ENTERPRISE","ADMIN")
                                        .anyRequest().authenticated()
                )
                /* 설명. Session 방식이 아닌 JWT Token 방식을 사용하겠다. */
                /* 설명. Session 방식이 아닌 JWT Token 방식으로 인증된 회원(Authentication)을 Local Thread로 지정하겠다. */
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        /* 설명. 매니저를 지닌 필터 등록 */
        http.addFilter(getAuthenticationFilter(authenticationManager()));

        /* 설명. 로그인 이후 토큰을 들고 온다면 JwtFilter를 추가해서 검증하도록 함 */
        /* 설명. UsernamePasswordAuthenticationFilter 보다 JwtFilter가 먼저 실행 하게 됌 */
        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /* 설명. Filter를 등록하기 위해 사용하는 메소드 */
    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, env);
    }


}
