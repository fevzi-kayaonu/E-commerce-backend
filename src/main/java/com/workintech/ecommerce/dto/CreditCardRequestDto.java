package com.workintech.ecommerce.dto;

import jakarta.validation.constraints.*;

public record CreditCardRequestDto(
        @NotBlank(message = "Credit card number cannot be blank")
        String no,

        @NotBlank(message = "Cardholder name cannot be blank")
        String name,

        @NotNull(message = "Expiration month cannot be null")
        @Min(value = 1, message = "Expiration month must be between 1 and 12")//databasede güncellenmesi gerekebilir
        @Max(value = 12, message = "Expiration month must be between 1 and 12")//databasede güncellenmesi gerekebilir
        Integer expireMonth,

        @NotNull(message = "Expiration year cannot be null")
        @Min(value = 2024, message = "Expiration year must be 2023 or later")//burayı otomatiğe bağlamamız gerek
        Integer expireYear,


        @Digits(integer = 4, fraction = 0, message = "CCV must be a 3 or 4-digit number")// digits ve primivtive tip size kullanımları için araştırma yap
        @Size(min = 3, max = 4, message = "CCV must be 3 or 4 digits")//databasede güncellenmesi gerekebilir
        @NotNull(message = "CCV cannot be null")
        Integer ccv
        ) {
}
