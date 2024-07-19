package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.PaymentRequestDto;
import com.workintech.ecommerce.entity.Payment;

public class PaymentMapper {

    public static Payment paymentReqestDtoToPayment(PaymentRequestDto paymentRequestDto){
        Payment payment = new Payment();
        payment.setCreditCard(CreditCardMapper.creditCardRequestDtoCreditCard(paymentRequestDto.creditCardRequestDto()));
        return payment;
    }

}
