package com.workintech.ecommerce.service;


import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.entity.Order;

public interface OrderService extends Service<Order> {

    Order addOrder(OrderRequestDto orderRequestDto, String user_mail);
}
