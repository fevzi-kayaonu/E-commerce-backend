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
        @Min(value = 24, message = "Expiration year must be 2023 or later")//burayı otomatiğe bağlamamız gerek
        @Max(value = 99, message = "Expiration year must be 2099 or before")//burayı otomatiğe bağlamamız gerek
        Integer expireYear,


        @Min(value = 100, message = "CCV must be a 3 or 4-digit number")
        @Max(value = 9999, message = "CCV must be a 3 or 4-digit number")
        @NotNull(message = "CCV cannot be null")
        Integer ccv
) {
}
