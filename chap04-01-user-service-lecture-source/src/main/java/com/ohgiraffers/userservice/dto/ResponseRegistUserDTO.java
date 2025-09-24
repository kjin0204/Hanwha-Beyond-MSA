package com.ohgiraffers.userservice.dto;

import lombok.Data;

@Data
public class ResponseRegistUserDTO {
    private String email;
    private String name;
    private String userId;      // 회원가입 이후 생서된 회원 고유 번호
}
