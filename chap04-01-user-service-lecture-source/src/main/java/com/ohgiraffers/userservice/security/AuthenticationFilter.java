package com.ohgiraffers.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.userservice.dto.RequestLoginDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        /* 설명. 새로 만든 프로바이더를 알고 있는 매니저를 인지시킴 */
        super(authenticationManager);
    }

    /* 필기. 인증 시작 */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLoginDTO creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginDTO.class);

            /* 필기. 토큰 생성해서 manager에게 전달 */
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPwd(),new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /* 필기. 인증 완료 */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 이후 spring security Authentication 객체로 관리되며 넘어온: {}",authResult);
    }


}
