package com.ohgiraffers.orderservice.controller;

import com.ohgiraffers.orderservice.dto.OrderDTO;
import com.ohgiraffers.orderservice.dto.ResponseOrderDTO;
import com.ohgiraffers.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<ResponseOrderDTO>> getUserOrders(@PathVariable int userId){
        List<OrderDTO> orderDTOList = orderService.getOrderByUserId(userId);
        List<ResponseOrderDTO> responseOrderDTOList = orderDTOToResponseOrder(orderDTOList);
        return ResponseEntity.ok().body(responseOrderDTOList);
    }

    private List<ResponseOrderDTO> orderDTOToResponseOrder(List<OrderDTO> orderDTOList) {

        List<ResponseOrderDTO> responseList = new ArrayList<>();

        for (OrderDTO orderDTO : orderDTOList) {
            ResponseOrderDTO responseOrder = new ResponseOrderDTO();
            responseOrder.setOrderDate(orderDTO.getOrderDate());
            responseOrder.setOrderTime(orderDTO.getOrderTime());
            responseOrder.setOrderMenus(orderDTO.getOrderMenus());

            responseList.add(responseOrder);
        }

        return responseList;
    }
}
