package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CreditCardRequestDto;
import com.workintech.ecommerce.entity.CreditCard;

public interface CreditCardService extends Service<CreditCard>{
    CreditCard addCreditCard(CreditCardRequestDto creditCardRequestDto, String user_mail);
}
