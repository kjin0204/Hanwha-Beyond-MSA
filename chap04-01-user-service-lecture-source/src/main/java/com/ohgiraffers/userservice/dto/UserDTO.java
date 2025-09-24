package com.ohgiraffers.userservice.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String name;
    private String pwd;

    /* 설명. ServiceImpl 계층에서 고유 회원 아이디 생성하며 추가(응답할 때도 활용) */
    private String userId;
}
