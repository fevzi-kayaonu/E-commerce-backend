package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_PaymentMethod;
import com.workintech.ecommerce.entity.Enum_PaymentStatus;

import java.time.Instant;

public record PaymentResponseDto(
        Long id,

        Enum_PaymentMethod method,

        Enum_PaymentStatus status,

        Instant date,

        Double amount,

        CreditCardResponseDto creditCardResponseDto
) {
}
