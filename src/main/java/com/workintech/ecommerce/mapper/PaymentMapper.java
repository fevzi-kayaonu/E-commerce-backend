package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.PaymentRequestDto;
import com.workintech.ecommerce.dto.PaymentResponseDto;
import com.workintech.ecommerce.entity.Payment;

public class PaymentMapper {

    public static Payment paymentReqestDtoToPayment(PaymentRequestDto paymentRequestDto){
        Payment payment = new Payment();
        payment.setCreditCard(CreditCardMapper.creditCardRequestDtoCreditCard(paymentRequestDto.creditCardRequestDto()));
        return payment;
    }

    public static PaymentResponseDto paymentToPaymentResponseDto(Payment payment){
        return new PaymentResponseDto(payment.getId(),payment.getMethod(),payment.getStatus(),payment.getDate(),payment.getAmount(),
                    CreditCardMapper.CreditCardTocreditCardResponseDto(payment.getCreditCard()));
    }

}
