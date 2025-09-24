package com.ohgiraffers.userservice.dto;

import lombok.Data;

@Data
public class RequestLoginDTO {
    private String email;
    private String pwd;
}
