package com.ohgiraffers.orderservice.aggregate;

import lombok.Data;

import java.util.List;

/* 설명. Order입장에서 OrderMenu를 볼 때 Collection 관계인 것을 활용해서 ResultMap 결과로 나올 타입으로 작성 */
@Data
public class OrderMenu {
    private int orderCode;
    private int menuCode;
    private int orderAmount;
}
