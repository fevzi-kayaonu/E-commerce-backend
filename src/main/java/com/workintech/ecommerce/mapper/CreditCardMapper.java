package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.CreditCardRequestDto;
import com.workintech.ecommerce.entity.CreditCard;

public class CreditCardMapper {

    public static CreditCard creditCardRequestDtoCreditCard(CreditCardRequestDto creditCardRequestDto){
        CreditCard creditCard =new CreditCard();
        creditCard.setNo(creditCardRequestDto.no());
        creditCard.setCcv(creditCardRequestDto.ccv());
        creditCard.setName(creditCardRequestDto.name());
        creditCard.setExpireMonth(creditCardRequestDto.expireMonth());
        creditCard.setExpireYear(creditCardRequestDto.expireYear());
        return creditCard;
    }

}
