package com.ohgiraffers.orderservice.service;

import com.ohgiraffers.orderservice.aggregate.Order;
import com.ohgiraffers.orderservice.aggregate.OrderMenu;
import com.ohgiraffers.orderservice.dto.OrderDTO;
import com.ohgiraffers.orderservice.dto.OrderMenuDTO;
import com.ohgiraffers.orderservice.repository.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getOrderByUserId(int userId) {
//        log.info("order 서비스에서 출력: {}", orderMapper.selectOrderByUserId(userId));
        List<Order> orderList = orderMapper.selectOrderByUserId(userId);
        List<OrderDTO> orderDTOList = orderToOrderDTO(orderList);
        return orderDTOList;
    }

    private List<OrderDTO> orderToOrderDTO(List<Order> orderList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderDate(order.getOrderDate());
            orderDTO.setOrderTime(order.getOrderTime());

            List<OrderMenu> orderMenuList = order.getOrderMenus();
            List<OrderMenuDTO> orderMenuDTOList = new ArrayList<>();
            for (OrderMenu orderMenu : orderMenuList) {
                OrderMenuDTO orderMenuDTO = new OrderMenuDTO();
                orderMenuDTO.setOrderCode(orderMenu.getOrderCode());
                orderMenuDTO.setMenuCode(orderMenu.getMenuCode());
                orderMenuDTO.setOrderAmount(orderMenu.getOrderAmount());

                orderMenuDTOList.add(orderMenuDTO);
            }

            orderDTO.setOrderMenus(orderMenuDTOList);
            orderDTO.setTotalOrderPrice(order.getTotalOrderPrice());
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }
}
