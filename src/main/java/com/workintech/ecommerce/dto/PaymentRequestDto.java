package com.workintech.ecommerce.dto;

import jakarta.validation.Valid;

public record PaymentRequestDto (@Valid
                                CreditCardRequestDto creditCardRequestDto) {
}
