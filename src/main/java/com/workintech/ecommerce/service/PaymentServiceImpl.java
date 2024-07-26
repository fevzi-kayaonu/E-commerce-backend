package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.PaymentRequestDto;
import com.workintech.ecommerce.entity.Payment;
import com.workintech.ecommerce.mapper.PaymentMapper;
import com.workintech.ecommerce.repository.CreditCardRepository;
import com.workintech.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final CreditCardRepository creditCardRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, CreditCardRepository creditCardRepository) {
        this.paymentRepository = paymentRepository;
        this.creditCardRepository = creditCardRepository;
    }


    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(null) ;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    //(dml)
    // buna koymaya gerek var mıl
    @Override
    public Payment delete(Long id) {
        Payment payment = findById(id);
        paymentRepository.delete(payment);
        return payment;
    }

    @Override
    public Payment addPayment(PaymentRequestDto paymentRequestDto){
        Payment payment = PaymentMapper.paymentReqestDtoToPayment(paymentRequestDto);
        payment.setCreditCard(creditCardRepository.findById(paymentRequestDto.creditCardId()).get());
        return payment;
    }


}
