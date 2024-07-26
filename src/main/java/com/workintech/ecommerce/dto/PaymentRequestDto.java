package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.Enum_PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentRequestDto (
        @NotNull(message = "Payment method name cannot be null")
        Enum_PaymentMethod method,

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")//Validasyon yazılacak
        Double amount,

        Long creditCardId) {
}
