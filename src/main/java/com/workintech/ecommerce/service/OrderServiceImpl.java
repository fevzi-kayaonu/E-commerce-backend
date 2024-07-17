package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        /*
        Optional<Address> address = addressRepository.findById(id);
        if(address.isPresent()){
            return address.get();
        }
        */
        return orderRepository.findById(id).orElseThrow(null) ;
    }

    @Transactional
    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    // buna koymaya gerek var mı sonuçta dml
    @Transactional
    @Override
    public Order delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
        return order;
    }
}
