package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.entity.*;
import com.workintech.ecommerce.exceptions.ErrorException;
import com.workintech.ecommerce.mapper.OrderMapper;
import com.workintech.ecommerce.mapper.PaymentMapper;
import com.workintech.ecommerce.repository.AddressRepository;
import com.workintech.ecommerce.repository.OrderRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.repository.UserRepository;
import com.workintech.ecommerce.entity.*;
import com.workintech.ecommerce.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;
    private final AddressService addressService;
    private final CreditCardService creditCardService;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ProductService productService, AddressService addressService, CreditCardService creditCardService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
        this.addressService = addressService;
        this.creditCardService = creditCardService;
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ErrorException("Order not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
        return order;
    }

    @Transactional
    public Order addOrder(OrderRequestDto orderRequestDto, String user_mail) {

        CreditCard creditCard = creditCardService.findById((orderRequestDto.paymentRequestDto().creditCardId()));
        Address address = addressService.findById(orderRequestDto.addressId());
        User user = userService.findByEmail(user_mail);

        List<Product> productList = orderRequestDto.productIdList().stream()
                .map(productService::findById
                )
                .toList();

        Order order = OrderMapper.orderRequestDtoToOrder(orderRequestDto);
        order.setAddress(address);
        order.setUser(user);
        order.setProducts(productList);
        order.setAmount(calculateTotalAmount(productList));
        order.setStatus(orderRequestDto.status());
        Payment payment = PaymentMapper.paymentReqestDtoToPayment(orderRequestDto.paymentRequestDto());
        payment.setOrder(order);
        payment.setCreditCard(creditCard);
        order.setPayment(payment);
        user.addOrder(order);
        return order;
    }

    private Double calculateTotalAmount(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

}

