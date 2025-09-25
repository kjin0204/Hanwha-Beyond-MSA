package com.ohgiraffers.userservice.infrastructure;

import com.ohgiraffers.userservice.dto.ResponseOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
/* 설명.
 *  1. FeignClient관련 라이브러리 추가
 *  2. main 가서 @EnableFeignClients 어노테이션 추가
 *  3. FeignClientConfig 클래스 설정으로 토큰 서빙 설정
* */

/* 설명. FeignClinet를 활용한 통신을 위한 interface */
@FeignClient(name="swcamp-order-service",url="localhost:8000", configuration = FeignClientConfig.class)
public interface OrderServiceClient {
    @GetMapping("/order-service/users/{userId}/orders")
    List<ResponseOrderDTO> getUserOrders(@PathVariable String userId);
}
