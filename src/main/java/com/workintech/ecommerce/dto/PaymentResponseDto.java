package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.CreditCard;
import com.workintech.ecommerce.entity.Enum_PaymentStatus;
import com.workintech.ecommerce.entity.Order;
import jakarta.persistence.*;

import java.time.Instant;

public record PaymentResponseDto(
        Long id,

        String method,

        Enum_PaymentStatus status,

        Instant date,

        Double amount,

        CreditCard creditCard
) {
}
