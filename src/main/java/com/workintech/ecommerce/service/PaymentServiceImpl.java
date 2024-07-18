package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Payment;
import com.workintech.ecommerce.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
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
}
