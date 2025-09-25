package com.ohgiraffers.userservice.infrastructure;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/* 설명. micro service 간의 통신은 gateway를 활용한 redirect이므로 이 설정을 통해 유지해야 할 것이 있다면 전달한다. */
@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        /* 설명. RequestInterceptor의 하위 구현체 생성(Anonymous class 방식) 익명 클래스 */
        return new RequestInterceptor() {

            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes requestAttributes =
                        (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

                if (requestAttributes != null) {
                    String authorizationHeader = requestAttributes.getRequest()
                            .getHeader("Authorization");
                    if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                        requestTemplate.header(HttpHeaders.AUTHORIZATION, authorizationHeader);
                    }
                }

            }
        };
    }
}
