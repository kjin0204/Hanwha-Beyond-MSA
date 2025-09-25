package com.ohgiraffers.orderservice.dto;

import lombok.Data;

import java.util.List;

/* 설명.
 *  Query쪽(우리는 Mybatis로 진행)은 계층 이동 및 DB와의 CRUD 관련해서까지 다 활용할 수 있지만
 *  DTO가 읨미없이 모든 경우의 수를 감당해 내다보면 사이즈가 너무 커지게 될 수 있으므로 가급적이면
 *  요청별로 구분해서 설계하고 Entity 개념의 클래스는 ResultMap의 결과를 받아 줄 수 있게 설계하는 것을
 *  추천함
 *  (DTO: 요청별 요청 및 응답 값 담는 용도(+쿼리의 재료)
 *  Entity 개념의 DTO(클래스): 모든 ResultMap의 결과를 받아줄 수 있는 용도
* */

@Data
public class OrderDTO {
    private int orderCode;
    private int userId;
    private String orderDate;
    private String orderTime;
    private int totalOrderPrice;

    private List<OrderMenuDTO> orderMenus;
}
