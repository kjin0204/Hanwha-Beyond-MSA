package com.ohgiraffers.userservice.security;


import com.ohgiraffers.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/* 설명. Jwt 토큰 방식의 로그인을 구현할 때 UsernamePasswordAuthenticationToken을 처리 할 프로바이더 */
/* 설명. Service 계층의 UserDetailService를 활용해 DB에서 사용자 조회 후 인증 */
@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;  //평문과 암호화 된 다이제스트를 비교하기 위한 도구

    public JwtAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    //일하겠습니다
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        /* 설명. 사용자가 로그인 시 입력한 값 */
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        /* 설명. DB에 있는 해당 회원의 정보 */
        UserDetails userDetails = userService.loadUserByUsername(email);

        if(!passwordEncoder.matches(pwd,userDetails.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        /* 설명. 예외가 발생하지 않고 이 부분 이후가 실행되면 UserDetails에 담긴(인증된 회원정보) 정보로 Token 생성 */

        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }

    //어떤 토큰을 처리하겠습니다(필터에서 만든 토큰이 여기로 넘어옴)
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
