package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_OrderStatus;
import com.workintech.ecommerce.entity.*;

import java.time.Instant;
import java.util.List;

public record OrderResponseDto(
        Long id,

        Instant date,

        Enum_OrderStatus status,

        AddressResponseDto addressResponseDto,

        UserResponseDto userResponseDto,//Securitiden sonra güncellenecek

        Double amount,

        List<ProductResponseDto> productResponseDtos,

        PaymentResponseDto paymentResponseDto
) {
}
