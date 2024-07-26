package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.PaymentRequestDto;
import com.workintech.ecommerce.entity.Payment;

public interface PaymentService extends Service<Payment>{

    Payment addPayment(PaymentRequestDto paymentRequestDto);
}
