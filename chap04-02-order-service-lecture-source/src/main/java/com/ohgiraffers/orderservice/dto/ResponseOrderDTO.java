package com.ohgiraffers.orderservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseOrderDTO {
    private String orderDate;
    private String orderTime;


    private List<OrderMenuDTO> orderMenus;
}
