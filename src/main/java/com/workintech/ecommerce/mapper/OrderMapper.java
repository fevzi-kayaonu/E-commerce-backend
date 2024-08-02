package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.dto.OrderResponseDto;
import com.workintech.ecommerce.entity.Order;

public class OrderMapper {

    public static Order orderRequestDtoToOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setPayment(PaymentMapper.paymentReqestDtoToPayment(orderRequestDto.paymentRequestDto()));
        return order;
    }

    public static OrderResponseDto orderToOrderResponseDto(Order order) {
        return new OrderResponseDto(order.getId(), order.getDate(), order.getStatus(),
                AddressMapper.addressToAddressResponseDto(order.getAddress()), UserMapper.userToUserResponseDto(order.getUser()),
                order.getAmount(), order.getProducts().stream().map(ProductMapper::productToProductResponseDto).toList(),
                PaymentMapper.paymentToPaymentResponseDto(order.getPayment()));
    }

}
