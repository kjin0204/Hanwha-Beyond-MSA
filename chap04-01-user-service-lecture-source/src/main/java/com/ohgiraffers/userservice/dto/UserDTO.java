package com.ohgiraffers.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String email;
    private String name;
    private String pwd;

    /* 설명. ServiceImpl 계층에서 고유 회원 아이디 생성하며 추가(응답할 때도 활용) */
    private String userId;

    /* 설명. FeignClient 이후(회원이 주문한 내역도 담기) */
    private List<ResponseOrderDTO> orders;
}
