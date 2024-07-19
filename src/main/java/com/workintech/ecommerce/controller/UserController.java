package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.dto.OrderResponseDto;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.mapper.OrderMapper;
import com.workintech.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {

    private final OrderService orderService;

    public UserController(OrderService orderService) {
        this.orderService = orderService;
    }

    //sipariş verme

    @PostMapping("/")
    OrderResponseDto save(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderService.save(orderService.createOrder(orderRequestDto));
        return OrderMapper.orderToOrderResponseDto(order);
    }
/*
    @PostMapping("/save")
    Order save(@RequestBody Order order){
        return orderService.save(order);
    }
*/

    // kendine adrress ekleyebilir


    // kendine credi kartı ekleyebilir


    // isminide güncellesin


}
