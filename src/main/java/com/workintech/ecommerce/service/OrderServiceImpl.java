package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.OrderRequestDto;
import com.workintech.ecommerce.entity.*;
import com.workintech.ecommerce.mapper.OrderMapper;
import com.workintech.ecommerce.repository.AddressRepository;
import com.workintech.ecommerce.repository.OrderRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class  OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;
    private final PaymentService paymentService;



    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, AddressRepository addressRepository, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;

        this.paymentService = paymentService;
    }


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(null) ;
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
    @Override
    public Order addOrder(OrderRequestDto orderRequestDto, String user_mail) {
        Optional<User> user = userRepository.findByEmail(user_mail);
        List<Product> productList = orderRequestDto.productIdList().stream()
                .map(item -> productRepository.findById(item)
                        .orElseThrow(() -> new RuntimeException("Product not found")))
                .toList();

        Double totalAmount = productList.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        Address address = addressRepository.findById(orderRequestDto.addressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (user.isPresent()) {
            Order order = OrderMapper.orderRequestDtoToOrder(orderRequestDto);
            order.setProducts(productList);
            order.setUser(user.get());
            order.setAddress(address);
            order.setAmount(totalAmount);
            order.setStatus(orderRequestDto.status());

            Order savedOrder = save(order);
            System.out.println("girdim order :" + savedOrder);

            // Ödemeyi ekle
            Payment payment = paymentService.addPayment(orderRequestDto.paymentRequestDto());
            payment.setOrder(savedOrder); // Ödeme nesnesinin order referansını ayarla

            paymentService.save(payment); // Ödemeyi kaydet
            System.out.println("girdim payment :" + payment);
            savedOrder.setPayment(payment); // Sipariş nesnesinin payment referansını ayarla
            return save(savedOrder); // Güncellenmiş siparişi kaydet
        }
        throw new RuntimeException("Order not found");
    }
}
