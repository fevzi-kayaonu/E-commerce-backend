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
public class  OrderServiceImpl implements OrderService {

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
        // credit cart bul
        CreditCard creditCard = creditCardService.findById((orderRequestDto.paymentRequestDto().creditCardId()));
        // Adresi bul
        Address address = addressService.findById(orderRequestDto.addressId());
        // Kullanıcıyı bul
        User user = userService.findByEmail(user_mail);
        // Ürünleri bul
        List<Product> productList = orderRequestDto.productIdList().stream()
                .map(productService::findById
                        )
                .toList();

        // Yeni siparişi oluştur
        Order order = OrderMapper.orderRequestDtoToOrder(orderRequestDto);
        order.setAddress(address);
        order.setUser(user);
        order.setProducts(productList);
        order.setAmount(calculateTotalAmount(productList)); // Toplam tutarı hesapla
        order.setStatus(orderRequestDto.status()); // Varsayılan bir durum belirleyin

        // Siparişi ve ödemeyi oluştur
        Payment payment = PaymentMapper.paymentReqestDtoToPayment(orderRequestDto.paymentRequestDto());
        payment.setOrder(order);
        payment.setCreditCard(creditCard);// Ödemeyi siparişe bağla
        order.setPayment(payment); // Siparişi ödemeye bağla

        // Siparişi ve ödemeyi kaydet
        user.addOrder(order);
        return order; // Payment otomatik olarak kaydedilir
    }

    private Double calculateTotalAmount(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice) // Ürün fiyatını toplar
                .sum();
    }

}

