package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.PaymentRequestDto;
import com.workintech.ecommerce.dto.PaymentResponseDto;
import com.workintech.ecommerce.entity.Enum_PaymentStatus;
import com.workintech.ecommerce.entity.Payment;

public class PaymentMapper {


    public static Payment paymentReqestDtoToPayment(PaymentRequestDto paymentRequestDto){
        Payment payment = new Payment();
        payment.setAmount(paymentRequestDto.amount());
        payment.setMethod(paymentRequestDto.method());
        payment.setStatus(Enum_PaymentStatus.ODENDI);
      //  payment.setCreditCard(CreditCardMapper.creditCardRequestDtoCreditCard(paymentRequestDto.creditCardRequestDto()));
        return payment;
    }

/*    public static Payment paymentReqestDtoToPayment(PaymentRequestDto paymentRequestDto) {
        if (paymentRequestDto == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setMethod(paymentRequestDto.method());
        payment.setStatus(Enum_PaymentStatus.ODENDI); // Burada uygun bir durum ayarlayın
        payment.setAmount(paymentRequestDto.amount());

        return payment;
    }*/

    public static PaymentResponseDto paymentToPaymentResponseDto(Payment payment){
        return new PaymentResponseDto(payment.getId(),payment.getMethod(),payment.getStatus(),payment.getDate(),payment.getAmount(),
                    CreditCardMapper.creditCardToCreditCardResponseDto(payment.getCreditCard()));
    }

}
