package com.ohgiraffers.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseFindUserDTO {
    private String email;
    private String name;
    private String userId;

    /* 설명. FeignClient 이후(주문내역도 함께) */
    private List<ResponseOrderDTO> orders;
}
