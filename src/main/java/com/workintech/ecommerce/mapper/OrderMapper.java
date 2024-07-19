package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.dto.OrderResponseDto;
import com.workintech.ecommerce.dto.UserResponseDto;
import com.workintech.ecommerce.entity.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static Order orderRequestDtoToOrder(OrderRequestDto orderRequestDto){
        Order order = new Order();
        order.setAmount(orderRequestDto.amount());
        order.setProducts(orderRequestDto.productRequestDtos().stream().map(ProductMapper::productRequestDtoToProduct).toList());
        order.setAddress(AddressMapper.addressRequestDtoToAddress(orderRequestDto.addressRequestDto()));
        order.setPayment(PaymentMapper.paymentReqestDtoToPayment(orderRequestDto.paymentRequestDto()));
        return order;
    }

    public static OrderResponseDto orderToOrderResponseDto(Order order){
        return new OrderResponseDto(order.getId(), order.getDate(),order.getStatus(),
                                    AddressMapper.addressToAddressResponseDto(order.getAddress()),new UserResponseDto()
    }

}
